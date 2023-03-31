package com.wxm.opencv;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.cvtColor;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WxmOptencvApplicationTests {

    @Test
    void testImg() throws Exception {
//        Img img=new Img();
//        img.dealImg("C:\\Users\\wangsm\\Desktop\\11\\1.jpg");
    }

    @Test
    void testOpencv() throws Exception {
//        // 解决awt报错问题
//        System.setProperty("java.awt.headless", "false");
//        System.out.println(System.getProperty("java.library.path"));
//        // 加载动态库
//        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
//        System.load(url.getPath());
//        // 读取图像(不能识别中文路径)
//        Mat image = imread("C:\\Users\\wangsm\\Desktop\\11\\1.jpg");
//        if (image.empty()) {
//            throw new Exception("image is empty");
//        }
//        imshow("Original Image", image);
//
//        // 创建输出单通道图像
//        Mat grayImage = new Mat(image.rows(), image.cols(), CvType.CV_8SC1);
//        // 进行图像色彩空间转换
//        cvtColor(image, grayImage, COLOR_RGB2GRAY);
//
//        imshow("Processed Image", grayImage);
//        imwrite("C:\\Users\\wangsm\\Desktop\\11\\1-r.jpg", grayImage);
//        waitKey();


    }

    @Test
    void contextLoads() {
    }

}
