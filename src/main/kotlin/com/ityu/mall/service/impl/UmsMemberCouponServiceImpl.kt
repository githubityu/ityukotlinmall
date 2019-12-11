package com.ityu.mall.service.impl

import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.CartPromotionItem
import com.ityu.mall.dto.SmsCouponHistoryDetail
import com.ityu.mall.model.SmsCouponHistory
import com.ityu.mall.repo.SmsCouponHistoryRepository
import com.ityu.mall.repo.SmsCouponRepository
import com.ityu.mall.service.UmsMemberCouponService
import com.ityu.mall.service.UmsMemberService
import com.ityu.mall.util.NativeResultProcessUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import javax.persistence.criteria.Predicate

/**
 * 会员优惠券管理Service实现类
 * Created by macro on 2018/8/29.
 */
@Service
class UmsMemberCouponServiceImpl : UmsMemberCouponService {
    @Autowired
    lateinit var memberService: UmsMemberService
    @Autowired
    lateinit var couponMapper: SmsCouponRepository
    @Autowired
    lateinit var couponHistoryMapper: SmsCouponHistoryRepository

    override fun add(couponId: Long?, memId: Long?): CommonResult<*> {
        val currentMember = memberService!!.getById(memId)
        //获取优惠券信息，判断数量
        val coupon = couponMapper.findByIdOrNull(couponId!!)?: return CommonResult.failed<Any>("优惠券不存在")
        if (coupon.count!! <= 0) {
            return CommonResult.failed<Any>("优惠券已经领完了")
        }
        val now = Date()
        if (now.before(coupon.enableTime)) {
            return CommonResult.failed<Any>("优惠券还没到领取时间")
        }
        //判断用户领取的优惠券数量是否超过限制
        val count = couponHistoryMapper!!.countAllByCouponIdAndMemberId(couponId, currentMember.id!!).toLong()
        if (count >= coupon.perLimit!!) {
            return CommonResult.failed<Any>("您已经领取过该优惠券")
        }
        //生成领取优惠券历史
        val couponHistory = SmsCouponHistory()
        couponHistory.couponId = couponId
        couponHistory.couponCode = generateCouponCode(currentMember.id)
        couponHistory.createTime = now
        couponHistory.memberId = currentMember.id
        couponHistory.memberNickname = currentMember.nickname
        //主动领取
        couponHistory.getType = 1
        //未使用
        couponHistory.useStatus = 0
        couponHistoryMapper.save(couponHistory)
        //修改优惠券表的数量、领取数量
        coupon.count = coupon.count!! - 1
        coupon.receiveCount = if (coupon.receiveCount == null) 1 else coupon.receiveCount!! + 1
        couponMapper.save(coupon)
        return CommonResult.success<Any?>(null, "领取成功")
    }

