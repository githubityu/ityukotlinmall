package com.ityu.mall.repo

import com.ityu.mall.model.UmsAdminLoginLog
import com.ityu.mall.model.UmsAdminPermissionRelation
import org.springframework.data.jpa.repository.JpaRepository

open interface UmsAdminLoginLogRepository : JpaRepository<UmsAdminLoginLog, Long> {


}