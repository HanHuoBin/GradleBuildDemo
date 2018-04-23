package com.hb.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by hanbin on 2017/10/27.
 */

public class PackageUtils {
    /**
     * 获取系统版本号
     *
     * @return
     */
    public static int getCurrentVersionCode(Context context) {
        int curVersionCode = 0;
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            curVersionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return curVersionCode;
    }

    /**
     * 获取app当前版本
     *
     * @return
     */
    public static String getCurrentVersion(Context context) {
        String curVersion = "";
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            curVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return curVersion;
    }
}
