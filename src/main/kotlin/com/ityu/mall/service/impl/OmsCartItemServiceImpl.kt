package com.ityu.mall.service.impl


import com.ityu.mall.dto.CartProduct
import com.ityu.mall.dto.CartPromotionItem
import com.ityu.mall.model.OmsCartItem
import com.ityu.mall.repo.OmsCartItemRepository
import com.ityu.mall.repo.PmsProductRepository
import com.ityu.mall.service.OmsCartItemService
import com.ityu.mall.service.OmsPromotionService
import com.ityu.mall.service.UmsMemberService
import com.ityu.mall.util.NativeResultProcessUtils.processResult

import com.ityu.mall.util.copyPropertiesIgnoreNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

import javax.persistence.criteria.Predicate


/**
 * 购物车管理Service实现类
 * Created by macro on 2018/8/2.
 */
@Service
class OmsCartItemServiceImpl : OmsCartItemService {
    @Autowired
    lateinit var cartItemMapper: OmsCartItemRepository
    @Autowired
    lateinit var productDao: PmsProductRepository
    @Autowired
    lateinit var promotionService: OmsPromotionService


    override fun add(cartItem: OmsCartItem, memberId: Long?): Int {
        cartItem.memberId = memberId
        cartItem.deleteStatus = 0
        val existC = getCartItem(cartItem)
        if (existC == null) {
            cartItem.createDate = Date()
            cartItemMapper.save(cartItem)
        } else {
            copyPropertiesIgnoreNull(cartItem, existC)
            existC.modifyDate = Date()
            existC.quantity = existC.quantity!! + cartItem.quantity!!
            cartItemMapper.save(existC)
        }
        return 1
    }

    override fun list(memberId: Long?): List<OmsCartItem> {
        return cartItemMapper.findAllByMemberIdAndDeleteStatus(memberId!!, 0)
    }

    override fun listPromotion(memberId: Long?): List<CartPromotionItem> {
        val cartItemList = list(memberId)
        return if (cartItemList.isNotEmpty()) {
            promotionService.calcCartPromotion(cartItemList)
        } else
            mutableListOf()
    }

    override fun updateQuantity(id: Long?, memberId: Long?, quantity: Int?): Int {
        val data = cartItemMapper.findByDeleteStatusAndIdAndMemberId(
                0, id!!, memberId!!)
        data.quantity = quantity
        cartItemMapper.save(data)
        return 1
    }

    override fun delete(memberId: Long?, ids: List<Long>): Int {
        val datas = cartItemMapper.findAllByMemberIdAndIdInAndDeleteStatus(
                memberId!!, ids, 0).map {
            it.deleteStatus = 1
            it
        }
        cartItemMapper.saveAll(datas)
        return datas.size
    }

    override fun getCartProduct(productId: Long?): CartProduct {
        return processResult(productDao.getCartProduct(productId!!), CartProduct::class.java)
    }

    override fun updateAttr(cartItem: OmsCartItem): Int {
        val findById = cartItemMapper.findById(cartItem.id!!).get()
        findById.modifyDate = Date()
        findById.deleteStatus = 1
        cartItemMapper.save(findById)
        cartItem.id = null
        add(cartItem, cartItem.memberId)
        return 1
    }

    override fun clear(memberId: Long?): Int {
        val list = list(memberId).map {
            it.deleteStatus = 1
            it
        }
        return list.size
    }

    /**
     * 根据会员id,商品id和规格获取购物车中商品
     */
    private fun getCartItem(cartItem: OmsCartItem): OmsCartItem? {
        val cartItemList = cartItemMapper.findAll(Specification { root, query, cb ->
            val list = mutableListOf<Predicate>()
            list.add(cb.equal(root.get<Long>("productId"), cartItem.productId!!))
            list.add(cb.equal(root.get<Long>("memberId"), cartItem.memberId!!))
            list.add(cb.equal(root.get<Int>("deleteStatus"), 0))

            if (!cartItem.sp1.isNullOrEmpty()) {
                list.add(cb.equal(root.get<String>("sp1"), cartItem.sp1))
            }
            if (!cartItem.sp2.isNullOrEmpty()) {
                list.add(cb.equal(root.get<String>("sp2"), cartItem.sp2))
            }
            if (!cartItem.sp3.isNullOrEmpty()) {
                list.add(cb.equal(root.get<String>("sp3"), cartItem.sp3))
            }
            return@Specification cb.and(*list.toTypedArray())
        })
        return if (cartItemList.isNotEmpty()) {
            cartItemList[0]
        } else null
    }

}