    /**
     * 16位优惠码生成：时间戳后8位+4位随机数+用户id后4位
     */
    private fun generateCouponCode(memberId: Long?): String {
        val sb = StringBuilder()
        val currentTimeMillis = System.currentTimeMillis()
        val timeMillisStr = currentTimeMillis.toString()
        sb.append(timeMillisStr.substring(timeMillisStr.length - 8))
        for (i in 0..3) {
            sb.append(Random().nextInt(10))
        }
        val memberIdStr = memberId.toString()
        if (memberIdStr.length <= 4) {
            sb.append(String.format("%04d", memberId))
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length - 4))
        }
        return sb.toString()
    }

    override fun list(useStatus: Int?, memId: Long): List<SmsCouponHistory?>? {
        return couponHistoryMapper.findAll(Specification { root, y, cb ->
            val list = mutableListOf<Predicate>()
            list.add(cb.equal(root.get<Long>("memberId"), memId))
            useStatus?.let {
                list.add(cb.equal(root.get<Long>("useStatus"), useStatus))
            }
            return@Specification cb.and(* list.toTypedArray())
        })
    }

    override fun listCart(cartItemList: List<CartPromotionItem>?, type: Int?, memId: Long): List<SmsCouponHistoryDetail>? {
        val now = Date()
        val allList = couponHistoryMapper.getDetailList(memId).map {
            NativeResultProcessUtils.processResult(it, SmsCouponHistoryDetail::class.java)
        }
        //根据优惠券使用类型来判断优惠券是否可用
        //根据优惠券使用类型来判断优惠券是否可用
        val enableList = mutableListOf<SmsCouponHistoryDetail>()
        val disableList = mutableListOf<SmsCouponHistoryDetail>()
        for (couponHistoryDetail in allList) {
            val useType: Int = couponHistoryDetail.coupon?.useType ?: 0
            val minPoint: BigDecimal = couponHistoryDetail.coupon?.minPoint ?: BigDecimal.ZERO
            val endTime: Date = couponHistoryDetail.coupon?.endTime ?: Date()
            if (useType == 0) { //0->全场通用
//判断是否满足优惠起点
//计算购物车商品的总价
                val totalAmount = calcTotalAmount(cartItemList!!)
                if (now.before(endTime) && totalAmount.subtract(minPoint).toInt() >= 0) {
                    enableList.add(couponHistoryDetail)
                } else {
                    disableList.add(couponHistoryDetail)
                }
            } else if (useType == 1) { //1->指定分类
//计算指定分类商品的总价
                val productCategoryIds: MutableList<Long> = ArrayList()
                for (categoryRelation in couponHistoryDetail.categoryRelationList!!) {
                    productCategoryIds.add(categoryRelation.productCategoryId!!)
                }
                val totalAmount = calcTotalAmountByproductCategoryId(cartItemList!!, productCategoryIds)
                if (now.before(endTime) && totalAmount.toInt() > 0 && totalAmount.subtract(minPoint).toInt() >= 0) {
                    enableList.add(couponHistoryDetail)
                } else {
                    disableList.add(couponHistoryDetail)
                }
            } else if (useType == 2) { //2->指定商品
//计算指定商品的总价
                val productIds: MutableList<Long> = ArrayList()
                for (productRelation in couponHistoryDetail.productRelationList!!) {
                    productIds.add(productRelation.productId!!)
                }
                val totalAmount = calcTotalAmountByProductId(cartItemList!!, productIds)
                if (now.before(endTime) && totalAmount.toInt() > 0 && totalAmount.subtract(minPoint).toInt() >= 0) {
                    enableList.add(couponHistoryDetail)
                } else {
                    disableList.add(couponHistoryDetail)
                }
            }
        }
        return if (type == 1) {
            enableList
        } else {
            disableList
        }

    }

    private fun calcTotalAmount(cartItemList: List<CartPromotionItem>): BigDecimal {
        var total = BigDecimal("0")
        for (item in cartItemList) {
            val realPrice: BigDecimal = item.price!!.subtract(item.reduceAmount)
            total = total.add(realPrice.multiply(BigDecimal(item.quantity!!)))
        }
        return total

    }

    private fun calcTotalAmountByproductCategoryId(cartItemList: List<CartPromotionItem>, productCategoryIds: List<Long>): BigDecimal {
        var total = BigDecimal("0")
        for (item in cartItemList) {
            if (productCategoryIds.contains(item.productCategoryId)) {
                val realPrice: BigDecimal = item.price!!.subtract(item.reduceAmount)
                total = total.add(realPrice.multiply(BigDecimal(item.quantity!!)))
            }
        }
        return total
    }

    private fun calcTotalAmountByProductId(cartItemList: List<CartPromotionItem>, productIds: List<Long>): BigDecimal {
        var total = BigDecimal("0")
        for (item in cartItemList) {
            if (productIds.contains(item.productId)) {
                val realPrice: BigDecimal = item.price!!.subtract(item.reduceAmount)
                total = total.add(realPrice.multiply(BigDecimal(item.quantity!!)))
            }
        }
        return total
    }
}