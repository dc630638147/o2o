package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author AF
 * @Description 用户信息
 * @Date 2019/12/7 19:19
 */
@Data
@Table(name = "tb_person_info")
public class PersonInfo {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    private String name;
    //头像地址
    @Column(name = "profile_img")
    private String profileImg;

    private String gender;

    private String phone;

    private String email;

    //类别 1-顾客  2-店家 3-超级管理员
    @Column(name = "user_type")
    private Integer userType;
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_time")
    private Date lastEditTime;

    @Column(name = "enable_status")
    private Integer enableStatus;




}
