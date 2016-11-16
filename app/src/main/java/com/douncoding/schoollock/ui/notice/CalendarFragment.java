package com.douncoding.schoollock.ui.notice;

import android.os.Bundle;
import android.view.View;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;

public class CalendarFragment extends BaseFragment {
    public static CalendarFragment newInstance() {
        Bundle args = new Bundle();
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_calendar;
    }

    @Override
    protected void initContentView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }
}
