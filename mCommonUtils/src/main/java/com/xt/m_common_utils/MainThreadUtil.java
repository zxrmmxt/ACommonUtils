package com.xt.m_common_utils;

import android.os.Handler;
import android.os.Looper;


/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class MainThreadUtil {

    private Handler mHandler;
    private static MainThreadUtil sMainThreadUtil;

    private MainThreadUtil() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThreadUtil getInstance() {
        if (sMainThreadUtil == null) {
            sMainThreadUtil = new MainThreadUtil();
        }
        return sMainThreadUtil;
    }
}
