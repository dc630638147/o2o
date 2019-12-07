package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 商品详情图片
 * @Date 2019/12/7 20:42
 */
@Data
public class ProductImg {
    private Long productImgId;
    //图片地址
    private String imgAddr;
    private String imgDesc;
    //权重，越大越靠前
    private Integer priority;
    private Date createTime;
    //商品id。属于哪个商品
    private Long productId;
}
