package com.ityu.mall.controller

import com.ityu.mall.common.CommonResult
import com.ityu.mall.dto.UmsAdminLoginParam
import com.ityu.mall.model.UmsAdmin
import com.ityu.mall.dto.UmsAdminParam
import com.ityu.mall.model.UmsPermission
import com.ityu.mall.model.UmsRole
import com.ityu.mall.service.UmsAdminService
import com.ityu.mall.service.UmsMemberService
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
@RequestMapping("/sso")
@Api(tags = ["UmsMemberController"], description = "会员登录注册管理")
@Slf4j
class UmsMemberController {
    @Autowired
   private lateinit var  memberService: UmsMemberService

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    @ResponseBody
    fun register(@RequestParam username: String,
                 @RequestParam password: String,
                 @RequestParam telephone: String,
                 @RequestParam authCode: String): CommonResult<Any> {
        return memberService.register(username, password, telephone, authCode)
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = [RequestMethod.GET])
    @ResponseBody
    fun getAuthCode(@RequestParam telephone: String): CommonResult<Any> {
        return memberService.generateAuthCode(telephone)
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = [RequestMethod.POST])
    @ResponseBody
    fun updatePassword(@RequestParam telephone: String,
                       @RequestParam password: String,
                       @RequestParam authCode: String): CommonResult<Any> {
        return memberService.updatePassword(telephone, password, authCode)
    }

}