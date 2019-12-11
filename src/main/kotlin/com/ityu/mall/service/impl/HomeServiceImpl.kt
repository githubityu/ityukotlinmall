package com.ityu.mall.service.impl


import com.ityu.mall.dto.FlashPromotionProduct
import com.ityu.mall.dto.HomeContentResult
import com.ityu.mall.dto.HomeFlashPromotion
import com.ityu.mall.model.*
import com.ityu.mall.repo.*
import com.ityu.mall.service.HomeService
import com.ityu.mall.util.NativeResultProcessUtils
import com.ityu.mall.util.getDate
import com.ityu.mall.util.getTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

/**
 * 首页内容管理Service实现类
 * Created by macro on 2019/1/28.
 */
@Service
class HomeServiceImpl : HomeService {
    @Autowired
    lateinit var advertiseMapper: SmsHomeAdvertiseRepository
    @Autowired
    lateinit var flashPromotionMapper: SmsFlashPromotionRepository
    @Autowired
    lateinit var promotionSessionMapper: SmsFlashPromotionSessionRepository
    @Autowired
    lateinit var productMapper: PmsProductRepository
    @Autowired
    lateinit var productCategoryMapper: PmsProductCategoryRepository
    @Autowired
    lateinit var subjectMapper: CmsSubjectRepository

    @Autowired
    lateinit var homeBrandR: SmsHomeBrandRepository
    @Autowired
    lateinit var rrandsR: PmsBrandRepository


    override fun content(): HomeContentResult {
        val result = HomeContentResult()
        //获取首页广告
        result.advertiseList = getHomeAdvertiseList()
//        homeBrandR.findALl(Specification { root, query, cb ->
//            val list = mutableListOf<Predicate>()
//            val join = root.join<PmsBrand,SmsHomeBrand>("recommendsBrand",
//                    JoinType.LEFT)
//            list.add(cb.equal(join.get<PmsBrand>("id"), "brand_id"))
//            return@Specification cb.and(*list.toTypedArray())
//        })
        val brands = rrandsR.getRecommendBrandList(PageRequest.of(0, 4))
        //获取推荐品牌
        result.brandList = brands
        //获取秒杀信息
        result.homeFlashPromotion = getHomeFlashPromotion()
        //获取新品推荐
        val datas = productMapper.getNewProductList(PageRequest.of(0, 4)).map {
            val processResult = NativeResultProcessUtils.processResult(it, PmsProduct::class.java)
            processResult
        }
        result.newProductList = datas
        //获取人气推荐
        result.hotProductList = productMapper.getHotProductList(PageRequest.of(0, 4)).map {
            NativeResultProcessUtils.processResult(it, PmsProduct::class.java)
        }
        //获取推荐专题
        result.subjectList = subjectMapper.getRecommendSubjectList(PageRequest.of(0, 4)).map {
            NativeResultProcessUtils.processResult(it, CmsSubject::class.java)
        }
        return result
    }

    override fun recommendProductList(pageSize: Int?, pageNum: Int?): List<PmsProduct> {
        return productMapper.findAllByDeleteStatusEqualsAndPublishStatusEquals(0, 1, PageRequest.of(pageNum!!, pageSize!!))
    }

    override fun getProductCateList(parentId: Long?): List<PmsProductCategory> {
        return productCategoryMapper.findAllByShowStatusEqualsAndParentIdEquals(1, parentId!!, Sort.by(Sort.Direction.DESC, "sort"))
    }

    override fun getSubjectList(cateId: Long?, pageSize: Int?, pageNum: Int?): List<CmsSubject> {
        return if (cateId == null) {
            subjectMapper.findAllByShowStatusEquals(1, PageRequest.of(pageNum!!, pageSize!!))
        } else {
            subjectMapper.findAllByShowStatusEqualsAndCategoryIdEquals(1, cateId, PageRequest.of(pageNum!!, pageSize!!))
        }
    }

    private fun getHomeAdvertiseList(): List<SmsHomeAdvertise> {
        return advertiseMapper.findAllByTypeEqualsAndStatusEquals(1, 1, Sort.by(Sort.Direction.DESC, "sort"))
    }


    //根据时间获取秒杀活动
    private fun getFlashPromotion(date: Date): SmsFlashPromotion? {
        val currDate = getDate(date)
        val flashPromotionList = flashPromotionMapper.findAllByStatusEqualsAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, currDate, currDate)
        return if (flashPromotionList.isNotEmpty()) {
            flashPromotionList[0]
        } else null
    }

    private fun getHomeFlashPromotion(): HomeFlashPromotion {
        val homeFlashPromotion = HomeFlashPromotion()
        //获取当前秒杀活动
        val now = Date()
        val flashPromotion = getFlashPromotion(now)
        if (flashPromotion != null) {
            //获取当前秒杀场次
            val flashPromotionSession = getFlashPromotionSession(now)
            if (flashPromotionSession != null) {
                homeFlashPromotion.startTime = (flashPromotionSession!!.startTime)
                homeFlashPromotion.endTime = (flashPromotionSession!!.endTime)
                //获取下一个秒杀场次
                val nextSession = getNextFlashPromotionSession(homeFlashPromotion.startTime!!)
                if (nextSession != null) {
                    homeFlashPromotion.nextStartTime = (nextSession!!.startTime)
                    homeFlashPromotion.nextEndTime = (nextSession!!.endTime)
                }
                //获取秒杀商品
                val flashProductList = productMapper.getFlashProductList(flashPromotion.id!!
                        , flashPromotionSession!!.id!!)
                homeFlashPromotion.productList = flashProductList.map {
                    NativeResultProcessUtils.processResult(it, FlashPromotionProduct::class.java)
                }
            }
        }
        return homeFlashPromotion
    }


    //根据时间获取秒杀场次
    private fun getFlashPromotionSession(date: Date): SmsFlashPromotionSession? {
        val currTime = getTime(date)
        val promotionSessionList = promotionSessionMapper.findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(currTime, currTime)
        return if (promotionSessionList.isNotEmpty()) {
            promotionSessionList[0]
        } else null
    }

    private fun getNextFlashPromotionSession(date: Date): SmsFlashPromotionSession? {
        val promotionSessionList = promotionSessionMapper.findAllByStartTimeGreaterThan(getTime(date), Sort.by(Sort.Direction.ASC, "startTime"))
        return if (promotionSessionList.isNotEmpty()) {
            promotionSessionList[0]
        } else null
    }


}


