package com.af.web.app;

import com.af.model.dto.ProductVo;
import com.af.model.pojo.Product;
import com.af.service.ProductService;
import com.af.utils.JSONResult;
import com.af.web.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author AF
 * @Description 商品controller
 * @Date 2019/12/14 15:09
 */
@RequestMapping("/productApp")
@Controller
@Slf4j
public class ProductController extends BaseController {

    private final static String PRODUCT_CODE_KEY = "PRODUCT_ADD_CODE";
    @Autowired
    private ProductService productService;

    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletResponse response) {
        getCode(PRODUCT_CODE_KEY, response);
    }

    @RequestMapping("/productAddPage")
    public ModelAndView productAddPage(@RequestParam("shopId") String shopId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/product/add");
        mv.addObject("shopId", shopId);
        return mv;
    }

    @RequestMapping("/addProduct")
    public JSONResult addProduct(Product product, @RequestParam("code") String code,
                                 @RequestParam("productImg") CommonsMultipartFile productImg,
                                 @RequestParam("productDescImg") CommonsMultipartFile productDescImg) {
        //验证码校验
        Object o = redisTemplate.opsForValue().get(PRODUCT_CODE_KEY + ":" + 1);
        if (o == null || !StringUtils.equals(code, o.toString())) {
            return JSONResult.errorMsg("验证码不正确");
        }
        if (product == null || StringUtils.isBlank(product.getProductName()) || product.getPriority() == null
                || StringUtils.isBlank(product.getNormalPrice()) || product.getPromotionPrice() == null) {
            return JSONResult.errorMsg("请填写数据");
        }
        if (productImg == null) {
            return JSONResult.errorMsg("请上传缩略图");
        }

        try {
            JSONResult addproduct = productService.addproduct(product, productImg, productDescImg);
            return addproduct;
        } catch (Exception e) {
            return JSONResult.errorMsg(e.getMessage());
        }
    }

    @RequestMapping("/productPage")
    public ModelAndView productPage(@RequestParam("shopId") String shopId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/product/list");
        mv.addObject("shopId",shopId);
        return mv;
    }

    @RequestMapping("/getProduct")
    @ResponseBody
    public JSONResult getProduct(@RequestBody ProductVo vo){
        vo.setEnableStatus(0);
        return productService.getProductList(vo);
    }

    @RequestMapping("/deleteProduct/{productId}")
    @ResponseBody
    public JSONResult delete(@PathVariable Integer productId){
        return productService.delete(productId);
    }
}
