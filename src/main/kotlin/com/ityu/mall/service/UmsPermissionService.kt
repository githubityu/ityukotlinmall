package com.ityu.mall.service


import com.ityu.mall.dto.UmsPermissionNode
import com.ityu.mall.model.UmsPermission

/**
 * 后台用户权限管理Service
 * Created by macro on 2018/9/29.
 */
interface UmsPermissionService {
    /**
     * 添加权限
     */
    fun create(permission: UmsPermission): Int

    /**
     * 修改权限
     */
    fun update(id: Long?, permission: UmsPermission): Int

    /**
     * 批量删除权限
     */
    fun delete(ids: List<Long>): Int

    /**
     * 以层级结构返回所有权限
     */
    fun treeList(): List<UmsPermissionNode>

    /**
     * 获取所有权限
     */
    fun list(): List<UmsPermission>
}
