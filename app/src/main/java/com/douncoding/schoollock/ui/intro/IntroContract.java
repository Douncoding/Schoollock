package com.douncoding.schoollock.ui.intro;

import android.content.Intent;

import com.douncoding.schoollock.model.Student;
import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.BaseContractView;

public interface IntroContract {
    interface View extends BaseContractView {
        void showProgressDialog(String message);

        void dismissProgressDialog();

        void showErrorMessage(String message);

        void showSignInFormUI();

        void showSignedInUI(Student item);
    }

    interface Presenter extends BaseContractPresenter {
        void onSignIn();

        void onSignOut();

        void onGoogleSignInActivityResult(Intent intent);
    }
}
