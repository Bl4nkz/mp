package com.example.test.widget;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

//实现点击事件监听接口
public class WidgetTestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "WidgetTestActivity";
    private EditText mUsername;
    private EditText mPassword;
    private ImageView mIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_test);

        //设置接口监听
        findViewById(R.id.btn).setOnClickListener(this);
        //匿名函数
        final TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WidgetTestActivity.this,
                        mPassword.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
        mIv = findViewById(R.id.iv);
        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG, "beforeTextChanged: char=" + s + ",start=" + start + ",count=" + count + ",after=" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: char=" + s + ",start=" + start + ",before=" + before + ",count=" + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: editable=" + s.toString());
            }
        });

        final ProgressBar pbCircle = findViewById(R.id.pb_circle);
        pbCircle.postDelayed(new Runnable() {
            @Override
            public void run() {
                //3秒后移除进度条
                pbCircle.setVisibility(View.GONE);
            }
        }, 3000);

        final ProgressBar pbHor = findViewById(R.id.pb_hor);
        pbHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = pbHor.getProgress();
                //每次点击增加进度，完成后移除
                progress += 10;
                if (progress > 100) {
                    pbHor.setVisibility(View.GONE);
                }
                pbHor.setProgress(progress);
            }
        });

        final SeekBar seekBar = findViewById(R.id.seek_bar);
        //每隔500毫秒增加一次进度，模拟数据变化
        seekBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                int progress = seekBar.getProgress();
                progress += 2;
                if (progress > seekBar.getMax()) {
                    //完成后取消自增任务
                    return;
                }
                seekBar.setProgress(progress);
                seekBar.postDelayed(this, 500);
            }
        }, 500);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //进度改变回调，fromUser用于判断是否用户拖拽
                Log.i(TAG, "onProgressChanged: progress=" + progress + ",fromUser=" + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //拖拽开始
                Log.i(TAG, "onStartTrackingTouch: " + seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
                //拖拽结束
                Log.i(TAG, "onStopTrackingTouch: " + seekBar.getProgress());
            }
        });


        CheckBox cb = findViewById(R.id.cb);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用户勾选或取消时，回调此方法
                tv.setText(isChecked ? "同意" : "取消");
            }
        });


        RadioGroup rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //通过String.format拼接字符串，%s表示占位符，s代表下一个参数是String，d代表数字等
                tv.setText(String.format("当前难度：%s",
                        ((RadioButton) group.findViewById(checkedId)).getText()));
            }
        });
        Switch sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tv.setText(String.format("数据网络下载：%b", isChecked));
            }
        });

    }

    @Override
    public void onClick(View v) {
        //按视图Id响应不同事件
        if (v.getId() == R.id.btn) {
            mIv.setImageResource(R.drawable.ic_home_black_24dp);
        } else if (v.getId() == R.id.tv) {
            Toast.makeText(this, "TextView clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
