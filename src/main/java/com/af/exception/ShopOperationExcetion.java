package com.af.exception;

/**
 * @Author AF
 * @Description shop操作的异常类
 * @Date 2019/12/8 17:31
 */
public class ShopOperationExcetion  extends RuntimeException {
    public ShopOperationExcetion(String msg){
        super(msg);
    }
}
