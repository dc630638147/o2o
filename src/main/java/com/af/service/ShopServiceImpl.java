package com.af.service;

import com.af.exception.ShopOperationExcetion;
import com.af.mapper.ShopMapper;
import com.af.model.pojo.Shop;
import com.af.utils.ImageUtil;
import com.af.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/8 15:23
 */
@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Value("${rootFilePath}")
    private String rootFilePath;//总路径
    @Value("${shopThumbnail}")
    private String shopThumbnail;//店铺缩略图路径

    /**
     * 增加店铺
     *
     * @param shop
     * @param
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONResult addShop(Shop shop, CommonsMultipartFile shopImgFile) {

        //上传信息
        try {
            //上传图片
            String path = ImageUtil.generateThumbnail(shopImgFile, rootFilePath, shopThumbnail);
            shop.setShopImg(path);
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //插入
            int res = shopMapper.insertSelective(shop);
          //  int i = 10/0;
            if (res < 1) {
                log.info("店铺创建失败,插入res{}", res);
                throw new RuntimeException("店铺创建失败");
            }
        } catch (Exception e) {
            log.info("店铺创建异常:{}", e.getMessage());
            throw new ShopOperationExcetion("店铺创建异常");
        }
        return JSONResult.ok();
    }
}
