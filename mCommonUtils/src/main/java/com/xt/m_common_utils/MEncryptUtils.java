package com.xt.m_common_utils;

import com.blankj.utilcode.util.EncryptUtils;

/**
 * Created by xuti on 2018/9/30.
 */
public class MEncryptUtils {
    public static String encryptMd5(String data) {
        return EncryptUtils.encryptMD5ToString(data);
    }

    public static String encryptAesNoPadding(String data, String key, String iv) {
        return EncryptUtils.encryptAES2HexString(data.getBytes(), key.getBytes(), "AES/CBC/NoPadding", iv.getBytes());
    }

    public static String encryptAesPKCS5Padding(String data, String key, String iv) {
        return EncryptUtils.encryptAES2HexString(data.getBytes(), key.getBytes(), "AES/CBC/PKCS5Padding", iv.getBytes());
    }
}