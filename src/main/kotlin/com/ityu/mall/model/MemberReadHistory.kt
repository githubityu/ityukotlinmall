package com.ityu.mall.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import java.util.Date

/**
 * 用户商品浏览历史记录
 * Created by macro on 2018/8/3.
 */
@Document
class MemberReadHistory {
    @Id
    var id: String? = null
    @Indexed
    var memberId: Long? = null
    var memberNickname: String? = null
    var memberIcon: String? = null
    @Indexed
    var productId: Long? = null
    var productName: String? = null
    var productPic: String? = null
    var productSubTitle: String? = null
    var productPrice: String? = null
    var createTime: Date? = null
}
