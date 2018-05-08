package com.ablingbling.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by tom on 2015/12/22.
 */
public class PwdEyeView extends AppCompatImageView {

    private EditText mTargetView;
    private boolean mIsDisplayPwd;

    public PwdEyeView(Context context) {
        super(context);
        initData(context, null, 0);
        initView();
    }

    public PwdEyeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs, 0);
        initView();
    }

    public PwdEyeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs, defStyleAttr);
        initView();
    }

    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {
        mIsDisplayPwd = false;

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PwdEyeView, defStyleAttr, 0);

            mIsDisplayPwd = ta.getBoolean(R.styleable.PwdEyeView_pev_isDisplay, mIsDisplayPwd);

            ta.recycle();
        }
    }

    private void initView() {
        setDisplayPwd(mIsDisplayPwd);

        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setDisplayPwd(!mIsDisplayPwd);
            }

        });
    }

    private void setDisplayPwd(boolean isDisplay) {
        setImageResource(isDisplay ? R.mipmap.ic_key_see : R.mipmap.ic_key_seeno);

        if (mTargetView != null) {
            mTargetView.setTransformationMethod(isDisplay ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
            mTargetView.setSelection(mTargetView.getText().toString().length()); //设置光标在最后面
        }

        mIsDisplayPwd = isDisplay;
    }

    public void setTargetView(EditText editText) {
        mTargetView = editText;

        setDisplayPwd(mIsDisplayPwd);
    }

}
