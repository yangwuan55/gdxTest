package com.baidu.camera.template.gdx;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by yangmengrong on 14-9-4.
 */
public class TemplateGestureListener implements GestureDetector.GestureListener {

    private GestureDetector.GestureListener mListener;

    public TemplateGestureListener(GestureDetector.GestureListener listener) {
        mListener = listener;
    }

    @Override
    public boolean touchDown(float v, float v2, int i, int i2) {
        return mListener.touchDown(v,v2,i,i2);
    }

    @Override
    public boolean tap(float v, float v2, int i, int i2) {
        return mListener.tap(v,v2,i,i2);
    }

    @Override
    public boolean longPress(float v, float v2) {
        return mListener.longPress(v,v2);
    }

    @Override
    public boolean fling(float v, float v2, int i) {
        return mListener.fling(v,v2,i);
    }

    @Override
    public boolean pan(float v, float v2, float v3, float v4) {
        return mListener.pan(v,v2,v3,v4);
    }

    @Override
    public boolean panStop(float v, float v2, int i, int i2) {
        return mListener.panStop(v,v2,i,i2);
    }

    @Override
    public boolean zoom(float v, float v2) {
        return mListener.zoom(v,v2);
    }

    @Override
    public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        return mListener.pinch(vector2,vector22,vector23,vector24);
    }
}
