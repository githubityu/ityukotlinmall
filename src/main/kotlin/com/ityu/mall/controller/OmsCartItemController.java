package com.ityu.mall.controller;


import com.ityu.mall.common.CommonResult;
import com.ityu.mall.dto.CartProduct;
import com.ityu.mall.dto.CartPromotionItem;
import com.ityu.mall.model.OmsCartItem;
import com.ityu.mall.service.OmsCartItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车管理Controller
 * Created by macro on 2018/8/2.
 */
@RestController
@Api(tags = "OmsCartItemController", description = "购物车管理")
@RequestMapping("/cart")
public class OmsCartItemController {
    @Autowired
    private OmsCartItemService cartItemService;
 

    @ApiOperation("添加商品到购物车")
    @PostMapping(value = "/{memberId}/add")
    public CommonResult add(@PathVariable Long memberId,@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.add(cartItem,memberId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取某个会员的购物车列表")
    @GetMapping(value = "/{memberId}/list")
    public CommonResult<List<OmsCartItem>> list(@PathVariable Long memberId) {
        List<OmsCartItem> cartItemList = cartItemService.list(memberId);
        return CommonResult.success(cartItemList);
    }

    @ApiOperation("获取某个会员的购物车列表,包括促销信息")
    @GetMapping(value = "/{memberId}/list/promotion")
    public CommonResult<List<CartPromotionItem>> listPromotion(@PathVariable Long memberId) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberId);
        return CommonResult.success(cartPromotionItemList);
    }

    @ApiOperation("修改购物车中某个商品的数量")
    @GetMapping(value = "/{memberId}/update/quantity")
    public CommonResult updateQuantity(@PathVariable Long memberId,@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        int count = cartItemService.updateQuantity(id, memberId, quantity);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取购物车中某个商品的规格,用于重选规格")
    @GetMapping(value = "/getProduct/{productId}")
    public CommonResult<CartProduct> getCartProduct(@PathVariable Long productId) {
        CartProduct cartProduct = cartItemService.getCartProduct(productId);
        return CommonResult.success(cartProduct);
    }

    @ApiOperation("修改购物车中商品的规格")
    @PostMapping(value = "/{memberId}/update/attr")
    public CommonResult updateAttr(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.updateAttr(cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除购物车中的某个商品")
    @PostMapping(value = "/{memberId}/delete")
    public CommonResult delete(@PathVariable Long memberId,@RequestParam("ids") List<Long> ids) {
        int count = cartItemService.delete(memberId, ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("清空购物车")
    @PostMapping(value = "/{memberId}/clear")
    public CommonResult clear(@PathVariable Long memberId) {
        int count = cartItemService.clear(memberId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
