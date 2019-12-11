package com.ityu.mall.service


import com.ityu.mall.model.MemberBrandAttention

/**
 * 会员关注Service
 * Created by macro on 2018/8/2.
 */
interface MemberAttentionService {
    /**
     * 添加关注
     */
    fun add(memberBrandAttention: MemberBrandAttention): Int

    /**
     * 取消关注
     */
    fun delete(memberId: Long?, brandId: Long?): Int

    /**
     * 获取用户关注列表
     */
    fun list(memberId: Long?): List<MemberBrandAttention>
}
