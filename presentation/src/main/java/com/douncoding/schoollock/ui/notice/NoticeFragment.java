package com.douncoding.schoollock.ui.notice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.NoticeModel;
import com.douncoding.schoollock.ui.BaseFragment;
import com.douncoding.schoollock.ui.widget.EndlessRecyclerOnScrollListener;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeFragment extends BaseFragment {
    public static final String TAG = NoticeFragment.class.getSimpleName();
    public static NoticeFragment newInstance() {
        Bundle args = new Bundle();
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener {
    }

    @BindView(R.id.course_spinner) Spinner spinner;
    @BindView(R.id.notice_list) RecyclerView recyclerView;

    @Inject NoticeAdapter adapter;

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
        return R.layout.fragment_notice;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
        getComponent(NoticeComponent.class).inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    public void setupCourseList(List<String> items, int position) {
        if (getContext() != null) {
            Collections.sort(items);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_drop_title_inverse, items);
            adapter.setDropDownViewResource(R.layout.spinner_drop_list);
            spinner.setAdapter(adapter);
            if (position < items.size() && position >= 0) {
                spinner.setSelection(position);
            }
        }
    }

    public void setupNoticeList(List<NoticeModel> items) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter.addAll(items);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {

            }
        });
    }
}
