package com.sai628.androidutils.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sai628.androidutils.R;


/**
 * @author Sai
 * @ClassName:
 * @Description:
 * @date 13/02/2017 18:08
 */
public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
    }
}
