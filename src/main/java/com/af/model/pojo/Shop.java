package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author AF
 * @Description 店铺信息
 * @Date 2019/12/7 20:22
 */
@Data
@Table(name = "tb_shop")
public class Shop {
    @Id
    @Column(name = "shop_id")
    private Integer shopId;

    /**
     * 店铺创建人 personInfoId
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "area_id")
    private Integer areaId;
    //店铺类别
    @Column(name = "shop_category_id")
    private Integer shopCategoryId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_desc")
    private String shopDesc;
    //店铺地址
    @Column(name = "shop_addr")
    private String shopAddr;
    //手机号
    private String phone;

    @Column(name = "shop_img")
    private String shopImg;
    //权重
    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;
    //状态
    @Column(name = "enable_status")
    private Integer enableStatus;
    //超级管理员给店家的提醒
    private String advice;
}
