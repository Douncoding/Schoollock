package com.douncoding.schoollock.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.HasComponent;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 홈 화면
 */
public class HomeActivity extends BaseActivity implements HasComponent<HomeComponent>,
        NavigationView.OnNavigationItemSelectedListener, HomeContentFragment.OnFragmentListener {
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.fragment_container) FrameLayout mFragmentContainer;

    @Inject HomePresenter presenter;
    @Inject Navigator navigator;

    private HomeComponent mHomeComponent;

    @Override
    public int getContentViewId() {
        return R.layout.activity_nav_and_appbar;
    }

    @Override
    public void initState(Bundle savedInstanceState) {

    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        this.setupToolbar();
        this.setupDrawerNavigation();

        addFragment(R.id.fragment_container, HomeContentFragment.newInstance(), null);
    }

    @Override
    public void initInjector() {
        mHomeComponent = DaggerHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .homeModule(new HomeModule())
                .build();
        mHomeComponent.inject(this);
    }

    @Override
    public BaseContractPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public HomeComponent getComponent() {
        return this.mHomeComponent;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onRollbookClicked() {
        navigator.navigateToRollbook(this);
    }

    @Override
    public void onNoticeClicked() {
        navigator.navigateToNotice(this);
    }

    @Override
    public void onLocationClicked() {
        navigator.navigateToBluetooth(this);
    }

    @Override
    public void onQuestionClicked() {
        navigator.navigateToQuestionVote(this);
    }

    @Override
    public void onRecordClicked() {
        navigator.navigateToRecord(this);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setupDrawerNavigation() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }
}
