package com.example.undergraphic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.LineHeightSpan;
import android.text.style.ReplacementSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public abstract class UnderGraphicSpan extends ReplacementSpan implements LineHeightSpan.WithDensity {
    protected int mDrawableWidth;
    protected int mDrawableHeight;

    // Margin between the bottom of the text and the top of the graphic.
    protected int mMargin;

    // How much we need to jog the text to line up with a larger-than-text-width drawable.
    protected int mStartShim = 0;

    protected UnderGraphicSpan() {
    }

    @Override
    public abstract void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x,
                              int top, int y, int bottom, @NonNull Paint paint);

    // ReplacementSpan override to determine the size (length) of the text.
    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        float baseTextWidth = paint.measureText(text, start, end);

        // If the width of the text is less than the width of our graphic, increase the text width
        // to match the drawable's width; otherwise, just return the width of the text.
        mStartShim = (baseTextWidth < mDrawableWidth) ? (int) (mDrawableWidth - baseTextWidth) / 2 : 0;
        return Math.round(baseTextWidth + mStartShim * 2);
    }

    // LineHeightSpan.WithDensity override to determine the height of the font with the dot.
    @Override
    public void chooseHeight(CharSequence charSequence, int i, int i1, int i2, int i3,
                             Paint.FontMetricsInt fontMetricsInt, TextPaint textPaint) {
        Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();

        fontMetricsInt.top = fm.top;
        fontMetricsInt.ascent = fm.ascent;
        fontMetricsInt.descent = fm.descent;

        // Our font now must accommodate the size of the drawable, so change the bottom of the
        // font to accommodate the drawable.
        fontMetricsInt.bottom = fm.bottom + mDrawableHeight + mMargin;
        fontMetricsInt.leading = fm.leading;
    }

    // Required but not used.
    @Override
    public void chooseHeight(CharSequence charSequence, int i, int i1, int i2, int i3,
                             Paint.FontMetricsInt fontMetricsInt) {
    }

    protected static int dpToPx(int dps, DisplayMetrics metrics) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                               (float) dps, metrics);
    }
}
