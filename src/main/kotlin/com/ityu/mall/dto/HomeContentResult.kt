package com.ityu.mall.dto


import com.ityu.mall.model.CmsSubject
import com.ityu.mall.model.PmsBrand
import com.ityu.mall.model.PmsProduct
import com.ityu.mall.model.SmsHomeAdvertise
import lombok.Getter
import lombok.Setter


/**
 * 首页内容返回信息封装
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
class HomeContentResult {
    //轮播广告
    var advertiseList: List<SmsHomeAdvertise>? = null
        set(advertiseList) {
            field = advertiseList
        }
    //推荐品牌
    var brandList: List<PmsBrand>? = null
        set(brandList) {
            field = brandList
        }
    //当前秒杀场次
    var homeFlashPromotion: HomeFlashPromotion? = null
        set(homeFlashPromotion) {
            field = homeFlashPromotion
        }
    //新品推荐
    var newProductList: List<PmsProduct>? = null
        set(newProductList) {
            field = newProductList
        }
    //人气推荐
    var hotProductList: List<PmsProduct>? = null
        set(hotProductList) {
            field = hotProductList
        }
    //推荐专题
    var subjectList: List<CmsSubject>? = null
        set(subjectList) {
            field = subjectList
        }
}
