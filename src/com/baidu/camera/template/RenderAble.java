package com.baidu.camera.template;

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

    void setAngle(float angle);

    float getAngle();

    Actor getActor();
}
