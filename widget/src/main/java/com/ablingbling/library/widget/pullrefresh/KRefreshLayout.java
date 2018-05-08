package com.ablingbling.library.widget.pullrefresh;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by xukui on 2018/1/17.
 */
public class KRefreshLayout extends SmartRefreshLayout {

    public KRefreshLayout(Context context) {
        super(context);
    }

    public KRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void finish() {
        if (isRefreshing()) {
            finishRefresh(0);
        }
        if (isLoading()) {
            finishLoadmore(0);
        }
    }

}
