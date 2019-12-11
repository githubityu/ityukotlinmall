package com.ityu.mall.service.impl


import com.ityu.mall.model.UmsMemberReceiveAddress
import com.ityu.mall.repo.UmsMemberReceiveAddressRepository
import com.ityu.mall.service.UmsMemberReceiveAddressService
import com.ityu.mall.service.UmsMemberService
import com.ityu.mall.util.copyPropertiesIgnoreNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户地址管理Service实现类
 * Created by macro on 2018/8/28.
 */
@Service
class UmsMemberReceiveAddressServiceImpl : UmsMemberReceiveAddressService {
    @Autowired
    lateinit var memberService: UmsMemberService
    @Autowired
    lateinit var addressMapper: UmsMemberReceiveAddressRepository

    override fun add(address: UmsMemberReceiveAddress, memberId: Long?): Int {
        address.memberId = memberId
        addressMapper.save(address)
        return 1
    }

    override fun delete(id: Long?, memberId: Long?): Int {
        addressMapper.deleteByMemberIdAndId(memberId!!, id!!)
        return 1
    }

    override fun update(id: Long?, memberId: Long?, address: UmsMemberReceiveAddress): Int {
        val item = getItem(id, memberId)
        copyPropertiesIgnoreNull(address, item)
        addressMapper.save(item)
        return item.id!!.toInt()
    }

    override fun list(memberId: Long?): List<UmsMemberReceiveAddress> {
        return addressMapper.findByMemberId(memberId!!)
    }

    override fun getItem(id: Long?, memberId: Long?): UmsMemberReceiveAddress {
        return addressMapper.findByMemberIdAndId(memberId!!, id!!)
    }


}
