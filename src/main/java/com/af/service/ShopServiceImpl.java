package com.af.service;

import com.af.enums.ShopStateEnum;
import com.af.exception.ShopOperationExcetion;
import com.af.mapper.ShopMapper;
import com.af.model.dto.ShopExecution;
import com.af.model.pojo.Shop;
import com.af.utils.ImageUtil;
import com.af.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/8 15:23
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    /**
     * 增加店铺
     * @param shop
     * @param shopImg
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOPID);
        }
        //上传信息
        try{
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //插入
            int res = shopMapper.insertSelective(shop);
            if(res < 1){
                throw new RuntimeException("店铺创建失败");
            }else{
                //图片存储
                if(shopImg == null){
                    //插入缩略图以及设置图片路径
                    addShopImg(shop,shopImg);
                    Example example = new Example(Shop.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("shopId",shop.getShopId());
                    res = shopMapper.updateByExampleSelective(shop,example);
                    if(res < 1){
                        throw new ShopOperationExcetion("更新店铺图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationExcetion("addShop error:"+e.getMessage());
        } 
        return new ShopExecution(ShopStateEnum.CHECK);
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        String dest = PathUtil.getImageBasePath();//中间路径
        //写入缩略图以及返回存储路径
        String path = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(path);
    }


}
