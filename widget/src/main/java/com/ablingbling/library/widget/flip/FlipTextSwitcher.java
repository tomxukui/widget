package com.ablingbling.library.widget.flip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.ablingbling.library.widget.util.DensityUtil;
import com.ablingbling.library.widget.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xukui on 2018/3/16.
 */
public class FlipTextSwitcher<T extends FlipItemEntity> extends TextSwitcher implements ViewSwitcher.ViewFactory {

    private static final int WHAT_UPDATE = 1;

    private Timer mTimer;

    private int mTextSize;
    private int mTextColor;
    private int mTextGravity;
    private int mDelay;
    private int mPeriod;
    private List<T> mList;
    private OnFlipTextSwitcherListener mListener;

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case WHAT_UPDATE: {
                    if (mList == null || mList.size() == 0) {
                        stop();
                        setText("");
                        setTag(null);

                    } else {
                        int index = 0;

                        if (getTag() != null) {
                            index = (int) getTag() + 1;
                        }

                        if (index < 0 || index >= mList.size()) {
                            index = 0;
                        }

                        setText(mList.get(index).getFlipItemName());
                        setTag(index);
                    }
                }
                break;

                default:
                    break;

            }
        }

    };

    public FlipTextSwitcher(Context context) {
        super(context);
        initData(context, null, 0);
        initView(context);
    }

    public FlipTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs, 0);
        initView(context);
    }

    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {
        mTextSize = DensityUtil.sp2px(12);
        mTextColor = Color.parseColor("#2b2b2b");
        mTextGravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        mDelay = 0;
        mPeriod = 5000;

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlipTextSwitcher, defStyleAttr, 0);

            mTextSize = ta.getDimensionPixelSize(R.styleable.FlipTextSwitcher_android_textSize, mTextSize);
            mTextColor = ta.getColor(R.styleable.FlipTextSwitcher_android_textColor, mTextColor);
            mTextGravity = ta.getInt(R.styleable.FlipTextSwitcher_android_gravity, mTextGravity);
            mDelay = ta.getInt(R.styleable.FlipTextSwitcher_fts_delay, mDelay);
            mPeriod = ta.getInt(R.styleable.FlipTextSwitcher_fts_period, mPeriod);

            ta.recycle();
        }
    }

    @SuppressLint("ResourceType")
    private void initView(Context context) {
        setFactory(this);
        setInAnimation(context, R.animator.roll_up_in);
        setOutAnimation(context, R.animator.roll_up_out);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    public View makeView() {
        TextView tv = new TextView(getContext());
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setGravity(mTextGravity);
        tv.setSingleLine();
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        tv.setTextColor(mTextColor);
        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (getTag() != null && mListener != null) {
                    int index = (int) getTag();

                    if (index >= 0 && mList != null && index < mList.size()) {
                        mListener.onItemClickListener(FlipTextSwitcher.this, mList.get(index), index);
                    }
                }
            }

        });
        return tv;
    }

    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    mHandler.sendEmptyMessage(WHAT_UPDATE);
                }

            }, mDelay, mPeriod);
        }
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void start() {
        stopTimer();
        startTimer();
    }

    public void stop() {
        stopTimer();
    }

    public void setData(List<T> list) {
        mList = list;
    }

    public void setOnFlipTextSwitcherListener(OnFlipTextSwitcherListener listener) {
        mListener = listener;
    }

    public interface OnFlipTextSwitcherListener<T extends FlipItemEntity> {

        void onItemClickListener(FlipTextSwitcher switcher, T t, int position);

    }

}
