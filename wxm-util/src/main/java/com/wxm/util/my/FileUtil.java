package com.wxm.util.my;

 
import com.alibaba.fastjson.JSON;
import com.wxm.base.enums.UtilEnum;
import com.wxm.base.exception.UtilException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/6 9:54
 * @since 1.0.0
 */
@Slf4j
public class FileUtil {

    /**
     * 读取文件为字符串
     * @param fileFullName
     * @param charset
     * @return
     * @throws IOException
     * @throws UtilException
     */
    public static String readFileToString(String fileFullName,String charset) throws IOException, UtilException {
        FileInputStream inputStream=new FileInputStream(new File(fileFullName));
        return readFileToString(inputStream,charset);
    }

    /**
     * 读取文件为字符串
     * @param inputStream
     * @param charset
     * @return
     * @throws IOException
     * @throws UtilException
     */
    public static String readFileToString(InputStream inputStream, String charset) throws IOException, UtilException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader= null;
        BufferedReader bufferedReader=null;
        try {
            inputStreamReader = new InputStreamReader(inputStream,charset);
            bufferedReader=new BufferedReader(inputStreamReader);
            String str=null;
            while((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("【{}】{}：{}", UtilEnum.UTIL_1006.toString(),UtilEnum.UTIL_1006.getMessage(),charset);
            throw new UtilException(UtilEnum.UTIL_1006.toString(),UtilEnum.UTIL_1006.getMessage()+":"+charset);
        } catch (Exception ie){
            log.error("【{}】{}：{}", UtilEnum.UTIL_9999.toString(),UtilEnum.UTIL_9999.getMessage(), ie.getMessage());
            throw new UtilException(UtilEnum.UTIL_9999.toString(),UtilEnum.UTIL_9999.getMessage()+":"+ ie.getMessage());
        }finally {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(inputStreamReader!=null){
                inputStreamReader.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return sb.toString();
    }


    /**
     * 读取文件到list 按照行存储
     * @param fileFullName
     * @param charset
     * @return
     * @throws IOException
     * @throws UtilException
     */
    public static List<String> readFile(String fileFullName,String charset) throws IOException, UtilException {
        FileInputStream inputStream=new FileInputStream(new File(fileFullName));
        return readFile(inputStream,charset);
    }

    /**
     * 读取文件到list 按照行存储
     * @param inputStream
     * @param charset
     * @return
     * @throws IOException
     * @throws UtilException
     */
    public static List<String>readFile(InputStream inputStream, String charset) throws IOException, UtilException {
        List<String>list=new ArrayList<String>();
        InputStreamReader inputStreamReader= null;
        BufferedReader bufferedReader=null;
        try {
            inputStreamReader = new InputStreamReader(inputStream,charset);
            bufferedReader=new BufferedReader(inputStreamReader);
            String str=null;
            while((str=bufferedReader.readLine())!=null){
                list.add(str.trim());
            }
        } catch (UnsupportedEncodingException e) {
            log.error("【{}】{}：{}", UtilEnum.UTIL_1006.toString(),UtilEnum.UTIL_1006.getMessage(),charset);
            throw new UtilException(UtilEnum.UTIL_1006.toString(),UtilEnum.UTIL_1006.getMessage()+":"+charset);
        } catch (Exception ie){
            log.error("【{}】{}：{}", UtilEnum.UTIL_9999.toString(),UtilEnum.UTIL_9999.getMessage(), ie.getMessage());
            throw new UtilException(UtilEnum.UTIL_9999.toString(),UtilEnum.UTIL_9999.getMessage()+":"+ ie.getMessage());
        }finally {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(inputStreamReader!=null){
                inputStreamReader.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return list;
    }
    /**
     * 创建文件
     * @param text 文件内容字符串(比如：json格式等)
     * @param filePath  文件全路径（带文件名）
     * @throws IOException
     * @throws UtilException
     */
    public static void createJobFile(String text,String filePath) throws IOException, UtilException {
        FileOutputStream fos = null;
        try {
            File file = new File(filePath);
            if(file.exists()){
                log.error("【{}】{}：{}", UtilEnum.UTIL_1001.toString(),UtilEnum.UTIL_1001.getMessage(),filePath);
                throw new UtilException(UtilEnum.UTIL_1001.toString(),UtilEnum.UTIL_1001.getMessage());
            }
            if(!file.getParentFile().exists()){
                try{
                    file.getParentFile().mkdirs();
                }catch (Exception e){
                    log.error("【{}】{}：{}", UtilEnum.UTIL_1002.toString(),UtilEnum.UTIL_1002.getMessage(),file.getParentFile().getParent());
                    throw new UtilException(UtilEnum.UTIL_1002.toString(),UtilEnum.UTIL_1002.getMessage());
                }
            }
            try{
                fos = new FileOutputStream(file);
                fos.write(text.getBytes(StandardCharsets.UTF_8));
            }catch (Exception e){
                log.error("【{}】{}：{}", UtilEnum.UTIL_1003.toString(),UtilEnum.UTIL_1003.getMessage(),filePath);
                throw new UtilException(UtilEnum.UTIL_1003.toString(),UtilEnum.UTIL_1003.getMessage());
            }
        } finally {
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }
    }


}
