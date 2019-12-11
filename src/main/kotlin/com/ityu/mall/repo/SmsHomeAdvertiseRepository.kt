package com.ityu.mall.repo

import com.ityu.mall.model.SmsHomeAdvertise
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

open interface SmsHomeAdvertiseRepository : JpaRepository<SmsHomeAdvertise, Long> {


    fun findAllByTypeEqualsAndStatusEquals(type: Int, status: Int,sort: Sort):List<SmsHomeAdvertise>

}