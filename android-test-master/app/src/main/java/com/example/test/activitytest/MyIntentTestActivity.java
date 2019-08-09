package com.example.test.activitytest;

import android.content.Intent;
import android.view.View;

public class MyIntentTestActivity extends TextActivity {


    @Override
    protected String getText() {
        return "测试匹配自定义隐式Intent";
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent("com.example.test.MyAction"));
    }
}
