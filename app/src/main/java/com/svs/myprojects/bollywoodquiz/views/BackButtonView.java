package com.svs.myprojects.bollywoodquiz.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.svs.myprojects.bollywoodquiz.R;

/**
 * Created by Snehal on 5/7/16.
 */
public class BackButtonView extends RelativeLayout {

    private Context mContext;
    private View mView;

    public BackButtonView(Context context) {
        super(context);
        mView = LayoutInflater.from(context).inflate(R.layout.back_button_view, this, true);
    }

    public BackButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        initView();
        mView = LayoutInflater.from(context).inflate(R.layout.back_button_view, this, true);

//        TypedArray a = context.obtainStyledAttributes(attrs,
//                R.styleable.Options, 0, 0);
//        String titleText = a.getString(R.styleable.Options_titleText);
//        int valueColor = a.getColor(R.styleable.Options_valueColor,
//                android.R.color.holo_blue_light);
//        a.recycle();
    }

//    private void initView() {
//
//    }
}
