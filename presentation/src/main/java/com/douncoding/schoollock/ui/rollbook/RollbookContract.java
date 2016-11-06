package com.douncoding.schoollock.ui.rollbook;

import com.douncoding.schoollock.model.AAModel;
import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.BaseContractView;

import java.util.List;

public interface RollbookContract {
    interface View extends BaseContractView {
        void renderClassList(List<String> items);

        void renderRollbookList(List<AAModel> items);

        void renderStatistics(int total, int attendance, int late, int absence);
    }

    interface Presenter extends BaseContractPresenter {

    }
}
