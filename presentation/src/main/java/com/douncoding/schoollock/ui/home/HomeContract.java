package com.douncoding.schoollock.ui.home;

import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.BaseContractView;

public interface HomeContract {
    interface View extends BaseContractView {

    }

    interface Presenter extends BaseContractPresenter {
        void 출결조회클릭();

        void 공지사항클릭();

        void 위치조정클릭();

        void 질문투표클릭();

        void 녹음촬영클릭();
    }
}
