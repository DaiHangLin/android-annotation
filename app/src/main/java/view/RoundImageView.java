package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author lin
 * @date 18/7/4
 * @license Copyright (c) 2016 那镁克
 */

public class RoundImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = RoundImageView.class.getSimpleName();
    private float width, height;
    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
    }

    private int radius = 100;

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: width: " + width );
        Log.e(TAG, "onDraw: height: " + height );
        if (width > 12 && height > 12) {
            Path path = new Path();
            path.moveTo(radius, 0);
            path.lineTo(width - radius, 0);
            path.quadTo(width, 0, width, radius);
            path.lineTo(width, height - radius);
            path.quadTo(width, height, width - radius, height);
            path.lineTo(radius, height);
            path.quadTo(0, height, 0,   height - radius);
            path.lineTo(0, radius);
            path.quadTo(0, 0, radius, 0);
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            canvas.drawPath(path, paint);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
