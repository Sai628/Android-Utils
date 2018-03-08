package com.sai628.androidutils.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


/**
 * @author Sai
 * @ClassName: HandlerUtils
 * @Description: Handler工具类
 * @date 08/03/2018 14:21
 */
public class HandlerUtils
{
    private HandlerUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    public interface OnReceiveMessageListener
    {
        void handleMessage(Message message);
    }


    public static class HandlerHolder extends Handler
    {
        WeakReference<OnReceiveMessageListener> listenerWeakReference;


        /**
         * 注意：推荐在Activity或者Activity内部持有类中实现该接口，不要使用匿名类，可能会被GC
         *
         * @param listener 接收消息回调接口
         */
        public HandlerHolder(OnReceiveMessageListener listener)
        {
            listenerWeakReference = new WeakReference<>(listener);
        }


        @Override
        public void handleMessage(Message msg)
        {
            if (listenerWeakReference != null && listenerWeakReference.get() != null)
            {
                listenerWeakReference.get().handleMessage(msg);
            }
        }
    }
}
