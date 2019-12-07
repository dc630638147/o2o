package com.af.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author AF
 * @Description 微信账号：微信绑定用户
 * @Date 2019/12/7 20:05
 */
@Data
public class WechatAuth {
    private Long wechatAuthId;
    private String openId;
    private Date creteTime;
    //用户信息
    private PersonInfo personInfo;
}
