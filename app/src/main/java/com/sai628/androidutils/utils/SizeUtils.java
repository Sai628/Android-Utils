package com.sai628.androidutils.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author Sai
 * @ClassName: SizeUtils
 * @Description: 尺寸相关工具类
 * @date 24/02/2017 14:21
 */
public class SizeUtils
{
    private SizeUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * dp 转 px
     *
     * @param dpValue dp 值
     * @return px 值
     */
    public static int dp2px(float dpValue)
    {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * px 转 dp
     *
     * @param pxValue px 值
     * @return dp 值
     */
    public static int px2dp(float pxValue)
    {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * sp 转 px
     *
     * @param spValue sp 值
     * @return px 值
     */
    public static int sp2px(float spValue)
    {
        final float fontScale = Utils.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * px 转 sp
     *
     * @param pxValue px 值
     * @return sp 值
     */
    public static int px2sp(float pxValue)
    {
        final float fontScale = Utils.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 各种单位转换
     *
     * @param unit    单位
     * @param value   值
     * @param metrics DisplayMetrics
     * @return 转换结果
     */
    public static float applyDimension(int unit, float value, DisplayMetrics metrics)
    {
        switch (unit)
        {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case TypedValue.COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }

        return 0;
    }


    public interface OnGetSizeListener
    {
        void onGetSize(View view);
    }


    /**
     * 在onCreate中获取视图的尺寸
     * <p>需回调onGetSizeListener接口, 在onGetSize中获取view的宽高</p>
     *
     * @param view     待获取尺寸的视图
     * @param listener 监听器
     */
    public static void forceGetViewSize(final View view, final OnGetSizeListener listener)
    {
        view.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (listener != null)
                {
                    listener.onGetSize(view);
                }
            }
        });
    }


    /**
     * 测量视图尺寸
     *
     * @param view 视图
     * @return int[0]: 视图宽度<br/>int[1]: 视图高度
     */
    public static int[] measureView(View view)
    {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null)
        {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int lpHeight = lp.height;
        int heightSpec;
        if (lpHeight > 0)
        {
            heightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        }
        else
        {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        view.measure(widthSpec, heightSpec);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }


    /**
     * 获取测量视图宽度
     *
     * @param view 视图
     * @return {@code int} 视图宽度
     */
    public static int getMeasureWidth(View view)
    {
        return measureView(view)[0];
    }


    /**
     * 获取测量视图高度
     *
     * @param view 视图
     * @return {@code int} 视图高度
     */
    public static int getMeasureHeight(View view)
    {
        return measureView(view)[1];
    }
}
