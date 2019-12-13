package com.af.web.app;

import com.af.model.dto.ShopVo;
import com.af.model.pojo.PersonInfo;
import com.af.model.pojo.Shop;
import com.af.service.PersonService;
import com.af.service.ShopService;
import com.af.utils.JSONResult;
import com.af.web.BaseController;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
 * @Date 2019/12/11 23:08
 */
@Slf4j
@Controller
@RequestMapping("/shopApi")
public class ShopController extends BaseController {

    @Autowired
    private DefaultKaptcha kaptcha;
    @Autowired
    private ShopService shopService;
    @Autowired
    private PersonService personService;

    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletResponse servletResponse) {
        byte[] kaptchaBytes = null;
        ByteArrayOutputStream byteArrOUT = new ByteArrayOutputStream();
        String kaptchaText = kaptcha.createText();
        //TODO 暂时模拟店家ID
        Integer ownerId = 1;
        redisTemplate.opsForValue().set(CODE_KEY + ":" + ownerId, kaptchaText, 60, TimeUnit.SECONDS);
        BufferedImage bufferedImage = kaptcha.createImage(kaptchaText);
        try {
            ImageIO.write(bufferedImage, "jpg", byteArrOUT);
        } catch (IOException e) {
            log.info("输出验证码图片发生异常");
            return;
        }

        //定义输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        kaptchaBytes = byteArrOUT.toByteArray();
        servletResponse.setHeader("Cache-Control", "no-store");
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setDateHeader("Expires", 0);
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
    public void redis() {
        redisTemplate.opsForValue().set("sssss", "ssssss");

        System.out.println(redisTemplate.opsForValue().get("sssss"));
    }


    @RequestMapping("/registerImg")
    @ResponseBody
    public JSONResult registerShopImg(@RequestParam(value = "file", required = false) CommonsMultipartFile file) {

        return new JSONResult();
    }


    /**
     * 注册店铺
     */
    @RequestMapping("/register")
    @ResponseBody
    public JSONResult registerShop(Shop shop, @RequestParam("code") String code, @RequestParam(value = "file", required = false) CommonsMultipartFile file) {
        if (shop == null) {
            return JSONResult.errorMsg("请填写店铺信息");
        }
        //校验code
        if (StringUtils.isBlank(code)) {
            return JSONResult.errorMsg("请填写验证码");
        }
        Object o = redisTemplate.opsForValue().get(CODE_KEY + ":" + 1);
        if (o == null || !StringUtils.equals(code, o.toString())) {
            return JSONResult.errorMsg("验证码不正确");
        }

        if (shop.getShopId() == null) {
            //创建
            if (file == null) {
                return JSONResult.errorMsg("请上传图片");
            }
            //给店铺设置创建者
            //TODO
            shop.setOwnerId(1);
            try {
                JSONResult jsonResult = shopService.addShop(shop, file);
                return jsonResult;
            } catch (Exception e) {
                log.info("店铺添加异常:{}", e.getMessage());
                return JSONResult.errorMsg(e.getMessage());
            }
        } else {
            //更新
            try {
                JSONResult jsonResult = shopService.updateShop(shop, file);
                return jsonResult;
            } catch (Exception e) {
                log.info("店铺更新异常:{}", e.getMessage());
                return JSONResult.errorMsg(e.getMessage());
            }
        }
    }

    /**
     * 进入编辑页
     *
     * @param shopId
     * @return
     */
    @RequestMapping("/shopEditPage")
    public ModelAndView shopEdit(@RequestParam("shopId") Integer shopId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/shop/edit");
        mv.addObject("shopId", shopId);
        return mv;
    }

    /**
     * 进入店铺增加页面
     *
     * @return
     */
    @RequestMapping("/addShopPage")
    public ModelAndView addShop(@RequestParam("ownerId") Integer ownerId, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/shop/index");
        mv.addObject("ownerId", ownerId);
        return mv;
    }

    /**
     * 根据商铺id查询
     *
     * @param shopId
     * @return
     */
    @ResponseBody
    @RequestMapping("/allShopInfo/{shopId}")
    public JSONResult allShopInfo(@PathVariable Integer shopId) {
        if (shopId == null) {
            return JSONResult.errorMsg("shopId不能为空");
        }
        //根据shopId查询shop信息
        return shopService.allShopInfo(shopId);
    }

    @RequestMapping("/shopListPage")
    public ModelAndView shopList(@RequestParam("ownerId") Integer ownerId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/shop/list");
        //设置ownerId
        mv.addObject("ownerId", ownerId);
        //查询店铺信息
        PersonInfo pi = personService.getById(ownerId);
        mv.addObject("pi", pi);
        return mv;
    }

    @RequestMapping("/getShopList")
    @ResponseBody
    public JSONResult getShopList(@RequestBody ShopVo shopVo) {
        if (shopVo == null || shopVo.getOwnerId() == null) {
            return JSONResult.errorMsg("用户不能为空");
        }
        if (shopVo.getPageNum() == null || shopVo.getPageNum() < 1) {
            shopVo.setPageNum(1);
        }
        if (shopVo.getPageSize() == null || shopVo.getPageSize() < 1) {
            shopVo.setPageSize(15);
        }
        PageInfo<Shop> shopPage = shopService.getShopPage(shopVo);
        return new JSONResult(shopPage);
    }
}
