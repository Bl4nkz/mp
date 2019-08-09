package com.example.test.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DeliverDataActivity extends TextActivity {

    @Override
    protected String getText() {
        return "向跳转的Activity传递数据";
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, FeedBackActivity.class);
        //向Intent内添加数据，处理String,还有其他基本类型数据，Parcelable/Serializable对象等可以设置
        i.putExtra(FeedBackActivity.KEY_PARAM, "param from DeliverDataActivity");
        //通过接收结果的方式启动Activity,指定RequestCode
        startActivityForResult(i, FeedBackActivity.REQUEST_GET_RESULT);
    }

    //在onActivityResult方法中处理结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断requestCode
        if (requestCode == FeedBackActivity.REQUEST_GET_RESULT) {
            //判断结果
            if (resultCode == Activity.RESULT_OK && data != null) {
                //弹出Toast显示结果
                Toast.makeText(this, "get Result:"
                        + data.getStringExtra(FeedBackActivity.KEY_RESULT), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "get Result failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
