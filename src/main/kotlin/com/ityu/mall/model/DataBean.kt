package com.ityu.mall.model

data class Triple4<A, B, C, D>(val first: A, val second: B, val third: C, val four: D)
data class Triple3<A, B, C>(val first: A, val second: B, val third: C)
data class Triple2<A, B>(val first: A, val second: B)

data class InputModel(
        var method: String,
        var value: String,
        var to: String,
        var amount: String,
        var address: String
)