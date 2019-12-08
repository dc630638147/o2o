package com.af.service;

import com.af.model.dto.ShopExecution;
import com.af.model.pojo.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/8 15:22
 */
public interface ShopService {
    public ShopExecution addShop(Shop shop,CommonsMultipartFile image);
}
