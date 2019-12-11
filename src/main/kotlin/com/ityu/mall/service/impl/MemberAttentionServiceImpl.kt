package com.ityu.mall.service.impl

import com.ityu.mall.model.MemberBrandAttention
import com.ityu.mall.repo.MemberBrandAttentionRepository
import com.ityu.mall.repo.UmsMemberLevelRepository
import com.ityu.mall.service.MemberAttentionService
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberAttentionServiceImpl(var repository: MemberBrandAttentionRepository) : MemberAttentionService {
    override fun add(memberBrandAttention: MemberBrandAttention): Int {
        var count = 0
        val findAttention = repository.findByMemberIdAndBrandId(memberBrandAttention.memberId, memberBrandAttention.brandId)
        if (findAttention == null) {
            memberBrandAttention.createTime = Date()
            repository.save(memberBrandAttention)
            count = 1
        }
        return count
    }

    override fun delete(memberId: Long?, brandId: Long?): Int {
        return repository.deleteByMemberIdAndBrandId(memberId, brandId)

    }

    override fun list(memberId: Long?): List<MemberBrandAttention> {
        return repository.findByMemberId(memberId)

    }


}