package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author AF
 * @Description 商品类别
 * @Date 2019/12/7 20:29
 */
@Data
@Table(name = "tb_product_category")
public class ProductCategory {
    @Id
    @Column(name = "product_category_id")
    private Integer productCategoryId;

    @Column(name = "product_category_name")
    private String productCategoryName;

    @Column(name = "product_category_desc")
    private String productCategoryDesc;

    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;
    //店铺id，是哪个店铺商品类别
    @Column(name = "shop_id")
    private Integer shopId;
}
