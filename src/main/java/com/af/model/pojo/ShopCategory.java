package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author AF
 * @Description 店铺类别
 * @Date 2019/12/7 20:17
 */
@Data
@Table(name = "tb_shop_category")
public class ShopCategory {
    @Id
    @Column(name = "shop_category_id")
    private Integer shopCategoryId;

    @Column(name = "shop_category_name")
    private String shopCategoryName;

    @Column(name = "shop_category_desc")
    private String shopCategoryDesc;

    @Column(name = "shop_category_img")
    private String shopCategoryImg;

    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;
    //上级店铺类别
    @Column(name = "parent_id")
    private Integer parentId;
}
