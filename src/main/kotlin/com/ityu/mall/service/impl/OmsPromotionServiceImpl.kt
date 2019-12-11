package com.ityu.mall.service.impl


import com.ityu.mall.dto.CartPromotionItem
import com.ityu.mall.dto.PromotionProduct
import com.ityu.mall.model.OmsCartItem
import com.ityu.mall.model.PmsProductFullReduction
import com.ityu.mall.model.PmsProductLadder
import com.ityu.mall.model.PmsSkuStock
import com.ityu.mall.repo.PmsProductRepository
import com.ityu.mall.service.OmsPromotionService
import com.ityu.mall.util.NativeResultProcessUtils
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * Created by macro on 2018/8/27.
 * 促销管理Service实现类
 */
@Service
class OmsPromotionServiceImpl : OmsPromotionService {
    @Autowired
    lateinit var portalProductDao: PmsProductRepository


    override fun calcCartPromotion(cartItemList: List<OmsCartItem>): List<CartPromotionItem> {
        //1.先根据productId对CartItem进行分组，以spu为单位进行计算优惠
        val productCartMap = groupCartItemBySpu(cartItemList)
        //2.查询所有商品的优惠相关信息
        val promotionProductList = getPromotionProductList(cartItemList)
        //3.根据商品促销类型计算商品促销优惠价格
        val cartPromotionItemList = ArrayList<CartPromotionItem>()
        productCartMap.map {
            val productId = it.key
            val promotionProduct = getPromotionProductById(productId, promotionProductList)
            val itemList = it.value
            val promotionType = promotionProduct!!.promotionType
            when (promotionType) {
                1 -> {
                    for (item in itemList) {
                        val cartPromotionItem = CartPromotionItem()
                        BeanUtils.copyProperties(item, cartPromotionItem)
                        cartPromotionItem.promotionMessage = ("单品促销")
                        //商品原价-促销价
                        val skuStock = getOriginalPrice(promotionProduct, item.productSkuId)
                        val originalPrice = skuStock!!.price
                        cartPromotionItem.reduceAmount = (originalPrice!!.subtract(skuStock.promotionPrice))
                        cartPromotionItem.realStock = (skuStock.stock!! - skuStock.lockStock!!)
                        cartPromotionItem.integration = (promotionProduct.giftPoint)
                        cartPromotionItem.growth = (promotionProduct.giftGrowth)
                        cartPromotionItemList.add(cartPromotionItem)
                    }
                }
                3 -> {
                    //打折优惠
                    val count = getCartItemCount(itemList)
                    val ladder = getProductLadder(count, promotionProduct.productLadderList!!)
                    if (ladder != null) {
                        for (item in itemList) {
                            val cartPromotionItem = CartPromotionItem()
                            BeanUtils.copyProperties(item, cartPromotionItem)
                            val message = getLadderPromotionMessage(ladder!!)
                            cartPromotionItem.promotionMessage = (message)
                            //商品原价-折扣*商品原价
                            val skuStock = getOriginalPrice(promotionProduct, item.productSkuId)
                            val originalPrice = skuStock!!.price
                            val reduceAmount = originalPrice!!.subtract(ladder!!.discount!!.multiply(originalPrice))
                            cartPromotionItem.reduceAmount = (reduceAmount)
                            cartPromotionItem.realStock = (skuStock.stock!! - skuStock.lockStock!!)
                            cartPromotionItem.integration = (promotionProduct.giftPoint)
                            cartPromotionItem.growth = (promotionProduct.giftGrowth)
                            cartPromotionItemList.add(cartPromotionItem)
                        }
                    } else {
                        handleNoReduce(cartPromotionItemList, itemList, promotionProduct)
                    }
                }
                4 -> {
                    val totalAmount = getCartItemAmount(itemList, promotionProductList)
                    val fullReduction = getProductFullReduction(totalAmount, promotionProduct.productFullReductionList!!)
                    if (fullReduction != null) {
                        for (item in itemList) {
                            val cartPromotionItem = CartPromotionItem()
                            BeanUtils.copyProperties(item, cartPromotionItem)
                            val message = getFullReductionPromotionMessage(fullReduction!!)
                            cartPromotionItem.promotionMessage = (message)
                            //(商品原价/总价)*满减金额
                            val skuStock = getOriginalPrice(promotionProduct, item.productSkuId)
                            val originalPrice = skuStock!!.price
                            val reduceAmount = originalPrice!!.divide(totalAmount, RoundingMode.HALF_EVEN).multiply(fullReduction!!.reducePrice)
                            cartPromotionItem.reduceAmount = (reduceAmount)
                            cartPromotionItem.realStock = (skuStock.stock!! - skuStock.lockStock!!)
                            cartPromotionItem.integration = (promotionProduct.giftPoint)
                            cartPromotionItem.growth = (promotionProduct.giftGrowth)
                            cartPromotionItemList.add(cartPromotionItem)
                        }
                    } else {
                        handleNoReduce(cartPromotionItemList, itemList, promotionProduct)
                    }
                }
                else -> {
                    handleNoReduce(cartPromotionItemList, itemList, promotionProduct)
                }
            }
            null
        }

        return cartPromotionItemList
    }


