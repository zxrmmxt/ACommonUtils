package com.xt.m_common_utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by xuti on 2018/9/29.
 */
public class MToastUtils {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void showShort(final Context context, final String text) {
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
