package com.wxm.security.validatecode;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 15:07:39
 */
public class SliderUtil {
    public static final int ZOOM_RATIO = 2;
    private static final int RECT_LENGTH = 88;
    private static final int CIRCLE_RADIUS = 16;
    private static final int SIDE_LENGTH = 116;
    private static final Color CLR_GLOW_INNER_HI = new Color(221, 211, 212, 148);
    private static final Color CLR_GLOW_INNER_LO = new Color(255, 255, 255);
    private static final Color CLR_GLOW_OUTER_HI = new Color(221, 211, 212, 124);
    private static final Color CLR_GLOW_OUTER_LO = new Color(255, 255, 255);
    private static final int LIGHT_HEIGHT_WIDTH = 3;
    private static final int SHADOW_WIDTH = 2;

    public SliderUtil() {
    }

    public static Coordinate getRandomCoordinate(int width, int height) {
        SecureRandom random = new SecureRandom();
        int x = random.nextInt(width - 232) + 116;
        int y = random.nextInt(height - 116 - 10) + 10;
        return new Coordinate(x, y);
    }

    public static Shape createShape() {
        Shape shapeRect = new Rectangle2D.Float(0.0F, 28.0F, 88.0F, 88.0F);
        Shape shapeTop = new Ellipse2D.Float(28.0F, 0.0F, 32.0F, 32.0F);
        Shape shapeRight = new Ellipse2D.Float(84.0F, 56.0F, 32.0F, 32.0F);
        Shape shapeBottom = new Ellipse2D.Float(28.0F, 88.0F, 32.0F, 32.0F);
        Area area = new Area(shapeRect);
        area.add(new Area(shapeTop));
        area.add(new Area(shapeRight));
        Area subArea = new Area(shapeBottom);
        area.subtract(subArea);
        return area;
    }

    public static BufferedImage cropImage(BufferedImage bi, int x, int y) {
        return bi.getSubimage(x, y, 116, 116);
    }

    public static BufferedImage createSideBufferedImage() {
        return new BufferedImage(116, 116, 6);
    }

    public static void fillBufferedImage(BufferedImage frontBufferedImage, BufferedImage backBufferedImage, BufferedImage cropBufferedImage, Shape shape) {
        for(int i = 0; i < 116; ++i) {
            for(int j = 0; j < 116; ++j) {
                if (shape.contains((double)i, (double)j)) {
                    frontBufferedImage.setRGB(i, j, cropBufferedImage.getRGB(i, j));
                    backBufferedImage.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }

    }

    public static BufferedImage dealLightAndShadow(BufferedImage bfm, Shape shape) throws IOException {
        BufferedImage buffimg = ((Graphics2D)bfm.getGraphics()).getDeviceConfiguration().createCompatibleImage(118, 118, 3);
        Graphics2D graphics2D = buffimg.createGraphics();
        Graphics2D g2 = (Graphics2D)bfm.getGraphics();
        paintBorderGlow(g2, 3, shape);
        paintBorderShadow(graphics2D, 2, shape);
        graphics2D.drawImage(bfm, 0, 0, (ImageObserver)null);
        return buffimg;
    }

    public static BufferedImage processFrontImage(BufferedImage frontBufferedImage, int imgHeight, int y) {
        BufferedImage compressBufferedImage = ImageUtil.compressImage(frontBufferedImage, 116, 116, 58, 58);
        BufferedImage bufferedImage = new BufferedImage(68, imgHeight / 2, 6);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(compressBufferedImage, 10, y / 2, (ImageObserver)null);
        g2d.dispose();
        return bufferedImage;
    }

    public static BufferedImage processBackImage(BufferedImage originImage, BufferedImage backBufferedImage, int imgWidth, int imgHeight, int x, int y) {
        BufferedImage bufferedImage = new BufferedImage(116, 116, 2);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.getInstance(3, 0.7F));
        graphics2D.drawImage(backBufferedImage, 0, 0, (ImageObserver)null);
        graphics2D.dispose();
        Graphics2D back = (Graphics2D)originImage.getGraphics();
        back.drawImage(bufferedImage, x, y, (ImageObserver)null);
        back.dispose();
        return ImageUtil.compressImage(originImage, imgWidth, imgHeight, imgWidth / 2, imgHeight / 2);
    }

    private static void paintBorderGlow(Graphics2D g2, int glowWidth, Shape clipShape) {
        int gw = glowWidth * 2;

        for(int i = gw; i >= 2; i -= 2) {
            float pct = (float)(gw - i) / (float)(gw - 1);
            Color mixHi = getMixedColor(CLR_GLOW_INNER_HI, pct, CLR_GLOW_OUTER_HI, 1.0F - pct);
            Color mixLo = getMixedColor(CLR_GLOW_INNER_LO, pct, CLR_GLOW_OUTER_LO, 1.0F - pct);
            g2.setPaint(new GradientPaint(0.0F, 8.75F, mixHi, 0.0F, 35.0F, mixLo));
            g2.setComposite(AlphaComposite.getInstance(10, pct));
            g2.setStroke(new BasicStroke((float)i));
            g2.draw(clipShape);
        }

    }

    private static void paintBorderShadow(Graphics2D g2, int shadowWidth, Shape clipShape) {
        BufferedImage buffimg = g2.getDeviceConfiguration().createCompatibleImage(118, 118, 3);
        Graphics2D graphics2D = buffimg.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int sw = shadowWidth * 2;

        for(int i = sw; i >= 2; i -= 2) {
            float pct = (float)(sw - i) / (float)(sw - 1);
            if (!((double)pct < 0.3) && !((double)pct > 0.8)) {
                graphics2D.setColor(getMixedColor(new Color(0, 0, 0), pct, Color.WHITE, 1.0F - pct));
                graphics2D.setStroke(new BasicStroke((float)i, 1, 1));
                graphics2D.draw(clipShape);
            }
        }

        g2.drawImage(buffimg, 1, 1, (ImageObserver)null);
    }

    private static Color getMixedColor(Color c1, float pct1, Color c2, float pct2) {
        float[] clr1 = c1.getComponents((float[])null);
        float[] clr2 = c2.getComponents((float[])null);

        for(int i = 0; i < clr1.length; ++i) {
            clr1[i] = clr1[i] * pct1 + clr2[i] * pct2;
        }

        return new Color(clr1[0], clr1[1], clr1[2], clr1[3]);
    }
}
