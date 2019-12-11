package com.ityu.mall.repo

import com.ityu.mall.model.SmsFlashPromotionSession
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.sql.Time
import java.util.*

open interface SmsFlashPromotionSessionRepository : JpaRepository<SmsFlashPromotionSession, Long> {

    fun findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(startTime: Time,
                                                                  endTime: Time):List<SmsFlashPromotionSession>



    fun findAllByStartTimeGreaterThan(startTime: Time,sort: Sort):List<SmsFlashPromotionSession>


}