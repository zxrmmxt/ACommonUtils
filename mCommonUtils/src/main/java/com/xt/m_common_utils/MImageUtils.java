package com.xt.m_common_utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.signature.ObjectKey;

import java.io.File;


/**
 * Created by xuti on 2017/5/18.
 * glide动画和placeholder不能同时设置
 */

public class MImageUtils {
    /*************************图片转换*********************************/
    /**
     * base64字符串转成图片
     */
    public static Bitmap getBitmap(String bitmapStr) {
        byte[] bytes = EncodeUtils.base64Decode(bitmapStr);
        return ImageUtils.bytes2Bitmap(bytes);
    }

    /**
     * 根据路径加载图片转成base64字符串
     */
    public static String getBase64Str(String path) {
        Bitmap bitmap = ImageUtils.getBitmap(path);
        byte[] bytes = ImageUtils.bitmap2Bytes(bitmap, Bitmap.CompressFormat.JPEG);
        return EncodeUtils.base64Encode2String(bytes);
    }

    /**
     * Return bitmap.
     *
     * @param resId The resource id.
     * @return bitmap
     */
    public static Bitmap getBitmap(Context context, @DrawableRes final int resId) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /*************************图片转换*********************************/


    /************************************************************网络图片加载*****************************************************************/
    public static void loadImageUrl(Context context, ImageView iv, String url) {
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    public static void loadImageBytes(Context context, ImageView iv, byte[] bytes) {
        Glide.with(context)
                .load(bytes)
                .into(iv);
    }

    public static void loadImageFile(Context context, ImageView iv, File file) {
        //.placeholder(R.drawable.user_placeholder)设置加载中图片
        Glide.with(context)
                .load(file)
                .into(iv);
    }

    public static void loadImageAsGif(Context context, ImageView iv, String url) {
        Glide.with(context)
                .asGif()
                .load(url)
                .into(iv);
    }

    /**
     * {@link MUrlUtils#getLastModified(String)}
     */
    private static void loadImageOfSameUrl(Context context, long lastModified, int resourceIdError, String imageUrl, ImageView iv) {
        RequestOptions requestOptions = new RequestOptions().signature(new ObjectKey(lastModified)).dontAnimate()
                .error(resourceIdError);
        Glide.with(context).load(imageUrl)
                .apply(requestOptions)
                .into(iv);
    }

    /**************************url加载bitmap****************************/
    public void loadImage(Context context, final String url, final int defaultImageId, SimpleTarget<Bitmap> target) {
        RequestOptions requestOptions = new RequestOptions().dontAnimate()
                .placeholder(defaultImageId)
                .error(defaultImageId);
        Glide.with(context).asBitmap().apply(requestOptions).load(url).into(target);
    }
    /**************************url加载bitmap****************************/
    /************************************************************网络图片加载*****************************************************************/

    /**************************设置图片灰色****************************/
    public static Bitmap toGrayScale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayScale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayScale;
    }

    public static Bitmap toGrayScale(Drawable drawable) {
        Bitmap bmpOriginal = ImageUtils.drawable2Bitmap(drawable);
        return toGrayScale(bmpOriginal);
    }

    public static Bitmap toGrayScale(Context context, int imageRes) {
        Bitmap bmpOriginal = getBitmap(context, imageRes);
        return toGrayScale(bmpOriginal);
    }
    /**************************设置图片灰色****************************/
}
