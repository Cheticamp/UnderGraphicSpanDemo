package com.example.undergraphicdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.example.undergraphic.UnderGraphicSpan;

public class UnderDotSpan extends UnderGraphicSpan {

    private final int mDrawableColor;

    /* Graphic is a dot (circle) */
    public UnderDotSpan(int dotSize, int dotColor, int margin) {
        mDrawableWidth = dotSize;
        //noinspection SuspiciousNameCombination
        mDrawableHeight = mDrawableWidth; // Circle
        mDrawableColor = dotColor;
        mMargin = margin;
    }

    /* drawOval - draw ovals and dots (circles) */
    @SuppressWarnings("unused")
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        super.draw(canvas, text, start, end, x, top, y, bottom, paint);

        paint.setColor(mDrawableColor);
        float textWidth = paint.measureText(text, start, end);
        float offset = mStartShim + x + (textWidth - mDrawableWidth) / 2;
        canvas.save();
        canvas.translate(offset, bottom - mDrawableHeight);
        canvas.drawOval(new RectF(0, 0, mDrawableWidth, mDrawableHeight), paint);
        canvas.restore();
    }
}
