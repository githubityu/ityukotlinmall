package com.ityu.mall.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed

import java.util.Date

/**
 * 用户收藏的商品
 * Created by macro on 2018/8/2.
 */
class MemberProductCollection {
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
