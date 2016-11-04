package com.douncoding.schoollock.ui.intro;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

public class IntroFragment extends BaseFragment implements View.OnClickListener {
    public static IntroFragment newInstance() {
        Bundle args = new Bundle();
        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.signin_btn) FancyButton mSignInButton;
    @BindView(R.id.login_btn) FancyButton mLoginButton;

    private OnFragmentListener onFragmentListener;
    public interface OnFragmentListener extends OnBaseListener {
        void onSignInClicked();
        void onLoginClicked();
    }

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
        return R.layout.fragment_intro;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
        this.getComponent(IntroComponent.class).inject(this);

        mSignInButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_btn:
                onFragmentListener.onSignInClicked();
                break;
            case R.id.login_btn:
                onFragmentListener.onLoginClicked();
                break;
        }
    }
}
