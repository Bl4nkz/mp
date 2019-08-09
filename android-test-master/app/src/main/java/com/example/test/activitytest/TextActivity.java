package com.example.test.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

//通常继承AppCompatActivity以保证兼容性
public class TextActivity extends AppCompatActivity {

    public final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_text);
        //设置文本
        ((TextView) findViewById(R.id.tv)).setText(getText());
    }

    //一个重载的onCreate方法，由属性创建时才会调用，正常启动不会调用到这里，setContentView写这里无效，要注意
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    //返回文本
    protected String getText() {
        return "测试隐式启动";
    }

    //定义xml里绑定的点击事件的方法
    public void onClick(View v) {
        //定义一个隐式的Intent
        Intent i = new Intent(Intent.ACTION_VIEW);
        //设置数据
        i.setData(Uri.parse("https://www.vivo.com"));
        //启动
        startActivity(i);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
