package com.ityu.mall.controller

import com.ityu.mall.service.UmsPermissionService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.UmsPermissionNode
import com.ityu.mall.model.UmsPermission
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*


@RestController
@Api(tags = ["UmsPermissionController"], description = "后台用户权限管理")
@RequestMapping("/admin/permission")
class UmsPermissionController {
    @Autowired
    var permissionService: UmsPermissionService? = null

    @ApiOperation("添加权限")
    @RequestMapping(value = "/create", method = [RequestMethod.POST])
    fun create(@RequestBody permission: UmsPermission): CommonResult<Int> {
        val count = permissionService!!.create(permission)
        return if (count > 0) {
            CommonResult.success(count)
        } else CommonResult.failed()
    }

    @ApiOperation("修改权限")
    @PostMapping(value = "/update/{id}")
    fun update(@PathVariable id: Long?, @RequestBody permission: UmsPermission): CommonResult<Int> {
        val count = permissionService!!.update(id, permission)
        return if (count > 0) {
            CommonResult.success(count)
        } else CommonResult.failed()
    }

    @ApiOperation("根据id批量删除权限")
    @PostMapping(value = "/delete")
    fun delete(@RequestParam("ids") ids: List<Long>): CommonResult<Int> {
        val count = permissionService!!.delete(ids)
        return if (count > 0) {
            CommonResult.success(count)
        } else CommonResult.failed()
    }
    @ApiOperation("以层级结构返回所有权限")
    @GetMapping(value = "/treeList")
    fun treeList(): CommonResult<List<UmsPermissionNode>> {
        val permissionNodeList = permissionService!!.treeList()
        return CommonResult.success(permissionNodeList)
    }
    @ApiOperation("获取所有权限列表")
    @GetMapping(value = "/list")
    fun list(): CommonResult<List<UmsPermission>> {
        val permissionList = permissionService!!.list()
        return CommonResult.success(permissionList)
    }

}