package com.example.undergraphicdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.example.undergraphic.UnderGraphicSpan;

public class UnderDotSpan extends UnderGraphicSpan {

    private final int mDrawableColor;

    /* Graphic is a dot (circle) */
    public UnderDotSpan(Context context, int dotSize, int dotColor, int margin) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        mDrawableWidth = dpToPx(dotSize, metrics);
        //noinspection SuspiciousNameCombination
        mDrawableHeight = mDrawableWidth; // Circle
        mDrawableColor = dotColor;
        mMargin = dpToPx(margin, metrics);
    }

    /* drawOval - draw ovals and dots (circles) */
    @SuppressWarnings("unused")
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        float textSize = paint.measureText(text, start, end);
        int textColor = paint.getColor();
        float textWidth = paint.measureText(text, start, end);
        paint.setColor(mDrawableColor);
        canvas.save();

        float offset = mStartShim + x + (textWidth - mDrawableWidth) / 2;
        canvas.translate(offset, bottom - mDrawableHeight);
        canvas.drawOval(new RectF(0, 0, mDrawableWidth, mDrawableHeight), paint);

        // Keep the starting shim, but reset the y-translation to write the text.
        paint.setColor(textColor);
        canvas.restore();
        canvas.save();
        canvas.translate(mStartShim, 0);
        canvas.drawText(text, start, end, x, y, paint);
    }
}
