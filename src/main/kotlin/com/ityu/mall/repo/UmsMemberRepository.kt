package com.ityu.mall.repo

import com.ityu.mall.model.UmsMember
import org.springframework.data.jpa.repository.JpaRepository

open interface UmsMemberRepository : JpaRepository<UmsMember, Long> {

     fun findAllByUsernameEquals(username: String):List<UmsMember>

     fun findByUsername(username: String):UmsMember

     fun findByPhoneEquals(phone: String):List<UmsMember>

}