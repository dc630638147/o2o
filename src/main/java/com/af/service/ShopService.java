package com.af.service;

import com.af.model.pojo.Shop;
import com.af.utils.JSONResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * @Author AF
 * @Description
 * @Date 2019/12/8 15:22
 */
public interface ShopService {
    public JSONResult addShop(Shop shop, CommonsMultipartFile shopImgFile);

    public JSONResult allShopInfo(Integer shopId);

    public JSONResult updateShop(Shop shop,CommonsMultipartFile file);
}
