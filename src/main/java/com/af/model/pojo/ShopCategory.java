package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 店铺类别
 * @Date 2019/12/7 20:17
 */
@Data
public class ShopCategory {
    private Long shopCategoryId;
    //店铺类别名
    private String shopCategoryName;
    //描述
    private String shopCategoryDesc;
    //图片
    private String showCategoryImg;
    //权重
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    //上级店铺
    private ShopCategory parent;
}
