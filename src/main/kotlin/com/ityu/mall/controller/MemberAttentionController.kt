package com.ityu.mall.controller


import com.ityu.mall.common.CommonResult
import com.ityu.mall.model.MemberBrandAttention
import com.ityu.mall.service.MemberAttentionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


/**
 * 会员关注品牌管理Controller
 * Created by macro on 2018/8/2.
 */
@RestController
@Api(tags = ["MemberAttentionController"], description = "会员关注品牌管理")
@RequestMapping("/member/attention")
class MemberAttentionController {
    @Autowired
    private val memberAttentionService: MemberAttentionService? = null

    @ApiOperation("添加品牌关注")
    @PostMapping(value = "/add")
    @ResponseBody
    fun add(@RequestBody memberBrandAttention: MemberBrandAttention): CommonResult<*> {
        val count = memberAttentionService!!.add(memberBrandAttention)
        return if (count > 0) {
            CommonResult.success(count)
        } else {
            CommonResult.failed<Any>()
        }
    }

    @ApiOperation("取消关注")
    @PostMapping(value = "/delete")
    fun delete(memberId: Long?, brandId: Long?): CommonResult<*> {
        val count = memberAttentionService!!.delete(memberId, brandId)
        return if (count > 0) {
            CommonResult.success(count)
        } else {
            CommonResult.failed<Any>()
        }
    }

    @ApiOperation("显示关注列表")
    @GetMapping(value = "/list/{memberId}")
    fun list(@PathVariable memberId: Long?): CommonResult<List<MemberBrandAttention>> {
        val memberBrandAttentionList = memberAttentionService!!.list(memberId)
        return CommonResult.success(memberBrandAttentionList)
    }
}
