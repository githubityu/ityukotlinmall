package com.ityu.mall.eth


import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

/**
 * Created by yujunlong on 2018/4/2.
 */

object Web3JClient {

    val MAIN_URL = "https://mainnet.infura.io"
    val ROPSTEN_URL = "https://ropsten.infura.io"
    val GETECH_URL = "http://47.95.6.91:1777"

    fun getWeb3j(isTest: Int): Web3j {
        return Web3j.build(HttpService(if (isTest == 0) GETECH_URL else ROPSTEN_URL))
    }

}
