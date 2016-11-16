package com.douncoding.schoollock.ui;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.douncoding.schoollock.AndroidApplication;
import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.ui.widget.ProgressDialogFragment;
import com.orhanobut.logger.Logger;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

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

//        this.setSystemBarTintable();
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

    /**
     * 아직 잘않되는 상태... 머가 문제일가.
     */
    private void setSystemBarTintable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // create our manager instance after the content view is set
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            // set the transparent color of the status bar, 20% darker
            tintManager.setTintColor(ContextCompat.getColor(this, R.color.colorWhiteTransparent));
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }

    public <T extends BaseFragment> void addFragment(@IdRes int containerViewId, T fragment, @Nullable String tag) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        if (getCurrentFragment() != null) {
            transaction.hide(getCurrentFragment());
        }

        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            transaction.add(containerViewId, fragment, tag);
            transaction.addToBackStack(null);
            transaction.commit();
            // 완료까지 지연
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    public <T extends BaseFragment> void replaceFragment(@IdRes int containerViewId, T fragment, @Nullable String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            transaction.replace(containerViewId, fragment, tag);
            transaction.commit();
            // 완료까지 지연
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    public BaseFragment getFragmentByTag(String tag) {
        return (BaseFragment)this.getSupportFragmentManager().findFragmentByTag(tag);
    }

    public BaseFragment getCurrentFragment() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return (BaseFragment)fragment;
            }
        }

        return null;
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication)getApplication()).getApplicationComponent();
    }

    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private static final String TAG_DIALOG_FRAGMENT = "tagDialogFragment";
    protected void showProgressDialog(String message) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getExistingDialogFragment();
        if (prev == null) {
            ProgressDialogFragment fragment = ProgressDialogFragment.newInstance(message);
            fragment.show(ft, TAG_DIALOG_FRAGMENT);
        }
    }

    protected void dismissProgressDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getExistingDialogFragment();
        if (prev != null) {
            ft.remove(prev).commit();
        }
    }

    private Fragment getExistingDialogFragment() {
        return getSupportFragmentManager().findFragmentByTag(TAG_DIALOG_FRAGMENT);
    }
}
