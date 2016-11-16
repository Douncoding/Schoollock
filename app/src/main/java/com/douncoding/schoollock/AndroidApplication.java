package com.douncoding.schoollock;

import android.app.Application;

import com.douncoding.schoollock.internal.di.ApplicationComponent;
import com.douncoding.schoollock.internal.di.ApplicationModule;
import com.douncoding.schoollock.internal.di.DaggerApplicationComponent;
import com.orhanobut.logger.Logger;

public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initDaggerConfiguration();
        this.initLoggerConfiguration();
        this.initDesignConfiguration();
    }

    private void initDaggerConfiguration() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        this.applicationComponent.inject(this);
    }

    private void initLoggerConfiguration() {
        Logger.init(getString(R.string.app_name));
    }

    private void initDesignConfiguration() {

    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
