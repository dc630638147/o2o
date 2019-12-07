package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 用户信息
 * @Date 2019/12/7 19:19
 */
@Data
public class PersonInfo {
    private Long userId;
    private String name;
    //头像地址
    private String profileImg;
    private String email;
    //性别
    private String gender;
    //用户状态
    private Integer enableStatus;
    //类别 1-顾客  2-店家 3-超级管理员
    private Integer userType;
    private Date createTime;
    private Date lastEditTime;




}
