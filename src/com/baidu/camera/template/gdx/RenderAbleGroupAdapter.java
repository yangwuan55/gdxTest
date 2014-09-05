package com.baidu.camera.template.gdx;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by yangmengrong on 14-8-28.
 */
public abstract class RenderAbleGroupAdapter implements RenderAbleGroup {
    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void updatePositon(int x, int y) {

    }

    @Override
    public void setScale(float scale) {

    }

    @Override
    public float getScale() {
        return 0;
    }

    @Override
    public void setRotation(float angle) {

    }

    @Override
    public float getRotation() {
        return 0;
    }

    @Override
    public Actor getActor() {
        return null;
    }


}
