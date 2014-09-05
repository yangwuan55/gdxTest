package com.baidu.camera.template.gdx;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.List;

/**
 * Created by yangmengrong on 14-9-3.
 */
public interface TemplateSceneController{

    void initTemplates();

    Group getCurrItem();

    Group getPreItem();

    Group getNextItem();

    boolean hasNext();

    boolean hasPre();

    boolean goNext();

    boolean goPre();

    Group[] getGroupForDraw();
}
