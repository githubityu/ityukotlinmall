package com.ityu.mall.service.impl


import com.ityu.mall.model.MemberReadHistory
import com.ityu.mall.repo.MemberReadHistoryRepository
import com.ityu.mall.service.MemberReadHistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.ArrayList
import java.util.Date

/**
 * 会员浏览记录管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
class MemberReadHistoryServiceImpl : MemberReadHistoryService {
    @Autowired
    private val memberReadHistoryRepository: MemberReadHistoryRepository? = null

    override fun create(memberReadHistory: MemberReadHistory): Int {
        memberReadHistory.id = null
        memberReadHistory.createTime = Date()
        memberReadHistoryRepository!!.save(memberReadHistory)
        return 1
    }

    override fun delete(ids: List<String>): Int {
        val deleteList = ArrayList<MemberReadHistory>()
        for (id in ids) {
            val memberReadHistory = MemberReadHistory()
            memberReadHistory.id = id
            deleteList.add(memberReadHistory)
        }
        memberReadHistoryRepository!!.deleteAll(deleteList)
        return ids.size
    }

    override fun list(memberId: Long?): List<MemberReadHistory> {
        return memberReadHistoryRepository!!.findByMemberIdOrderByCreateTimeDesc(memberId)
    }
}
