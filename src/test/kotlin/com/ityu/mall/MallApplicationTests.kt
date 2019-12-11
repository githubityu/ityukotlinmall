package com.ityu.mall

import com.ityu.mall.component.TestSender
import com.ityu.mall.repo.OmsCartItemRepository
import com.ityu.mall.repo.PmsProductRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime
import javax.persistence.criteria.Predicate

@RunWith(SpringRunner::class)
@SpringBootTest
class MallApplicationTests {

    @Autowired
    lateinit var r : PmsProductRepository
    @Test
    fun contextLoads() {
    }

    @Test
    fun test(){

    }


}
