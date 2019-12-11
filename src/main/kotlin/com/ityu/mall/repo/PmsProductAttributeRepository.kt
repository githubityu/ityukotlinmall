package com.ityu.mall.repo

import com.ityu.mall.model.OmsCartItem
import com.ityu.mall.model.PmsProductAttribute
import com.ityu.mall.model.UmsAdminLoginLog
import com.ityu.mall.model.UmsAdminPermissionRelation
import org.springframework.data.jpa.repository.JpaRepository

open interface PmsProductAttributeRepository : JpaRepository<PmsProductAttribute, Long> {


}