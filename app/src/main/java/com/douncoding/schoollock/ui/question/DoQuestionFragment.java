package com.douncoding.schoollock.ui.question;

import android.os.Bundle;
import android.view.View;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;

import butterknife.ButterKnife;

public class DoQuestionFragment extends BaseFragment {
    public static final String TAG = DoQuestionFragment.class.getSimpleName();
    public static DoQuestionFragment newInstance() {
        Bundle args = new Bundle();
        DoQuestionFragment fragment = new DoQuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_do_question;
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
}
