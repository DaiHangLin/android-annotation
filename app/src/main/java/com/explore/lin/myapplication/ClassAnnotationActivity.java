package com.explore.lin.myapplication;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.explore.lin.processorlib.ClassBindActivity;
import com.explore.lin.processorlib.ClassBindView;

import annotion.FinderInject;

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

    @ClassBindView(R.id.round)
    ImageView ivRound;

    @ClassBindView(R.id.videoview)
    VideoView videoView;

    @ClassBindView(R.id.videoview2)
    VideoView videoView2;

    @ClassBindView(R.id.ratation)
    Button btnRotate;


    private final static String TAG = ClassAnnotationActivity.class.getSimpleName();
    private OrientationEventListener mOrientationListener; // 屏幕方向改变监听器
    private boolean mIsLand = false; // 是否是横屏
    private boolean mClick = false; // 是否点击
    private boolean mClickLand = true; // 点击进入横屏
    private boolean mClickPort = true; // 点击进入竖屏

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        FinderInject.inject(this);
        tv1.setText("class annotation build success");
        tv1.setOnClickListener(v -> ivRound.setImageResource(R.drawable.ic_launcher_background));
//        startListener();
        videoView.setVideoURI(Uri.parse("http://60.205.145.196/ray/resource/view/img_src/2827119"));
        videoView.start();
        videoView2.setVideoURI(Uri.parse("http://60.205.145.196/ray/resource/view/img_src/2827119"));
        videoView2.start();
        btnRotate.setOnClickListener(l -> {
            mClick = true;
            if (!mIsLand) {
//                if (onClickOrientationListener != null) {
//                    onClickOrientationListener.landscape();
//                }
                Log.e(TAG, "onCreate: onClickOrientationListener.landscape()" );
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mIsLand = true;
                mClickLand = false;
            } else {
//                if (onClickOrientationListener != null) {
//                    onClickOrientationListener.portrait();
//                }
                Log.e(TAG, "onCreate: onClickOrientationListener.REVERSE_LANDSCAPE()" );
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                mIsLand = false;
                mClickPort = false;
            }
        });
    }

    /**
     * 开启监听器     */
    private final void startListener() {
        mOrientationListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int rotation) {
                // 设置竖屏
                if (((rotation >= 0) && (rotation <= 30)) || (rotation >= 330)) {
                    if (mClick) {
                        if (mIsLand && !mClickLand) {
                            return;
                        } else {
                            mClickPort = true;
                            mClick = false;
                            mIsLand = false;
                        }
                    } else {
                        if (mIsLand) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            mIsLand = false;
                            mClick = false;
                        }
                    }
                }
                // 设置横屏
                else if (((rotation >= 230) && (rotation <= 310))) {
                    if (mClick) {
                        if (!mIsLand && !mClickPort) {
                            return;
                        } else {
                            mClickLand = true;
                            mClick = false;
                            mIsLand = true;
                        }
                    } else {
                        if (!mIsLand) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            mIsLand = true;
                            mClick = false;
                        }
                    }
                }
            }
        };
        mOrientationListener.enable();
    }
}
