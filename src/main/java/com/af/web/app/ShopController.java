package com.af.web.app;

import com.af.enums.ShopStateEnum;
import com.af.model.dto.ShopExecution;
import com.af.model.pojo.Shop;
import com.af.service.ShopService;
import com.af.utils.JSONResult;
import com.af.web.BaseController;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/11 23:08
 */
@Slf4j
@RestController
@RequestMapping("/shopApi")
 public class ShopController extends BaseController{

    @Autowired
    private DefaultKaptcha kaptcha;
    @Autowired
    private ShopService shopService;

    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletResponse servletResponse){
        byte [] kaptchaBytes = null;
        ByteArrayOutputStream byteArrOUT = new ByteArrayOutputStream();
        String kaptchaText =  kaptcha.createText();
        //TODO 暂时模拟店家ID
        Integer ownerId = 1;
        redis.set(CODE_KEY+":"+ownerId,kaptchaText,60);
        BufferedImage bufferedImage = kaptcha.createImage(kaptchaText);
        try {
            ImageIO.write(bufferedImage,"jpg",byteArrOUT);
        } catch (IOException e) {
            log.info("输出验证码图片发生异常");
            return;
        }

        //定义输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        kaptchaBytes = byteArrOUT.toByteArray();
        servletResponse.setHeader("Cache-Control","no-store");
        servletResponse.setHeader("Pragma","no-cache");
        servletResponse.setDateHeader("Expires",0);
        servletResponse.setContentType("image/jpeg");
        try {
            ServletOutputStream servletOutputStream = servletResponse.getOutputStream();
            servletOutputStream.write(kaptchaBytes);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/redis")
    public void redis(){
        redisTemplate.opsForValue().set("sssss","ssssss");
        System.out.println();
    }


    /**
     * 注册店铺
     */
    @RequestMapping("/register")
    public JSONResult registerShop(@RequestBody Shop shop, CommonsMultipartFile file){
        if(file == null){
            return JSONResult.errorMsg("请上传图片");
        }
        if(shop == null){
            return JSONResult.errorMsg("请填写店铺信息");
        }
        //给店铺设置创建者
        //TODO
        shop.setOwnerId(1);

        try{
            JSONResult jsonResult  = shopService.addShop(shop, file);
            return jsonResult;
        }catch (Exception e){
            log.info("店铺添加异常:{}",e.getMessage());
            return JSONResult.errorMsg(e.getMessage());
        }
    }

}
