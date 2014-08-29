package com.baidu.camera.template;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.baidu.camera.template.module.TemplateScene;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class TemplateController implements TemplateModule {
    private AndroidApplication mAndroidApplication;
    private ViewGroup mViewParent;
    private GdxApp mGdxApp;
    private AndroidApplicationConfiguration mConfig;
    private View mGdxView;
    private boolean mIsOpened;

    public TemplateController(AndroidApplication application, ViewGroup viewParent) {
        mAndroidApplication = application;
        mViewParent = viewParent;
        initGdx();
    }

    private void initGdx() {
        mGdxApp = new GdxApp();
        mConfig = new AndroidApplicationConfiguration();
        mConfig.r = mConfig.g = mConfig.b = mConfig.a = 8;
        mConfig.hideStatusBar = false;
        mConfig.isTranslucent = true;
    }

    @Override
    public void open() {
        open(getSavedOrLastTemplate());
    }

    @Override
    public void open(TemplateScene tempItem) {
        mGdxApp.setTemplate(tempItem);
        mGdxView = mAndroidApplication.initializeForView(mGdxApp, mConfig);
        mViewParent.addView(mGdxView);
        mIsOpened = true;
    }

    @Override
    public void close() {
        mViewParent.removeView(mGdxView);
        mIsOpened = false;
    }

    @Override
    public boolean isOpened() {
        return mIsOpened;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public ApplicationAdapter getApplicationAdapter() {
        return mGdxApp;
    }

    public TemplateScene getSavedOrLastTemplate() {
        return null;
    }
}
