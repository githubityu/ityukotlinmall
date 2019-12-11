package com.ityu.mall.repo

import com.ityu.mall.model.SmsCouponHistory
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.persistence.Tuple

open interface SmsCouponHistoryRepository : JpaRepository<SmsCouponHistory, Long> {
    @Query("""
         SELECT
            ch.*,
            c.id c_id,
            c.name c_name,
            c.amount c_amount,
            c.min_point c_min_point,
            c.platform c_platform,
            c.start_time c_start_time,
            c.end_time c_end_time,
            c.note c_note,
            c.use_type c_use_type,
            c.type c_type,
            cpr.id cpr_id,cpr.product_id cpr_product_id,
            cpcr.id cpcr_id,cpcr.product_category_id cpcr_product_category_id
        FROM
            sms_coupon_history ch
            LEFT JOIN sms_coupon c ON ch.coupon_id = c.id
            LEFT JOIN sms_coupon_product_relation cpr ON cpr.coupon_id = c.id
            LEFT JOIN sms_coupon_product_category_relation cpcr ON cpcr.coupon_id = c.id
        WHERE ch.member_id = ?1
        AND ch.use_status = 0
    """,
            nativeQuery = true)
    fun getDetailList(memberId: Long): List<Tuple>


    fun countAllByCouponIdAndMemberId(couponId: Long, memberId: Long): Int


    fun findAll(specification: Specification<SmsCouponHistory>): List<SmsCouponHistory>


}