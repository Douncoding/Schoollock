package com.douncoding.schoollock.ui.notice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.internal.di.HasComponent;
import com.douncoding.schoollock.model.NoticeModel;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeActivity extends BaseActivity implements HasComponent<NoticeComponent>,
    NoticeMenuFragment.OnFragmentListener, NoticeContract.View, NoticeFragment.OnFragmentListener {
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, NoticeActivity.class);
        return intent;
    }

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @Inject NoticePresenter presenter;

    private NoticeComponent mNoticeComponent;

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
        addFragment(R.id.fragment_container, NoticeMenuFragment.newInstance(), NoticeMenuFragment.TAG);
    }

    @Override
    public void initInjector() {
        mNoticeComponent = DaggerNoticeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .noticeModule(new NoticeModule())
                .build();
        mNoticeComponent.inject(this);
    }

    @Override
    public BaseContractPresenter getPresenter() {
        return presenter;
    }

    @Override
    public NoticeComponent getComponent() {
        return this.mNoticeComponent;
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_notice_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onNoticePerCourseClicked() {
        addFragment(R.id.fragment_container, NoticeFragment.newInstance(), NoticeFragment.TAG);
        presenter.onLoadNotice();
    }

    @Override
    public void onTimetableClicked() {
        addFragment(R.id.fragment_container, TimetableFragment.newInstance(), NoticeFragment.TAG);
        presenter.onLoadTimetable();
    }

    @Override
    public void onCalendarClicked() {
        addFragment(R.id.fragment_container, CalendarFragment.newInstance(), NoticeFragment.TAG);
        presenter.onLoadCalendar();
    }

    @Override
    public void showContentTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void renderCourseList(List<String> items) {
        NoticeFragment noticeFragment = (NoticeFragment)getFragmentByTag(NoticeFragment.TAG);
        if (noticeFragment != null) {
            noticeFragment.setupCourseList(items, 0);
        }
    }

    @Override
    public void renderNoticeList(List<NoticeModel> items) {
        NoticeFragment noticeFragment = (NoticeFragment)getFragmentByTag(NoticeFragment.TAG);
        if (noticeFragment != null) {
            noticeFragment.setupNoticeList(items);
        }
    }
}
