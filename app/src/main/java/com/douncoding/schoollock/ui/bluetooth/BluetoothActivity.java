package com.douncoding.schoollock.ui.bluetooth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseActivity;
import com.douncoding.schoollock.ui.BaseContractPresenter;

import butterknife.ButterKnife;

public class BluetoothActivity extends BaseActivity {
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, BluetoothActivity.class);
        return intent;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_nav_and_appbar;
    }

    @Override
    public void initState(Bundle savedInstanceState) {

    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        addFragment(R.id.fragment_container, BluetoothFragment.newInstance(), null);
    }

    @Override
    public void initInjector() {

    }

    @Override
    public BaseContractPresenter getPresenter() {
        return null;
    }
}
