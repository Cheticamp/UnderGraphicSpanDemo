package com.example.undergraphicdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.example.undergraphic.UnderGraphicSpan;

public class UnderDrawableSpan extends UnderGraphicSpan {

    private final Drawable mDrawable;

    /* Graphic is plain old Drawable */
    public UnderDrawableSpan(Drawable drawable,
                             Integer drawableWidth, Integer drawableHeight, Integer margin) {

        mDrawable = drawable;
        mDrawableWidth = (drawableWidth != null) ? drawableWidth : drawable.getIntrinsicWidth();
        mDrawableHeight = (drawableHeight != null) ? drawableHeight : drawable.getIntrinsicHeight();
        mMargin = (margin != null) ? margin : 0;
    }

    /* Draw plain old Drawable */
    @SuppressWarnings("unused")
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {

        super.draw(canvas, text, start, end, x, top, y, bottom, paint);

        float textWidth = paint.measureText(text, start, end);
        float offset = mStartShim + x + (textWidth - mDrawableWidth) / 2;
        mDrawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        canvas.save();
        canvas.translate(offset, bottom - mDrawableHeight);
        mDrawable.draw(canvas);
        canvas.restore();
    }
}
