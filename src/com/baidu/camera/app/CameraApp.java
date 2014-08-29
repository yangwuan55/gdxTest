package com.baidu.camera.app;

import android.app.Application;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class CameraApp extends Application {
    private static CameraApp instance;

    public static CameraApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
