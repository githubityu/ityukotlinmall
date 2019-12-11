package com.ityu.mall.controller


import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.HomeContentResult
import com.ityu.mall.model.CmsSubject
import com.ityu.mall.model.PmsProduct
import com.ityu.mall.model.PmsProductCategory
import com.ityu.mall.service.HomeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * 首页内容管理Controller
 * Created by macro on 2019/1/28.
 */
@RestController
@Api(tags = ["HomeController"], description = "首页内容管理")
@RequestMapping("/home")
class HomeController {
    @Autowired
    private val homeService: HomeService? = null

    @ApiOperation("首页内容页信息展示")
    @GetMapping(value = "/content")
    fun content(): CommonResult<HomeContentResult> {
        val contentResult = homeService!!.content()
        return CommonResult.success(contentResult)
    }

    @ApiOperation("分页获取推荐商品")
    @GetMapping(value = "/recommendProductList")
    fun recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") pageSize: Int?,
                             @RequestParam(value = "pageNum", defaultValue = "0") pageNum: Int?): CommonResult<List<PmsProduct>> {
        val productList = homeService!!.recommendProductList(pageSize, pageNum)
        return CommonResult.success(productList)
    }

    @ApiOperation("获取首页商品分类")
    @GetMapping(value = "/productCateList/{parentId}")
    fun getProductCateList(@PathVariable parentId: Long?): CommonResult<List<PmsProductCategory>> {
        val productCategoryList = homeService!!.getProductCateList(parentId)
        return CommonResult.success(productCategoryList)
    }

    @ApiOperation("根据分类获取专题")
    @GetMapping(value = "/subjectList")
    fun getSubjectList(@RequestParam(required = false) cateId: Long?,
                       @RequestParam(value = "pageSize", defaultValue = "4") pageSize: Int?,
                       @RequestParam(value = "pageNum", defaultValue = "0") pageNum: Int?): CommonResult<List<CmsSubject>> {
        val subjectList = homeService!!.getSubjectList(cateId, pageSize, pageNum)
        return CommonResult.success(subjectList)
    }
}
