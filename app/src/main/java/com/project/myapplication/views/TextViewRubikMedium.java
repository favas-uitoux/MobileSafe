package com.project.myapplication.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by UITOUX5 on 06/03/18.
 */

public class TextViewRubikMedium extends AppCompatTextView {
    public TextViewRubikMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Rubik-Medium.ttf"));
    }
}
