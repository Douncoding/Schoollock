package com.douncoding.schoollock.ui.intro;

import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.internal.di.PerActivity;
import com.douncoding.schoollock.ui.BasePresenter;

import javax.inject.Inject;

@PerActivity
public class IntroPresenter extends BasePresenter implements IntroContract.Presenter {

    @Inject
    Navigator navigator;

    @Inject
    public IntroPresenter() {
    }

    @Override
    public void onSignIn() {

    }

    @Override
    public void onLogin() {
        navigator.navigateToHome(mView.context());
    }
}
