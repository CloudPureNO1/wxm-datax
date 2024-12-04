package com.wxm.security.validatecode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 14:24:45
 */
@Slf4j
@Service
public class StandardValidateCodeServiceImpl implements ValidateCodeService {
    @Override
    public String type() {
        return ValidateCodeType.STANDARD.type();
    }

    @Override
    public ValidateCode build(HttpServletRequest request) {
        try {
            int w = 85;
            int h = 40;
            BufferedImage image = new BufferedImage(w, h, 1);
            SecureRandom rand = new SecureRandom();
            Graphics2D g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color[] colors = new Color[5];
            Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
            float[] fractions = new float[colors.length];

            for (int i = 0; i < colors.length; ++i) {
                colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
                fractions[i] = rand.nextFloat();
            }

            Arrays.sort(fractions);
            g2.setColor(Color.GRAY);
            g2.fillRect(0, 0, w, h);
            Color c = this.getRandColor(200, 250);
            g2.setColor(c);
            g2.fillRect(0, 2, w, h - 4);
            SecureRandom random = new SecureRandom();
            g2.setColor(this.getRandColor(160, 200));

            int area;
            int fontSize;
            int x;
            int y;
            for (int i = 0; i < 20; ++i) {
                area = random.nextInt(w - 1);
                fontSize = random.nextInt(h - 1);
                x = random.nextInt(6) + 1;
                y = random.nextInt(12) + 1;
                g2.drawLine(area, fontSize, area + x + 40, fontSize + y + 20);
            }

            float yawpRate = 0.05F;
            area = (int) (yawpRate * (float) w * (float) h);

            for (fontSize = 0; fontSize < area; ++fontSize) {
                x = random.nextInt(w);
                y = random.nextInt(h);
                int rgb = this.getRandomIntColor();
                image.setRGB(x, y, rgb);
            }

            this.shear(g2, w, h, c);
            g2.setColor(this.getRandColor(100, 160));
            fontSize = h - 4;
            Font font = new Font("Arial", 2, fontSize);
            g2.setFont(font);
            String sRand = "";
            int verifySize = 4;
            char[] chars = this.generateVerifyCode(verifySize).toCharArray();

            for (int i = 0; i < verifySize; ++i) {
                sRand = sRand + chars[i];
                AffineTransform affine = new AffineTransform();
                affine.setToRotation(0.5235987755982988 * rand.nextDouble() * (double) (rand.nextBoolean() ? 1 : -1), (double) (w / verifySize * i + fontSize / 3), (double) (h / 2));
                g2.setTransform(affine);
                g2.drawString(String.valueOf(chars[i]), (w - 10) / verifySize * i + 2, h / 2 + fontSize / 2 - 5);
            }

            g2.dispose();
            String back = ImageUtil.bufferedImageToBase64(image);
            request.getSession().setAttribute("validateCode", sRand);
            ValidateCode validateCode = new ValidateCode();
            validateCode.setBack(back);
            return validateCode;
        } catch (Exception e) {
            log.error("获取验证码失败！", e);
            throw new SecurityException("获取验证码失败！");
        }
    }

    @Override
    public void check(ValidateCode dto, HttpServletRequest request) {
        String validateCode = request.getParameter("validateCode");
        log.debug(">>> 验证={},sid={}", validateCode, request.getSession().getId());

        if (!StringUtils.hasText(validateCode)) {
            throw new SecurityException("验证码不能为空！");
        }


        String scode = (String) request.getSession().getAttribute("validateCode");

        if (!validateCode.equalsIgnoreCase(scode)) {
            ValidateCodeService.errorCountAdd(request);
            throw new SecurityException("验证码错误！");
        } else {
            request.getSession().setAttribute("validateCode", (Object) null);
            request.getSession().setAttribute("validateCodeErrorCount", 0);
        }

    }


    public String generateVerifyCode(int verifySize) {
        return this.generateVerifyCode(verifySize, "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz");
    }

    public String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
        }

        int codesLen = sources.length();
        SecureRandom rand = new SecureRandom();
        StringBuilder verifyCode = new StringBuilder(verifySize);

        for (int i = 0; i < verifySize; ++i) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }

        return verifyCode.toString();
    }

    private Color getRandColor(int fc, int bc) {
        SecureRandom random = new SecureRandom();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private int getRandomIntColor() {
        int[] rgb = this.getRandomRgb();
        int color = 0;
        int[] var3 = rgb;
        int var4 = rgb.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            int c = var3[var5];
            color <<= 8;
            color |= c;
        }

        return color;
    }

    private int[] getRandomRgb() {
        SecureRandom random = new SecureRandom();
        int[] rgb = new int[3];

        for (int i = 0; i < 3; ++i) {
            rgb[i] = random.nextInt(255);
        }

        return rgb;
    }

    private void shear(Graphics g, int w1, int h1, Color color) {
        this.shearX(g, w1, h1, color);
        this.shearY(g, w1, h1, color);
    }

    private void shearX(Graphics g, int w1, int h1, Color color) {
        SecureRandom random = new SecureRandom();
        int period = random.nextInt(2);
        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; ++i) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + 6.283185307179586 * (double) phase / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private void shearY(Graphics g, int w1, int h1, Color color) {
        SecureRandom random = new SecureRandom();
        int period = random.nextInt(40) + 10;
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;

        for (int i = 0; i < w1; ++i) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + 6.283185307179586 * (double) phase / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }

    }
}
