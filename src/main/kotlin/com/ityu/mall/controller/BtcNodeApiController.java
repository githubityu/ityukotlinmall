package com.ityu.mall.controller;


import com.ityu.mall.annotation.Limit;
import com.ityu.mall.annotation.Log;
import com.ityu.mall.btc.domain.BtcAccount;
import com.ityu.mall.btc.service.BtcNodeApiService;
import com.ityu.mall.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author lihe
 */
@Api(value = "BTC API")
@RequestMapping("/v1/btc")
@RestController
public class BtcNodeApiController {


    private final BtcNodeApiService btcNodeApiService;

    public BtcNodeApiController(BtcNodeApiService btcNodeApiService) {
        this.btcNodeApiService = btcNodeApiService;
    }

    @ApiOperation(value = "创建钱包")
    @PostMapping("/newAccount")
    public CommonResult createAccount() {
        BtcAccount account = btcNodeApiService.createAccount();
        return CommonResult.success(account);
    }

    @Log
    @Limit(key = "fee", period = 20, count = 5, name = "获取手续费", prefix = "limit")
    @ApiOperation(value = "获取手续费")
    @PostMapping("/fee")
    public CommonResult fee() {
        double fee = btcNodeApiService.fee();
        return CommonResult.success(fee);
    }
    @ApiOperation(value = "导入账号" )
    @PostMapping(value = "/importAccount",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "privateKey", value = "aa", paramType = "form")
            }
    )
    public CommonResult importAccount(@NotEmpty String privateKey) {
        BtcAccount account = btcNodeApiService.importAccount(privateKey);
        return CommonResult.success(account);
    }


    @ApiOperation("BTC转账")
    @PostMapping("/transfer")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "privateKey", value = "私钥", paramType = "form"),
            @ApiImplicitParam(name = "toAddress", value = "接收方地址", paramType = "form"),
            @ApiImplicitParam(name = "amount", value = "数量", example = "0.2", paramType = "form")
    })
    public CommonResult transfer(@NotEmpty String privateKey,
                                 @NotEmpty String toAddress,
                                 @NotNull Double amount) {
        btcNodeApiService.transfer(privateKey, toAddress, amount);
        return CommonResult.success(null);
    }

}
