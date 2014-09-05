package com.baidu.camera.template.gdx;

import android.util.Log;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmengrong on 14-9-1.
 */
public class TemplateStage extends Stage {

    private static final String TAG = "TemplateStage";
    private List<WeakReference<StageTouchListener>> mStageTouchListeners = new ArrayList<WeakReference<StageTouchListener>>();

    public void addStageTouchListener(StageTouchListener listener) {
        mStageTouchListeners.add(new WeakReference<StageTouchListener>(listener));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer > 0) {
            return super.touchDown(screenX,screenY,pointer,button);
        }
        for (WeakReference<StageTouchListener> weakReference : mStageTouchListeners) {
            StageTouchListener stageTouchListener = weakReference.get();
            if (stageTouchListener != null) {
                stageTouchListener.onDown(screenX,screenY,pointer,button);
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer > 0) {
            return super.touchDragged(screenX, screenY, pointer);
        }
        for (WeakReference<StageTouchListener> weakReference : mStageTouchListeners) {
            StageTouchListener stageTouchListener = weakReference.get();
            if (stageTouchListener != null) {
                stageTouchListener.onMove(screenX,screenY,pointer);
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer > 0) {
            return super.touchUp(screenX, screenY, pointer, button);
        }
        for (WeakReference<StageTouchListener> weakReference : mStageTouchListeners) {
            StageTouchListener stageTouchListener = weakReference.get();
            if (stageTouchListener != null) {
                stageTouchListener.onUp(screenX,screenY,pointer,button);
            }
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public static interface StageTouchListener{
        void onDown(int screenX, int screenY, int pointer, int button);
        void onMove(int screenX, int screenY, int pointer);
        void onUp(int screenX, int screenY, int pointer, int button);
    }
}
