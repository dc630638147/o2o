package com.af.web.app;

import com.af.model.dto.ProductCategoryVo;
import com.af.model.pojo.ProductCategory;
import com.af.service.ProductCategoryService;
import com.af.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/14 12:12
 */
@RequestMapping("/productCategoryApi")
@Controller
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/getProductCatetgroyList")
    @ResponseBody
    public JSONResult getProductCatetgroy(@RequestBody ProductCategoryVo vo) {
        List<ProductCategory> productCategoryList = productCategoryService.getProductCategory(vo);
        return JSONResult.ok(productCategoryList);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult deleteProductCategory(@RequestParam("productCategoryId") Integer productCategoryId) {
        return productCategoryService.delete(productCategoryId);
    }

    @RequestMapping("/batchAdd")
    @ResponseBody
    public JSONResult batchAdd(@RequestBody List<ProductCategory> list) {
        try {
            JSONResult jsonResult = productCategoryService.batchAdd(list);
            return jsonResult;
        } catch (Exception e) {
            return JSONResult.errorMsg(e.getMessage());
        }
    }
}
