package com.wxm.service.biz.sys.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wxm.base.bean.JobBean;
import com.wxm.base.bean.JsonBean;
import com.wxm.base.bean.QuartzForm;
import com.wxm.base.bean.datax.DataXDbSource;
import com.wxm.base.bean.datax.DataXDbTarget;
import com.wxm.base.bean.datax.DataXErrorLimit;
import com.wxm.base.bean.datax.DataXSpeed;
import com.wxm.base.dto.quartz.in.*;
import com.wxm.base.dto.quartz.item.Item11003Out;
import com.wxm.base.dto.quartz.out.*;
import com.wxm.base.enums.BizSvcEnum;
import com.wxm.base.exception.*;
import com.wxm.datax.config.DataXPropertiesConfig;
import com.wxm.druid.entity.quartz.CronTriggers;
import com.wxm.druid.entity.quartz.DataXDetails;
import com.wxm.druid.entity.quartz.Triggers;
import com.wxm.druid.entity.quartz.nodb.JobDetailAndTriggerBean;


import com.wxm.quartz.job.DataXCronJob;
import com.wxm.quartz.service.IQuartzService;
import com.wxm.service.biz.sys.IBizQuartzService;
import com.wxm.service.db.quartz.ICronTriggersService;
import com.wxm.service.db.quartz.IDataXDetailsService;
import com.wxm.service.db.quartz.IJobDetailsService;
import com.wxm.service.db.quartz.ITriggerService;
import com.wxm.util.my.BeanUtils;
import com.wxm.util.my.FileUtil;
import com.wxm.util.my.MyUUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/29 15:00
 * @since 1.0.0
 */
@Slf4j
@Service("bizQuartzService")
public class BizQuartzService implements IBizQuartzService {
    private static final String SYS_TYPE_WINDOWS = "windows";
    private final DataXPropertiesConfig dataXPropertiesConfig;
    private final IQuartzService quartzService;
    private final IDataXDetailsService dataXDetailsService;
    private final IJobDetailsService jobDeiService;
    private final ITriggerService triggerService;
    private final ICronTriggersService cronTriggersService;

    public BizQuartzService(@Qualifier("jobDeiService") IJobDetailsService jobDeiService, ITriggerService triggerService, DataXPropertiesConfig dataXPropertiesConfig, IQuartzService quartzService, IDataXDetailsService dataXDetailsService, ICronTriggersService cronTriggersService) {
        this.jobDeiService = jobDeiService;
        this.triggerService = triggerService;
        this.dataXPropertiesConfig = dataXPropertiesConfig;
        this.quartzService = quartzService;
        this.dataXDetailsService = dataXDetailsService;
        this.cronTriggersService = cronTriggersService;
    }


