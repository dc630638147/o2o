package com.af.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/11 23:19
 */
@RestController
@Slf4j
public class BaseController {



    @Autowired
    public DefaultKaptcha kaptcha;
    @Autowired
    public RedisTemplate redisTemplate;

    public void getCode(String key, HttpServletResponse response){
        byte[] kaptchaBytes = null;
        ByteArrayOutputStream byteArrOUT = new ByteArrayOutputStream();
        String kaptchaText = kaptcha.createText();
        //TODO 暂时模拟店家ID
        Integer ownerId = 1;
        redisTemplate.opsForValue().set(key+":"+1, kaptchaText, 60, TimeUnit.SECONDS);
        BufferedImage bufferedImage = kaptcha.createImage(kaptchaText);
        try {
            ImageIO.write(bufferedImage, "jpg", byteArrOUT);
        } catch (IOException e) {
            log.info("输出验证码图片发生异常");
            return;
        }
        //定义输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        kaptchaBytes = byteArrOUT.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(kaptchaBytes);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
