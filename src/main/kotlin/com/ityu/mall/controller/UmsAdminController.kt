package com.ityu.mall.controller

import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.UmsAdminLoginParam
import com.ityu.mall.model.UmsAdmin
import com.ityu.mall.dto.UmsAdminParam
import com.ityu.mall.model.UmsPermission
import com.ityu.mall.model.UmsRole
import com.ityu.mall.service.UmsAdminService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.HashMap
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/admin")
@Api(tags = ["UmsAdminController"], description = "后台用户管理")
@Slf4j
class UmsAdminController {
    @Autowired
    var adminService: UmsAdminService? = null
    @Value("\${jwt.tokenHeader}")
    private val tokenHeader: String? = null
    @Value("\${jwt.tokenHead}")
    private val tokenHead: String? = null


    @ApiOperation(value = "测试")
    @GetMapping(value = "/testA")
    fun testA(): String {
        return "测试"
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    fun register(@RequestBody umsAdminParam: UmsAdminParam, result: BindingResult): CommonResult<UmsAdmin> {
        val umsAdmin = adminService?.register(umsAdminParam)
        if (umsAdmin == null) {
            CommonResult.failed<Unit>()
        }
        return CommonResult.success(umsAdmin)
    }

    @ApiOperation(value = "登录以后返回token header tokenHead+token")
    @PostMapping(value = "/login")
    fun login(@RequestBody umsAdminLoginParam: UmsAdminLoginParam, result: BindingResult): CommonResult<HashMap<String, String>> {
        val token = adminService?.login(umsAdminLoginParam.username, umsAdminLoginParam.password)
                ?: return CommonResult.validateFailed("用户名或密码错误")
        val tokenMap = HashMap<String, String>()
        tokenMap["token"] = token
        tokenMap["tokenHead"] = tokenHead!!
        return CommonResult.success(tokenMap)
    }


    @ApiOperation(value = "根据用户名获取后台管理员")
    @GetMapping(value = "/getAdmin")
    fun getAdmin(@RequestParam("username") username: String): CommonResult<UmsAdmin> {
        return CommonResult.success(adminService?.getAdminByUsername(username) as UmsAdmin)
    }

    @ApiOperation(value = "根据用户id获取后台管理员")
    @GetMapping(value = "/{id}")
    fun getItem(@PathVariable("id") id: Long): CommonResult<UmsAdmin> {
        return CommonResult.success(adminService?.getItem(id))
    }

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/token/refresh")
    fun refreshToken(request: HttpServletRequest): CommonResult<HashMap<String, String>> {
        val token = request.getHeader(tokenHeader)
        val refreshToken = adminService?.refreshToken(token) ?: return CommonResult.failed()
        val tokenMap = HashMap<String, String>()
        tokenMap["token"] = refreshToken
        tokenMap["tokenHead"] = tokenHead!!
        return CommonResult.success(tokenMap)
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info")
    fun getAdminInfo(principal: Principal): CommonResult<HashMap<String, Any>> {
        val username = principal.name
        val umsAdmin = adminService?.getAdminByUsername(username) as UmsAdmin
        val data = HashMap<String, Any>()
        data["username"] = umsAdmin?.username ?: ""
        val rolelist = (adminService?.getRoleList(umsAdmin?.id) ?: mutableListOf())
        data["roles"] = rolelist
        data["icon"] = umsAdmin?.icon ?: ""
        return CommonResult.success(data)
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/list")
    fun list(@RequestParam(value = "name", required = false) name: String,
             @RequestParam(value = "pageSize", defaultValue = "5") pageSize: Int?,
             @RequestParam(value = "pageNum", defaultValue = "1") pageNum: Int?): CommonResult<List<UmsAdmin>> {
        val adminList = adminService?.list(name, pageSize, pageNum)
        return CommonResult.success(adminList)
    }


    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/update")
    fun updateRole(@RequestParam("adminId") adminId: Long?,
                   @RequestParam("roleIds") roleIds: List<Long>): CommonResult<Int> {
        val count = adminService?.updateRole(adminId, roleIds)?:0
        return if (count >= 0) {
            CommonResult.success(count)
        } else CommonResult.failed()
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping(value = "/role/{adminId}")
    fun getRoleList(@PathVariable adminId: Long?): CommonResult<List<UmsRole>> {
        val roleList = adminService?.getRoleList(adminId)
        return CommonResult.success(roleList)
    }

    @ApiOperation("给用户分配+-权限")
    @PostMapping(value = "/permission/update")
    fun updatePermission(@RequestParam adminId: Long?,
                         @RequestParam("permissionIds") permissionIds: List<Long>): CommonResult<Int> {
        val count = adminService?.updatePermission(adminId, permissionIds)?:0
        return if (count > 0) {
            CommonResult.success(count)
        } else CommonResult.failed()
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping(value = "/permission/{adminId}")
    fun getPermissionList(@PathVariable adminId: Long?): CommonResult<List<UmsPermission>> {
        val permissionList = adminService?.getPermissionList(adminId)
        return CommonResult.success(permissionList)
    }
}