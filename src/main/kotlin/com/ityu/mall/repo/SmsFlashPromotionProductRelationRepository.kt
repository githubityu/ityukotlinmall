package com.ityu.mall.repo

import com.ityu.mall.model.CmsSubject
import com.ityu.mall.model.PmsBrand
import com.ityu.mall.model.SmsFlashPromotionProductRelation
import org.springframework.data.jpa.repository.JpaRepository

open interface SmsFlashPromotionProductRelationRepository : JpaRepository<SmsFlashPromotionProductRelation, Long> {


}