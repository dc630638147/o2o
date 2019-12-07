package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author AF
 * @Description 商品详情图片
 * @Date 2019/12/7 20:42
 */
@Data
@Table(name = "tb_product_img")
public class ProductImg {
    @Id
    @Column(name = "product_img_id")
    private Integer productImgId;
    //图片地址
    @Column(name = "img_addr")
    private String imgAddr;

    @Column(name = "img_desc")
    private String imgDesc;

    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;
    //商品id，属于哪个商品
    @Column(name = "product_id")
    private Integer productId;
}
