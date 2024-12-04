package com.wxm.api.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wxm.api.service.IGatewayService;
import com.wxm.base.bean.JobBean;
import com.wxm.base.exception.BaseException;
import com.wxm.base.exception.JobException;
import com.wxm.base.exception.UtilException;
import com.wxm.datax.config.DataXPropertiesConfig;
import com.wxm.druid.entity.quartz.DataXDetails;


import com.wxm.quartz.job.DataXCronJob;
import com.wxm.quartz.service.IQuartzService;
import com.wxm.service.db.quartz.IDataXDetailsService;
import com.wxm.service.db.quartz.ITriggerService;
import com.wxm.util.my.FileUtil;
import com.wxm.util.my.MyUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/5 17:28
 * @since 1.0.0
 */
@Service("gatewayService")
public class GatewayService implements IGatewayService {
    private static final String SYS_TYPE_WINDOWS="windows";
    @Autowired
    private   DataXPropertiesConfig dataXPropertiesConfig;
    @Autowired
    private IQuartzService quartzService;
    @Autowired
    private IDataXDetailsService dataXDetailsService;

    @Autowired
    private ITriggerService triggerService;


    //  @Transactional(rollbackFor = Exception.class)   导致多数据源切换失败
    @DSTransactional
    @Override
    public String addJob(String text) throws IOException, UtilException, JobException {
        JSONObject jsonObject=JSON.parseObject(text);
        JSONObject ct01=jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0);
        JSONObject reader=ct01.getJSONObject("reader");
        JSONObject writer=ct01.getJSONObject("writer");
        String dbReader=reader.getString("name").replace("reader","");
        String dbWriter=writer.getString("name").replace("writer","");
        JSONArray tables=reader.getJSONObject("parameter").getJSONArray("connection").getJSONObject(0).getJSONArray("table");
        // String tableName=tables.stream().map(Object::toString).collect(Collectors.joining("~")).toString();
        String tableName=tables.toJavaList(String.class).stream().collect(Collectors.joining("~")).toString();



        String cron=jsonObject.getString("cron");
        // 移除quartz定时任务的参数，剩下的就是datax的数据同步执行数据
        jsonObject.remove("cron");
        String jobFileName=dbReader+"2"+dbWriter+"-"+ MyUUIDUtil.uuid(tableName);
        String separator =SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType())?"\\":"/";
        String fileName=jobFileName+".json";
        String filePath=dataXPropertiesConfig.getDataxJobFileDirectory();
        String jobFilePath=filePath + separator + fileName;
        FileUtil.createJobFile(jsonObject.toJSONString(),jobFilePath);

        JobBean jobBean=new JobBean().setJobName("J"+jobFileName).setJobGroup("G"+jobFileName).setJobDescription("")
                .setTriggerName("T"+jobFileName).setTriggerGroup("TG"+jobFileName).setTriggerDescription("")
                // "0/5 * * * * ?"
                .setCron(cron);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
        Date date=new Date();
        DataXDetails dataXDetails=new DataXDetails();
        dataXDetails.setFileName(fileName);
        dataXDetails.setFilePath(filePath);
        dataXDetails.setFileStatus("1");
        dataXDetails.setCreateTime(date);
        dataXDetails.setCreator(userDetail.getUsername());
        dataXDetails.setUpdateTime(date);
        dataXDetails.setOperator(userDetail.getUsername());
        dataXDetails.setFileId(MyUUIDUtil.uuid());
        dataXDetailsService.save(dataXDetails);
        quartzService.addJob(DataXCronJob.class,jobBean,jobFilePath);
        return jobFilePath;
    }


    @Override
    public String addJob(JobBean jobBean,String text) throws BaseException, IOException {
        JSONObject jsonObject=JSON.parseObject(text);
        JSONObject ct01=jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0);
        JSONObject reader=ct01.getJSONObject("reader");
        JSONObject writer=ct01.getJSONObject("writer");
        String dbReader=reader.getString("name").replace("reader","");
        String dbWriter=writer.getString("name").replace("writer","");
        JSONArray tables=reader.getJSONObject("parameter").getJSONArray("connection").getJSONObject(0).getJSONArray("table");
        String tableName=tables.toJavaList(String.class).stream().collect(Collectors.joining("~")).toString();


        String jobFileName=dbReader+"2"+dbWriter+"-"+tableName+".json";
        String separator =SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType())?"\\":"/";
        String jobFilePath=dataXPropertiesConfig.getDataxJobFileDirectory() + separator + jobFileName;
        FileUtil.createJobFile(text,jobFilePath);
        quartzService.addJob(DataXCronJob.class,jobBean,jobFilePath);
        return jobFilePath;
    }
}
