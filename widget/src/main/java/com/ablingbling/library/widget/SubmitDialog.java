package com.ablingbling.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ablingbling.library.widget.util.DensityUtil;

/**
 * Created by xukui on 2018/3/27.
 */

public class SubmitDialog extends Dialog {

    private static final int Size = DensityUtil.dp2px(150);
    private static final long Duration = 2000;//毫秒

    private AppCompatImageView iv_icon;
    private ProgressBar bar_progress;
    private TextView tv_message;

    public SubmitDialog(@NonNull Context context) {
        super(context, R.style.SubmitDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_submit);
        initWindow();
        initView();
        setView();
    }

    private void initWindow() {
        getWindow().setLayout(Size, Size);
        getWindow().setGravity(Gravity.CENTER);
    }

    private void initView() {
        iv_icon = findViewById(R.id.iv_icon);
        bar_progress = findViewById(R.id.bar_progress);
        tv_message = findViewById(R.id.tv_message);
    }

    private void setView() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 已废弃, 只内部调用, 外部不要使用
     */
    @Deprecated
    @Override
    public void show() {
        super.show();
    }

    /**
     * 已废弃, 只内部调用, 外部不要使用
     */
    @Deprecated
    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void show(String msg) {
        if (isShowing()) {
            return;
        }

        show();

        iv_icon.setVisibility(View.GONE);
        bar_progress.setVisibility(View.VISIBLE);
        tv_message.setText(msg);
    }

    public void showSubmit() {
        show("提交中...");
    }

    private void dismiss(@DrawableRes int resId, String msg, long duration, final OnSubmitDialogListener listener) {
        if (!isShowing()) {
            return;
        }

        bar_progress.setVisibility(View.GONE);

        tv_message.setText(msg);

        iv_icon.setVisibility(View.VISIBLE);
        iv_icon.setImageResource(resId);
        Drawable drawable = iv_icon.getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat) {
            ((AnimatedVectorDrawableCompat) drawable).start();
        } else if (drawable instanceof AnimatedVectorDrawable) {
            ((AnimatedVectorDrawable) drawable).start();
        }

        tv_message.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (isShowing()) {
                    dismiss();

                    if (listener != null) {
                        listener.onFinishListener(SubmitDialog.this);
                    }
                }
            }

        }, duration);
    }

    public void dismissSilence() {
        if (!isShowing()) {
            return;
        }

        bar_progress.setVisibility(View.GONE);
        tv_message.setText("");
        iv_icon.setVisibility(View.GONE);

        dismiss();
    }

    public void dismissSuccess(String msg, OnSubmitDialogListener listener) {
        dismiss(R.drawable.anim_toast_success, msg, Duration, listener);
    }

    public void dismissFailure(String msg, OnSubmitDialogListener listener) {
        dismiss(R.drawable.anim_toast_failure, msg, Duration, listener);
    }

    public void dismissFailure(String msg) {
        dismiss(R.drawable.anim_toast_failure, msg, Duration, null);
    }

    public interface OnSubmitDialogListener {

        void onFinishListener(SubmitDialog dialog);

    }

}
