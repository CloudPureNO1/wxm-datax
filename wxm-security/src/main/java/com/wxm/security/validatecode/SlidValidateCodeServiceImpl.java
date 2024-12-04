package com.wxm.security.validatecode;

import com.wxm.util.my.MyUUIDUtil;
import com.wxm.util.my.code.SM3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-05-21 14:24:45
 */
@Slf4j
@Service
public class SlidValidateCodeServiceImpl implements ValidateCodeService{
    private final RedisTemplate<String, String> redisTemplate;
    private final ResourceLoader resourceLoader;
    public SlidValidateCodeServiceImpl(RedisTemplate<String, String> redisTemplate, ResourceLoader resourceLoader) {
        this.redisTemplate = redisTemplate;
        this.resourceLoader = resourceLoader;
    }


    @Override
    public String type() {
        return ValidateCodeType.SLID.type();
    }

    @Override
    public ValidateCode build(HttpServletRequest request) {
        if (this.redisTemplate == null) {
            throw new SecurityException("滑块验证码需要启用redis！");
        } else {
            try {
                BufferedImage bi = ImageUtil.getBufferedImage(this.resourceLoader);
                int width = bi.getWidth();
                int height = bi.getHeight();
                Coordinate coordinate = SliderUtil.getRandomCoordinate(width, height);
                BufferedImage cropBufferedImage = SliderUtil.cropImage(bi, coordinate.getX(), coordinate.getY());
                Shape shape = SliderUtil.createShape();
                BufferedImage frontBufferedImage = SliderUtil.createSideBufferedImage();
                BufferedImage backBufferedImage = SliderUtil.createSideBufferedImage();
                SliderUtil.fillBufferedImage(frontBufferedImage, backBufferedImage, cropBufferedImage, shape);
                frontBufferedImage = SliderUtil.dealLightAndShadow(frontBufferedImage, shape);
                frontBufferedImage = SliderUtil.processFrontImage(frontBufferedImage, height, coordinate.getY());
                backBufferedImage = SliderUtil.processBackImage(bi, backBufferedImage, width, height, coordinate.getX(), coordinate.getY());
                String front = ImageUtil.bufferedImageToBase64(frontBufferedImage);
                String back = ImageUtil.bufferedImageToBase64(backBufferedImage);
                String token = MyUUIDUtil.uuid("wxm");
                String tokenVal = (double)coordinate.getX() * 1.0 / 2.0 + "_" + coordinate.getY();
                this.redisTemplate.opsForValue().set("slider_token_" + token, tokenVal, 1L, TimeUnit.MINUTES);
                ValidateCode validateCodeDTO = new ValidateCode();
                validateCodeDTO.setBack(back);
                validateCodeDTO.setFront(front);
                validateCodeDTO.setToken(token);
                return validateCodeDTO;
            } catch (Exception e) {
                log.error("获取验证码失败！", e);
                throw new SecurityException("获取验证码失败！");
            }
        }
    }

    @Override
    public void check(ValidateCode dto, HttpServletRequest request) {
        try{
            String val = (String)this.redisTemplate.opsForValue().get("slider_token_" + dto.getToken());
            if (val == null) {
                throw new SecurityException("验证码已失效！");
            } else {
                String[] vale = val.split("_");
                Double x = Double.parseDouble(vale[0]);
                Integer y = Integer.parseInt(vale[1]);
                if (Math.abs(Double.parseDouble(dto.getCheckCode()) - x) < 4.0) {
                    String validateCode = SM3Util.encode(dto.getToken() + dto.getCheckCode());
                    request.getSession().setAttribute("validateCode", validateCode);
                } else {
                    ValidateCodeService.errorCountAdd(request);
                    this.redisTemplate.delete("slider_token_" + dto.getToken());
                    throw new SecurityException("验证码无效！");
                }
            }
        }catch (Exception e){
            throw new SecurityException("验证码异常！");
        }
    }
}
