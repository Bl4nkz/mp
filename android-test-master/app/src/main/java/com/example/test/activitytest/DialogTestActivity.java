package com.example.test.activitytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

public class DialogTestActivity extends TextActivity {

    @Override
    protected String getText() {
        return "弹出Dialog生命周期变化";
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("dialog")
                .setCancelable(true)
                .setPositiveButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setMessage("dialog message")
                .create()
                .show();
    }
}
