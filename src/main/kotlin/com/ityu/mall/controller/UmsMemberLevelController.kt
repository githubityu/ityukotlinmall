package com.ityu.mall.controller

import com.ityu.mall.common.CommonResult
import com.ityu.mall.model.UmsMemberLevel
import com.ityu.mall.service.UmsMemberLevelService

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 会员等级管理Controller
 * Created by macro on 2018/4/26.
 */
@RestController
@Api(tags = ["UmsMemberLevelController"], description = "会员等级管理")
@RequestMapping("/memberLevel")
class UmsMemberLevelController {
    @Autowired
    val memberLevelService: UmsMemberLevelService? = null

    @GetMapping(value = "/list")
    @ApiOperation("查询所有会员等级")
    fun list(@RequestParam("defaultStatus") defaultStatus: Int?): CommonResult<List<UmsMemberLevel>> {
        val memberLevelList = memberLevelService!!.list(defaultStatus)
        return CommonResult.success(memberLevelList)
    }
}
