package com.wxm.opencv.demo;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-20 13:25:07
 */
public class OpenCVUtil {
    /**
     * 寻找轮廓，并按照递增排序
     *
     * @param cannyMat
     * @return
     */
    public static List<MatOfPoint> findContours(Mat cannyMat) {
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();

        // 寻找轮廓
        Imgproc.findContours(cannyMat, contours, hierarchy, Imgproc.RETR_LIST,                 Imgproc.CHAIN_APPROX_SIMPLE,
                new Point(0, 0));
        if (contours.size() <= 0) {
            throw new RuntimeException("未找到图像轮廓");
        } else {
            // 对contours进行了排序，按递增顺序
            contours.sort(new Comparator<MatOfPoint>() {
                @Override
                public int compare(MatOfPoint o1, MatOfPoint o2) {
                    MatOfPoint2f mat1 = new MatOfPoint2f(o1.toArray());
                    RotatedRect rect1 = Imgproc.minAreaRect(mat1);
                    Rect r1 = rect1.boundingRect();

                    MatOfPoint2f mat2 = new MatOfPoint2f(o2.toArray());
                    RotatedRect rect2 = Imgproc.minAreaRect(mat2);
                    Rect r2 = rect2.boundingRect();

                    return (int) (r1.area() - r2.area());
                }
            });
            return contours;
        }
    }

    /**
     * 作用：返回边缘检测之后的最大轮廓
     *
     * @param cannyMat
     *            Canny之后的Mat矩阵
     * @return
     */
    public static MatOfPoint findMaxContour(Mat cannyMat) {
        List<MatOfPoint> contours = findContours(cannyMat);
        return contours.get(contours.size() - 1);
    }

    /**
     * 返回边缘检测之后的最大矩形
     *
     * @param cannyMat
     *            Canny之后的mat矩阵
     * @return
     */
    public static RotatedRect findMaxRect(Mat cannyMat) {
        MatOfPoint maxContour = findMaxContour(cannyMat);

        MatOfPoint2f matOfPoint2f = new MatOfPoint2f(maxContour.toArray());

        RotatedRect rect = Imgproc.minAreaRect(matOfPoint2f);
        Imgproc.boundingRect(cannyMat);

        return rect;
    }

    /**
     * 获取倾斜角度，只能矫正小角度倾斜，大于90度没法判断文字朝向
     * @author lyzhou2
     * @date 2022/3/4 15:05
     * @param cannyMat
     * @return
     */
    public static double getAngle (Mat cannyMat) {
        Mat lines = new Mat();
        //累加器阈值参数，小于设置值不返回
        int threshold = 100;
        //最低线段长度，低于设置值则不返回
        double minLineLength = 200;
        //间距小于该值的线当成同一条线
        double maxLineGap = 10;
        Imgproc.HoughLinesP(cannyMat, lines, 1, Math.PI/180, threshold, minLineLength, maxLineGap);
        //倾斜角度
        double angle = 0;
        //所有线倾斜角度之和
        double totalAngle = 0;
        for (int i = 0; i < lines.rows(); i++) {
            double[] line = lines.get(i, 0);
            //计算每条线弧度
            //这个计算是给Imgproc.getRotationMatrix2D方法用的，但是生成的图片会比原图大十几倍，简直离谱，有知道的大佬也可告知下怎么在不改变图片分辨率和清晰度的情况下缩小
//            double radian = Math.atan(Math.abs(line[3] - line[1]) * (1.0) / (line[2] - line[0]));
            //这个计算是给Graphics2D旋转使用
            double radian = Math.atan((line[3] - line[1]) * (-1.0) / (line[2] - line[0]));
            //计算每条线的倾斜角度
            double lineAngle = 360 * radian / (2 * Math.PI);
            //表格类图片要过滤掉竖线，不然取得角度会有问题
            if (Math.abs(lineAngle) > 45) {
                lineAngle = 90 - lineAngle;
            }
            totalAngle += lineAngle;
        }
        //取角度平均数
        angle = totalAngle / lines.rows();
        return angle;
    }
}
