package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 头条
 * @Date 2019/12/7 20:12
 */
@Data
public class HeadLine {
    private Long lineId;
    //头条名
    private String lineName;
    //头条吧链接
    private String lineLink;
    //头条图片
    private String lineImg;
    //权重
    private String priority;
    //状态 0-可用  1-不可用
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;

}
