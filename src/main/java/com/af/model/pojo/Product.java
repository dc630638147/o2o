package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @Author AF
 * @Description 商品类
 * @Date 2019/12/7 20:35
 */
@Data
@Table(name = "tb_product")
public class Product {
    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;
    //缩略图
    @Column(name = "img_addr")
    private String imgAddr;
    //原价
    @Column(name = "normal_price")
    private String normalPrice;
    //折扣价
    @Column(name = "promotion_price")
    private String promotionPrice;
    //权重
    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;
    //状态
    @Column(name = "enable_status")
    private Integer enableStatus;

    private Integer point;
    //类别id
    @Column(name = "product_category_id")
    private Integer productCategoryId;
    //店铺id
    @Column(name = "shop_id")
    private Integer shopId;
    //详情图 有多个

}
