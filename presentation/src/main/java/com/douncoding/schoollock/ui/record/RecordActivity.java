package com.douncoding.schoollock.ui.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;

import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.HasComponent;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecordActivity extends BaseActivity implements HasComponent<RecordComponent>,
        RecordMenuFragment.OnFragmentListener, RecordContract.View {
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, RecordActivity.class);
        return intent;
    }

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @Inject Navigator navigator;
    @Inject RecordPresenter presenter;

    private RecordComponent mRecordComponent;

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

        addFragment(R.id.fragment_container, RecordMenuFragment.newInstance(), null);
    }

    @Override
    public void initInjector() {
        mRecordComponent = DaggerRecordComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .recordModule(new RecordModule())
                .build();
        mRecordComponent.inject(this);
    }

    @Override
    public BaseContractPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public RecordComponent getComponent() {
        return this.mRecordComponent;
    }

    @Override
    public void onMemoClicked() {

    }

    @Override
    public void onMicClicked() {
        navigator.navigateToVoiceRecord(this);
    }

    @Override
    public void onCameraClicked() {
        navigator.navigateToCamera(this);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_record_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
