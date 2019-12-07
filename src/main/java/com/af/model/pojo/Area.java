package com.af.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author AF
 * @Description 区域实体类
 * @Date 2019/12/7 19:16
 */
@Data
@Table(name = "tb_area")
public class Area {
    //区域id
    @Id
    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "area_desc")
    private String areaDesc;

    //权重
    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;
}
