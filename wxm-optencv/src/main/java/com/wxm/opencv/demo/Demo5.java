package com.wxm.opencv.demo;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import java.io.*;
import java.util.Properties;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-20 14:14:32
 */
@Slf4j
public class Demo5 {
    public static void main(String[] args) {
        try {
            opencv();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            log.info("user.dir>>>>>>>{}",e.getMessage());
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
                log.info("jar properties>>>>>>>{}",e.getMessage());
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

    public static void opencv() throws Exception {
        /**
         * 数 “摄像头ID号”：摄像设备（摄像头）的 ID 编号，默认值为 -1，表示随机选取一个摄像头。
         *
         * 如果有多个摄像头，则用数字 “0”， “1” 等表示
         * 如果只有一个摄像头，既可以使用“0”也可以使用“-1” 作为摄像头ID号
         */
        VideoCapture camara = new VideoCapture(0);
        // 第一次没打开，打开
        if (!camara.isOpened()) {
            camara.open(0);
        }
        // 在此检测，没打卡，报错
        if (!camara.isOpened()) {
            throw new Exception("摄像头初始化失败");
        }

        Mat mat = new Mat();
        while (camara.read(mat)) {
            HighGui.namedWindow("camara");
            HighGui.imshow("camara", mat);
            HighGui.waitKey(2);

            mat.release();
        }
        camara.release();
        HighGui.destroyAllWindows();
    }
}
