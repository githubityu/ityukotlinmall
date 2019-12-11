package com.ityu.mall.repo


import com.ityu.mall.model.MemberProductCollection
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * 商品收藏Repository
 * Created by macro on 2018/8/2.
 */
interface MemberProductCollectionRepository : MongoRepository<MemberProductCollection, String> {
    fun findByMemberIdAndProductId(memberId: Long?, productId: Long?): MemberProductCollection?
    fun deleteByMemberIdAndProductId(memberId: Long?, productId: Long?): Int
    fun findByMemberId(memberId: Long?): List<MemberProductCollection>
}
