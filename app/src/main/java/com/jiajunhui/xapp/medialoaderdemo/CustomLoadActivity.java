package com.jiajunhui.xapp.medialoaderdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Taurus on 2016/8/29.
 */
public class CustomLoadActivity extends AppCompatActivity {

    private TextView tv_photo_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        tv_photo_info = (TextView) findViewById(R.id.tv_photo_info);

    }
}
