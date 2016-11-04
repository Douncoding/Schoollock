package com.douncoding.schoollock;

import android.content.Context;

import com.douncoding.schoollock.ui.home.HomeActivity;

import javax.inject.Inject;

public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToHome(Context context) {
        if (context != null) {
            context.startActivity(HomeActivity.getCallingIntent(context));
        }
    }
}
