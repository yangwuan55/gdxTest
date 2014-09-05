package com.baidu.camera.template;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.baidu.camera.template.gdx.GdxApp;
import com.baidu.camera.template.gdx.TemplateSceneController;
import com.baidu.camera.template.module.ElementGroup;
import com.baidu.camera.template.module.TemplateElement;
import com.baidu.camera.template.module.TemplateScene;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class TemplateController implements TemplateModule , GdxApp.GdxListener {
    private AndroidApplication mAndroidApplication;
    private ViewGroup mViewParent;
    private GdxApp mGdxApp;
    private AndroidApplicationConfiguration mConfig;
    private View mGdxView;
    private boolean mIsOpened;

    private static TemplateController instance;
    private boolean isGdxCreated = false;
    private TemplateScene mCurrTemplate;

    private TemplateController(AndroidApplication application, ViewGroup viewParent) {
        mAndroidApplication = application;
        mViewParent = viewParent;
        initGdx();
    }

    public static TemplateController getInstance(AndroidApplication application, ViewGroup viewParent) {
        if (instance == null) {
            instance = new TemplateController(application,viewParent);
        }
        return instance;
    }

    private void initGdx() {
        mGdxApp = new GdxApp(this);
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
    public void open(TemplateScene scene) {
        mCurrTemplate = scene;
        if (isGdxCreated) {
            mGdxApp.setTemplate(scene);
        }
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
    public ApplicationAdapter getApplicationAdapter() {
        return mGdxApp;
    }

    @Override
    public void saveTemplate() {

    }

    public TemplateScene getSavedOrLastTemplate() {
        TemplateScene test = new TemplateScene();
        ElementGroup elements = new ElementGroup();
        TemplateElement element = new TemplateElement();
        element.setPath("test.png");
        element.setX(100);
        element.setY(100);
        element.setScale(1.0f);
        elements.add(element);
        test.setElements(elements);
        return test;
    }

    @Override
    public void onGdxCreate() {
        isGdxCreated = true;
        if (mCurrTemplate != null) {
            mGdxApp.setTemplate(mCurrTemplate);
        }
    }
}
