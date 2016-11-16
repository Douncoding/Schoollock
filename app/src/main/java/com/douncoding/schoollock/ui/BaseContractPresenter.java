package com.douncoding.schoollock.ui;

public interface BaseContractPresenter {

    void attachView(BaseContractView view);

    void resume();

    void pause();

    void destroy();
}
