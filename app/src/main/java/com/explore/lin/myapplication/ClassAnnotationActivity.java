package com.explore.lin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.explore.lin.processorlib.ClassBindActivity;
import com.explore.lin.processorlib.ClassBindView;

/**
 * @author lin
 * @date 18/6/28
 * @license Copyright (c) 2016 那镁克
 */

@ClassBindActivity
public class ClassAnnotationActivity extends AppCompatActivity{

    @ClassBindView(R.id.tv1)
    TextView tv1;

    @ClassBindView(R.id.tv2)
    TextView tv2;

    @ClassBindView(R.id.tv3)
    TextView tv3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        BindViewFor_ClassAnnotationActivity.bind(this);
        tv1.setText("class annotation build success");
    }
}
