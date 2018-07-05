package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author lin
 * @date 18/7/5
 * @license Copyright (c) 2016 那镁克
 */

public class RotateLayout extends FrameLayout {
    private Matrix mForward = new Matrix();
    private Matrix mReverse = new Matrix();
    private float[] mTemp = new float[2];

    public RotateLayout(@NonNull Context context) {
        this(context, null);
    }

    public RotateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.rotate(180, getWidth()/2, getHeight()/2);
        mForward = canvas.getMatrix();
        mForward.invert(mReverse);
        canvas.save();
        canvas.setMatrix(mForward);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final float[] temp = mTemp;
        temp[0] = event.getX();
        temp[1] = event.getY();

        mReverse.mapPoints(temp);

        event.setLocation(temp[0], temp[1]);
        return super.dispatchTouchEvent(event);
    }
}
