package com.example.marvelworld.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

    public static String getMd5(long time, String privateKey, String publicKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest((time + privateKey + publicKey).getBytes());
            BigInteger num = new BigInteger(1, digest);
            StringBuilder hashText = new StringBuilder(num.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
           throw new IllegalArgumentException(e);
        }
    }
}
