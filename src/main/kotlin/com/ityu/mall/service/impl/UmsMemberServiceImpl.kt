package com.ityu.mall.service.impl

import com.ityu.mall.bo.MemberDetails
import com.ityu.mall.common.CommonResult
import com.ityu.mall.model.UmsMember
import com.ityu.mall.repo.UmsMemberLevelRepository
import com.ityu.mall.repo.UmsMemberRepository
import com.ityu.mall.service.RedisService
import com.ityu.mall.service.UmsMemberService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import org.springframework.util.StringUtils
import java.lang.StringBuilder
import java.util.*

@Service
class UmsMemberServiceImpl(var umr: UmsMemberRepository,
                           var umlr: UmsMemberLevelRepository,
                           var passwordEncoder: PasswordEncoder,
                           var redisService: RedisService
) : UmsMemberService {

    override fun getCurrentMember(): UmsMember {
        val ctx = SecurityContextHolder.getContext()
        val auth = ctx.authentication
        val memberDetails = auth.principal as MemberDetails
        return memberDetails.umsMember
    }
    override fun getByUsername(username: String): UmsMember? {
        val allDatas = umr.findAllByUsernameEquals(username)
        return if (allDatas.isNotEmpty()) {
            allDatas[0]
        } else {
            null
        }
    }

    override fun getById(id: Long?): UmsMember {
       return  umr.findById(id!!).get()
    }

    override fun register(username: String, password: String, telephone: String, authCode: String): CommonResult<Any> {
        if (!verifyAuthCode(authCode, telephone)) {
            return CommonResult.failed("验证码错误")
        }
        val findByPhoneEquals = umr.findByPhoneEquals(telephone)
        if(findByPhoneEquals.isNotEmpty()){
            return CommonResult.failed("该用户已经存在")
        }
        val umsMember = UmsMember()
        umsMember.username = (username)
        umsMember.phone =(telephone)
        umsMember.password = (passwordEncoder.encode(password))
        umsMember.createTime = (Date())
        umsMember.status = (1)

        val findAllByDefaultStatusEquals = umlr.findAllByDefaultStatusEquals(1)
        if(findAllByDefaultStatusEquals.isNotEmpty()){
            umsMember.memberLevelId = findAllByDefaultStatusEquals[0].id
        }
        umr.save(umsMember)
        return CommonResult.success(null, "注册成功")
    }

    override fun generateAuthCode(telephone: String): CommonResult<Any> {
        val sb = StringBuilder()
        val random = Random()
        for (i in 0..5) {
            sb.append(random.nextInt(10))
        }
        //验证码绑定手机号并存储到redis
        redisService[REDIS_KEY_PREFIX_AUTH_CODE + telephone] = sb.toString()
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS!!)
        return CommonResult.success(sb.toString(), "获取验证码成功")
    }

    override fun updatePassword(telephone: String, password: String, authCode: String): CommonResult<Any> {
        val memberList = umr.findByPhoneEquals(telephone)
        if (CollectionUtils.isEmpty(memberList)) {
            return CommonResult.failed("该账号不存在")
        }
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            return CommonResult.failed("验证码错误")
        }
        val umsMember = memberList[0]
        umsMember.password = (passwordEncoder.encode(password))
        umr.save(umsMember)
        return CommonResult.success(null, "密码修改成功")
    }

    override fun updateIntegration(id: Long?, integration: Int?) {
        val record = umr.findById(id!!).get()
        record.integration = integration
        umr.save(record)
    }
    @Value("\${rediskey.prefix.authCode}")
    val REDIS_KEY_PREFIX_AUTH_CODE: String? = null
    @Value("\${rediskey.expire.authCode}")
    val AUTH_CODE_EXPIRE_SECONDS: Long? = null


    //对输入的验证码进行校验
    private fun verifyAuthCode(authCode: String, telephone: String): Boolean {
        if (StringUtils.isEmpty(authCode)) {
            return false
        }
        val realAuthCode = redisService[REDIS_KEY_PREFIX_AUTH_CODE + telephone]?:""
        return authCode == realAuthCode
    }

}