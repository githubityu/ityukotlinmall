package com.ityu.mall.repo

import com.ityu.mall.model.SmsCoupon
import org.springframework.data.jpa.repository.JpaRepository

open interface SmsCouponRepository : JpaRepository<SmsCoupon, Long> {



}