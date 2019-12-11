package com.ityu.mall.service


import com.ityu.mall.dto.CartProduct
import com.ityu.mall.dto.CartPromotionItem
import com.ityu.mall.model.OmsCartItem
import org.springframework.transaction.annotation.Transactional

/**
 * 购物车管理Service
 * Created by macro on 2018/8/2.
 */
interface OmsCartItemService {
    /**
     * 查询购物车中是否包含该商品，有增加数量，无添加到购物车
     */
    @Transactional
    fun add(cartItem: OmsCartItem,memberId: Long?): Int

    /**
     * 根据会员编号获取购物车列表
     */
    fun list(memberId: Long?): List<OmsCartItem>

    /**
     * 获取包含促销活动信息的购物车列表
     */
    fun listPromotion(memberId: Long?): List<CartPromotionItem>

    /**
     * 修改某个购物车商品的数量
     */
    fun updateQuantity(id: Long?, memberId: Long?, quantity: Int?): Int

    /**
     * 批量删除购物车中的商品
     */
    fun delete(memberId: Long?, ids: List<Long>): Int

    /**
     * 获取购物车中用于选择商品规格的商品信息
     */
    fun getCartProduct(productId: Long?): CartProduct

    /**
     * 修改购物车中商品的规格
     */
    @Transactional
    fun updateAttr(cartItem: OmsCartItem): Int

    /**
     * 清空购物车
     */
    fun clear(memberId: Long?): Int
}
