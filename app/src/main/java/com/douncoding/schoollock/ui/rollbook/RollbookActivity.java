package com.douncoding.schoollock.ui.rollbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.HasComponent;
import com.douncoding.schoollock.model.AAModel;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RollbookActivity extends BaseActivity implements RollbookContract.View,
    RollbookFragment.OnFragmentListener, HasComponent<RollbookComponent> {
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, RollbookActivity.class);
        return intent;
    }

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @Inject RollbookPresenter presenter;
    private RollbookComponent mRollbookComponent;

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
        this.presenter.attachView(this);
        this.setupToolbar();

        addFragment(R.id.fragment_container, RollbookFragment.newInstance(), RollbookFragment.TAG);
    }

    @Override
    public void initInjector() {
        mRollbookComponent = DaggerRollbookComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .rollbookModule(new RollbookModule())
                .build();
        mRollbookComponent.inject(this);
    }

    @Override
    public BaseContractPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public RollbookComponent getComponent() {
        return this.mRollbookComponent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_rollbook_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void renderClassList(List<String> items) {
        RollbookFragment rollbookFragment = (RollbookFragment)getFragmentByTag(RollbookFragment.TAG);
        rollbookFragment.setupSpinner(items, 0);
    }

    @Override
    public void renderRollbookList(List<AAModel> items) {
        RollbookFragment rollbookFragment = (RollbookFragment)getFragmentByTag(RollbookFragment.TAG);
        rollbookFragment.setupRecyclerView(items);
    }

    @Override
    public void renderStatistics(int total, int attendance, int late, int absence) {
        RollbookFragment rollbookFragment = (RollbookFragment)getFragmentByTag(RollbookFragment.TAG);
        rollbookFragment.setupStatistics(total, attendance, late, absence);
    }
}
