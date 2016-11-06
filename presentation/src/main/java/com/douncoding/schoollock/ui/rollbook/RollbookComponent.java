package com.douncoding.schoollock.ui.rollbook;

import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.internal.di.PerActivity;

import dagger.Component;

/**
 * Created by douncoding on 2016. 11. 5..
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, RollbookModule.class})
public interface RollbookComponent {
    void inject(RollbookActivity rollbookActivity);
    void inject(RollbookFragment rollbookFragment);
}
