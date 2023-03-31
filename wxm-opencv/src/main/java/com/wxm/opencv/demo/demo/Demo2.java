package com.wxm.opencv.demo.demo;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;

/**
 * TODO
 *
 * @author wangsm
 * @date 2023-03-20 12:34:02
 * @version 1.0.0
 */
@Slf4j
public class Demo2 {
    static {
        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
        System.load(url.getPath());
    }

    public static void main(String[] args) {
        Mat img= Imgcodecs.imread("C:\\Users\\wangsm\\Desktop\\11\\1.jpg");
        log.info("读取tup:{},图片是否为空：{}",img,img.empty());
        Mat dst1 = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);

        Mat dst2 = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);

        Mat dst3 = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);

        //向右翻转

        Core.flip(img, dst1, 1);

//向下翻转

        Core.flip(img, dst2, 0);

//同时向右向下翻转

        Core.flip(img, dst3, -1);

        HighGui.imshow("原始",img);

        HighGui.imshow("向右旋转",dst1);

        HighGui.imshow("向下旋转",dst2);

        HighGui.imshow("同时向右向下翻转",dst3);

        HighGui.waitKey();
    }
}
