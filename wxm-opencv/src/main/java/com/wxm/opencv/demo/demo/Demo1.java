package com.wxm.opencv.demo.demo;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-20 12:17:51
 */
public class Demo1 {
//    public static void main(String[] args) throws Exception {
//        // 加载动态库
//        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
//        System.load(url.getPath());
//        // 读取图像(不能识别中文路径)
//        Mat mat = imread("C:\\Users\\wangsm\\Desktop\\11\\1.jpg");
//        if (mat.empty()) {
//            throw new Exception("mat is empty");
//        }
//        System.out.println("图片颜色通道数：" + mat.channels());
//        System.out.println("图片色深：" + mat.depth()); // CV_8U=0,CV_8S=1,CV_16U=2,CV_16S=3,CV_32S=4,CV_32F=5,CV_64F=6
//        System.out.println("图片宽：" + mat.rows());
//        System.out.println("图片高：" + mat.cols());
//        System.out.println("图片size：" + mat.size());
//        System.out.println("图片总像素：" + mat.total());
//        System.out.println("图片像素：\n" + mat.dump());
//    }

    public static void main(String[] args) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //        // 加载动态库
        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("C:\\Users\\wangsm\\Desktop\\11\\1.jpg");
        HighGui.imshow("原来",src);

        HighGui.imshow("lena",src);
        HighGui.waitKey(0);
        Mat dst = src.clone();
        //定义图像的中心
        Point center = new Point(src.width() / 2, src.height() / 2);
        Mat rotationMatrix2D = Imgproc.getRotationMatrix2D(center, 90, 1.0);//旋转的角度：33.0，缩放的比例：1.0
        Imgproc.warpAffine(src,dst,rotationMatrix2D,dst.size(),Imgproc.INTER_NEAREST);
        HighGui.imshow("lena",dst);
        HighGui.waitKey(0);
    }
}
