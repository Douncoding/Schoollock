package com.douncoding.schoollock.ui.notice;

import com.douncoding.schoollock.model.NoticeModel;
import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.BaseContractView;

import java.util.List;

public interface NoticeContract {
    interface View extends BaseContractView {
        void showContentTitle(String title);

        void renderCourseList(List<String> items);

        void renderNoticeList(List<NoticeModel> items);
    }

    interface Presenter extends BaseContractPresenter {
        /**
         * 과목별 공지사항 관련 데이터 로딩
         */
        void onLoadNotice();

        /**
         * 주요 일정 로딩
         */
        void onLoadCalendar();

        /**
         * 시간표 로딩
         */
        void onLoadTimetable();
    }
}
