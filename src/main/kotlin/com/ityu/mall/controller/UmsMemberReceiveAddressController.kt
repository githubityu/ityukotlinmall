package com.ityu.mall.controller


import com.ityu.mall.common.CommonResult
import com.ityu.mall.model.UmsMemberReceiveAddress
import com.ityu.mall.service.UmsMemberReceiveAddressService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * 会员收货地址管理Controller
 * Created by macro on 2018/8/28.
 */
@RestController
@Api(tags = ["UmsMemberReceiveAddressController"], description = "会员收货地址管理")
@RequestMapping("/member/address")
class UmsMemberReceiveAddressController {
    @Autowired
    private val memberReceiveAddressService: UmsMemberReceiveAddressService? = null

    @ApiOperation("添加收货地址")
    @PostMapping(value = "/add/{memberId}")
    fun add(@PathVariable memberId: Long?, @RequestBody address: UmsMemberReceiveAddress): CommonResult<*> {
        val count = memberReceiveAddressService!!.add(address, memberId)
        return if (count > 0) {
            CommonResult.success(count)
        } else CommonResult.failed<Any>()
    }

    @ApiOperation("删除收货地址")
    @PostMapping(value = "/delete/{memberId}/{id}")
    fun delete(@PathVariable memberId: Long?, @PathVariable id: Long?): CommonResult<*> {
        val count = memberReceiveAddressService!!.delete(id, memberId)
        return if (count > 0) {
            CommonResult.success(count)
        } else CommonResult.failed<Any>()
    }

    @ApiOperation("修改收货地址")
    @PostMapping(value = "/update/{memberId}/{id}")
    fun update(@PathVariable memberId: Long?, @PathVariable id: Long?, @RequestBody address: UmsMemberReceiveAddress): CommonResult<*> {
        val count = memberReceiveAddressService!!.update(id, memberId, address)
        return if (count > 0) {
            CommonResult.success(count)
        } else CommonResult.failed<Any>()
    }

    @ApiOperation("显示所有收货地址")
    @GetMapping(value = "/list/{memberId}")
    fun list(@PathVariable memberId: Long?): CommonResult<List<UmsMemberReceiveAddress>> {
        val addressList = memberReceiveAddressService!!.list(memberId)
        return CommonResult.success(addressList)
    }

    @ApiOperation("显示某用户有一个收货地址")
    @GetMapping(value = "/{memberId}/{id}")
    fun getItem(@PathVariable memberId: Long?, @PathVariable id: Long?): CommonResult<UmsMemberReceiveAddress> {
        val address = memberReceiveAddressService!!.getItem(id, memberId)
        return CommonResult.success(address)
    }
}
