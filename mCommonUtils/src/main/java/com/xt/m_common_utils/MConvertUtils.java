package com.xt.m_common_utils;

import com.blankj.utilcode.util.ConvertUtils;

/**
 * Created by xuti on 2018/9/29.
 */
public class MConvertUtils {
    public static String bytes2HexString(byte[] bytes) {
        return ConvertUtils.bytes2HexString(bytes);
    }

    public static byte[] hexString2Bytes(String hexString) {
        return ConvertUtils.hexString2Bytes(hexString);
    }
}
