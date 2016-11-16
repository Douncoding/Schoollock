package com.douncoding.schoollock.ui.question;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.QuestionModel;
import com.douncoding.schoollock.ui.BaseFragment;
import com.douncoding.schoollock.ui.widget.EndlessRecyclerOnScrollListener;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionListFragment extends BaseFragment {
    public static final String TAG = QuestionListFragment.class.getSimpleName();
    public static QuestionListFragment newInstance() {
        Bundle args = new Bundle();
        QuestionListFragment fragment = new QuestionListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener {
        void onQuestionItemClick(QuestionModel item);
    }

    @BindView(R.id.question_list) RecyclerView recyclerView;
    @BindView(R.id.course_spinner) Spinner spinner;
    @Inject QuestionAdapter adapter;


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
        return R.layout.fragment_question_list;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
        this.getComponent(QuestionVoteComponent.class).inject(this);
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

    public void setupQuestionList(List<QuestionModel> items) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter.addAll(items);
        adapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, QuestionModel item) {
                onFragmentListener.onQuestionItemClick(item);
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {

            }
        });
    }

    public void addQuestionList(List<QuestionModel> items) {

    }
}
