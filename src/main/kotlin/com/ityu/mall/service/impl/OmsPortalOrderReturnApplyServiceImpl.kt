package com.ityu.mall.service.impl

import com.ityu.mall.dto.OmsOrderReturnApplyParam
import com.ityu.mall.model.OmsOrderReturnApply
import com.ityu.mall.repo.OmsOrderReturnApplyRepository
import com.ityu.mall.service.OmsPortalOrderReturnApplyService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * 订单退货管理Service实现类
 * Created by macro on 2018/10/17.
 */
@Service
class OmsPortalOrderReturnApplyServiceImpl : OmsPortalOrderReturnApplyService {
    @Autowired
    private val returnApplyMapper: OmsOrderReturnApplyRepository? = null

    override fun create(returnApply: OmsOrderReturnApplyParam?): Int {
        val realApply = OmsOrderReturnApply()
        BeanUtils.copyProperties(returnApply!!, realApply)
        realApply.createTime = Date()
        realApply.status = 0
        returnApplyMapper!!.save(realApply)
        return 1
    }
}