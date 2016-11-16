package com.douncoding.schoollock.ui.intro;

import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.internal.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, IntroModule.class})
public interface IntroComponent {
    void inject(IntroActivity introActivity);
    void inject(IntroFragment introFragment);
}
