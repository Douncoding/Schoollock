package com.douncoding.schoollock.ui.bluetooth;

import android.os.Bundle;
import android.view.View;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.ui.BaseFragment;

import butterknife.ButterKnife;


public class BluetoothFragment extends BaseFragment {
    public static BluetoothFragment newInstance() {
        Bundle args = new Bundle();
        BluetoothFragment fragment = new BluetoothFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentViewId() {
        return R.layout.fragment_bluetooth;
    }

    @Override
    protected void initContentView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }
}
