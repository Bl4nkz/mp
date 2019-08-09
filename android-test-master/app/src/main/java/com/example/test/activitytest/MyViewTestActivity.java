package com.example.test.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class MyViewTestActivity extends TextActivity {
    @Override
    protected String getText() {
        return "匹配自定义data";
    }

    @Override
    public void onClick(View v) {
        //定义一个隐式的Intent
        Intent i = new Intent(Intent.ACTION_VIEW);
        //设置数据
        i.setData(Uri.parse("my://example.com"));
        //启动
        startActivity(i);
    }
}
