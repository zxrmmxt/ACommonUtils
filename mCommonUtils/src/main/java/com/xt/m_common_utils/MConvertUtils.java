package com.xt.m_common_utils;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ConvertUtils;

/**
 * Created by xuti on 2018/9/29.
 */
public class MConvertUtils {
    public static String bytes2HexString(byte[] bytes) {
        return doBytes2HexString(bytes);
    }

    @NonNull
    private static String doBytes2HexString(byte[] bytes) {
        return ConvertUtils.bytes2HexString(bytes);
    }

    public static byte[] hexString2Bytes(String hexString) {
        return doHexString2Bytes(hexString);
    }

    private static byte[] doHexString2Bytes(String hexString) {
        return ConvertUtils.hexString2Bytes(hexString);
    }
}
