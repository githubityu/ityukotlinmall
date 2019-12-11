package com.ityu.mall.repo

import com.ityu.mall.model.UmsRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional

open interface UmsRoleRepository : JpaRepository<UmsRole, Long> {
    @Modifying
    @Transactional
    fun deleteAllByIdIn(id: List<Long>)
}