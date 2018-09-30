package com.xt.m_common_utils;

import java.text.DecimalFormat;

/**
 * Created by xuti on 2018/6/26.
 */
public class DecimalFormatUtils {
    private static final String DECIMAL_FORMAT_1 = "0.0";
    private static final String DECIMAL_FORMAT_2 = "0.00";
    private static final String DECIMAL_FORMAT_3 = "0.000";
    private static final String DECIMAL_FORMAT_4 = "#.##";
    public static String keep1Decimal(double d) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_1);
        return decimalFormat.format(d);
    }

    public static String keep2Decimal(double d) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_2);
        return decimalFormat.format(d);
    }


    public static String keep3Decimal(double d) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_3);
        return decimalFormat.format(d);
    }

    public static String keepAtMost2Decimal(double d) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_4);
        return decimalFormat.format(d);
    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    public static int getDecimalSize(String decimal) {
        if (isNumber(decimal)) {
            String[] split = decimal.split("\\.");
            if (split.length == 2) {
                return split[1].length();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
