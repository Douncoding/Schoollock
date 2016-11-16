package com.douncoding.schoollock.ui.notice;

import com.douncoding.schoollock.internal.di.PerActivity;
import com.douncoding.schoollock.model.NoticeModel;
import com.douncoding.schoollock.ui.BaseContractView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class NoticePresenter implements NoticeContract.Presenter {
    private NoticeContract.View mView;

    @Inject
    public NoticePresenter() {
    }

    @Override
    public void attachView(BaseContractView view) {
        mView = (NoticeContract.View)view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onLoadNotice() {
        mView.showContentTitle("과목별 공지사항");

        this.requestCourseList();
        this.requestNoticeList();
    }

    @Override
    public void onLoadCalendar() {
        mView.showContentTitle("주요일정");

        this.requestCalendar();
    }

    @Override
    public void onLoadTimetable() {
        mView.showContentTitle("시간표");

        this.requestTimetable();
    }

    private void requestCourseList() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add("수강과목#"+i);
        }
        mView.renderCourseList(items);
    }

    private void requestNoticeList() {
        List<NoticeModel> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(new NoticeModel("공지사항#" + i, "공지사항 내용#" + i, "2016.11.06"));
        }
        mView.renderNoticeList(items);
    }

    private void requestTimetable() {

    }

    private void requestCalendar() {

    }
}
