package com.xt.m_common_utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xuti on 2018/6/14.
 */
public class MSpUtils {

    /***********************同步更新*********************************/
    private static SharedPreferences getSharedPreferences(Context context, String spFileName) {
        return context.getSharedPreferences(spFileName, Context.MODE_PRIVATE);
    }

    public static boolean putWithCommit(Context context, String spFileName, String key, String value) {
        return getSharedPreferences(context, spFileName).edit().putString(key, value).commit();
    }

    public static boolean putWithCommit(Context context, String spFileName, String key, boolean value) {
        return getSharedPreferences(context, spFileName).edit().putBoolean(key, value).commit();
    }

    public static boolean putWithCommit(Context context, String spFileName, String key, int value) {
        return getSharedPreferences(context, spFileName).edit().putInt(key, value).commit();
    }

    public static boolean removeWithCommit(Context context, String spFileName, String key) {
        return getSharedPreferences(context, spFileName).edit().remove(key).commit();
    }

    /***********************同步更新*********************************/

    public static String get(Context context, String spFileName, String key, String defaultValue) {
        return getSharedPreferences(context, spFileName).getString(key, defaultValue);
    }

    public static boolean get(Context context, String spFileName, String key, boolean defaultValue) {
        return getSharedPreferences(context, spFileName).getBoolean(key, defaultValue);
    }

    public static int get(Context context, String spFileName, String key, int defaultValue) {
        return getSharedPreferences(context, spFileName).getInt(key, defaultValue);
    }
}
