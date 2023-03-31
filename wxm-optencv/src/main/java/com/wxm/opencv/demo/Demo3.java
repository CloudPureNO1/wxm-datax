package com.wxm.opencv.demo;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * TODO
 *
 * 用opencv的霍夫检测计算出倾斜角度，通过Graphics2D来旋转图片
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-20 13:10:25
 */
public class Demo3 {
    static {
        //加载opencv动态库，必要
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
        System.load(url.getPath());
    }
    public static void main(String[] args) {
        second();
    }

    public static void second () {
        try {
            //输入图片路径
            String srcPath = "C:\\Users\\wangsm\\Desktop\\11\\22.jpg";
            //输出图片路径
            String rotatePath = "C:\\Users\\wangsm\\Desktop\\11\\22-new.jpg";

            //输入图片
            Mat src = Imgcodecs.imread(srcPath);
            //灰度化
            Mat gray = new Mat();
            if (src.channels() == 3) {
                Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
                src = gray;
            } else {
                System.out.println("不是RGB图片!");
            }
            //边缘算法检测
            Mat cannyMat = src.clone();
            //表示迟滞过程的第一个阈值
            double threshold1 = 60;
            //表示迟滞过程的第二个阈值，通常把第一个阈值*2或*3
            double threshold2 = threshold1 * 3;
            Imgproc.Canny(src, cannyMat, threshold1, threshold2);
            //计算倾斜角度
            double angle = OpenCVUtil.getAngle(cannyMat);
            //图片旋转
            BufferedImage srcBuff = ImageIO.read(new File(srcPath));
            //图片宽度
            int width = srcBuff.getWidth(null);
            //图片高度
            int height = srcBuff.getHeight(null);
            BufferedImage res = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = res.createGraphics();
            //设置图片底色
            g2.setBackground(Color.WHITE);
            g2.fillRect(0, 0, width, height);
            g2.rotate(Math.toRadians(angle), width / 2, height / 2);
            g2.drawImage(srcBuff, null, null);
            g2.dispose();
//            ImageIO.write生成png速度特别慢，听说jdk1.9以后有优化（没试过），但是本项目不能改jdk版本，所以选择PngEncoder来生成图片
            ImageIO.write(res, "jpg", new File(rotatePath));
//            PngEncoder encoder=new PngEncoder();
//            encoder.withBufferedImage(res).toFile(rotatePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
