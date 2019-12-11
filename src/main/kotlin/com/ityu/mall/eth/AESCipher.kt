package com.ityu.mall.eth

//
//  https://github.com/WelkinXie/AESCipher-Java
//

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.Base64



object AESCipher {
    val IV_STRING = "1234567890123456"
    val KEY = "asdwerftgvcfrgtb"

    //加密
    @Throws(Exception::class)
    fun encryptAES(content: String): String {

        val byteContent = content.toByteArray(charset("UTF-8"))

        // 注意，为了能与 iOS 统一
        // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
        val enCodeFormat = KEY.toByteArray()
        val secretKeySpec = SecretKeySpec(enCodeFormat, "AES")

        val initParam = IV_STRING.toByteArray()
        val ivParameterSpec = IvParameterSpec(initParam)

        // 指定加密的算法、工作模式和填充方式
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

        val encryptedBytes = cipher.doFinal(byteContent)

        // 同样对加密后数据进行 base64 编码
        val encoder = Base64.getEncoder()
        return encoder.encodeToString(encryptedBytes)
    }

    //解密
    @Throws(Exception::class)
    fun decryptAES(content: String): String {

        // base64 解码
        val decoder = Base64.getDecoder()
        val encryptedBytes = decoder.decode(content)

        val enCodeFormat = KEY.toByteArray()
        val secretKey = SecretKeySpec(enCodeFormat, "AES")

        val initParam = IV_STRING.toByteArray()
        val ivParameterSpec = IvParameterSpec(initParam)

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)

        val result = cipher.doFinal(encryptedBytes)

        return String(result, charset("UTF-8"))
    }

    @Throws(NoSuchPaddingException::class, BadPaddingException::class, InvalidAlgorithmParameterException::class, NoSuchAlgorithmException::class, IllegalBlockSizeException::class, UnsupportedEncodingException::class, InvalidKeyException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        var fa: String? = null
        try {
            fa = AESCipher.encryptAES("8A1C7A2EB92183C34578CE4118236444C88089FF5E605A8B08A862E981A4E1B0")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        println(fa)


    }


    fun getDecryptAES(data: String): String {
        try {
            return AESCipher.decryptAES(data)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    fun getEncryptAES(data: String): String {
        try {
            return AESCipher.encryptAES(data)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

}
