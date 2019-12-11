package com.ityu.mall.repo

import com.ityu.mall.model.UmsAdmin
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

open interface UmsAdminRepository : JpaRepository<UmsAdmin, Long> {

    fun findAllByUsername(username: String): List<UmsAdmin>

    fun findAllByUsernameLikeOrNickNameLike(username: String, nickName: String, pageable: Pageable?): List<UmsAdmin>
}