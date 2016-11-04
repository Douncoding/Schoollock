package com.douncoding.schoollock.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.douncoding.schoollock.AndroidApplication;
import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity implements BaseContractView {
    // Content Layout Resource
    public abstract int getContentViewId();

    public abstract void initState(Bundle savedInstanceState);
    public abstract void initUiAndListener();
    public abstract void initInjector();
    public abstract BaseContractPresenter getPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        this.initState(savedInstanceState);
        this.initInjector();
        this.initUiAndListener();
    }

    /**
     * 풀스크린 형태의 액티비티가 필요한 경우 오버라이드하여 반환값을 'true' 로 설정한다.
     */
    protected boolean isFullscreen() {
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isFullscreen()) {
            setFullscreen();
        }
    }

    /**
     * https://developer.android.com/training/system-ui/immersive.html#sticky
     * Sticky immersive mode
     */
    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().resume();
            getPresenter().attachView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getPresenter() != null)
            getPresenter().pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null)
            getPresenter().destroy();
    }

    @Override
    public Context context() {
        return this;
    }

    public <T extends BaseFragment> void addFragment(@IdRes int containerViewId, T fragment, @Nullable String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            transaction.add(containerViewId, fragment, tag);
            transaction.commit();
        }
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication)getApplication()).getApplicationComponent();
    }

}
