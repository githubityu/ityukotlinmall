package com.ityu.mall.service

import com.ityu.mall.model.UmsAdmin
import com.ityu.mall.dto.UmsAdminParam
import com.ityu.mall.model.UmsPermission
import com.ityu.mall.model.UmsRole
import org.springframework.transaction.annotation.Transactional

interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    fun getAdminByUsername(username: String): Any?

    /**
     * 注册功能
     */
    fun register(umsAdminParam: UmsAdminParam): UmsAdmin?

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    fun login(username: String, password: String): String

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    fun refreshToken(oldToken: String): String?

    /**
     * 根据用户id获取用户
     */
    abstract fun getItem(id: Long?): UmsAdmin?

    /**
     * 根据用户名或昵称分页查询用户
     */
    fun list(name: String, pageSize: Int?, pageNum: Int?): List<UmsAdmin>

    /**
     * 修改指定用户信息
     */
    abstract fun update(id: Long?, admin: UmsAdmin): Int

    /**
     * 删除指定用户
     */
    fun delete(id: Long?)

    /**
     * 修改用户角色关系
     */
    @Transactional
    fun updateRole(adminId: Long?, roleIds: List<Long>): Int

    /**
     * 获取用户对于角色
     */
    fun getRoleList(adminId: Long?): List<UmsRole>

    /**
     * 修改用户的+-权限
     */
    @Transactional
    fun updatePermission(adminId: Long?, permissionIds: List<Long>): Int

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    fun getPermissionList(adminId: Long?): List<UmsPermission>


}