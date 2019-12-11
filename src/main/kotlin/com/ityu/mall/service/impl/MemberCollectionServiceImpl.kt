package com.ityu.mall.service.impl


import com.ityu.mall.model.MemberProductCollection
import com.ityu.mall.repo.MemberProductCollectionRepository
import com.ityu.mall.service.MemberCollectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * 会员收藏Service实现类
 * Created by macro on 2018/8/2.
 */
@Service
class MemberCollectionServiceImpl : MemberCollectionService {
    @Autowired
    private val productCollectionRepository: MemberProductCollectionRepository? = null

    override fun addProduct(productCollection: MemberProductCollection): Int {
        var count = 0
        val findCollection = productCollectionRepository!!.findByMemberIdAndProductId(productCollection.memberId, productCollection.productId)
        if (findCollection == null) {
            productCollection.createTime = Date()
            productCollectionRepository.save(productCollection)
            count = 1
        }
        return count
    }

    override fun deleteProduct(memberId: Long?, productId: Long?): Int {
        return productCollectionRepository!!.deleteByMemberIdAndProductId(memberId, productId)
    }

    override fun listProduct(memberId: Long?): List<MemberProductCollection> {
        return productCollectionRepository!!.findByMemberId(memberId)
    }
}
