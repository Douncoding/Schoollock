package com.douncoding.schoollock.ui.lock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.douncoding.schoollock.Navigator;
import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;
import com.douncoding.schoollock.ui.home.HomeActivity;

/**
 * Created by douncoding on 2016. 11. 15..
 */

public class LockScreenActivity extends AppCompatActivity {
    private static final String PACKAGE_NAME_EXTRA = "lockScreen.extra.packageName";
    public static void startActivity (Context context, String packageName) {
        Intent callingIntent = new Intent(context, LockScreenActivity.class);
        callingIntent.putExtra(PACKAGE_NAME_EXTRA, packageName);
        callingIntent.setFlags(
                Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(callingIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startActivity(HomeActivity.getCallingIntent(this));
    }
}
