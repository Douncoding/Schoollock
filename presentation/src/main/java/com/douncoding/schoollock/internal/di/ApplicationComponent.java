package com.douncoding.schoollock.internal.di;

import com.douncoding.schoollock.AndroidApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 최상위 Component
 * App-Level 의 인스턴스 제공 (데이터베이스/네트워크/어플리케이션 컨텍스트)
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AndroidApplication androidApplication);
}
