package com.ityu.mall.controller;


import com.ityu.mall.common.CommonResult;
import com.ityu.mall.dto.CartPromotionItem;
import com.ityu.mall.dto.SmsCouponHistoryDetail;
import com.ityu.mall.model.SmsCouponHistory;
import com.ityu.mall.service.OmsCartItemService;
import com.ityu.mall.service.UmsMemberCouponService;
import com.ityu.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户优惠券管理Controller
 * Created by macro on 2018/8/29.
 */
@RestController
@Api(tags = "UmsMemberCouponController", description = "用户优惠券管理")
@RequestMapping("/member/coupon")
public class UmsMemberCouponController {
    @Autowired
    private UmsMemberCouponService memberCouponService;
    @Autowired
    private OmsCartItemService cartItemService;

    @ApiOperation("领取指定优惠券")
    @PostMapping(value = "/add/{couponId}/{memId}")
    @ResponseBody
    public CommonResult add(@PathVariable Long couponId, @PathVariable Long memId) {
        return memberCouponService.add(couponId, memId);
    }

    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
                    allowableValues = "0,1,2", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "memId", value = "0", paramType = "path", dataType = "integer")
    })
    @GetMapping(value = "/list/{memId}")
    @ResponseBody
    public CommonResult<List<SmsCouponHistory>> list(@PathVariable Long memId, @RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistory> couponHistoryList = memberCouponService.list(useStatus, memId);
        return CommonResult.success(couponHistoryList);
    }
    @ApiOperation("获取登录会员购物车的相关优惠券")
    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
            defaultValue = "1", allowableValues = "0,1", paramType = "path", dataType = "integer")
    @GetMapping(value = "/list/cart/{type}/{memId}")
    @ResponseBody
    public CommonResult<List<SmsCouponHistoryDetail>> listCart(@PathVariable String type, @PathVariable Long memId) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memId);
        List<SmsCouponHistoryDetail> couponHistoryList = memberCouponService.listCart(cartPromotionItemList, Integer.parseInt(type), memId);
        return CommonResult.success(couponHistoryList);
    }
}
