package com.sai628.androidutils.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;


/**
 * @author Sai
 * @ClassName: IntentUtils
 * @Description: 意图工具类
 * @date 14/02/2017 15:09
 */
public class IntentUtils
{
    private IntentUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 获取其它应用组件的意图
     *
     * @param packageName 包名
     * @param className   activity 的全路径类名(包含所在的包名)
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className)
    {
        return getComponentIntent(packageName, className, null);
    }


    /**
     * 获取其它应用组件的意图
     *
     * @param packageName 包名
     * @param className   activity 的全路径类名(包含所在的包名)
     * @param bundle
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className, Bundle bundle)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        ComponentName componentName = new ComponentName(packageName, className);
        intent.setComponent(componentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }
}
