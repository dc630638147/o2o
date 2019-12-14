package com.af.web.app;

import com.af.model.dto.ShopCategoryVo;
import com.af.model.pojo.ShopCategory;
import com.af.service.ShopCategoryService;
import com.af.utils.JSONResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author AF
 * @Description 商铺分类，提供给app的接口
 * @Date 2019/12/11 0:58
 */
@RestController
@RequestMapping("/app/shopCategory")
public class ShopCategoryAppController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @GetMapping("/getAll")
    public JSONResult getAllCategory() {
        List<ShopCategory> allCategoryList = shopCategoryService.getAllCategory(null);
        if (CollectionUtils.isEmpty(allCategoryList)) {
            return JSONResult.errorMsg("数据不存在");
        }
        return JSONResult.ok(allCategoryList);
    }

    @RequestMapping("/getShopCategory")
    public JSONResult getShopCategory(@RequestBody ShopCategoryVo shopCategoryVo) {
        List<ShopCategory> allCategory = shopCategoryService.getAllCategory(shopCategoryVo);
        return JSONResult.ok(allCategory);
    }

}
