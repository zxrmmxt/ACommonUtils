package com.xt.m_common_utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by xuti on 2018/9/30.
 */
public class MWebViewUtils {
    public static void goToBrower(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
