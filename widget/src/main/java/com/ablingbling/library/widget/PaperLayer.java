package com.ablingbling.library.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by xukui on 2018/3/27.
 */

public class PaperLayer extends FrameLayout {

    private FrameLayout paperLayer_frame_mask;
    private ProgressBar paperLayer_bar_progress;
    private LinearLayout paperLayer_linear_icon;

    private boolean mIsLoading;
    private OnRefreshListener mOnRefreshListener;

    public PaperLayer(@NonNull Context context) {
        super(context);
        initData();
        initView(context);
    }

    public PaperLayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
        initView(context);
    }

    public PaperLayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PaperLayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initData();
        initView(context);
    }

    private void initData() {
        mIsLoading = false;
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_paper_layer, this);

        paperLayer_frame_mask = findViewById(R.id.paperLayer_frame_mask);

        paperLayer_bar_progress = findViewById(R.id.paperLayer_bar_progress);

        paperLayer_linear_icon = findViewById(R.id.paperLayer_linear_icon);
        paperLayer_linear_icon.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                autoRefresh();
            }

        });
    }

    public void autoRefresh() {
        if (mIsLoading) {
            return;
        }

        mIsLoading = true;

        paperLayer_frame_mask.bringToFront();
        paperLayer_frame_mask.setVisibility(View.VISIBLE);
        paperLayer_bar_progress.setVisibility(View.VISIBLE);
        paperLayer_linear_icon.setVisibility(View.GONE);

        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefresh(this);
        }
    }

    public void finishSuccess() {
        if (!mIsLoading) {
            return;
        }

        mIsLoading = false;

        paperLayer_frame_mask.setVisibility(View.GONE);
        paperLayer_bar_progress.setVisibility(View.GONE);
        paperLayer_linear_icon.setVisibility(View.GONE);
    }

    public void finishFailure() {
        if (!mIsLoading) {
            return;
        }

        mIsLoading = false;

        paperLayer_frame_mask.setVisibility(View.VISIBLE);
        paperLayer_bar_progress.setVisibility(View.GONE);
        paperLayer_linear_icon.setVisibility(View.VISIBLE);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }

    public interface OnRefreshListener {

        void onRefresh(PaperLayer paperLayer);

    }

}
