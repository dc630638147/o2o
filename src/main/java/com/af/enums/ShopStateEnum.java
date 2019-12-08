package com.af.enums;

import lombok.Getter;

/**
 * @Author AF
 * @Description 商铺操作响应枚举
 * @Date 2019/12/8 15:10
 */
@Getter
public enum ShopStateEnum {
    CHECK(0,"审核中"),
    OFFLINE(-1,"下线"),
    SUCCESS(1,"操作成功"),
    PAA(2,"通过认证"),
    INNER_ERROR(1001,"内部系统错误"),
    NULL_SHOPID(-1002,"ShopId为空");

    private int state;
    private String stateInfo;

    private ShopStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum shopStateEnum : values()){
            if(shopStateEnum.state == state){
                return shopStateEnum;
            }
        }
        return null;
    }
}
