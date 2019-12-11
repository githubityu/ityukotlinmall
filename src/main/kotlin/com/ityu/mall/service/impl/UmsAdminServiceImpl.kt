package com.ityu.mall.service.impl

import com.ityu.mall.dto.UmsAdminParam
import com.ityu.mall.model.*
import com.ityu.mall.repo.*
import com.ityu.mall.service.UmsAdminService
import com.ityu.mall.util.JwtTokenUtil
import com.ityu.mall.util.NativeResultProcessUtils
import com.ityu.mall.util.getCurrentRequest
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import kotlin.streams.toList

@Service
class UmsAdminServiceImpl(var authenticationManager: AuthenticationManager,
                          var uaRepo: UmsAdminRepository,
                          var umRepo: UmsMemberRepository,
                          var userDetailsService: UserDetailsService,
                          var jwtTokenUtil: JwtTokenUtil,
                          var uaprRepo: UmsAdminPermissionRelationRepository,
                          var uarrRepo: UmsAdminRoleRelationRepository,
                          var uallRepo: UmsAdminLoginLogRepository,
                          var passwordEncoder: PasswordEncoder
) : UmsAdminService {

    @Value("\${jwt.tokenHead}")
    private val tokenHead: String? = null

    override fun register(umsAdminParam: UmsAdminParam): UmsAdmin? {
        val umsAdmin = UmsAdmin()
        BeanUtils.copyProperties(umsAdminParam, umsAdmin)
        umsAdmin.createTime = Date()
        umsAdmin.status = 1
        if (uaRepo.findAllByUsername(umsAdmin.username).isNotEmpty()) {
            return null
        }
        val encodePassword = passwordEncoder.encode(umsAdmin.password)
        umsAdmin.password = encodePassword
        uaRepo.saveAndFlush(umsAdmin)
        return umsAdmin
    }

    override fun login(username: String, password: String): String {
        val userDetails = userDetailsService.loadUserByUsername(username)

        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw BadCredentialsException("密码不正确")
        }

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        SecurityContextHolder.getContext().authentication = authentication
        val token = jwtTokenUtil.generateToken(userDetails)
        insertLoginLog(username)

        return token
    }

    private fun insertLoginLog(username: String) {
        val admin = getAdminByUsername(username) as UmsAdmin
        admin?.let {
            val loginLog = UmsAdminLoginLog()
            loginLog.adminId = admin.id
            loginLog.createTime = Date()
            val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?
            val request = attributes!!.request
            loginLog.ip = request.remoteAddr
            uallRepo.save(loginLog)
        }
    }

    override fun refreshToken(oldToken: String): String? {
        tokenHead?.let {
            val token = oldToken.substring(tokenHead.length)
            return if (jwtTokenUtil.canRefresh(token)) {
                jwtTokenUtil.refreshToken(token)
            } else null
        }
        return null
    }

    override fun getItem(id: Long?): UmsAdmin? {
        id?.let {
            return uaRepo.findById(id).get()
        }
        return null
    }

    override fun list(name: String, pageSize: Int?, pageNum: Int?): List<UmsAdmin> {
        return uaRepo.findAllByUsernameLikeOrNickNameLike("%$name%", "%$name%", PageRequest.of(pageNum!!, pageSize!!))
    }

    override fun update(id: Long?, admin: UmsAdmin): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


    }

    override fun delete(id: Long?) {
        uaRepo.deleteById(id!!)
    }

    override fun updateRole(adminId: Long?, roleIds: List<Long>): Int {
        uarrRepo.deleteByAdminIdEquals(adminId!!)
        if (roleIds.isNotEmpty()) {
            val uarrs = roleIds.map {
                val rr = UmsAdminRoleRelation()
                rr.adminId = adminId
                rr.roleId = it
                return@map rr
            }
            uarrRepo.saveAll(uarrs)
        }
        return roleIds.size
    }

    override fun getRoleList(adminId: Long?): List<UmsRole> {
        return uarrRepo.getRoleList(adminId!!).map {
            NativeResultProcessUtils.processResult(it, UmsRole::class.java)
        }
    }

    override fun updatePermission(adminId: Long?, permissionIds: List<Long>): Int {
        //删除原所有权限关系
        uaprRepo.deleteByAdminIdEquals(adminId!!)
        //获取用户所有角色权限
        val permissionList = getPermissionList(adminId!!)

        val rolePermissionList = permissionList.map {
            it.id
        }

        return if (permissionIds.isNotEmpty()) {
            val relationList = mutableListOf<UmsAdminPermissionRelation>()
            val addPermissionIdList = rolePermissionList.stream().filter { permissionId ->
                !rolePermissionList.contains(permissionId)
            }.toList()

            val subPermissionIdList = permissionIds.stream().filter { permissionId ->
                !rolePermissionList.contains(permissionId)
            }.toList()

            relationList.addAll(convert(adminId, 1, addPermissionIdList))
            relationList.addAll(convert(adminId, -1, subPermissionIdList))
            uaprRepo.saveAll(relationList)
            relationList.size
        } else 0
    }

    override fun getPermissionList(adminId: Long?): List<UmsPermission> {
        val data = uarrRepo.getPermissionList(adminId!!)
        val map = data.map {
            NativeResultProcessUtils.processResult(it, UmsPermission::class.java, "createTime")
        }
        return map
    }

    override fun getAdminByUsername(username: String): Any? {
        val currentRequest = getCurrentRequest()
        if (currentRequest.requestURI.contains("admin")) {
            val ua = uaRepo.findAllByUsername(username)
            return if (ua.isEmpty()) null else ua[0]
        } else {
            val ua = umRepo.findAllByUsernameEquals(username)
            return if (ua.isEmpty()) null else ua[0]
        }
    }

    /**
     * 将+-权限关系转化为对象
     */
    private fun convert(adminId: Long?, type: Int?, permissionIdList: List<Long>): List<UmsAdminPermissionRelation> {
        return permissionIdList.map { permissionId ->
            val relation = UmsAdminPermissionRelation()
            relation.adminId = adminId
            relation.type = type
            relation.permissionId = permissionId
            relation
        }
    }


}