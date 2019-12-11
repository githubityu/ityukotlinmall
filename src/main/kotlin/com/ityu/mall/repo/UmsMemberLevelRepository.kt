package com.ityu.mall.repo

import com.ityu.mall.model.UmsMemberLevel
import org.springframework.data.jpa.repository.JpaRepository

open interface UmsMemberLevelRepository : JpaRepository<UmsMemberLevel, Long> {

    fun findAllByDefaultStatusEquals(defaultStatus: Int):List<UmsMemberLevel>

}