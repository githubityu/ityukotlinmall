package com.ityu.mall.controller


import com.ityu.mall.common.CommonResult
import com.ityu.mall.model.MemberReadHistory
import com.ityu.mall.service.MemberReadHistoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * 会员商品浏览记录管理Controller
 * Created by macro on 2018/8/3.
 */
@RestController
@Api(tags = ["MemberReadHistoryController"], description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
class MemberReadHistoryController {
    @Autowired
    private val memberReadHistoryService: MemberReadHistoryService? = null

    @ApiOperation("创建浏览记录")
    @PostMapping(value = "/create")
    fun create(@RequestBody memberReadHistory: MemberReadHistory): CommonResult<*> {
        val count = memberReadHistoryService!!.create(memberReadHistory)
        return if (count > 0) {
            CommonResult.success(count)
        } else {
            CommonResult.failed<Any>()
        }
    }

    @ApiOperation("删除浏览记录")
    @PostMapping(value = "/delete")
    fun delete(@RequestParam("ids") ids: List<String>): CommonResult<*> {
        val count = memberReadHistoryService!!.delete(ids)
        return if (count > 0) {
            CommonResult.success(count)
        } else {
            CommonResult.failed<Any>()
        }
    }

    @ApiOperation("展示浏览记录")
    @GetMapping(value = "/list")
    fun list(memberId: Long?): CommonResult<List<MemberReadHistory>> {
        val memberReadHistoryList = memberReadHistoryService!!.list(memberId)
        return CommonResult.success(memberReadHistoryList)
    }
}
