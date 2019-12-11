package com.ityu.mall.repo

import com.ityu.mall.model.PmsSkuStock
import org.springframework.data.jpa.repository.JpaRepository

open interface PmsSkuStockRepository : JpaRepository<PmsSkuStock, Long> {


}