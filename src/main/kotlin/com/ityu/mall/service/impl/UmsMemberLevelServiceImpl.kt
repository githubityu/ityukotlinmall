package com.ityu.mall.service.impl

import com.ityu.mall.model.UmsMemberLevel
import com.ityu.mall.repo.UmsMemberLevelRepository
import com.ityu.mall.service.UmsMemberLevelService
import org.springframework.stereotype.Service

@Service
class UmsMemberLevelServiceImpl(var repository: UmsMemberLevelRepository) : UmsMemberLevelService {
    override fun list(defaultStatus: Int?): List<UmsMemberLevel> {
        return repository.findAllByDefaultStatusEquals(defaultStatus!!)
    }

}