package com.douncoding.schoollock.ui.home;

import com.douncoding.schoollock.internal.di.ActivityModule;
import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.internal.di.PerActivity;

import dagger.Component;

/**
 * Created by douncoding on 2016. 11. 3..
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, HomeModule.class})
public interface HomeComponent {

}
