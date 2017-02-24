package com.sai628.androidutils.utils;

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
