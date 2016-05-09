package com.svs.myprojects.bollywoodquiz.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svs.myprojects.bollywoodquiz.R;

/**
 * Created by Snehal on 5/7/16.
 */
public class LevelButtonView extends LinearLayout {

    private View mView;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private TextView mDescriptionTextView;

    public LevelButtonView(Context context, View mView) {
        super(context);
        mView = LayoutInflater.from(context).inflate(R.layout.level_button_view, this, true);

    }

    public LevelButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LevelButton, 0, 0);
        String titleText = a.getString(R.styleable.LevelButton_button_text);
        String contentText = a.getString(R.styleable.LevelButton_button_content);
        String descriptionText = a.getString(R.styleable.LevelButton_button_description);
        a.recycle();

        mView = LayoutInflater.from(context).inflate(R.layout.level_button_view, this, true);

        mTitleTextView = (TextView) mView.findViewById(R.id.level_button_text);
        mTitleTextView.setText(titleText);

        mContentTextView = (TextView) mView.findViewById(R.id.level_button_content);
        mContentTextView.setText(contentText);

        mDescriptionTextView = (TextView) mView.findViewById(R.id.level_button_description);
        mDescriptionTextView.setText(descriptionText);

    }

    public void setLevelContentText(String string){
        mContentTextView.setText(string);
    }

    public void setLevelDescriptionText(String string){
        mDescriptionTextView.setText(string);
    }

}
