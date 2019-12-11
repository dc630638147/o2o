package com.af.web;

import com.af.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/11 23:19
 */
@RestController
public class BaseController {

    @Autowired
    public RedisOperator redis;

    public final String CODE_KEY = "KAPTCHA_CODE";

}
