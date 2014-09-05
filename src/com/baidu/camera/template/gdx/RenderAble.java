package com.baidu.camera.template.gdx;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by yangmengrong on 14-8-28.
 */
public interface RenderAble {

    int getX();

    int getY();

    void updatePositon(int x, int y);

    void setScale(float scale);

    float getScale();

    void setRotation(float angle);

    float getRotation();

    Actor getActor();
}
