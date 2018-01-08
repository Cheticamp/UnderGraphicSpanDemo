package com.example.undergraphicdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.example.undergraphic.UnderGraphicSpan;

public class UnderOvalSpan extends UnderGraphicSpan {

    private final int mDrawableColor;

    /* Graphic is an oval */
    public UnderOvalSpan(int ovalWidth, int ovalHeight, int ovalColor, int margin) {

        mDrawableWidth = ovalWidth;
        mDrawableHeight = ovalHeight;
        mDrawableColor = ovalColor;
        mMargin = margin;
    }

    /* drawOval - draw an oval (or a circle) */
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
