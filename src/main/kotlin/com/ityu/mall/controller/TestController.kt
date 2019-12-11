package com.ityu.mall.controller

import com.ityu.mall.component.TestSender
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
@RequestMapping("/send")
@Slf4j
class TestController {
    @Autowired
    lateinit var sender: TestSender
    @ApiOperation(value = "测试")
    @GetMapping(value = "/testA")
    fun testA(): String {
        sender.sendMessage("${LocalDateTime.now().second}",50000)
        return "发送成功"
    }
}