package com.spike.crowd.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 实现对字符串的 md5 加密
 */
public class Encryption {

    private static String algorithm = "md5";

    public static String encrypt(String source) {

        // 判断 source 是否有效
        if (source == null || source.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALID);
        }

        // 执行加密
        byte[] encryptedBytes = new byte[0];
        try {
            encryptedBytes = MessageDigest.getInstance(algorithm).digest(source.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 按照 16 进制将 bigInteger 的值转换为字符串
        int signum = 1;
        int radix = 16;
        BigInteger bigInteger = new BigInteger(signum, encryptedBytes);

        return bigInteger.toString(radix).toUpperCase();
    }

}
