package com.ityu.mall.repo

import com.ityu.mall.model.PmsBrand
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.persistence.Tuple

open interface PmsBrandRepository : JpaRepository<PmsBrand, Long> {

//    fun findALl(specification : Specification<PmsBrand>)
    @Query("""
           SELECT b.*
        FROM
            sms_home_brand hb
            LEFT JOIN pms_brand b ON hb.brand_id = b.id
        WHERE
            hb.recommend_status = 1
            AND b.show_status = 1
        ORDER BY
            hb.sort DESC
    """, nativeQuery = true
    )
    fun getRecommendBrandList(pageRequest: PageRequest):List<PmsBrand>
}