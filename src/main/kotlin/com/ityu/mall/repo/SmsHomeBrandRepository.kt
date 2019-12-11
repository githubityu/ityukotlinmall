package com.ityu.mall.repo

import com.ityu.mall.model.SmsHomeBrand
import org.springframework.data.jpa.repository.JpaRepository


open interface SmsHomeBrandRepository : JpaRepository<SmsHomeBrand, Long> {

    //fun findALl(specification: Specification<PmsBrand>)




}