package com.svs.myprojects.bollywoodquiz.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.svs.myprojects.bollywoodquiz.R;

/**
 * Created by Snehal on 5/4/16.
 *
 * USAGE:
 *
 *  mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
 *  RecyclerView.ItemDecoration itemDecoration = new SimpleDividerItemDecoration(getActivity(), true);
 *  mRecyclerView.addItemDecoration(itemDecoration);

 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private Boolean mSkipFirst;

    public SimpleDividerItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
        mSkipFirst = false;
    }

    public SimpleDividerItemDecoration(Context context, boolean skipFirst) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
        mSkipFirst = skipFirst;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        int i = 0;
        if (mSkipFirst)
            i = 1;
        for (; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
