package com.ablingbling.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ablingbling.library.widget.util.DensityUtil;

/**
 * Created by tom on 2017/3/21.
 */

public class SegmentControlView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private int mTextSize;
    private int mTextColorNor;
    private int mTextColorSelected;
    private int mTextBgNor;
    private int mTextBgSelected;
    private int mTextPaddingLeft;
    private int mTextPaddingTop;
    private int mTextPaddingRight;
    private int mTextPaddingBottom;

    private ViewPager viewPager;
    private String[] mNames;

    public SegmentControlView(Context context) {
        super(context);
        initData(context, null, 0);
        initView();
    }

    public SegmentControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs, 0);
        initView();
    }

    public SegmentControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs, defStyleAttr);
        initView();
    }

    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {
        mTextSize = DensityUtil.sp2px(14);
        mTextColorNor = Color.WHITE;
        mTextColorSelected = Color.BLUE;
        mTextBgNor = Color.GRAY;
        mTextBgSelected = Color.WHITE;
        mTextPaddingLeft = DensityUtil.dp2px(10);
        mTextPaddingTop = DensityUtil.dp2px(10);
        mTextPaddingRight = DensityUtil.dp2px(10);
        mTextPaddingBottom = DensityUtil.dp2px(10);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SegmentControlView, defStyleAttr, 0);

            mTextSize = ta.getDimensionPixelSize(R.styleable.SegmentControlView_scv_textSize, mTextSize);
            mTextColorNor = ta.getColor(R.styleable.SegmentControlView_scv_textColor_nor, mTextColorNor);
            mTextColorSelected = ta.getColor(R.styleable.SegmentControlView_scv_textColor_selected, mTextColorSelected);
            mTextBgNor = ta.getResourceId(R.styleable.SegmentControlView_scv_textBg_nor, mTextBgNor);
            mTextBgSelected = ta.getResourceId(R.styleable.SegmentControlView_scv_textBg_selected, mTextBgSelected);
            mTextPaddingLeft = (int) ta.getDimension(R.styleable.SegmentControlView_scv_textPaddingLeft, mTextPaddingLeft);
            mTextPaddingTop = (int) ta.getDimension(R.styleable.SegmentControlView_scv_textPaddingTop, mTextPaddingTop);
            mTextPaddingRight = (int) ta.getDimension(R.styleable.SegmentControlView_scv_textPaddingRight, mTextPaddingRight);
            mTextPaddingBottom = (int) ta.getDimension(R.styleable.SegmentControlView_scv_textPaddingBottom, mTextPaddingBottom);

            ta.recycle();

        }
    }

    private void initView() {
        setOrientation(HORIZONTAL);
    }

    private void setView() {
        removeAllViews();

        if (mNames == null || mNames.length == 0) {
            return;
        }

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < mNames.length; i++) {
            TextView view = new TextView(getContext());

            view.setLayoutParams(params);
            view.setText(mNames[i]);
            view.setPadding(mTextPaddingLeft, mTextPaddingTop, mTextPaddingRight, mTextPaddingBottom);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            if (viewPager.getCurrentItem() == i) {
                view.setTextColor(mTextColorSelected);
                view.setBackgroundResource(mTextBgSelected);

            } else {
                view.setTextColor(mTextColorNor);
                view.setBackgroundResource(mTextBgNor);
            }

            final int clickIndex = i;
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(clickIndex, true);
                }

            });

            addView(view);
        }
    }

    public void setup(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);

        PagerAdapter adapter = viewPager.getAdapter();
        int count = adapter.getCount();
        mNames = new String[count];
        for (int i = 0; i < count; i++) {
            mNames[i] = adapter.getPageTitle(i).toString();
        }

        setView();
    }

    /***********************************
     * 监听事件
     ******************************************/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setView();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
