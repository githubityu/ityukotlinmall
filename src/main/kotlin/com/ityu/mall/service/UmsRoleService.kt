package com.ityu.mall.service


import com.ityu.mall.model.UmsPermission
import com.ityu.mall.model.UmsRole
import org.springframework.transaction.annotation.Transactional

/**
 * 后台角色管理Service
 * Created by macro on 2018/9/30.
 */
interface UmsRoleService {
    /**
     * 添加角色
     */
    fun create(role: UmsRole): Int

    /**
     * 修改角色信息
     */
    fun update(id: Long?, role: UmsRole): Int

    /**
     * 批量删除角色
     */
    fun delete(ids: List<Long>): Int

    /**
     * 获取指定角色权限
     */
    fun getPermissionList(roleId: Long?): List<UmsPermission>

    /**
     * 修改指定角色的权限
     */
    @Transactional
    fun updatePermission(roleId: Long?, permissionIds: List<Long>): Int

    /**
     * 获取角色列表
     */
    fun list(): List<UmsRole>
}
