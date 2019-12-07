package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 店铺信息
 * @Date 2019/12/7 20:22
 */
@Data
public class Shop {
    private Long shopId;
    //店铺名
    private String shopName;
    private String shopDesc;
    //店铺地址
    private String shopAddr;
    //手机号
    private String phone;
    //店铺图片
    private String shopImg;
    //权重
    private String priority;
    private Date createTime;
    private Date lastEditTime;
    //状态  0-审核中，-1：不可用  1-可用
    private Integer enableStatus;
    //超级管理员给店家的提醒
    private String advice;
    //区域
    private Area area;
    //店家信息
    private PersonInfo owner;
    //店铺类别
    private ShopCategory shopCategory;
}
