package com.douncoding.schoollock.lock;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.douncoding.schoollock.model.AppInfo;
import com.douncoding.schoollock.model.BaseAppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douncoding on 2016. 11. 15..
 */

public class AppInfoManager {
    public enum AppType { ALL_APP, USER_APP, SYSTEM_APP }

    private Context context;
    private ActivityManager activityManager;
    private PackageManager packageManager;

    public AppInfoManager(Context context) {
        this.context = context;
        activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        packageManager = context.getPackageManager();
    }

    public AppInfo querySimpleAppInfo (String packageName) {
        // 패키지 존재 유무 확인
        try {
            ApplicationInfo info = packageManager.getApplicationInfo(packageName, 0);

            AppType type = ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) ?
                    AppType.USER_APP : AppType.SYSTEM_APP;

            AppInfo appInfo = new AppInfo();
            appInfo.setPackageName(packageName);
            appInfo.setIcon(info.loadIcon(packageManager));
            appInfo.setUserApp(type == AppType.USER_APP);
            appInfo.setName(info.loadLabel(packageManager).toString());
            return appInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BaseAppInfo> queryAllAppInfo(AppType appType) {
        List<BaseAppInfo> appInfoList = new ArrayList<>();
        List<ApplicationInfo> applicationInfoList = packageManager.getInstalledApplications(0);

        for (ApplicationInfo info : applicationInfoList) {
            AppType type = ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) ?
                    AppType.USER_APP : AppType.SYSTEM_APP;

            if ((appType == AppType.ALL_APP) || (appType == type)) {
                AppInfo appInfo = new AppInfo();
                appInfo.setPackageName(info.packageName);
                appInfo.setIcon(info.loadIcon(packageManager));
                appInfo.setName(info.loadLabel(packageManager).toString());
                appInfo.setUserApp(type == AppType.USER_APP);
                appInfoList.add(appInfo);
            }
        }

        return appInfoList;
    }

    public ComponentName queryTopComponentName () {
        if (Build.VERSION.SDK_INT < 21) {
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
            if (runningTaskInfos.size() > 0) {
                return runningTaskInfos.get(0).topActivity;
            }
        }
        return null;
    }
}
