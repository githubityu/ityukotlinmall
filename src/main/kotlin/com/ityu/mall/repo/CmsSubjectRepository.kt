package com.ityu.mall.repo

import com.ityu.mall.model.CmsSubject
import com.ityu.mall.model.PmsBrand
import com.ityu.mall.model.PmsProduct
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.persistence.Tuple

open interface CmsSubjectRepository : JpaRepository<CmsSubject, Long> {
    @Query(
            """
         SELECT s.*
        FROM
            sms_home_recommend_subject hs
            LEFT JOIN cms_subject s ON hs.subject_id = s.id
        WHERE
            hs.recommend_status = 1
            AND s.show_status = 1
        ORDER BY
            hs.sort DESC
            """
            , nativeQuery = true
    )
    fun getRecommendSubjectList(pageRequest: PageRequest): List<Tuple>


    fun findAllByShowStatusEqualsAndCategoryIdEquals(showStatus: Int, categoryId: Long,pageRequest: Pageable?):List<CmsSubject>
    fun findAllByShowStatusEquals(showStatus: Int,pageRequest: Pageable?):List<CmsSubject>



}