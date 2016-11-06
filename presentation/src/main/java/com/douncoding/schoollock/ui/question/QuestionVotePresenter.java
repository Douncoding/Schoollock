package com.douncoding.schoollock.ui.question;

import com.douncoding.schoollock.model.QuestionModel;
import com.douncoding.schoollock.ui.BaseContractView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class QuestionVotePresenter implements QuestionVoteContract.Presenter {
    private QuestionVoteContract.View mView;

    @Inject
    public QuestionVotePresenter() {
    }

    @Override
    public void attachView(BaseContractView view) {
        this.mView = (QuestionVoteContract.View)view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onLoadQuestionList() {
        this.requestCourseList();
        this.requestQuestionList();
    }

    private void requestCourseList() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add("수강과목#"+i);
        }
        mView.renderCourseInQuestionList(items);
    }

    private void requestQuestionList() {
        List<QuestionModel> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new QuestionModel("작성자#"+i, "내용#"+i));
        }
        mView.renderQuestionList(items);
    }
}
