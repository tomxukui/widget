package com.ablingbling.library.widget.util;

import android.content.res.Resources;

/**
 * Created by xukui on 2017/11/17.
 */

public class DensityUtil {

    /**
     * 根据手机的分辨率从dp 的单位 转成为px(像素)
     */
    public static final int dp2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px(像素) 的单位 转成为dp
     */
    public static final int px2dp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px(像素) 的单位 转成为sp
     */
    public static final int px2sp(float pxValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从sp(像素) 的单位 转成为px
     */
    public static final int sp2px(float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}