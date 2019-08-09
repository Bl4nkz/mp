package com.example.test;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//新建一个Activity,继承自ListActivity，并添加到manifest.xml中
public class MainActivity extends ListActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        //构造一个Adapter，传入上下文，子视图布局文件和数据列表
        ArrayAdapter<ActivityInfo> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getActivities());
        //设置到ListActivity上
        setListAdapter(adapter);
    }
    //定义一个方法获取当前应用所有的活动
    public List<ActivityInfo> getActivities() {
        try {
            PackageManager pm = this.getPackageManager();
            PackageInfo info = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            List<ActivityInfo> result = new ArrayList<>(Arrays.asList(info.activities));
            for (int i = result.size() - 1; i >= 0; i--) {
                ActivityInfo activityInfo = result.get(i);
                if (activityInfo.name.endsWith(MainActivity.class.getSimpleName())) {
                    //移除当前活动自己
                    result.remove(activityInfo);
                    break;
                }
            }
            return result;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "getActivity failed", e);
            return new ArrayList<>(0);
        }
    }

    //设置点击事件
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ActivityInfo info = (ActivityInfo) l.getAdapter().getItem(position);
        try {
            //构造一个Intent用于跳转
            Intent i = new Intent(this, Class.forName(info.name));
            //跳到对应的活动中
            startActivity(i);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "start activity failed", e);
        }
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
