package com.ityu.mall.repo

import com.ityu.mall.model.PmsProduct
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.persistence.Tuple

open interface PmsProductRepository : JpaRepository<PmsProduct, Long> {
    @Query(
            """
                 SELECT
            pr.flash_promotion_price,
            pr.flash_promotion_count,
            pr.flash_promotion_limit,
            p.*
        FROM
            sms_flash_promotion_product_relation pr
            LEFT JOIN pms_product p ON pr.product_id = p.id
        WHERE
            pr.flash_promotion_id = ?1
            AND pr.flash_promotion_session_id = ?2
            """
            , nativeQuery = true
    )
    fun getFlashProductList(flashPromotionId: Long, sessionId: Long): List<Tuple>


    @Query(
            """
        SELECT p.*
        FROM
            sms_home_new_product hp
            LEFT JOIN pms_product p ON hp.product_id = p.id
        WHERE
            hp.recommend_status = 1
            AND p.publish_status = 1
        ORDER BY
            hp.sort DESC
            """
            , nativeQuery = true
    )
    fun getNewProductList(pageRequest: PageRequest): List<Tuple>


    @Query("""
         SELECT p.*
        FROM
            sms_home_recommend_product hp
            LEFT JOIN pms_product p ON hp.product_id = p.id
        WHERE
            hp.recommend_status = 1
            AND p.publish_status = 1
        ORDER BY
            hp.sort DESC
            """
            , nativeQuery = true
    )
    fun getHotProductList(pageRequest: PageRequest): List<Tuple>


    fun findAllByDeleteStatusEqualsAndPublishStatusEquals(deleteStatus: Int, publishStatus: Int, pageRequest: Pageable?): List<PmsProduct>


    @Query("""
         SELECT
            p.id id,
            p.`name` name,
            p.sub_title subTitle,
            p.price price,
            p.pic pic,
            p.product_attribute_category_id,
            p.stock stock,
            pa.id attr_id,
            pa.`name` attr_name,
            ps.id sku_id,
            ps.sku_code sku_code,
            ps.price sku_price,
            ps.sp1 sku_sp1,
            ps.sp2 sku_sp2,
            ps.sp3 sku_sp3,
            ps.stock sku_stock,
            ps.pic sku_pic
        FROM
            pms_product p
            LEFT JOIN pms_product_attribute pa ON p.product_attribute_category_id = pa.product_attribute_category_id
            LEFT JOIN pms_sku_stock ps ON p.id=ps.product_id
        WHERE
            p.id = ?1
            AND pa.type = 0
         ORDER BY pa.sort desc
    """, nativeQuery = true)
    fun getCartProduct(id: Long): Tuple


    @Query("""
        SELECT
            p.id,
            p.`name`,
            p.promotion_type,
            p.gift_growth,
            p.gift_point,
            sku.id sku_id,
            sku.price sku_price,
            sku.sku_code sku_sku_code,
            sku.promotion_price sku_promotion_price,
            sku.stock sku_stock,
            sku.lock_stock sku_lock_stock,
            ladder.id ladder_id,
            ladder.count ladder_count,
            ladder.discount ladder_discount,
            full_re.id full_id,
            full_re.full_price full_full_price,
            full_re.reduce_price full_reduce_price
        FROM
            pms_product p
            LEFT JOIN pms_sku_stock sku ON p.id = sku.product_id
            LEFT JOIN pms_product_ladder ladder ON p.id = ladder.product_id
            LEFT JOIN pms_product_full_reduction full_re ON p.id = full_re.product_id
        WHERE
            p.id IN (:ids)
    """, nativeQuery = true)
    fun getPromotionProductList(ids: List<Long>):List<Tuple>


}