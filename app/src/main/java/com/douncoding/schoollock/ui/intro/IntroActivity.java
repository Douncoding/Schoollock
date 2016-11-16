package com.douncoding.schoollock.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.douncoding.schoollock.Constants;
import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.HasComponent;
import com.douncoding.schoollock.model.Student;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 시작화면
 */
public class IntroActivity extends BaseActivity implements HasComponent<IntroComponent>
        ,IntroContract.View, IntroFragment.OnFragmentListener {
    private IntroComponent mIntroComponent;

    @Inject IntroPresenter presenter;
    @Inject Navigator navigator;

    @Override
    public int getContentViewId() {
        return R.layout.activity_intro;
    }

    @Override
    public void initState(Bundle savedInstanceState) {
        // Nothings.
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        presenter.attachView(this);
    }

    @Override
    public void initInjector() {
        mIntroComponent = DaggerIntroComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .introModule(new IntroModule(this))
                .build();

        mIntroComponent.inject(this);
    }

    @Override
    public BaseContractPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public IntroComponent getComponent() {
        return this.mIntroComponent;
    }

    @Override
    public void onSignInClicked() {
        presenter.onSignIn();
    }

    @Override
    public void onSignOutClicked() {
        presenter.onSignOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RC_GOOGLE_SIGN_IN) {
            presenter.onGoogleSignInActivityResult(data);
        }
    }

    @Override
    public void showProgressDialog(String message) {
        super.showProgressDialog(message);
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
    }

    @Override
    public void showErrorMessage(String message) {
        super.showToastMessage(message);
    }

    @Override
    public void showSignedInUI(Student item) {
        String message = item.getName() + "님 환영합니다!";
        showToastMessage(message);
//        new Handler().postDelayed(() -> {
//            navigator.navigateToHome(this);
//            finish();
//        }, 2000);
    }

    @Override
    public void showSignInFormUI() {
        addFragment(R.id.fragment_container, IntroFragment.newInstance(), null);
    }
}