    /**
     * 以spu为单位对购物车中商品进行分组
     */
    private fun groupCartItemBySpu(cartItemList: List<OmsCartItem>): Map<Long, List<OmsCartItem>> {
        val productCartMap = TreeMap<Long, MutableList<OmsCartItem>>()
        for (cartItem in cartItemList) {
            var productCartItemList = productCartMap[cartItem.productId!!]
            if (productCartItemList == null) {
                productCartItemList = ArrayList()
                productCartItemList.add(cartItem)
                productCartMap[cartItem.productId!!] = productCartItemList
            } else {
                productCartItemList.add(cartItem)
            }
        }
        return productCartMap
    }

    /**
     * 获取打折优惠的促销信息
     */
    private fun getLadderPromotionMessage(ladder: PmsProductLadder): String {
        val sb = StringBuilder()
        sb.append("打折优惠：")
        sb.append("满")
        sb.append(ladder.count)
        sb.append("件，")
        sb.append("打")
        sb.append(ladder.discount!!.multiply(BigDecimal(10)))
        sb.append("折")
        return sb.toString()
    }


    /**
     * 获取满减促销消息
     */
    private fun getFullReductionPromotionMessage(fullReduction: PmsProductFullReduction): String {
        val sb = StringBuilder()
        sb.append("满减优惠：")
        sb.append("满")
        sb.append(fullReduction.fullPrice)
        sb.append("元，")
        sb.append("减")
        sb.append(fullReduction.reducePrice)
        sb.append("元")
        return sb.toString()
    }

    /**
     * 对没满足优惠条件的商品进行处理
     */
    private fun handleNoReduce(cartPromotionItemList: MutableList<CartPromotionItem>, itemList: List<OmsCartItem>, promotionProduct: PromotionProduct) {
        for (item in itemList) {
            val cartPromotionItem = CartPromotionItem()
            BeanUtils.copyProperties(item, cartPromotionItem)
            cartPromotionItem.promotionMessage = ("无优惠")
            cartPromotionItem.reduceAmount = (BigDecimal(0))
            val skuStock = getOriginalPrice(promotionProduct, item.productSkuId)
            if (skuStock != null) {
                cartPromotionItem.realStock = (skuStock!!.stock!! - skuStock!!.lockStock!!)
            }
            cartPromotionItem.integration = (promotionProduct.giftPoint)
            cartPromotionItem.growth = (promotionProduct.giftGrowth)
            cartPromotionItemList.add(cartPromotionItem)
        }
    }

    /**
     * 获取商品的原价
     */
    private fun getOriginalPrice(promotionProduct: PromotionProduct, productSkuId: Long?): PmsSkuStock? {
        if(promotionProduct.skuStockList!=null){
            for (skuStock in promotionProduct.skuStockList!!) {
                if (productSkuId == skuStock.id) {
                    return skuStock
                }
            }
        }
        return null
    }

    /**
     * 根据商品id获取商品的促销信息
     */
    private fun getPromotionProductById(productId: Long?, promotionProductList: List<PromotionProduct>): PromotionProduct? {
        for (promotionProduct in promotionProductList) {
            if (productId == promotionProduct.id) {
                return promotionProduct
            }
        }
        return null
    }

    private fun getPromotionProductList(cartItemList: List<OmsCartItem>): List<PromotionProduct> {
        val productIdList = cartItemList.map {
            it.productId!!
        }
        return portalProductDao.getPromotionProductList(productIdList).map {
            NativeResultProcessUtils.processResult(it, PromotionProduct::class.java)
        }
    }

    /**
     * 获取购物车中指定商品的数量
     */
    private fun getCartItemCount(itemList: List<OmsCartItem>): Int {
        var count = 0
        for (item in itemList) {
            count += item.quantity!!
        }
        return count
    }

    /**
     * 获取购物车中指定商品的总价
     */
    private fun getCartItemAmount(itemList: List<OmsCartItem>, promotionProductList: List<PromotionProduct>): BigDecimal {
        var amount = BigDecimal(0)
        for (item in itemList) {
            //计算出商品原价
            val promotionProduct = getPromotionProductById(item.productId, promotionProductList)
            val skuStock = getOriginalPrice(promotionProduct!!, item.productSkuId)
            if(skuStock!=null){
                amount = amount.add(skuStock!!.price!!.multiply(BigDecimal(item.quantity!!)))
            }
        }
        return amount
    }


    /**
     * 根据购买商品数量获取满足条件的打折优惠策略
     */
    private fun getProductLadder(count: Int, productLadderList: List<PmsProductLadder>): PmsProductLadder? {
        //按数量从大到小排序
        productLadderList.sortedWith(Comparator { o1, o2 -> o2.count!! - o1.count!! })
        for (productLadder in productLadderList) {
            if (count >= productLadder.count!!) {
                return productLadder
            }
        }
        return null
    }

    private fun getProductFullReduction(totalAmount: BigDecimal, fullReductionList: List<PmsProductFullReduction>): PmsProductFullReduction? {
        //按条件从高到低排序
        fullReductionList.sortedWith(Comparator { o1, o2 -> o2.fullPrice!!.subtract(o1.fullPrice).toInt() })
        for (fullReduction in fullReductionList) {
            if (totalAmount.subtract(fullReduction.fullPrice).toInt() >= 0) {
                return fullReduction
            }
        }
        return null
    }


}
