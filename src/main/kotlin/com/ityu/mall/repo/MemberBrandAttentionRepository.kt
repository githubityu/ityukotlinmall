package com.ityu.mall.repo


import com.ityu.mall.model.MemberBrandAttention
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * 会员关注Repository
 * Created by macro on 2018/8/2.
 */
interface MemberBrandAttentionRepository : MongoRepository<MemberBrandAttention, String> {
    fun findByMemberIdAndBrandId(memberId: Long?, brandId: Long?): MemberBrandAttention?
    fun deleteByMemberIdAndBrandId(memberId: Long?, brandId: Long?): Int
    fun findByMemberId(memberId: Long?): List<MemberBrandAttention>
}
