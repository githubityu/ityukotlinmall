package com.ityu.mall.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import java.util.Date

/**
 * 会员关注的品牌
 * Created by macro on 2018/8/2.
 */
@Document
class MemberBrandAttention {
    @Id
    var id: String? = null
    @Indexed
    var memberId: Long? = null
    var memberNickname: String? = null
    var memberIcon: String? = null
    @Indexed
    var brandId: Long? = null
    var brandName: String? = null
    var brandLogo: String? = null
    var brandCity: String? = null
    var brandAttentionCount: Int? = null
    var createTime: Date? = null
}
