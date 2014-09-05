package com.baidu.camera.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.WindowManager;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class CameraApp extends Application {
    private static CameraApp instance;
    public static int sScreenWidth;
    public static int sScreenHeight;
    private Handler mHandler = new Handler();


    public static CameraApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        WindowManager windowManager = (WindowManager) CameraApp.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Rect outSize = new Rect();
        windowManager.getDefaultDisplay().getRectSize(outSize);
        sScreenWidth = outSize.width();
        sScreenHeight = outSize.height();
    }

    public Handler getHandler() {
        return mHandler;
    }
}
