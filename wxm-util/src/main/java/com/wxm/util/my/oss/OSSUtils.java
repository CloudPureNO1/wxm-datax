package com.wxm.util.my.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.wxm.base.bean.OSSFileBean;
import com.wxm.base.enums.OSSEnum;
import com.wxm.base.exception.OSSException;
import com.wxm.util.my.oss.config.OSSConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/10/21 10:33
 * @since 1.0.0
 */
@Slf4j
@Component
public class OSSUtils {
    /**
     * {@link com.aliyun.oss.ClientConfiguration}  MAX_CONNECTIONS
     * 设置OSSClient允许打开的最大HTTP连接数，默认为1024个
     */
    private static final int CONF_MAX_CONNECTIONS = 1024;
    /**
     * 设置Socket层传输数据的超时时间，默认为50000毫秒
     */
    private static final int CONF_SOCKET_TIMEOUT = 50000;
    /**
     * 设置Socket层传输数据的超时时间，默认为50000毫秒
     */
    private static final int CONF_CONNECTION_TIMEOUT = 50000;
    /**
     * 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时
     */
    private static final int CONF_CONNECTION_REQUEST_TIMEOUT = -1;
    /**
     * 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒
     */
    private static final int CONF_IDLE_CONNECTION_TIME = 60000;
    /**
     * 设置失败请求重试次数，默认为3次
     */
    private static final int CONF_MAX_ERROR_RETRY = 3;


    /**
     * 静态对象  在set 非静态 方法中采用@{@link Autowired}
     */
    private static OSSConfig ossConfig;

    @Autowired
    public void setOSSConfig(OSSConfig config){
        ossConfig=config;
    }

    public static OSSConfig getOssConfig() {
        return ossConfig;
    }

    private static ClientBuilderConfiguration initConf() {
        // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();

        // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        conf.setMaxConnections(CONF_MAX_CONNECTIONS);
        // 设置Socket层传输数据的超时时间，默认为50000毫秒。
        conf.setSocketTimeout(CONF_SOCKET_TIMEOUT);
        // 设置建立连接的超时时间，默认为50000毫秒。
        conf.setConnectionTimeout(CONF_CONNECTION_TIMEOUT);
        // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        conf.setConnectionRequestTimeout(CONF_CONNECTION_REQUEST_TIMEOUT);
        // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        conf.setIdleConnectionTime(CONF_IDLE_CONNECTION_TIME);
        // 设置失败请求重试次数，默认为3次。
        conf.setMaxErrorRetry(CONF_MAX_ERROR_RETRY);
        // 设置是否支持将自定义域名作为Endpoint，默认支持。
        conf.setSupportCname(true);
        // 设置是否开启二级域名的访问方式，默认不开启。
        conf.setSLDEnabled(false);
        // 设置连接OSS所使用的协议（HTTP/HTTPS），默认为HTTP。
        conf.setProtocol(Protocol.HTTP);

        return conf;
    }


    /**
     * OSSClient实例
     */
    private static volatile OSS ossClient;

