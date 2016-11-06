package com.douncoding.schoollock.ui.question;

import com.douncoding.schoollock.model.QuestionModel;
import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.BaseContractView;

import java.util.List;

public interface QuestionVoteContract {
    interface View extends BaseContractView {
        void renderQuestionList(List<QuestionModel> items);

        void renderCourseInQuestionList(List<String> items);
    }

    interface Presenter extends BaseContractPresenter {
        /**
         * 질문 목록 로딩
         */
        void onLoadQuestionList();
    }
}
