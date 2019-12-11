package com.ityu.mall.service


import com.ityu.mall.dto.HomeContentResult
import com.ityu.mall.model.CmsSubject
import com.ityu.mall.model.PmsProduct
import com.ityu.mall.model.PmsProductCategory

/**
 * 首页内容管理Service
 * Created by macro on 2019/1/28.
 */
interface HomeService {

    /**
     * 获取首页内容
     */
    fun content(): HomeContentResult

    /**
     * 首页商品推荐
     */
    fun recommendProductList(pageSize: Int?, pageNum: Int?): List<PmsProduct>

    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    fun getProductCateList(parentId: Long?): List<PmsProductCategory>

    /**
     * 根据专题分类分页获取专题
     * @param cateId 专题分类id
     */
    fun getSubjectList(cateId: Long?, pageSize: Int?, pageNum: Int?): List<CmsSubject>
}
