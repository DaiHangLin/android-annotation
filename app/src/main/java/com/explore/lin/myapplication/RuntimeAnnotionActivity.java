package com.explore.lin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.lang.reflect.Proxy;

import annotion.BindView;
import annotion.BindViewInject;
import annotion.ContentView;
import annotion.ContentViewInject;
import interfaces.IVehicle;
import modal.Car;
import modal.VehicleHandler;
import modal.VehicleProxy;

@ContentView(R.layout.activity_main)
public class RuntimeAnnotionActivity extends AppCompatActivity {

    @BindView(R.id.classAnnotion)
    private Button btnGotoClassAnnotionActivity;

    @BindView(R.id.proxy)
    private Button btnProxy;

    @BindView(R.id.dynamicProxy)
    private Button btnDynamicProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentViewInject.inject(RuntimeAnnotionActivity.this);
        BindViewInject.inject(RuntimeAnnotionActivity.this);
        btnGotoClassAnnotionActivity.setText("interface");
        btnProxy.setText("proxy");
        btnDynamicProxy.setText("dynamicProxy");
        btnGotoClassAnnotionActivity.setOnClickListener(l -> {
            IVehicle v = new Car();
            v.start();
            v.forward();
            v.reverse();
            v.stop();
        });
        btnProxy.setOnClickListener(l -> {
            IVehicle v = new Car();
            IVehicle p = new VehicleProxy(v);
            p.start();
            p.forward();
            p.reverse();
            p.stop();
        });
        btnDynamicProxy.setOnClickListener(l -> {
            IVehicle v = new Car();
            ClassLoader cl = IVehicle.class.getClassLoader();
            IVehicle dp = (IVehicle) Proxy.newProxyInstance(cl, new Class[]{IVehicle.class}, new VehicleHandler(v));
            dp.start();
            dp.forward();
            dp.reverse();
            dp.stop();

        });
    }

}
