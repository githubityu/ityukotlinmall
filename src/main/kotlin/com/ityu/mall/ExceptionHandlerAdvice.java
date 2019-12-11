package com.ityu.mall;

import com.ityu.mall.common.CommonResult;
import com.ityu.mall.exception.BaseApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = BaseApiException.class)
    public CommonResult<String> doBaseApiException(BaseApiException e) {
        return CommonResult.failed(e.getMessage());
    }

}
