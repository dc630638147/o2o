package com.af.model.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author AF
 * @Description 商品类
 * @Date 2019/12/7 20:35
 */
@Data
public class Product {
    private Long productId;
    //商品描述
    private String productName;
    //描述
    private String productDesc;
    //缩略图
    private String imgAddr;
    //原价
    private String normalPrice;
    //折扣价
    private String promotionPrice;
    //权重
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    //-1：不可用  0-下架   1-在前端展示系统展示
    private Integer enableStatus;
    //商品详情图片列表
    private List<ProductImg> productImgList;
    //商品类别
    private ProductCategory productCategory;
    //店铺
    private Shop shop;

}
