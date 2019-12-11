package com.ityu.mall.service


import com.ityu.mall.model.MemberReadHistory

/**
 * 会员浏览记录管理Service
 * Created by macro on 2018/8/3.
 */
interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    fun create(memberReadHistory: MemberReadHistory): Int

    /**
     * 批量删除浏览记录
     */
    fun delete(ids: List<String>): Int

    /**
     * 获取用户浏览历史记录
     */
    fun list(memberId: Long?): List<MemberReadHistory>
}
