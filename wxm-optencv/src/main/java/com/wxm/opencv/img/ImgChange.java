package com.wxm.opencv.img;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-22 13:17:52
 */
public class ImgChange {

    public static void rotate(){
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("C:\\Users\\wangsm\\Desktop\\11\\10.jpg");
        HighGui.imshow("lena",src);
//        HighGui.waitKey(0);
        Mat dst = src.clone();
        //定义图像的中心
        Point center = new Point(src.width() / 2, src.height() / 2);
        //旋转的角度：33.0，缩放的比例：1.0
//        Mat rotationMatrix2D = Imgproc.getRotationMatrix2D(center, 33.0, 1.0);
        Mat rotationMatrix2D = Imgproc.getRotationMatrix2D(center, -90, 1.0);
        Imgproc.warpAffine(src,dst,rotationMatrix2D,dst.size(), Imgproc.INTER_NEAREST);
        HighGui.imshow("lena",dst);
        HighGui.waitKey(0);
    }

    public static void main(String[] args) {
        rotate();
    }

    static {
        //加载opencv动态库，必要
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
        System.load(url.getPath());
    }
}
