package com.sai628.androidutils.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * @author Sai
 * @ClassName: ImeUtils
 * @Description: 系统输入键盘工具类
 * @date 02/03/2018 16:57
 */
public class ImeUtils
{
    private ImeUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 动态隐藏软键盘
     *
     * @param activity activity
     */
    public static void hideIme(Activity activity)
    {
        View view = activity.getCurrentFocus();
        if (view == null)
        {
            view = new View(activity);
        }

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * 动态显示软键盘
     *
     * @param context  上下文
     * @param editText 输入框
     */
    public static void showIme(Context context, EditText editText)
    {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }


    /**
     * 切换软键盘显示与否状态
     *
     * @param context 上下文
     */
    public static void toggleSoftInput(Context context)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    /**
     * 检测软键盘是否已显示
     *
     * @param context
     * @return
     */
    public static boolean isImeShowing(Context context)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }
}
