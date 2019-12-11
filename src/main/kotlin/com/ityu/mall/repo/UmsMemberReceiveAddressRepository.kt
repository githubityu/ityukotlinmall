package com.ityu.mall.repo

import com.ityu.mall.model.UmsMemberReceiveAddress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional


open interface UmsMemberReceiveAddressRepository : JpaRepository<UmsMemberReceiveAddress, Long> {

   @Modifying
   @Transactional
   fun deleteByMemberIdAndId(memberId: Long, id: Long)

   fun findByMemberId(memberId: Long):List<UmsMemberReceiveAddress>

   fun findByMemberIdAndId(memberId: Long, id: Long):UmsMemberReceiveAddress

}