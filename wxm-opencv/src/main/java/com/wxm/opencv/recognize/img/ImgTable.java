package com.wxm.opencv.recognize.img;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Table;
import com.sun.rowset.internal.Row;
import com.wxm.opencv.demo.demo.Demo5;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-06-08 17:27:01
 */
@Slf4j
public class ImgTable {
    public static void main(String[] args) {
        delTable("C:\\Users\\wangsm\\Desktop\\11\\table.jpg", "C:\\Users\\wangsm\\Desktop\\11\\");
    }



    /**
     * 处理table
     *
     * @author mc
     * @time 2019-9-26
     */
    public static void delTable(String url, String path) {
        Mat srcImage = Imgcodecs.imread(url);
        ImgUtil.save(path + "source.png", srcImage);
        // 图片二值化
        Mat thresh_image = ImgUtil.ImgBinarization(srcImage);
        ImgUtil.save(path + "binarization.png", thresh_image);

        //获取图片上的竖线 1
        Mat vertical_line = ImgUtil.getLines(thresh_image, Math.PI / 1, 1);
        ImgUtil.save(path + "vertical_line.png", vertical_line);

        //获取图片上的横线 2
        Mat horizontal_line = ImgUtil.getLines(thresh_image, Math.PI / 180, 2);
        ImgUtil.save(path + "horizontal_line.png", horizontal_line);

        // 将横线和竖线合并为一张图片
        Mat mask_image = new Mat();
        Core.add(horizontal_line, vertical_line, mask_image);
        ImgUtil.save(path + "mask_image.png", mask_image);
        /*
         * 通过 bitwise_and 定位横线、垂直线交汇的点
         */
        Mat points_image = new Mat();
        Core.bitwise_and(horizontal_line, vertical_line, points_image);
        ImgUtil.save(path + "points_image.png", points_image);

        /**
         * 处理图片中的表格
         */
        ImgUtil.editTable(srcImage, mask_image, points_image, path);

        //将处理后的图像保存为文件，可做调试使用
        ImgUtil.save(path + "result_image.png", srcImage);

    }


    static {
//        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
//        System.load(url.getPath());
//        String systemName = System.getProperty("os.name");
//        System.out.println("system name is :"+systemName);
        // 从配置文件中读取 opencv.properties

        String userDir = System.getProperty("user.dir");
        // 开发环境（是，模块所在的目录），比如wxm-optencv 所在目录是wxm-datax
        System.out.println(">>>>>>>userDir>>>" + userDir);
        // 读取jar包同级目录下 config文件夹下的配置文件
        String filePath = userDir + File.separator + "config" + File.separator + "opencv.properties";
        try {
            InputStream in = new FileInputStream(new File(filePath));
            Properties properties = new Properties();
            properties.load(in);
            String opencvDll = properties.getProperty("opencv.java.dll");
            System.load(opencvDll);
        } catch (FileNotFoundException e) {
            log.info("user.dir>>>>>>>{}", e.getMessage());
            //读取配置文件，jar包同级的配置文件
            Properties properties = new Properties();
            File file = new File("opencv.properties");
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                properties.load(fis);
                String opencvDll = properties.getProperty("opencv.java.dll");
                System.load(opencvDll);
                fis.close();
            } catch (FileNotFoundException ex) {
                log.info("jar properties>>>>>>>{}", e.getMessage());
                // jar包中的配置文件
                InputStream in = Demo5.class.getClassLoader().getResourceAsStream("opencv.properties");
                try {
                    properties.load(in);
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
                String opencvDll = properties.getProperty("opencv.java.dll");
                System.load(opencvDll);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
