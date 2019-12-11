package com.ityu.mall.repo


import com.ityu.mall.model.SmsFlashPromotion
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

open interface SmsFlashPromotionRepository : JpaRepository<SmsFlashPromotion, Long> {

    fun findAllByStatusEqualsAndStartDateLessThanEqualAndEndDateGreaterThanEqual(status: Int, startDate: Date, endDate: Date):List<SmsFlashPromotion>


}