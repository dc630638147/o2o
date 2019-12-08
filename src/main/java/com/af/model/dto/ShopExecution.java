package com.af.model.dto;

import com.af.enums.ShopStateEnum;
import com.af.model.pojo.Shop;
import lombok.Data;

import java.util.List;

/**
 * @Author AF
 * @Description 商铺的构造器
 * @Date 2019/12/8 15:16
 */
@Data
public class ShopExecution {
    private int state;
    private String stateInfo;
    private int count;
    private Shop shop;
    private List<Shop> shopList;

    public ShopExecution(){

    }
    //店铺操作失败使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }
    //店铺操作成功使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum,Shop shop){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop = shop;
    }
    //店铺操作成功使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum,List<Shop> shopList){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
