package com.ityu.mall.repo

import com.ityu.mall.model.PmsProductCategory
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

open interface PmsProductCategoryRepository : JpaRepository<PmsProductCategory, Long> {



    fun findAllByShowStatusEqualsAndParentIdEquals(showStatus: Int, parentId: Long,sort: Sort):List<PmsProductCategory>

}