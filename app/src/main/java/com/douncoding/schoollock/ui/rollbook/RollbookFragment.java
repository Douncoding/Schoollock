package com.douncoding.schoollock.ui.rollbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.AAModel;
import com.douncoding.schoollock.ui.BaseFragment;
import com.douncoding.schoollock.ui.widget.EndlessRecyclerOnScrollListener;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RollbookFragment extends BaseFragment {
    public static final String TAG = RollbookFragment.class.getSimpleName();
    public static RollbookFragment newInstance() {
        Bundle args = new Bundle();
        RollbookFragment fragment = new RollbookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener extends OnBaseListener {

    }

    @BindView(R.id.class_spinner) Spinner spinner;
    @BindView(R.id.rollbook_list) RecyclerView recyclerView;
    @BindView(R.id.total_count) TextView mTotalCountText;
    @BindView(R.id.attended_count) TextView mAttendanceCountText;
    @BindView(R.id.late_count) TextView mLateCountText;
    @BindView(R.id.absent_count) TextView mAbsenceCountText;

    @Inject
    RollbookAdapter adater;

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
        return R.layout.fragment_rollbook;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
        getComponent(RollbookComponent.class).inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    public void setupSpinner(List<String> items, int position) {
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

    public void setupRecyclerView(List<AAModel> items) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adater.addDataSet(items);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adater);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {

            }
        });
    }

    public void setupStatistics(int total, int attended, int late, int absent) {
        mTotalCountText.setText(String.valueOf(total));
        mAttendanceCountText.setText(String.valueOf(attended));
        mLateCountText.setText(String.valueOf(late));
        mAbsenceCountText.setText(String.valueOf(absent));
    }
}
