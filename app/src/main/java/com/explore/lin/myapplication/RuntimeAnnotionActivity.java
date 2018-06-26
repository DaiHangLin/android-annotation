package com.explore.lin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import annotion.BindView;
import annotion.BindViewInject;
import annotion.ContentView;
import annotion.ContentViewInject;

@ContentView(R.layout.activity_main)
public class RuntimeAnnotionActivity extends AppCompatActivity {

    @BindView(R.id.classAnnotion)
    private Button btnGotoClassAnnotionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentViewInject.inject(RuntimeAnnotionActivity.this);
        BindViewInject.inject(RuntimeAnnotionActivity.this);
        btnGotoClassAnnotionActivity.setText("haha");
    }

}
