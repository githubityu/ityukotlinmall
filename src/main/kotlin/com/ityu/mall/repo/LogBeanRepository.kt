package com.ityu.mall.repo

import com.ityu.mall.model.LogBean
import org.springframework.data.jpa.repository.JpaRepository

open interface LogBeanRepository : JpaRepository<LogBean, Long> {




}