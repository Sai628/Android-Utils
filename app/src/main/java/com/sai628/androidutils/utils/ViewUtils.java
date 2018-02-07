package com.sai628.androidutils.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;


/**
 * @author Sai
 * @ClassName: ViewUtils
 * @Description: 视图工具类
 * @date 07/02/2018 16:14
 */
public class ViewUtils
{
    public static final long SCROLL_DELAY_TIME = 100L;


    private ViewUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class");
    }


    /**
     * 根据index索引值获取viewPager中的Fragment对象
     *
     * @param viewPager 分页视图对象
     * @param index     索引值
     * @return Fragment对象
     */
    public static Fragment getItem(ViewPager viewPager, int index)
    {
        if (viewPager == null)
        {
            return null;
        }

        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter != null && adapter instanceof FragmentPagerAdapter)
        {
            FragmentPagerAdapter pagerAdapter = (FragmentPagerAdapter) adapter;
            if (index >= 0 && index < pagerAdapter.getCount())
            {
                return pagerAdapter.getItem(index);
            }
        }

        return null;
    }


    /**
     * 设置视图控件的margin值
     *
     * @param view   待设置margin值的视图
     * @param left   左边margin
     * @param top    顶部margin
     * @param right  右边margin
     * @param bottom 底部margin
     */
    public static void setMargins(View view, int left, int top, int right, int bottom)
    {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)
        {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }


    /**
     * 根据子View的内容设置ListView的高度
     *
     * @param listview 列表对象
     * @return int 设置完成后的列表高度
     */
    public static int setListViewHeightBasedOnChildren(ListView listview)
    {
        ListAdapter adapter = listview.getAdapter();
        if (adapter == null)
        {
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++)
        {
            View listItem = adapter.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (adapter.getCount() - 1));
        listview.setLayoutParams(params);

        return params.height;
    }


    /**
     * 对ViewGroup中的子视图设置动画
     *
     * @param viewGroup 视图组
     * @param animation 动画
     */
    public static void showAnimationBaseOnChildView(ViewGroup viewGroup, Animation animation)
    {
        if (viewGroup != null)
        {
            for (int i = 0, count = viewGroup.getChildCount(); i < count; i++)
            {
                viewGroup.getChildAt(i).setAnimation(animation);
                animation.startNow();
            }
        }
    }


    /**
     * 设置视图出现动画
     *
     * @param view      视图
     * @param animation 动画
     */
    public static void showView(View view, Animation animation)
    {
        if (view.getVisibility() == View.VISIBLE)
        {
            return;
        }

        view.setVisibility(View.VISIBLE);
        view.setAnimation(animation);
        animation.startNow();
    }


    /**
     * 设置视图隐藏动画
     *
     * @param view      视图
     * @param animation 动画
     */
    public static void hiddenView(View view, Animation animation)
    {
        if (view.getVisibility() == View.INVISIBLE)
        {
            return;
        }

        view.setAnimation(animation);
        animation.startNow();
        view.setVisibility(View.INVISIBLE);
    }


    /**
     * 设置视图消失动画
     *
     * @param view      视图
     * @param animation 动画
     */
    public static void dismissView(View view, Animation animation)
    {
        if (view.getVisibility() == View.GONE)
        {
            return;
        }

        view.setAnimation(animation);
        animation.startNow();
        view.setVisibility(View.GONE);
    }


    /**
     * 延迟滚动视图到顶部
     * <p>默认延迟为{@link ViewUtils#SCROLL_DELAY_TIME}毫秒</p>
     *
     * @param scrollView 滚动视图
     */
    public static void scrollToTopDelayed(final ScrollView scrollView)
    {
        scrollToTopDelayed(scrollView, SCROLL_DELAY_TIME);
    }


    /**
     * 根据延迟时间delayMillis滚动视图到顶部
     *
     * @param scrollView  滚动视图
     * @param delayMillis 延迟毫秒数
     */
    public static void scrollToTopDelayed(final ScrollView scrollView, long delayMillis)
    {
        if (scrollView != null)
        {
            scrollView.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    scrollView.scrollTo(0, 0);
                }
            }, delayMillis);
        }
    }


    /**
     * 延迟滚动视图到底部
     * <p>默认延迟为{@link ViewUtils#SCROLL_DELAY_TIME}毫秒</p>
     *
     * @param scrollView 滚动视图
     */
    public static void scrollToBottomDelayed(final ScrollView scrollView)
    {
        scrollToBottomDelayed(scrollView, SCROLL_DELAY_TIME);
    }


    /**
     * 根据延迟时间delayMillis滚动视图到底部
     *
     * @param scrollView  滚动视图
     * @param delayMillis 延迟毫秒数
     */
    public static void scrollToBottomDelayed(final ScrollView scrollView, long delayMillis)
    {
        if (scrollView != null)
        {
            scrollView.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            }, delayMillis);
        }
    }
}
