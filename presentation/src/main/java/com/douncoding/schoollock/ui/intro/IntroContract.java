package com.douncoding.schoollock.ui.intro;

import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.BaseContractView;

public interface IntroContract {
    interface View extends BaseContractView {

    }

    interface Presenter extends BaseContractPresenter {
        void onSignIn();

        void onLogin();
    }
}
