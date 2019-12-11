package com.ityu.mall.service


import com.ityu.mall.model.UmsMemberReceiveAddress

/**
 * 用户地址管理Service
 * Created by macro on 2018/8/28.
 */
interface UmsMemberReceiveAddressService {
    /**
     * 添加收货地址
     */
    fun add(address: UmsMemberReceiveAddress,memberId: Long?): Int

    /**
     * 删除收货地址
     * @param id 地址表的id
     */
    fun delete(id: Long?,memberId: Long?): Int

    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
    fun update(id: Long?,memberId: Long?, address: UmsMemberReceiveAddress): Int

    /**
     * 返回当前用户的收货地址
     */
    fun list(memberId: Long?): List<UmsMemberReceiveAddress>

    /**
     * 获取地址详情
     * @param id 地址id
     */
    fun getItem(id: Long?,memberId: Long?): UmsMemberReceiveAddress
}
