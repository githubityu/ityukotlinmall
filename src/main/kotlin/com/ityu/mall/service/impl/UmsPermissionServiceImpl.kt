package com.ityu.mall.service.impl

import com.ityu.mall.dto.UmsPermissionNode
import com.ityu.mall.model.UmsPermission
import com.ityu.mall.repo.UmsPermissionRepository
import com.ityu.mall.service.UmsPermissionService
import com.ityu.mall.util.copyPropertiesIgnoreNull
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import java.util.*

import kotlin.streams.toList

@Service
class UmsPermissionServiceImpl(var permissionMapper: UmsPermissionRepository) : UmsPermissionService {
    override fun create(permission: UmsPermission): Int {
        permission.status = (1)
        permission.createTime = Date()
        permission.sort = (0)
        permissionMapper.saveAndFlush(permission)
        return permission.id.toInt()
    }

    override fun update(id: Long?, permission: UmsPermission): Int {
        val per = permissionMapper.findById(id!!).get()
        per?.let {
            copyPropertiesIgnoreNull(permission, it)
            permissionMapper.save(it)
            return it.id.toInt()
        }
        return 0
    }

    override fun delete(ids: List<Long>): Int {
        permissionMapper.deleteAllByIdIn(ids)
        return ids.size
    }

    override fun treeList(): List<UmsPermissionNode> {
        val permissionList = permissionMapper.findAll()
        return permissionList.filter {
            it.pid == 0L
        }.map {
            covert(it, permissionList)
        }
    }

    override fun list(): List<UmsPermission> {
        return permissionMapper.findAll()
    }

    private fun covert(permission: UmsPermission, permissionList: List<UmsPermission>): UmsPermissionNode {
        val node = UmsPermissionNode()
        BeanUtils.copyProperties(permission, node)
        val children = permissionList
                .filter { subPermission -> subPermission.pid == permission.id }
                .map { subPermission -> covert(subPermission, permissionList) }
        node.children = children
        return node
    }
}