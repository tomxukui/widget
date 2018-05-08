package com.ablingbling.library.widget.pullrefresh.footer;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.getSize;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by xukui on 2017/11/26.
 */

public class BallFooter extends ViewGroup implements RefreshFooter {

    private BallView mBallView;
    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;

    //<editor-fold desc="ViewGroup">
    public BallFooter(@NonNull Context context) {
        super(context);
        initView(context, null, 0);
    }

    public BallFooter(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public BallFooter(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mBallView = new BallView(context);
        addView(mBallView, WRAP_CONTENT, WRAP_CONTENT);
        setMinimumHeight(DensityUtil.dp2px(50));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpec = makeMeasureSpec(getSize(widthMeasureSpec), AT_MOST);
        int heightSpec = makeMeasureSpec(getSize(heightMeasureSpec), AT_MOST);
        mBallView.measure(widthSpec, heightSpec);
        setMeasuredDimension(
                resolveSize(mBallView.getMeasuredWidth(), widthMeasureSpec),
                resolveSize(mBallView.getMeasuredHeight(), heightMeasureSpec)
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int pwidth = getMeasuredWidth();
        int pheight = getMeasuredHeight();
        int cwidth = mBallView.getMeasuredWidth();
        int cheight = mBallView.getMeasuredHeight();
        int left = pwidth / 2 - cwidth / 2;
        int top = pheight / 2 - cheight / 2;
        mBallView.layout(left, top, left + cwidth, top + cheight);
    }
    //</editor-fold>

    //<editor-fold desc="RefreshFooter">
    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public void onPullingUp(float percent, int offset, int footerHeight, int extendHeight) {
    }

    @Override
    public void onPullReleasing(float percent, int offset, int footerHeight, int extendHeight) {
    }

    @Override
    public void onLoadmoreReleased(RefreshLayout layout, int footerHeight, int extendHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int footerHeight, int extendHeight) {
        mBallView.startAnim();
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        mBallView.stopAnim();
        return 0;
    }

    @Override
    public boolean setLoadmoreFinished(boolean finished) {
        return false;
    }

    @Override
    @Deprecated
    public void setPrimaryColors(@ColorInt int... colors) {
        if (colors.length > 1) {
            mBallView.setNormalColor(colors[1]);
            mBallView.setAnimatingColor(colors[0]);
        } else if (colors.length > 0) {
            mBallView.setNormalColor(ColorUtils.compositeColors(0x99ffffff, colors[0]));
            mBallView.setAnimatingColor(colors[0]);
        }
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return mSpinnerStyle;
    }
    //</editor-fold>

    //<editor-fold desc="API">
    public BallFooter setSpinnerStyle(SpinnerStyle mSpinnerStyle) {
        this.mSpinnerStyle = mSpinnerStyle;
        return this;
    }

    public BallFooter setIndicatorColor(@ColorInt int color) {
        mBallView.setIndicatorColor(color);
        return this;
    }

    public BallFooter setNormalColor(@ColorInt int color) {
        mBallView.setNormalColor(color);
        return this;
    }

    public BallFooter setAnimatingColor(@ColorInt int color) {
        mBallView.setAnimatingColor(color);
        return this;
    }
    //</editor-fold>

}