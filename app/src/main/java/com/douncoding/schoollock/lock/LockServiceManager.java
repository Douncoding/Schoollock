package com.douncoding.schoollock.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * {@link LockService} 의 관리자 역할 수행
 */
public class LockServiceManager {
    private static final String LOCK_START_ACTION = "action.startLock";
    private static final String LOCK_STOP_ACTION = "action.stopLock";
    private static final String CHANGE_TOP_COMPONENT_ACTION = "action.changeTopComponent";
    private static final String CHANGE_TOP_COMPONENT_EXTRA = "extra.changeTopComponent";

    LockServiceManager(Context context, OnReceiveListener listener) {
        LockBroadcastReceiver broadcastReceiver = new LockBroadcastReceiver(listener);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCK_START_ACTION);
        intentFilter.addAction(LOCK_STOP_ACTION);
        intentFilter.addAction(CHANGE_TOP_COMPONENT_ACTION);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void appLockStart(Context context) {
        Intent intent = new Intent(LOCK_START_ACTION);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void appLockStop(Context context) {
        Intent intent = new Intent(LOCK_STOP_ACTION);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    static void changeTopComponent(Context context, String packageName) {
        Intent intent = new Intent(CHANGE_TOP_COMPONENT_ACTION);
        intent.putExtra(CHANGE_TOP_COMPONENT_EXTRA, packageName);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    interface OnReceiveListener {
        void onLockServiceStart();
        void onLockServiceStop();
        void onChangedTopComponent(String packageName);
    }

    class LockBroadcastReceiver extends BroadcastReceiver {
        private OnReceiveListener onReceiveListener;
        public LockBroadcastReceiver(OnReceiveListener onReceiveListener) {
            this.onReceiveListener = onReceiveListener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LOCK_START_ACTION)) {
                onReceiveListener.onLockServiceStart();
            } else if (intent.getAction().equals(LOCK_STOP_ACTION)) {
                onReceiveListener.onLockServiceStop();
            } else if (intent.getAction().equals(CHANGE_TOP_COMPONENT_ACTION)) {
                String packageName = intent.getStringExtra(CHANGE_TOP_COMPONENT_EXTRA);
                onReceiveListener.onChangedTopComponent(packageName);
            } else {
                throw new UnsupportedOperationException("Not supported action.");
            }
        }
    }
}
