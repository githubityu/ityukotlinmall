package com.ityu.mall.repo

import com.ityu.mall.model.UmsPermission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional

open interface UmsPermissionRepository : JpaRepository<UmsPermission, Long> {


    @Modifying
    @Transactional
    fun deleteAllByIdIn(id: List<Long>)
}