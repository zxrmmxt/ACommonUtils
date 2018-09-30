package com.xt.m_common_utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by xuti on 2018/7/4.
 */
public class MTextUtils {
    public static void setNexaBoldTTF(TextView textView){
        if(textView != null){
            AssetManager am = textView.getContext().getAssets();
            Typeface typeface = Typeface.createFromAsset(am,"fonts/nexa_bold.otf");
            textView.setTypeface(typeface);
        }
    }
}
