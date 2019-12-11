package com.ityu.mall.repo

import com.ityu.mall.model.UmsAdminPermissionRelation
import org.springframework.data.jpa.repository.JpaRepository

open interface UmsAdminPermissionRelationRepository : JpaRepository<UmsAdminPermissionRelation, Long> {
        fun deleteByAdminIdEquals(adminId: Long)

}