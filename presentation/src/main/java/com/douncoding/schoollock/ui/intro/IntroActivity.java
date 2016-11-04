package com.douncoding.schoollock.ui.intro;

import android.os.Bundle;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.HasComponent;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 시작화면
 */
public class IntroActivity extends BaseActivity implements HasComponent<IntroComponent>
        , IntroContract.View, IntroFragment.OnFragmentListener {

    @Override
    public int getContentViewId() {
        return R.layout.activity_basic;
    }

    private IntroComponent mIntroComponent;

    @Inject IntroPresenter presenter;

    @Override
    public void initState(Bundle savedInstanceState) {
        // Nothings.
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        addFragment(R.id.fragment_container, IntroFragment.newInstance(), null);
    }

    @Override
    public void initInjector() {
        mIntroComponent = DaggerIntroComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .introModule(new IntroModule())
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
    public void onLoginClicked() {
        presenter.onLogin();
    }
}
