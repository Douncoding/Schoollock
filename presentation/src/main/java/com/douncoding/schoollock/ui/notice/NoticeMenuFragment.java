package com.douncoding.schoollock.ui.notice;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeMenuFragment extends BaseFragment {
    public static final String TAG = NoticeMenuFragment.class.getSimpleName();
    public static NoticeMenuFragment newInstance() {
        Bundle args = new Bundle();
        NoticeMenuFragment fragment = new NoticeMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener {
        void onNoticePerCourseClicked();
        void onTimetableClicked();
        void onCalendarClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            this.onFragmentListener = (OnFragmentListener)context;
        } else {
            throw new IllegalArgumentException("must be implements OnFragmentListener.");
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_notice_menu;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @OnClick(R.id.notice_per_course_panel) public void onNoticeClicked(ViewGroup viewGroup) {
        onFragmentListener.onNoticePerCourseClicked();
    }

    @OnClick(R.id.timetable_panel) public void onTimetableClicked(ViewGroup viewGroup) {
        onFragmentListener.onTimetableClicked();
    }

    @OnClick(R.id.calendar_panel) public void onCalendarClicked(ViewGroup viewGroup) {
        onFragmentListener.onCalendarClicked();
    }
}
