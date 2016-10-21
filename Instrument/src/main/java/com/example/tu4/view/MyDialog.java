package com.example.tu4.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.tu4.R;

/**
 * Created by MR.WEN on 2016/10/20.
 */

public class MyDialog extends Dialog {
    Context context;

    public MyDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog);
    }
}
