package com.douncoding.schoollock.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeContentFragment extends BaseFragment {
    public static HomeContentFragment newInstance() {
        Bundle args = new Bundle();
        HomeContentFragment fragment = new HomeContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener extends OnBaseListener {
        void onRollbookClicked();
        void onNoticeClicked();
        void onLocationClicked();
        void onQuestionClicked();
        void onRecordClicked();
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
        return R.layout.fragment_home;
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

    @OnClick(R.id.rollbook_panel) public void onRollbookClicked(ViewGroup viewGroup) {
        onFragmentListener.onRollbookClicked();
    }

    @OnClick(R.id.notice_panel) public void onNoticeClicked(ViewGroup viewGroup) {
        onFragmentListener.onNoticeClicked();
    }

    @OnClick(R.id.location_adjust_panel) public void onLocationClicked(ViewGroup viewGroup) {
        onFragmentListener.onLocationClicked();
    }

    @OnClick(R.id.question_answer_panel) public void onQuestionClicked(ViewGroup viewGroup) {
        onFragmentListener.onQuestionClicked();
    }

    @OnClick(R.id.record_panel) public void onRecordClicked(ViewGroup viewGroup) {
        onFragmentListener.onRecordClicked();
    }
}
