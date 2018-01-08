package com.example.undergraphicdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.example.undergraphic.UnderGraphicSpan;

public class UnderDrawableSpan extends UnderGraphicSpan {

    private final Drawable mDrawable;

    /* Graphic is plain old Drawable */
    public UnderDrawableSpan(Context context, Drawable drawable,
                             Integer drawableWidth, Integer drawableHeight, Integer margin) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        mDrawable = drawable;
        mDrawableWidth = (drawableWidth != null)
            ? dpToPx(drawableWidth, metrics) : drawable.getIntrinsicWidth();
        mDrawableHeight = (drawableHeight != null)
            ? dpToPx(drawableHeight, metrics) : drawable.getIntrinsicHeight();
        mMargin = (margin != null) ? dpToPx(margin, metrics) : 0;
    }

    /* Draw plain old Drawable */
    @SuppressWarnings("unused")
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        float textWidth = paint.measureText(text, start, end);
        float offset = mStartShim + x + (textWidth - mDrawableWidth) / 2;

        mDrawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        canvas.save();
        canvas.translate(offset, bottom - mDrawableHeight);
        mDrawable.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(mStartShim, 0);
        canvas.drawText(text, start, end, x, y, paint);
        canvas.restore();
    }
}
