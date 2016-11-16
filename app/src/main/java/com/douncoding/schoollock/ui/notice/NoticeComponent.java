package com.douncoding.schoollock.ui.notice;

import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.internal.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NoticeModule.class})
public interface NoticeComponent {
    void inject(NoticeActivity noticeActivity);
    void inject(NoticeMenuFragment noticeMenuFragment);
    void inject(NoticeFragment noticeFragment);
}
