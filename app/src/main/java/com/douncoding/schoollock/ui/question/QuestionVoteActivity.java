package com.douncoding.schoollock.ui.question;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.HasComponent;
import com.douncoding.schoollock.model.QuestionModel;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionVoteActivity extends BaseActivity implements HasComponent<QuestionVoteComponent>,
    QuestionVoteContract.View, QuestionVoteMenuFragment.OnFragmentListener, QuestionListFragment.OnFragmentListener,
    QuestionDetailFragment.OnFragmentListener {
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, QuestionVoteActivity.class);
        return intent;
    }

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Inject Navigator navigator;
    @Inject QuestionVotePresenter presenter;
    private QuestionVoteComponent mQuestionVoteComponent;

    @Override
    public int getContentViewId() {
        return R.layout.activity_nav_and_appbar;
    }

    @Override
    public void initState(Bundle savedInstanceState) {

    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        this.presenter.attachView(this);
        this.setupToolbar();

        addFragment(R.id.fragment_container, QuestionVoteMenuFragment.newInstance(), null);
    }

    @Override
    public void initInjector() {
        mQuestionVoteComponent = DaggerQuestionVoteComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .questionVoteModule(new QuestionVoteModule())
                .build();
        mQuestionVoteComponent.inject(this);
    }

    @Override
    public BaseContractPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public QuestionVoteComponent getComponent() {
        return this.mQuestionVoteComponent;
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_question_vote_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onVoteYesClicked() {
        showToastMessage("O");
    }

    @Override
    public void onVoteNoClicked() {
        showToastMessage("X");
    }

    @Override
    public void onDoQuestionClicked() {
        addFragment(R.id.fragment_container, DoQuestionFragment.newInstance(), DoQuestionFragment.TAG);
    }

    @Override
    public void onQuestionListClicked() {
        addFragment(R.id.fragment_container, QuestionListFragment.newInstance(), QuestionListFragment.TAG);
        presenter.onLoadQuestionList();
    }

    @Override
    public void onQuestionItemClick(QuestionModel item) {
        addFragment(R.id.fragment_container, QuestionDetailFragment.newInstance(), QuestionDetailFragment.TAG);
    }

    @Override
    public void renderQuestionList(List<QuestionModel> items) {
        QuestionListFragment fragment = (QuestionListFragment)getFragmentByTag(QuestionListFragment.TAG);
        if (fragment != null) {
            fragment.setupQuestionList(items);
        }
    }

    @Override
    public void renderCourseInQuestionList(List<String> items) {
        QuestionListFragment fragment = (QuestionListFragment)getFragmentByTag(QuestionListFragment.TAG);
        if (fragment != null) {
            fragment.setupCourseList(items, 0);
        }
    }
}
