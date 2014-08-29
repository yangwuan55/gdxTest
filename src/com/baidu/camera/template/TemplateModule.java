package com.baidu.camera.template;

import android.view.MotionEvent;
import com.badlogic.gdx.ApplicationAdapter;
import com.baidu.camera.template.module.TemplateScene;

/**
 * Created by yangmengrong on 14-8-26.
 */
public interface TemplateModule {
    void open();

    void open(TemplateScene tempItem);

    void close();

    boolean isOpened();

    boolean onTouchEvent(MotionEvent event);

    ApplicationAdapter getApplicationAdapter();
}
