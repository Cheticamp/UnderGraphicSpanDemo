package com.example.undergraphicdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.TextView;

import com.example.undergraphic.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display drawable
        Drawable drawable =
            ResourcesCompat.getDrawable(getResources(), R.drawable.gradient_drawable, null);
        String text = "Drawable";
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new UnderDrawableSpan(drawable, null, null, 8),
                          0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = findViewById(R.id.textDrawable);
        textView.setText(spannable, TextView.BufferType.SPANNABLE);

        // Display dot
        text = "Dot";
        spannable = new SpannableString(text);
        spannable.setSpan(new UnderDotSpan(20, 0xFF039BE5, 8),
                          0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView = findViewById(R.id.textDot);
        textView.setText(spannable, TextView.BufferType.SPANNABLE);

        // Display an oval
        text = "Oval";
        spannable = new SpannableString(text);
        spannable.setSpan(new UnderOvalSpan(60, 48, 0xFF40FF56, 8),
                          0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView = findViewById(R.id.textOval);
        textView.setText(spannable, TextView.BufferType.SPANNABLE);
    }
}
