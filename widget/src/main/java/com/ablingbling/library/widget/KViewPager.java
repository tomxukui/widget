package com.ablingbling.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class KViewPager extends ViewPager {

    private boolean mIsPagingEnabled;

    public KViewPager(Context context) {
        super(context);
        initData(context, null, 0);
    }

    public KViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs, 0);
    }

    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {
        mIsPagingEnabled = true;

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.KViewPager, defStyleAttr, 0);

            mIsPagingEnabled = ta.getBoolean(R.styleable.KViewPager_kvp_pagingEnabled, mIsPagingEnabled);

            ta.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mIsPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mIsPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        mIsPagingEnabled = b;
    }

}