    /**
     * 初始化： 双重锁单例
     */
    private static OSS getOssClient() {
        if (ossClient == null) {
            synchronized (OSS.class) {
                if (ossClient == null) {
                    ClientBuilderConfiguration conf = initConf();
                    ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret(), conf);
                    //程序退出关闭ossClient
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> ossClient.shutdown()));
                }
            }
        }
        return ossClient;
    }

    /**
     * File
     * @param key  生成的唯一主键，下载时用
     * @param file
     * @return
     * @throws OSSException
     */
    public boolean upload(String key, File file) throws OSSException {
        try{
            ossClient=getOssClient();
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(),key,file);
            PutObjectResult putObjectResult=ossClient.putObject(putObjectRequest);
            if(putObjectResult==null||putObjectResult.getETag()==null){
                return false;
            }
            return true;
        }catch (Exception e){
            log.error("【{}】{}：{}", OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage(),e.getMessage(),e);
            throw new OSSException(OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage()+":"+e.getMessage());
        }finally {
            // 关闭OSSClient。
            // ossClient.shutdown();
        }
    }

    /**
     * File
     * @param key  生成的唯一主键，下载时用
     * @param file
     * @param metadata
     * @return
     * @throws OSSException
     */
    public boolean upload(String key, File file, ObjectMetadata metadata) throws OSSException {
        try{
            ossClient=getOssClient();
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(),key,file,metadata);
            PutObjectResult putObjectResult=ossClient.putObject(putObjectRequest);
            if(putObjectResult==null||putObjectResult.getETag()==null){
                return false;
            }
            return true;
        }catch (Exception e){
            log.error("【{}】{}：{}", OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage(),e.getMessage(),e);
            throw new OSSException(OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage()+":"+e.getMessage());
        }finally {
            // 关闭OSSClient。
            // ossClient.shutdown();
        }
    }

    /**
     * InputStream
     * @param key  生成的唯一主键，下载时用
     * @param inputStream
     * @return
     * @throws OSSException
     */
    public boolean upload(String key, InputStream inputStream) throws OSSException {
        try{
            ossClient=getOssClient();
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(),key,inputStream);
            PutObjectResult putObjectResult=ossClient.putObject(putObjectRequest);
            if(putObjectResult==null||putObjectResult.getETag()==null){
                return false;
            }
            return true;
        }catch (Exception e){
            log.error("【{}】{}：{}", OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage(),e.getMessage(),e);
            throw new OSSException(OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage()+":"+e.getMessage());
        }finally {
            // 关闭OSSClient。
            // ossClient.shutdown();
        }
    }

    /**
     * InputStream
     * @param key  生成的唯一主键，下载时用
     * @param inputStream
     * @param metadata
     * @return
     * @throws OSSException
     */
    public boolean upload(String key, InputStream inputStream, ObjectMetadata metadata) throws OSSException {
        try{
            ossClient=getOssClient();
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(),key,inputStream,metadata);
            PutObjectResult putObjectResult=ossClient.putObject(putObjectRequest);
            if(putObjectResult==null||putObjectResult.getETag()==null){
                return false;
            }
            return true;
        }catch (Exception e){
            log.error("【{}】{}：{}", OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage(),e.getMessage(),e);
            throw new OSSException(OSSEnum.OSS_1001.toString(),OSSEnum.OSS_1001.getMessage()+":"+e.getMessage());
        }finally {
            // 关闭OSSClient。
            // ossClient.shutdown();
        }
    }


    /**
     * 文件下载
     * @param key
     * @return  {@link OSSObject}
     * @throws IOException
     * @throws OSSException
     */
    public OSSObject download(String key) throws IOException, OSSException {
        OSSObject ossObject=null;
        try{
            ossClient=getOssClient();
            ossObject=ossClient.getObject(ossConfig.getBucketName(),key);
            return ossObject;
            // ossObject.close();  处理流后关闭  其实就是关闭流，在后面由业务去关闭
        }catch (Exception e){
            log.error("【{}】{}：{}", OSSEnum.OSS_1002.toString(),OSSEnum.OSS_1002.getMessage(),e.getMessage(),e);
            throw new OSSException(OSSEnum.OSS_1002.toString(),OSSEnum.OSS_1002.getMessage()+":"+e.getMessage());
        }
    }

    public List<Map<String, byte[]>> downloadByListFilePath(List<Map<String, String>> listFilePath, String pathPropertyName, String keyPropertyName) throws  OSSException {
        List<Map<String, byte[]>> list = new ArrayList<>();
        try{
            for (Map<String, String> map : listFilePath) {
                String key="";
                InputStream inputStream=null;
                OSSObject ossObject=null;
                try {
                    key = map.get(pathPropertyName) == null ? "" : map.get(pathPropertyName);
                    if (StringUtils.hasLength(key)) {
                        ossClient=getOssClient();
                        ossObject=ossClient.getObject(ossConfig.getBucketName(),key);

                        if(ossObject!=null&&ossObject.getObjectContent()!=null){
                            inputStream = ossObject.getObjectContent();
                            byte[] fileData = IOUtils.toByteArray(inputStream);
                            Map<String, byte[]> rsMap = new HashMap<>();
                            rsMap.put(map.get(keyPropertyName), fileData);
                            list.add(rsMap);
                        }
                    }
                } catch (Exception e) {
                    log.error("显示文件时，获取文件异常：继续执行: path={}", key,e);
                }finally {
                    // 就是关闭流
            /*        if(ossObject!=null){
                        ossObject.close();
                    }*/
                    if(inputStream!=null){
                        inputStream.close();
                    }
                }
            }
            return list;
        }catch (Exception e){
            log.error("【{}】{}：{}", OSSEnum.OSS_1002.toString(),OSSEnum.OSS_1002.getMessage(),e.getMessage(),e);
            throw new OSSException(OSSEnum.OSS_1002.toString(),OSSEnum.OSS_1002.getMessage()+":"+e.getMessage());
        }
    }


    private String  getContentType(String fileType){
        StringBuffer buf = new StringBuffer();
        if("jpeg".equalsIgnoreCase(fileType)||"jpg".equalsIgnoreCase(fileType)||"png".equals(fileType)){
            buf.append("image/");
        }else if("pdf".equalsIgnoreCase(fileType)){
            buf.append("application");
        }else{
            buf.append("image/");
        }
        return buf.append(fileType).toString();
    }



    /**
     * 文件下载
     * @param key
     * @return  {@link OSSObject}
     * @throws IOException
     * @throws OSSException
     */
    @Deprecated
    public OSSFileBean downloadWithBean(String key) throws IOException, OSSException {
        OSSObject ossObject=null;
        try{
            ossClient=getOssClient();
            ossObject=ossClient.getObject(ossConfig.getBucketName(),key);
            if(ossObject==null){
                log.error("文件【{}】下载，返回OSSObject为null",key);
                throw new OSSException("文件【"+key+"】下载，返回OSSObject为null");
            }
            OSSFileBean ossFileBean=new OSSFileBean();
            ossFileBean.setKey(key);
            ossFileBean.setInputStream(ossObject.getObjectContent());
            ossFileBean.setMetadata(ossObject.getObjectMetadata());
            ossFileBean.setContentType(ossObject.getObjectMetadata().getContentType());
            ossFileBean.setSize(ossObject.getObjectMetadata().getContentLength());
            ossFileBean.setLastModified(ossObject.getObjectMetadata().getLastModified());
            return ossFileBean;
        }catch (Exception e){
            if(e instanceof OSSException){
                throw e;
            }
            log.error("【{}】{}：{}", OSSEnum.OSS_1002.toString(),OSSEnum.OSS_1002.getMessage(),e.getMessage(),e);
            throw new OSSException(OSSEnum.OSS_1002.toString(),OSSEnum.OSS_1002.getMessage()+":"+e.getMessage());
        }finally {
            //ossObject对象使用完毕后必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            // 调用处关闭，即在获取文件流后关闭，否则报错
            // if(ossObject!=null){ossObject.close();}
        }
    }

}
