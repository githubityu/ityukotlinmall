package com.ityu.mall.service

import com.ityu.mall.dto.OmsOrderReturnApplyParam

/**
 * 订单退货管理Service
 * Created by macro on 2018/10/17.
 */
interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    fun create(returnApply: OmsOrderReturnApplyParam?): Int
}