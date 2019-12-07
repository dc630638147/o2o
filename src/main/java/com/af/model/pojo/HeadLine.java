package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author AF
 * @Description 头条
 * @Date 2019/12/7 20:12
 */
@Data
@Table(name = "tb_head_line")
public class HeadLine {
    @Id
    @Column(name = "line_id")
    private Integer lineId;
    //头条名
    @Column(name = "line_name")
    private String lineName;
    //头条链接
    @Column(name = "line_link")
    private String lineLink;
    //头条图片
    @Column(name = "line_img")
    private String lineImg;
    //权重
    private Integer priority;

    //状态 0-可用  1-不可用
    @Column(name = "enable_status")
    private Integer enableStatus;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;

}
