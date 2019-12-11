package com.ityu.mall.service


import com.ityu.mall.model.UmsMemberLevel

/**
 * 会员等级管理Service
 * Created by macro on 2018/4/26.
 */
interface UmsMemberLevelService {
    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    fun list(defaultStatus: Int?): List<UmsMemberLevel>
}
