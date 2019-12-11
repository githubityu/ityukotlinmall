package com.ityu.mall.util



import lombok.extern.slf4j.Slf4j

@Slf4j
object Test {
    fun d() {

    }
}

fun main(args: Array<String>) {
    val dat = mutableListOf<Long>(1,2,3)
   val data =  dat.joinToString {
        """
            '$it'
        """.trimIndent()
    }
    println(data)
}
