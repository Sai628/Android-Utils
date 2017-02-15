package com.sai628.androidutils.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


/**
 * @author Sai
 * @ClassName: AnimationUtils
 * @Description: 动画工具类
 * @date 15/02/2017 12:30
 */
public class AnimationUtils
{
    private AnimationUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 从顶部进入动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation inFromTopAnimation(long durationMills)
    {
        Animation inFromTop = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromTop.setDuration(durationMills);
        inFromTop.setInterpolator(new AccelerateInterpolator());

        return inFromTop;
    }


    /**
     * 从底部进入动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation inFromBottomAnimation(long durationMills)
    {
        Animation inFromBottom = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromBottom.setDuration(durationMills);
        inFromBottom.setInterpolator(new AccelerateInterpolator());

        return inFromBottom;
    }


    /**
     * 从左边进入动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation inFromLeftAnimation(long durationMills)
    {
        Animation inFromLeft = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(durationMills);
        inFromLeft.setInterpolator(new AccelerateInterpolator());

        return inFromLeft;
    }


    /**
     * 从右边进入动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation inFromRightAnimation(long durationMills)
    {
        Animation inFromRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(durationMills);
        inFromRight.setInterpolator(new AccelerateInterpolator());

        return inFromRight;
    }


    /**
     * 从中部进入动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation inFromCenterAnimation(long durationMills)
    {
        Animation inFromCenter = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        inFromCenter.setDuration(durationMills);
        inFromCenter.setInterpolator(new AccelerateInterpolator());
        inFromCenter.setFillAfter(true);

        return inFromCenter;
    }


    /**
     * 从顶部退出动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation outToTopAnimation(long durationMills)
    {
        Animation outToTop = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f);
        outToTop.setDuration(durationMills);
        outToTop.setInterpolator(new AccelerateInterpolator());

        return outToTop;
    }


    /**
     * 从底部退出动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation outToBottomAnimation(long durationMills)
    {
        Animation outToBottom = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f);
        outToBottom.setDuration(durationMills);
        outToBottom.setInterpolator(new AccelerateInterpolator());

        return outToBottom;
    }


    /**
     * 从左边退出动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation outToLeftAnimation(long durationMills)
    {
        Animation outToLeft = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        outToLeft.setDuration(durationMills);
        outToLeft.setInterpolator(new AccelerateInterpolator());

        return outToLeft;
    }


    /**
     * 从右边退出动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation outToRightAnimation(long durationMills)
    {
        Animation outToRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        outToRight.setDuration(durationMills);
        outToRight.setInterpolator(new AccelerateInterpolator());

        return outToRight;
    }


    /**
     * 从中部退出动画
     *
     * @param durationMills 动画持续时间(毫秒)
     * @return
     */
    public static Animation outToCenterAnimation(long durationMills)
    {
        Animation outToCenter = new ScaleAnimation(1.0f, 0.7f, 1.0f, 0.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        outToCenter.setDuration(durationMills);
        outToCenter.setInterpolator(new AccelerateInterpolator());

        return outToCenter;
    }


    /**
     * 抖动动画
     *
     * @param counts        1秒钟内的抖动次数
     * @param durationMills 动画持续时间
     * @return
     */
    public static Animation shakeAnimation(int counts, long durationMills)
    {
        Animation animation = new TranslateAnimation(0, 10, 0, 0);
        animation.setDuration(durationMills);
        animation.setInterpolator(new CycleInterpolator(counts));

        return animation;
    }
}
