package com.ityu.mall.repo

import com.ityu.mall.model.CmsSubject
import com.ityu.mall.model.OmsOrderSetting
import com.ityu.mall.model.PmsBrand
import com.ityu.mall.model.PmsProduct
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.persistence.Tuple

open interface OmsOrderSettingRepository : JpaRepository<OmsOrderSetting, Long> {



}