package com.af.web.shopAdmin;

import com.af.enums.ShopStateEnum;
import com.af.exception.ShopOperationExcetion;
import com.af.model.dto.ShopExecution;
import com.af.model.pojo.PersonInfo;
import com.af.model.pojo.Shop;
import com.af.service.ShopService;
import com.af.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/9 22:43
 */
@RestController
@Slf4j
public class ShopAdminController {
    @Autowired
    private ShopService shopService;

    /**
     * 注册店铺
     */
    public JSONResult registerShop(@RequestBody Shop shop, CommonsMultipartFile file){
        if(file == null){
            return JSONResult.errorMsg("请上传图片");
        }
        if(shop == null){
            return JSONResult.errorMsg("请填写店铺信息");
        }
        //给店铺设置创建者
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1);
        shop.setOwnerId(owner.getUserId());
        //保存
        ShopExecution shopExecution;
        try{
            shopExecution = shopService.addShop(shop,file);
        }catch (Exception e){
            log.info("店铺添加异常:{}",e.getMessage());
            return JSONResult.errorMsg(e.getMessage());
        }
        if(shopExecution.getState() == ShopStateEnum.CHECK.getState()){
            //成功
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("店铺创建失败");
    }
}
