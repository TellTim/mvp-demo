package com.hunter.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * The type Adapt screen utils.
 */
public final class AdaptScreenUtils {

    private static boolean isInitMiui = false;
    private static Field mTmpMetrics;

    /**
     * Adapt for the horizontal screen, and call it in [android.app.Activity.getResources].
     *
     * @param resources the resources
     * @param designWidth the design width
     * @return the resources
     */
    public static Resources adaptWidth(Resources resources, int designWidth) {
        DisplayMetrics dm = getDisplayMetrics(resources);
        float newXdpi = dm.xdpi = (dm.widthPixels * 72f) / designWidth;
        setAppDmXdpi(newXdpi);
        return resources;
    }

    /**
     * Adapt for the vertical screen, and call it in [android.app.Activity.getResources].
     *
     * @param resources the resources
     * @param designHeight the design height
     * @return the resources
     */
    public static Resources adaptHeight(Resources resources, int designHeight) {
        DisplayMetrics dm = getDisplayMetrics(resources);
        float newXdpi = dm.xdpi = (dm.heightPixels * 72f) / designHeight;
        setAppDmXdpi(newXdpi);
        return resources;
    }

    /**
     * Close adapt resources.
     *
     * @param resources The resources.
     * @return the resource
     */
    public static Resources closeAdapt(Resources resources) {
        DisplayMetrics dm = getDisplayMetrics(resources);
        float newXdpi = dm.xdpi = dm.density * 72;
        setAppDmXdpi(newXdpi);
        return resources;
    }

    /**
     * Value of pt to value of px.
     *
     * @param ptValue The value of pt.
     * @return value of px
     */
    public static int pt2Px(float ptValue) {
        DisplayMetrics metrics = Utils.getApp().getResources().getDisplayMetrics();
        return (int) (ptValue * metrics.xdpi / 72f + 0.5);
    }

    /**
     * Value of px to value of pt.
     *
     * @param pxValue The value of px.
     * @return value of pt
     */
    public static int px2Pt(float pxValue) {
        DisplayMetrics metrics = Utils.getApp().getResources().getDisplayMetrics();
        return (int) (pxValue * 72 / metrics.xdpi + 0.5);
    }

    /**
     * dip-->px
     *
     * @param dip the dip
     * @return the int
     */
    public static int dp2Px(int dip) {

        float density = Utils.getApp().getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }


    private static void setAppDmXdpi(final float xdpi) {
        Utils.getApp().getResources().getDisplayMetrics().xdpi = xdpi;
    }

    private static DisplayMetrics getDisplayMetrics(Resources resources) {
        DisplayMetrics miuiDisplayMetrics = getMiuiTmpMetrics(resources);
        if (miuiDisplayMetrics == null) {
          return resources.getDisplayMetrics();
        }
        return miuiDisplayMetrics;
    }

    private static DisplayMetrics getMiuiTmpMetrics(Resources resources) {
        if (!isInitMiui) {
            DisplayMetrics ret = null;
            String simpleName = resources.getClass().getSimpleName();
            if ("MiuiResources".equals(simpleName) || "XResources".equals(simpleName)) {
                try {
                    mTmpMetrics = Resources.class.getDeclaredField("mTmpMetrics");
                    mTmpMetrics.setAccessible(true);
                    ret = (DisplayMetrics) mTmpMetrics.get(resources);
                } catch (Exception e) {
                    Log.e("AdaptScreenUtils", "no field of mTmpMetrics in resources.");
                }
            }
            isInitMiui = true;
            return ret;
        }
        if (mTmpMetrics == null) {
          return null;
        }
        try {
            return (DisplayMetrics) mTmpMetrics.get(resources);
        } catch (Exception e) {
            return null;
        }
    }
}
