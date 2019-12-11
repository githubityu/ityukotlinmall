package com.ityu.mall.service.impl


import com.ityu.mall.dto.PmsBrandParam
import com.ityu.mall.model.PmsBrand
import com.ityu.mall.repo.PmsBrandRepository
import com.ityu.mall.repo.PmsProductRepository
import com.ityu.mall.service.PmsBrandService
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

/**
 * 商品品牌Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
class PmsBrandServiceImpl(var brandR: PmsBrandRepository, productR: PmsProductRepository) : PmsBrandService {


    override fun listAllBrand(): List<PmsBrand> {
        return brandR.findAll()
    }

    override fun createBrand(pmsBrandParam: PmsBrandParam): Int {
        val pmsBrand = PmsBrand()
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand)
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.firstLetter)) {
            pmsBrand.firstLetter = (pmsBrand.name!!.substring(0, 1))
        }
        brandR.save(pmsBrand)
        return 0

    }

    override fun updateBrand(id: Long?, pmsBrandParam: PmsBrandParam): Int {
        return 0
    }

    override fun deleteBrand(id: Long?): Int {
        return 0
    }

    override fun deleteBrand(ids: List<Long>): Int {
        return 0
    }

    override fun listBrand(keyword: String, pageNum: Int, pageSize: Int): List<PmsBrand> {
        return mutableListOf()
    }

    override fun getBrand(id: Long?): PmsBrand {
        return PmsBrand()
    }

    override fun updateShowStatus(ids: List<Long>, showStatus: Int?): Int {
        return 0
    }

    override fun updateFactoryStatus(ids: List<Long>, factoryStatus: Int?): Int {
        return 0
    }
}
