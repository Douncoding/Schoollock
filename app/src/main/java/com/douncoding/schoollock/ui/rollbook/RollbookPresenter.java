package com.douncoding.schoollock.ui.rollbook;

import com.douncoding.schoollock.model.AAModel;
import com.douncoding.schoollock.ui.BaseContractView;
import com.douncoding.schoollock.ui.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class RollbookPresenter implements RollbookContract.Presenter {
    private RollbookContract.View mView;

    @Inject
    public RollbookPresenter() {
    }

    @Override
    public void attachView(BaseContractView view) {
        this.mView = (RollbookContract.View)view;
    }

    @Override
    public void resume() {
        this.requestCourseList();
        this.requestRollbookList();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    private void requestCourseList() {
        List<String> items = new ArrayList<>();
        items.add("항공우주공학개론");
        items.add("안드로이드 프로그래밍");
        items.add("자바 프로그래밍");
        items.add("데이터구조");

        mView.renderClassList(items);
    }

    private void requestRollbookList() {
        List<AAModel> items = new ArrayList<>();
        items.add(new AAModel("12-07 (수)\n10:00:00", "출석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));
        items.add(new AAModel("12-07 (수)\n10:00:00", "결석"));

        mView.renderRollbookList(items);
        mView.renderStatistics(20, 12, 0, 8);
    }
}
