package com.explore.lin.myapplication;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.explore.lin.processorlib.ClassBindActivity;
import com.explore.lin.processorlib.ClassBindView;

import java.io.IOException;

import annotion.FinderInject;

/**
 * @author lin
 * @date 18/7/5
 * @license Copyright (c) 2016 那镁克
 */

@ClassBindActivity
public class RotateVideoActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener {

    private static final String TAG = RotateVideoActivity.class.getSimpleName();
    private MediaPlayer mMediaPlayer;

    @ClassBindView(R.id.textureview)
    TextureView textureView;

    @ClassBindView(R.id.rotate)
    Button btnRotate;

    private float degree = 90;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textureview);
        FinderInject.inject(RotateVideoActivity.this);
        textureView.setSurfaceTextureListener(this);
        btnRotate.setOnClickListener(l -> {
            Matrix matrix = new Matrix();
            int width = textureView.getWidth();
            int height = textureView.getHeight();
            matrix.postRotate(degree, width/2, height/2);
            textureView.setTransform(matrix);
            degree += 90;
        });
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Surface s = new Surface(surface);

        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource("http://www.rayclass.com/ray/resource/uuid/6264118e3d1b7a005cedcc491b0627e7/src");
            mMediaPlayer.setSurface(s);
            mMediaPlayer.prepare();
            mMediaPlayer.setOnBufferingUpdateListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnVideoSizeChangedListener(this);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int w, int h) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
