package com.ityu.mall.service


import com.ityu.mall.dto.PmsBrandParam
import com.ityu.mall.model.PmsBrand
import org.springframework.transaction.annotation.Transactional

/**
 * 商品品牌Service
 * Created by macro on 2018/4/26.
 */
interface PmsBrandService {
    fun listAllBrand(): List<PmsBrand>

    fun createBrand(pmsBrandParam: PmsBrandParam): Int
    @Transactional
    fun updateBrand(id: Long?, pmsBrandParam: PmsBrandParam): Int

    fun deleteBrand(id: Long?): Int

    fun deleteBrand(ids: List<Long>): Int

    fun listBrand(keyword: String, pageNum: Int, pageSize: Int): List<PmsBrand>

    fun getBrand(id: Long?): PmsBrand

    fun updateShowStatus(ids: List<Long>, showStatus: Int?): Int

    fun updateFactoryStatus(ids: List<Long>, factoryStatus: Int?): Int
}
