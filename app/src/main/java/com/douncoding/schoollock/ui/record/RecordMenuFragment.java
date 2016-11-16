package com.douncoding.schoollock.ui.record;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;
import com.douncoding.schoollock.ui.notice.NoticeMenuFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordMenuFragment extends BaseFragment {
    public static RecordMenuFragment newInstance() {
        Bundle args = new Bundle();
        RecordMenuFragment fragment = new RecordMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener {
        void onMemoClicked();
        void onMicClicked();
        void onCameraClicked();
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
        return R.layout.fragment_record;
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

    @OnClick(R.id.memo_panel) public void onMemoClicked(ViewGroup viewGroup) {
        onFragmentListener.onMemoClicked();
    }

    @OnClick(R.id.voice_record_panel) public void onMicClicked(ViewGroup viewGroup) {
        onFragmentListener.onMicClicked();
    }

    @OnClick(R.id.camera_record_panel) public void onCameraClicked(ViewGroup viewGroup) {
        onFragmentListener.onCameraClicked();
    }
}
