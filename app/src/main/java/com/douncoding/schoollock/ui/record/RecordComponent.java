package com.douncoding.schoollock.ui.record;

import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.internal.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, RecordModule.class})
public interface RecordComponent {
    void inject(RecordActivity recordActivity);
}
