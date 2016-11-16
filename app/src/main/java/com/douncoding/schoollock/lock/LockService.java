package com.douncoding.schoollock.lock;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.douncoding.schoollock.R;
import com.douncoding.schoollock.model.AppInfo;
import com.douncoding.schoollock.ui.lock.LockScreenActivity;
import com.orhanobut.logger.Logger;

import java.util.concurrent.locks.Lock;


public class LockService extends Service implements LockServiceManager.OnReceiveListener {
    private static final int FOREGROUND_NOTIFICATION_ID = 0x1234;

    private boolean isRunning;
    private AppInfoManager appInfoManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new LockServiceManager(LockService.this, this);
        appInfoManager = new AppInfoManager(LockService.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.registerRestartAppService();
    }

    @Override
    public void onLockServiceStart() {
        isRunning = true;
        setupForeground();
    }

    @Override
    public void onLockServiceStop() {
        isRunning = false;
        stopForeground(true);
    }

    @Override
    public void onChangedTopComponent(String packageName) {
        if (!isRunning || packageName.isEmpty()) return;

        if (ifNeedLock(packageName)) {
            LockScreenActivity.startActivity(LockService.this, packageName);
        }
    }

    private boolean ifNeedLock(String packageName) {
        AppInfo appInfo = appInfoManager.querySimpleAppInfo(packageName);

        if (appInfo.isUserApp() && !getPackageName().equals(packageName)) {
            Logger.w("시스템 앱");
            return false;
        } else {
            Logger.i("사용 허가되지 않은 앱 감지:%s", packageName);
            return true;
        }
    }

    private void registerRestartAppService() {
        Intent intent = new Intent(this, LockService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 10*1000; // 10초 후에 알람이벤트 발생
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 10*1000, pendingIntent);
    }

    private void setupForeground() {
        NotificationCompat.Action unlockAction
                = new NotificationCompat.Action.Builder(0, "잠금해제", null).build();

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("스쿨락")
                .setContentText("스쿨락이 설정되어 있는 상태입니다.")
                .setSmallIcon(R.drawable.ic_notification_foreground)
                .addAction(unlockAction)
                .build();
        startForeground(FOREGROUND_NOTIFICATION_ID, notification);
    }
}
