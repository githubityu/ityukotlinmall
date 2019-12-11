package com.ityu.mall.repo

import com.ityu.mall.model.OmsCartItem
import com.ityu.mall.model.UmsAdminLoginLog
import com.ityu.mall.model.UmsAdminPermissionRelation
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Tuple

open interface OmsCartItemRepository : JpaRepository<OmsCartItem, Long> {

    fun findAllByMemberIdAndDeleteStatus(memberId: Long, deleteStatus: Int): List<OmsCartItem>

    fun findAll(specification: Specification<OmsCartItem>): List<OmsCartItem>


    fun findByDeleteStatusAndIdAndMemberId(deleteStatus: Int, id: Long, memberId: Long):OmsCartItem


    fun findAllByMemberIdAndIdInAndDeleteStatus(memberId: Long, id: List<Long>, deleteStatus: Int):List<OmsCartItem>


}