    /**
     * 此处抛出 3个异常，DbSvcException，BizSvcException，BaseException
     * 但是 ，前两个异常继承了BaseException，所以只要抛出一个
     *
     * @return
     * @throws DbSvcException
     * @throws BaseException
     * @throws BizSvcException
     */
    @Override
    public List<Quartz11001Out> service11001(Quartz11001In quartz10001In) throws BizSvcException, DbSvcException, UtilException {
        try {
            List<JobDetailAndTriggerBean> listSource = jobDeiService.queryJobDetailAndTriggerList();
            // 不采用 json 转换和hutool工具
            // 单例模式
            BeanUtils instance = BeanUtils.getInstance();
            List<Quartz11001Out> listTarget = instance.copyList(listSource, JobDetailAndTriggerBean.class, Quartz11001Out.class);
            return listTarget;
        } catch (DbSvcException e) {
            throw e;
        } catch (UtilException e) {
            e.setMessage("定时任务列表" + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务列表"), e.getMessage());
        }
    }

    @Override
    public Quartz11002Out service11002(Quartz11002In quartz11002In) throws BizSvcException, DbSvcException, UtilException {
        try {
            PageInfo pageInfo = triggerService.queryByCon(quartz11002In);
            return new Quartz11002Out().setTotal(pageInfo.getTotal()).setList(pageInfo.getList());
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务触发器列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务触发器列表"), e.getMessage());
        }
    }

    @Override
    public Quartz11003Out service11003(Quartz11003In quartz11003In) throws BizSvcException, DbSvcException, UtilException {
        try {
            PageInfo pageInfo = triggerService.queryByConFmt(quartz11003In);
            return new Quartz11003Out().setTotal(pageInfo.getTotal()).setList(JSON.parseArray(JSON.toJSONString(pageInfo.getList()), Item11003Out.class));
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务触发器列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务触发器列表"), e.getMessage());
        }
    }

    @Override
    public Quartz11004Out service11004(Quartz11004In quartz11004In) throws BizSvcException, UtilException {
        try {
            Quartz11004Out quartz11004Out = new Quartz11004Out();
            QueryWrapper qWrapper = new QueryWrapper();
            qWrapper.eq("sched_name", quartz11004In.getSchedName());
            qWrapper.eq("trigger_name", quartz11004In.getTriggerName());
            qWrapper.eq("trigger_group", quartz11004In.getTriggerGroup());
            Triggers triggers = triggerService.getOne(qWrapper);
            if (triggers == null && !StringUtils.hasLength(triggers.getJobName())) {
                throw new RuntimeException("没有对应的触发器或者当前定时任务的数据有问题");
            }
            if ("CRON".equalsIgnoreCase(triggers.getTriggerType())) {
                QueryWrapper wrapper = new QueryWrapper();
                wrapper.eq("trigger_name", triggers.getTriggerName());
                wrapper.eq("trigger_group", triggers.getTriggerGroup());
                CronTriggers cronTriggers = cronTriggersService.getOne(wrapper);
                if (cronTriggers != null || StringUtils.hasLength(cronTriggers.getCronExpression())) {
                    QuartzForm quartzForm = new QuartzForm();
                    quartzForm.setCron(cronTriggers.getCronExpression());
                    quartz11004Out.setQuartzForm(quartzForm);
                }
            }

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("file_name", triggers.getJobName().substring(1) + ".json");
            DataXDetails dataXDetails = dataXDetailsService.getOne(queryWrapper);
            if (dataXDetails == null) {
                throw new RuntimeException("当前定时任务，没有对应的数据同步文件，请删除");
            }
            String separator = SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType()) ? "\\" : "/";
            String fileFullPath = dataXDetails.getFilePath() + separator + dataXDetails.getFileName();
            String jobStr = FileUtil.readFileToString(fileFullPath, "utf-8");
            if (StringUtils.hasLength(jobStr)) {
                JsonBean jsonBean = JSON.parseObject(jobStr, JsonBean.class);
                List<JsonBean.Job.Content> list = jsonBean.getJob().getContent();
                if (CollectionUtils.isEmpty(list)) {
                    throw new RuntimeException("没有content，请删除");
                }
                if (list.size() > 1) {
                    throw new RuntimeException("多个content ,非程序添加，请核查");
                }

                DataXSpeed speed = new DataXSpeed();
                speed.setChannel(jsonBean.getJob().getSetting().getSpeed().getChannel() + "");
                speed.setSpByte(jsonBean.getJob().getSetting().getSpeed().getByteX() + "");
                quartz11004Out.setSpeed(speed);
                DataXErrorLimit errorLimit = new DataXErrorLimit();
                errorLimit.setRecord(jsonBean.getJob().getSetting().getErrorLimit().getRecord() + "");
                errorLimit.setPercentage(jsonBean.getJob().getSetting().getErrorLimit().getPercentage().toString());
                quartz11004Out.setErrorLimit(errorLimit);
                DataXDbSource dbSource = new DataXDbSource();

                JsonBean.Job.Content.Reader reader = list.get(0).getReader();
                dbSource.setName(reader.getName());
                if (reader.getParameter().getFetchSize() != null && reader.getParameter().getFetchSize() > 0) {
                    dbSource.setFetchSize(reader.getParameter().getFetchSize() + "");
                }
                dbSource.setSplitPk(reader.getParameter().getSplitPk());
                dbSource.setQuerySql(reader.getParameter().getQuerySql());
                dbSource.setWhere(reader.getParameter().getWhere());
                dbSource.setUsername(reader.getParameter().getUsername());
                dbSource.setPassword(reader.getParameter().getPassword());
                if (!CollectionUtils.isEmpty(reader.getParameter().getColumn())) {
                    dbSource.setColumn(reader.getParameter().getColumn().stream().collect(Collectors.joining(",")));
                }

                List<JsonBean.Job.Content.Reader.Parameter.Connection> connectionList = reader.getParameter().getConnection();
                if (connectionList.size() > 1) {
                    throw new RuntimeException("多个connection ,非程序添加，请核查");
                }
                if (!CollectionUtils.isEmpty(connectionList)) {
                    JsonBean.Job.Content.Reader.Parameter.Connection connection = connectionList.get(0);
                    List<String> jdbcUrlList = connection.getJdbcUrl();
                    if (jdbcUrlList.size() > 1) {
                        throw new RuntimeException("多个jdbcUrl ,非程序添加，请核查");
                    }
                    if (!CollectionUtils.isEmpty(jdbcUrlList)) {
                        dbSource.setJdbcUrl(jdbcUrlList.get(0));
                    }
                    if (!CollectionUtils.isEmpty(connection.getTable())) {
                        dbSource.setTable(connection.getTable().stream().collect(Collectors.joining(",")));
                    }
                }

                quartz11004Out.setDbSource(dbSource);

                DataXDbTarget dbTarget = new DataXDbTarget();
                JsonBean.Job.Content.Writer writer = list.get(0).getWriter();
                dbTarget.setName(writer.getName());
                dbTarget.setWriteMode(writer.getParameter().getWriteMode());
                dbTarget.setUsername(writer.getParameter().getUsername());
                dbTarget.setPassword(writer.getParameter().getPassword());
                if (!CollectionUtils.isEmpty(writer.getParameter().getColumn())) {
                    dbTarget.setColumn(writer.getParameter().getColumn().stream().collect(Collectors.joining(",")));
                }
                if (!CollectionUtils.isEmpty(writer.getParameter().getPostSql())) {
                    dbTarget.setPostSql(writer.getParameter().getPostSql().stream().collect(Collectors.joining(",")));
                }
                if (!CollectionUtils.isEmpty(writer.getParameter().getPreSql())) {
                    dbTarget.setPreSql(writer.getParameter().getPreSql().stream().collect(Collectors.joining(",")));
                }
                dbTarget.setBatchSize(writer.getParameter().getBatchSize() + "");

                List<JsonBean.Job.Content.Writer.ParameterX.ConnectionX> connectionWList = writer.getParameter().getConnection();
                if (connectionWList.size() > 1) {
                    throw new RuntimeException("多个connection ,非程序添加，请核查");
                }
                if (!CollectionUtils.isEmpty(connectionWList)) {
                    JsonBean.Job.Content.Writer.ParameterX.ConnectionX connectionW = connectionWList.get(0);
                    if (StringUtils.hasLength(connectionW.getJdbcUrl())) {
                        dbTarget.setJdbcUrl(connectionW.getJdbcUrl());
                    }
                    if (!CollectionUtils.isEmpty(connectionW.getTable())) {
                        dbTarget.setTable(connectionW.getTable().stream().collect(Collectors.joining(",")));
                    }
                }
                quartz11004Out.setDbTarget(dbTarget);
            }
            return quartz11004Out;
        } catch (UtilException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务-数据同步"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务-数据同步"), e.getMessage());
        }
    }

    @Override
    public List<JobDetailAndTriggerBean> getJobDetailAndTriggerList() throws DbSvcException, BizSvcException {
        try {
            return jobDeiService.queryJobDetailAndTriggerList();
        } catch (DbSvcException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务列表"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1001.toString(), BizSvcEnum.BIZ_SVC_1001.getMessage("定时任务列表"), e.getMessage());
        }

    }

    @DSTransactional
    @Override
    public Quartz12001Out service12001(Quartz12001In quartz12001In) throws BizSvcException, JobException {
        try {
            String dbReader = quartz12001In.getReaderName();
            String dbWriter = quartz12001In.getWriterName();
            String tables = quartz12001In.getTables();

            // String cron=quartz12001In.getQuartzForm().getCron();  不能直接获取到
            String cron = quartz12001In.getCron();
            String jobFileName = dbReader + "2" + dbWriter + "-" + MyUUIDUtil.uuid(tables);
            String separator = SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType()) ? "\\" : "/";
            String fileName = jobFileName + ".json";
            String filePath = dataXPropertiesConfig.getDataxJobFileDirectory();
            String jobFilePath = filePath + separator + fileName;
            FileUtil.createJobFile(JSON.toJSONString(quartz12001In.getJob(true)), jobFilePath);

            JobBean jobBean = new JobBean().setJobName("J" + jobFileName).setJobGroup("G" + jobFileName).setJobDescription("")
                    .setTriggerName("T" + jobFileName).setTriggerGroup("TG" + jobFileName).setTriggerDescription("")
                    // "0/5 * * * * ?"
                    .setCron(cron);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            Date date = new Date();
            DataXDetails dataXDetails = new DataXDetails();
            dataXDetails.setFileName(fileName);
            dataXDetails.setFilePath(filePath);
            dataXDetails.setFileStatus("1");
            dataXDetails.setCreateTime(date);
            dataXDetails.setCreator(userDetail.getUsername());
            dataXDetails.setUpdateTime(date);
            dataXDetails.setOperator(userDetail.getUsername());
            dataXDetails.setFileId(MyUUIDUtil.uuid());
            dataXDetailsService.save(dataXDetails);
            quartzService.addJob(DataXCronJob.class, jobBean, jobFilePath);
            return new Quartz12001Out().setJobFilePath(jobFilePath);
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("数据同步定时任务"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("数据同步定时任务"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Quartz12002Out service12002(Quartz12002In quartz12002In) throws BizSvcException, JobException {
        try {
            String cron = quartz12002In.getCron();
            String jobClass = quartz12002In.getClassName();
            String customClass = quartz12002In.getCustomClassName();
            String jobClassName = jobClass.substring(jobClass.lastIndexOf(".") + 1);
            String customClassName = customClass.substring(customClass.lastIndexOf(".") + 1);
            String jobFileName = "";
            if (StringUtils.hasLength(customClassName)) {
                jobFileName = jobClassName + "-" + customClassName + "-" + MyUUIDUtil.uuid(customClassName);
            } else {
                jobFileName = jobClassName + "-" + MyUUIDUtil.uuid(jobClassName);
            }

            JobBean jobBean = new JobBean().setJobName("J" + jobFileName).setJobGroup("G" + jobFileName).setJobDescription("")
                    .setTriggerName("T" + jobFileName).setTriggerGroup("TG" + jobFileName).setTriggerDescription("")
                    // "0/5 * * * * ?"
                    .setCron(cron);

            // 不能出入cron 因为其设置在trigger中，存入job_data中就很不对了
            Map<String, Object> params = new HashMap<>();
            params.put("jobFileName", jobFileName);
            quartzService.addJob(Class.forName(jobClass), jobBean, customClass, JSON.toJSONString(params));
            return new Quartz12002Out();
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("普通定时任务"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1002.toString(), BizSvcEnum.BIZ_SVC_1002.getMessage("普通定时任务"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Quartz13001Out service13001(Quartz13001In quartz13001In) throws BizSvcException, JobException {
        try {
            /**
             * 必须采用如下容错规则
             * 1、根据文件名称查询 data_x_details 数据，判断是否存在
             * 2、先根据sched_name,trigger_name,trigger_group 查询triggers数据
             * 3、先停止quartz定时任务
             * 为了防止不可预测问题，
             * 4、删除data_x_details
             * 5、删除data_x_details 对应的datax数据同步文件
             * 6、判断定时任务中的信息与文件名称是否一致，如果一致且定时设置一样，恢复resumeJob定时任务，
             * 如果名称一致，而cron不一致rescheduleJob（删除员来触发器，关联新的触发器），删除定时任务，然后重建定时任务
             *
             * 7、存储 data_x_details 数据，新建数据文件和插入datax数据
             */

            QueryWrapper<DataXDetails> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("file_name", quartz13001In.getTriggerIn().getJobName().substring(1) + ".json");
            List<DataXDetails> list = dataXDetailsService.list(queryWrapper);
            if (CollectionUtils.isEmpty(list)) {
                throw new RuntimeException("当前定时任务，没有对应的数据同步记录，请删除");
            }
            if (list.size() > 1) {
                throw new RuntimeException("一个文件名对应多条数据同步记录，请删除");
            }

            QueryWrapper qWrapper = new QueryWrapper();
            qWrapper.eq("sched_name", quartz13001In.getTriggerIn().getSchedName());
            qWrapper.eq("trigger_name", quartz13001In.getTriggerIn().getTriggerName());
            qWrapper.eq("trigger_group", quartz13001In.getTriggerIn().getTriggerGroup());
            Triggers triggers = triggerService.getOne(qWrapper);
            if (triggers == null && !StringUtils.hasLength(triggers.getJobName())) {
                throw new RuntimeException("没有对应的触发器或者当前定时任务的数据有问题");
            }

            quartzService.pauseJob(quartz13001In.getTriggerIn().getJobName(), quartz13001In.getTriggerIn().getJobGroup());

            DataXDetails dataXDetails = list.get(0);
            dataXDetailsService.removeById(dataXDetails);

            String filePathOld = dataXDetails.getFilePath();
            String fileNameOld = dataXDetails.getFileName();
            if (StringUtils.hasLength(filePathOld) && StringUtils.hasLength(fileNameOld)) {
                String separator = SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType()) ? "\\" : "/";
                // 删除对应的文件
                File file = new File(filePathOld + separator + fileNameOld);
                if (file.exists() && file.isFile()) {
                    if (!file.delete()) {
                        throw new RuntimeException("datax文件删除失败：" + filePathOld);
                    }
                }
            }

            String dbReader = quartz13001In.getReaderName();
            String dbWriter = quartz13001In.getWriterName();
            String tables = quartz13001In.getTables();
            String cron = quartz13001In.getCron();
            String dbType = dbReader + "2" + dbWriter;
//            String jobFileName = dbType + "-" + MyUUIDUtil.uuid(tables);
            String jobFileName=fileNameOld.split(".json")[0];
            String separator = SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType()) ? "\\" : "/";
//            String fileName = jobFileName + ".json";
            // 编辑的时候，文件名不能变
            String fileName=fileNameOld;
            String filePath = dataXPropertiesConfig.getDataxJobFileDirectory();
            String jobFilePath = filePath + separator + fileName;


            String dbTypeOld = quartz13001In.getTriggerIn().getJobName().split("-")[0];

            if (!("J"+dbType).equals(dbTypeOld)) {
                quartzService.deleteJob(quartz13001In.getTriggerIn().getJobName(), quartz13001In.getTriggerIn().getJobGroup());
                JobBean jobBean = new JobBean().setJobName("J" + jobFileName).setJobGroup("G" + jobFileName).setJobDescription("")
                        .setTriggerName("T" + jobFileName).setTriggerGroup("TG" + jobFileName).setTriggerDescription("")
                        // "0/5 * * * * ?"
                        .setCron(cron);
                quartzService.addJob(DataXCronJob.class, jobBean, jobFilePath);
            } else {
                QueryWrapper wrapper = new QueryWrapper();
                wrapper.eq("trigger_name", triggers.getTriggerName());
                wrapper.eq("trigger_group", triggers.getTriggerGroup());
                CronTriggers cronTriggers = cronTriggersService.getOne(wrapper);
                if (cronTriggers == null || !StringUtils.hasLength(cronTriggers.getCronExpression())
                ||(!cron.equals(cronTriggers.getCronExpression()))
                ) {
                    quartzService.rescheduleJob(quartz13001In.getTriggerIn().getTriggerName(), quartz13001In.getTriggerIn().getTriggerGroup(), cron);
                } else {
                    quartzService.resumeJob(quartz13001In.getTriggerIn().getJobName(), quartz13001In.getTriggerIn().getJobGroup());
                }
            }


            FileUtil.createJobFile(JSON.toJSONString(quartz13001In.getJob(true)), jobFilePath);


            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetails userDetail = (UserDetails) securityContext.getAuthentication().getPrincipal();
            Date date = new Date();
            dataXDetails = new DataXDetails();
            dataXDetails.setFileName(fileName);
            dataXDetails.setFilePath(filePath);
            dataXDetails.setFileStatus("1");
            dataXDetails.setCreateTime(date);
            dataXDetails.setCreator(userDetail.getUsername());
            dataXDetails.setUpdateTime(date);
            dataXDetails.setOperator(userDetail.getUsername());
            dataXDetailsService.save(dataXDetails);
            return new Quartz13001Out().setJobFilePath(jobFilePath);
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("数据同步定时任务"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("数据同步定时任务"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Quartz13002Out service13002(Quartz13002In quartz13002In) throws BizSvcException, JobException {
        try {
            /**
             * 必须采用如下容错规则
             * 1.先查询触发器
             * 2.查询cron
             * 3.查询jobdetail
             * 4.jobClass 和customClass 都没有变，更新触发器，否则，删除定时任务，然后新增
             *
             */


            QueryWrapper qWrapper = new QueryWrapper();
            qWrapper.eq("sched_name", quartz13002In.getTriggersIn().getSchedName());
            qWrapper.eq("trigger_name", quartz13002In.getTriggersIn().getTriggerName());
            qWrapper.eq("trigger_group", quartz13002In.getTriggersIn().getTriggerGroup());
            Triggers triggers = triggerService.getOne(qWrapper);
            if (triggers == null && !StringUtils.hasLength(triggers.getJobName())) {
                throw new RuntimeException("没有对应的触发器");
            }


            Map<String, String> map = jobDeiService.queryMap(triggers.getJobName(), triggers.getJobGroup());
            if (map == null || map.isEmpty()) {
                throw new RuntimeException("没有对应的定时任务或者定时任务有误");
            }
            // this.triggerData.jobData.split('\r\n').filter(item=>item.indexOf('customClass=')!==-1)[0].split('=').filter(item=>item.indexOf('customClass')===-1)[0]

            String jobData = map.get("jobData");
            String jobClassNameOld = map.get("jobClassName");
            String customClassOld=null;
            if (StringUtils.hasLength(jobData)) {
                customClassOld  = Arrays.stream(Arrays.stream(jobData.split("\r\n")).filter(item -> item.indexOf("customClass=") != -1).findFirst().orElse("").split("=")).filter(item -> item.indexOf("customClass") == -1).findFirst().orElse("");
            }
            if(quartz13002In.getClassName().equals(jobClassNameOld)&&StringUtils.hasLength(customClassOld)&&customClassOld.equals(quartz13002In.getCustomClassName())){
                quartzService.rescheduleJob(quartz13002In.getTriggersIn().getTriggerName(), quartz13002In.getTriggersIn().getTriggerGroup(), quartz13002In.getCron());
            }else{
                quartzService.deleteJob(triggers.getJobName(),triggers.getJobGroup());

                String cron = quartz13002In.getCron();
                String jobClass = quartz13002In.getClassName();
                String customClass = quartz13002In.getCustomClassName();
                String jobClassName = jobClass.substring(jobClass.lastIndexOf(".") + 1);
                String customClassName = customClass.substring(customClass.lastIndexOf(".") + 1);
                String jobFileName = "";
                if (StringUtils.hasLength(customClassName)) {
                    jobFileName = jobClassName + "-" + customClassName + "-" + MyUUIDUtil.uuid(customClassName);
                } else {
                    jobFileName = jobClassName + "-" + MyUUIDUtil.uuid(jobClassName);
                }

                JobBean jobBean = new JobBean().setJobName("J" + jobFileName).setJobGroup("G" + jobFileName).setJobDescription("")
                        .setTriggerName("T" + jobFileName).setTriggerGroup("TG" + jobFileName).setTriggerDescription("")
                        // "0/5 * * * * ?"
                        .setCron(cron);

                // 不能出入cron 因为其设置在trigger中，存入job_data中就很不对了
                Map<String, Object> params = new HashMap<>();
                params.put("jobFileName", jobFileName);
                quartzService.addJob(Class.forName(jobClass), jobBean, customClass, JSON.toJSONString(params));
            }

            return new Quartz13002Out();
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("普通定时任务"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1003.toString(), BizSvcEnum.BIZ_SVC_1003.getMessage("普通定时任务"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Quartz14001Out service14001(Quartz14001In quartz14001In) throws BizSvcException, JobException {
        try {
            Quartz14001Out quartz14001Out = new Quartz14001Out();
            QueryWrapper<DataXDetails> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("file_name", quartz14001In.getJobName().substring(1) + ".json");
            List<DataXDetails> list = dataXDetailsService.list(queryWrapper);

            if (!CollectionUtils.isEmpty(list) && list.size() > 1) {
                throw new RuntimeException("一个文件名对应多条记录");
            }
            /**
             * 为了 防止 定时任务删除成功，而 datax相关失败和
             * datax相关处理成功，耳钉是任务删除失败
             * 必须采用如下容错规则
             *
             * 1、先停止quartz定时任务
             * 2、删除data_x_details 数据
             * 3、删除data_x_details 对应的datax数据同步文件
             * 4、删除quartz定时任务
             *
             * 因为先暂停了定时任务，处理完后，在删除定时任务，
             * 那么在对应模块中重启任务的时候，需要先判断是否有对应的datax数据，没有提示无效，删除
             */
            if (!CollectionUtils.isEmpty(list) && list.size() == 1) {
                quartzService.pauseJob(quartz14001In.getJobName(), quartz14001In.getJobGroup());
                DataXDetails dataXDetails = list.get(0);
                dataXDetailsService.removeById(dataXDetails);

                String filePath = dataXDetails.getFilePath();
                String fileName = dataXDetails.getFileName();
                quartz14001Out.setJobFilePath(filePath);
                if (StringUtils.hasLength(filePath) && StringUtils.hasLength(fileName)) {
                    String separator = SYS_TYPE_WINDOWS.equals(dataXPropertiesConfig.getSysType()) ? "\\" : "/";
                    // 删除对应的文件
                    File file = new File(filePath + separator + fileName);
                    if (file.exists() && file.isFile()) {
                        if (!file.delete()) {
                            throw new RuntimeException("datax文件删除失败：" + filePath);
                        }
                    }
                }
            }
            quartzService.deleteJob(quartz14001In.getJobName(), quartz14001In.getJobGroup());
            return quartz14001Out;
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("数据同步定时任务"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("数据同步定时任务"), e.getMessage());
        }
    }

    @DSTransactional
    @Override
    public Quartz14002Out service14002(Quartz14002In quartz14002In) throws BizSvcException, JobException {
        try {
            quartzService.deleteJob(quartz14002In.getJobName(), quartz14002In.getJobGroup());
            return new Quartz14002Out();
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            log.error("【{}】{}：{}", BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("普通定时任务"), e.getMessage(), e);
            throw new BizSvcException(BizSvcEnum.BIZ_SVC_1004.toString(), BizSvcEnum.BIZ_SVC_1004.getMessage("普通定时任务"), e.getMessage());
        }
    }

    @Override
    public Quartz15001Out service15001(Quartz15001In quartz15001In) throws BizSvcException, JobException {
        quartzService.pauseJob(quartz15001In.getJobName(),quartz15001In.getJobGroup());
        return new Quartz15001Out();
    }

    @Override
    public Quartz16001Out service16001(Quartz16001In quartz16001In) throws BizSvcException, JobException {
        quartzService.resumeJob(quartz16001In.getJobName(),quartz16001In.getJobGroup());
        return new Quartz16001Out();
    }
}
