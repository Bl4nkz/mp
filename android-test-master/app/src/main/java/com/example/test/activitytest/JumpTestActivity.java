package com.example.test.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

//通常继承AppCompatActivity以保证兼容性
public class JumpTestActivity extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();
    private TextView mMsg;
    private CheckBox mCheckBoxNewTask;
    private CheckBox mCheckBoxClearTop;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置刚刚创建的布局文件为内容视图
        setContentView(R.layout.activity_jump_test);
        //找到刚才的文本组件
        mMsg = findViewById(R.id.tvMsg);
        //设置类名到文本上
        mMsg.setText(getClass().getSimpleName());
        mCheckBoxNewTask = findViewById(R.id.checkBox1);
        mCheckBoxClearTop = findViewById(R.id.checkBox2);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent:" + intent);
    }

    //xml上绑定的onClick方法
    public void onClick(View v) {
        Intent i = null;
        //根据Id响应不同功能,这里跳转到不同的活动
        switch (v.getId()) {
            case R.id.btnA:
                i = new Intent(this, StandardActivity.class);
                break;
            case R.id.btnB:
                i = new Intent(this, SingleTopActivity.class);
                break;
            case R.id.btnC:
                i = new Intent(this, SingleTaskActivity.class);
                break;
            case R.id.btnD:
                i = new Intent(this, SingleInstanceActivity.class);
                break;
        }
        if (i != null) {
            if (mCheckBoxNewTask.isChecked()) {
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if(mCheckBoxClearTop.isChecked()){
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            startActivity(i);
        }
    }
}
