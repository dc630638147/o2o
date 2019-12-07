package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 商品类别
 * @Date 2019/12/7 20:29
 */
@Data
public class ProductCategory {
    private Long productCategoryId;
    //店铺id，是哪个店铺商品类别
    private Long shopId;
    //名称
    private String productCategoryName;
    //权重
    private Integer priority;
    private Date creaTime;
}
