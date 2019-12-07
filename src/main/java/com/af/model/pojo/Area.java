package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 区域实体类
 * @Date 2019/12/7 19:16
 */
@Data
public class Area {
    //区域ID
    private Integer areaId;
    //区域名
    private String areaName;
    //权重
    private Integer priority;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;
}
