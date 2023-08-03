package com.wxm.opencv.websocket.test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2023-06-12 20:57:08
 */
public class TestTextOcr {
    public static void main(String[] args) throws IOException {
        // 创建实例
        ITesseract instance = new Tesseract();

        // 设置识别语言

        instance.setLanguage("chi_sim");

        // 设置识别引擎

        instance.setOcrEngineMode(1);

        // 读取文件

        BufferedImage image = ImageIO.read(TestTextOcr.class.getResourceAsStream("/table.jpg"));
        try {

            // 识别

            String result = instance.doOCR(image);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }


    }
}
