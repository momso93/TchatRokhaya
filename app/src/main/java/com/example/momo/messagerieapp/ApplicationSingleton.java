package com.example.momo.messagerieapp;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.quickblox.core.QBSettings;

import vc908.stickerfactory.StickersManager;

public class ApplicationSingleton extends Application {
    private static final String TAG = ApplicationSingleton.class.getSimpleName();

    public static final String APP_ID = "32672";
    public static final String AUTH_KEY = "emOJuOgyeZry7bX";
    public static final String AUTH_SECRET = "WX35vv7eOH682Gy";
    public static final String STICKER_API_KEY = "bf6f27299d7b4c08b21ffa76e7c62426";

    private static ApplicationSingleton instance;
    public static ApplicationSingleton getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

        instance =this;
        // Initialise QuickBlox SDK
        //
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
        StickersManager.initialize(STICKER_API_KEY, this);
    }


    public int getAppVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
}
