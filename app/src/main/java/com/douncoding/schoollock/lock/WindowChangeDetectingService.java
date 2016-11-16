package com.douncoding.schoollock.lock;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityEvent;

import com.orhanobut.logger.Logger;

/**
 * Created by douncoding on 2016. 11. 15..
 */

public class WindowChangeDetectingService extends AccessibilityService {
    private boolean detail = false;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            LockServiceManager.changeTopComponent(getApplicationContext(), event.getPackageName().toString());

            // 액티비티 이름까지 추적하고 싶은 경우 사용
            if (detail) {
                try {
                    ComponentName componentName = new ComponentName(
                            event.getPackageName().toString(),
                            event.getClassName().toString()
                    );

                    ActivityInfo activityInfo = getPackageManager().getActivityInfo(componentName, 0);
                    boolean isActivity = activityInfo != null;
                    if (isActivity) {
                        Logger.i("CurrentActivity:" + componentName.flattenToShortString());
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
