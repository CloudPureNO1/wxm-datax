package com.wxm.security.validatecode;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 15:01:38
 */
public class ImageUtil {
    public static final String PNG_BASE64_PREFIX = "data:image/png;base64,";

    public ImageUtil() {
    }

    public static BufferedImage getBufferedImage(ResourceLoader resourceLoader) throws IOException {
        Resource[] resources = ((AnnotationConfigServletWebServerApplicationContext)resourceLoader).getResources("classpath:static/img/*.png");
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(resources.length);
        InputStream in = resources[index].getInputStream();
        Throwable var5 = null;

        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(in);
        } catch (IOException e) {
            throw e;
        }finally {
            if (in != null) {
                if (var5 != null) {
                    try {
                        in.close();
                    } catch (Throwable var14) {
                        var5.addSuppressed(var14);
                    }
                } else {
                    in.close();
                }
            }
        }
        return bufferedImage;
    }

    public static BufferedImage compressImage(BufferedImage origin, int originWidth, int originHeight, int afterWidth, int afterHeight) {
        BufferedImage bi = new BufferedImage(afterWidth, afterHeight, 6);
        Graphics2D graphics = bi.createGraphics();
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHints(renderingHints);
        graphics.drawImage(origin, 0, 0, afterWidth, afterHeight, 0, 0, originWidth, originHeight, (ImageObserver)null);
        graphics.dispose();
        return bi;
    }

    public static String bufferedImageToBase64(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", stream);
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(stream.toByteArray()).replaceAll("\\r", "").replaceAll("\\n", "");
    }

    public static BufferedImage base64ToBufferedImage(String base64) throws IOException {
        if (base64.contains("data:image/png;base64,")) {
            base64 = base64.substring("data:image/png;base64,".length());
        }

        byte[] bytes1 = Base64.getDecoder().decode(base64);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
        return ImageIO.read(bais);
    }
}
