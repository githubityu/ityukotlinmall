package com.ityu.mall.service.impl

import com.ityu.mall.model.UmsPermission
import com.ityu.mall.model.UmsRole
import com.ityu.mall.model.UmsRolePermissionRelation
import com.ityu.mall.repo.UmsRolePermissionRelationRepository
import com.ityu.mall.repo.UmsRoleRepository
import com.ityu.mall.service.UmsRoleService
import com.ityu.mall.util.NativeResultProcessUtils
import com.ityu.mall.util.copyPropertiesIgnoreNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UmsRoleServiceImpl(var urr: UmsRoleRepository, var urprr: UmsRolePermissionRelationRepository) : UmsRoleService {
    override fun create(role: UmsRole): Int {
        role.createTime = Date()
        role.status = 1
        role.adminCount = 0
        role.sort = 0
        urr.save(role)
        return 1
    }

    override fun update(id: Long?, role: UmsRole): Int {
        val findById = urr.findById(id!!).get()
        findById.let {
            copyPropertiesIgnoreNull(role, it)
            urr.save(it)
            return it.id.toInt()
        }
        return 0
    }

    override fun delete(ids: List<Long>): Int {
        urr.deleteAllByIdIn(ids)
        return ids.size
    }

    override fun getPermissionList(roleId: Long?): List<UmsPermission> {
        val permissionList = urprr.getPermissionList(roleId)
        return permissionList.map {
            NativeResultProcessUtils.processResult(it, UmsPermission::class.java)
        }
    }

    override fun updatePermission(roleId: Long?, permissionIds: List<Long>): Int {
        urprr.deleteAllByRoleId(roleId!!)
        //批量插入新关系
        val relationList = permissionIds.map {
            val relation = UmsRolePermissionRelation()
            relation.roleId = roleId
            relation.permissionId = it
            return@map relation
        }
        urprr.saveAll(relationList)
        return relationList.size
    }

    override fun list(): List<UmsRole> {
        return urr.findAll()
    }

}