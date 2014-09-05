package com.baidu.camera.template.gdx;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.baidu.camera.template.module.TemplateElement;
import com.baidu.camera.template.module.TemplateScene;

/**
 * Created by yangmengrong on 14-8-29.
 */
public class ActorManager implements GdxApp.SceneListener {
    private static ActorManager instance;

    private int zIndex;

    private ActorManager(){}

    public static ActorManager getInstance() {
        if (instance == null) {
            instance = new ActorManager();
        }
        return instance;
    }

    public Actor getActor(TemplateElement element) {
        ElementActor elementActor = new ElementActor(element);
        elementActor.setActorChangeListener(element);
        elementActor.setZIndex(++zIndex);
        return elementActor;
    }

    @Override
    public void onSceneChange(TemplateScene scene) {
        zIndex = 0;
    }
}
