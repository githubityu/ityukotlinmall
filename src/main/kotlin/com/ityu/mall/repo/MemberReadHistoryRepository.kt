package com.ityu.mall.repo


import com.ityu.mall.model.MemberReadHistory
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * 会员商品浏览历史Repository
 * Created by macro on 2018/8/3.
 */
interface MemberReadHistoryRepository : MongoRepository<MemberReadHistory, String> {
    fun findByMemberIdOrderByCreateTimeDesc(memberId: Long?): List<MemberReadHistory>
}
