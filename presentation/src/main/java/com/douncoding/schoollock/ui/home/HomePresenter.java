package com.douncoding.schoollock.ui.home;

import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.BasePresenter;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter implements HomeContract.Presenter {

    @Inject Navigator navigator;

    @Inject
    public HomePresenter() {
    }

    @Override
    public void 출결조회클릭() {

    }

    @Override
    public void 공지사항클릭() {

    }

    @Override
    public void 위치조정클릭() {

    }

    @Override
    public void 질문투표클릭() {

    }

    @Override
    public void 녹음촬영클릭() {

    }
}
