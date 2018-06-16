package com.openclassrooms.wonder.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.openclassrooms.wonderfuloc.R;

/**
 * Created by Philippe on 03/04/2018.
 */

public class WonderTextView extends AppCompatTextView {

    public WonderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.applyStyle(context, attrs);
    }

    public WonderTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.applyStyle(context, attrs);
    }

    // ---

    private void applyStyle(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WonderTextView);
        int cf = a.getInteger(R.styleable.WonderTextView_fontName, 0);
        int fontName;
        switch (cf) {
            case 1:
                fontName = R.string.Roboto_Bold;
                break;
            case 2:
                fontName = R.string.Roboto_Light;
                break;
            case 3:
                fontName = R.string.Roboto_Medium;
                break;
            case 4:
                fontName = R.string.Roboto_Thin;
                break;
            default:
                fontName = R.string.Roboto_Medium;
                break;
        }

        String customFont = getResources().getString(fontName);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + customFont + ".ttf");
        setTypeface(tf);
        a.recycle();
    }
}
