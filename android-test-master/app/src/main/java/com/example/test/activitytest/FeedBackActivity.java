package com.example.test.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class FeedBackActivity extends TextActivity {
    //定义常量
    public static final int REQUEST_GET_RESULT = 1;
    public static final String KEY_PARAM = "com.example.test.activitytest.FeedBackActivity.KEY_PARAM";
    public static final String KEY_RESULT = "com.example.test.activitytest.FeedBackActivity.KEY_RESULT";

    @Override
    protected String getText() {
        //通过getIntent方法获取启动时指定的Intent对象，通过getXXXExtra获取数据,这里获取String对象
        return "收到数据：" + getIntent().getStringExtra(KEY_PARAM) + "\n反馈数据并结束";
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();
        //构造Intent返回结果
        result.putExtra(KEY_RESULT, "msg from FeedBackActivity");
        //设置结果码和数据
        setResult(Activity.RESULT_OK, result);
        //结束Activity
        finish();
    }
}
