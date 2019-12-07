package com.af.model.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author AF
 * @Description 微信账号：微信绑定用户
 * @Date 2019/12/7 20:05
 */
@Data
@Table(name = "tb_wechat_auth")
public class WechatAuth {
    @Id
    @Column(name = "wechat_auth_id")
    private Integer wechatAuthId;
    //personInfo用户id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "create_time")
    private Date createTime;
}
