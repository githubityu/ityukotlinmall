package com.ityu.mall.exception;

/**
 * 异常基础类
 *
 * @author lihe
 */
public class BaseApiException extends RuntimeException {


    public BaseApiException(String message) {
        super(message);
    }
}
