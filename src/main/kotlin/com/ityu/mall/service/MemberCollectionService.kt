package com.ityu.mall.service


import com.ityu.mall.model.MemberProductCollection

/**
 * 会员收藏Service
 * Created by macro on 2018/8/2.
 */
interface MemberCollectionService {
    fun addProduct(productCollection: MemberProductCollection): Int

    fun deleteProduct(memberId: Long?, productId: Long?): Int

    fun listProduct(memberId: Long?): List<MemberProductCollection>
}
