package com.douncoding.schoollock.ui;

import android.support.annotation.NonNull;

public abstract class BasePresenter implements BaseContractPresenter {
    protected BaseContractView mView;

    @Override
    public void attachView(@NonNull BaseContractView view) {
        this.mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mView = null;
    }
}
