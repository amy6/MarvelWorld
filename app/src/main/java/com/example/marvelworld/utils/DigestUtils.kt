package com.example.marvelworld.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object DigestUtils {
    @JvmStatic
    fun getMd5(time: Long, privateKey: String, publicKey: String): String {
        return try {
            val messageDigest = MessageDigest.getInstance("MD5")
            val digest = messageDigest.digest((time.toString() + privateKey + publicKey).toByteArray())
            val num = BigInteger(1, digest)
            val hashText = StringBuilder(num.toString(16))
            while (hashText.length < 32) {
                hashText.insert(0, "0")
            }
            hashText.toString()
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalArgumentException(e)
        }
    }
}