package com.ityu.mall.service.impl


import com.ityu.mall.model.LogBean
import com.ityu.mall.repo.LogBeanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 首页内容管理Service实现类
 * Created by macro on 2019/1/28.
 */
@Service
class LogBeanService  {
    @Autowired
    lateinit var logRes: LogBeanRepository


    fun save(logBean: LogBean):Int{
        logRes.save(logBean)
        return 1
    }


}


