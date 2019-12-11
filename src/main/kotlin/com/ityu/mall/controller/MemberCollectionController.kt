package com.ityu.mall.controller


import com.ityu.mall.common.CommonResult
import com.ityu.mall.model.MemberProductCollection
import com.ityu.mall.service.MemberCollectionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * 会员收藏管理Controller
 * Created by macro on 2018/8/2.
 */
@RestController
@Api(tags = ["MemberCollectionController"], description = "会员收藏管理")
@RequestMapping("/member/collection")
class MemberCollectionController {
    @Autowired
    private val memberCollectionService: MemberCollectionService? = null

    @ApiOperation("添加商品收藏")
    @PostMapping(value = "/addProduct")
    fun addProduct(@RequestBody productCollection: MemberProductCollection): CommonResult<*> {
        val count = memberCollectionService!!.addProduct(productCollection)
        return if (count > 0) {
            CommonResult.success(count)
        } else {
            CommonResult.failed<Any>()
        }
    }

    @ApiOperation("删除收藏商品")
    @PostMapping(value = "/deleteProduct")
    fun deleteProduct(memberId: Long?, productId: Long?): CommonResult<*> {
        val count = memberCollectionService!!.deleteProduct(memberId, productId)
        return if (count > 0) {
            CommonResult.success(count)
        } else {
            CommonResult.failed<Any>()
        }
    }

    @ApiOperation("显示关注列表")
    @GetMapping(value = "/listProduct/{memberId}")
    fun listProduct(@PathVariable memberId: Long?): CommonResult<List<MemberProductCollection>> {
        val memberProductCollectionList = memberCollectionService!!.listProduct(memberId)
        return CommonResult.success(memberProductCollectionList)
    }
}
