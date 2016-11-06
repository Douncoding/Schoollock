package com.douncoding.schoollock.ui.record;

import com.douncoding.schoollock.internal.di.PerActivity;
import com.douncoding.schoollock.ui.BaseContractView;

import javax.inject.Inject;


@PerActivity
public class RecordPresenter implements RecordContract.Presenter {
    private RecordContract.View mView;

    @Inject
    public RecordPresenter() {
    }

    @Override
    public void attachView(BaseContractView view) {
        this.mView = (RecordContract.View)view;
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
}
