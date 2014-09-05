package com.baidu.camera.template.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.baidu.camera.template.module.TemplateScene;

import javax.microedition.khronos.opengles.GL10;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmengrong on 14-8-26.
 */
public class GdxApp extends ApplicationAdapter {

    private static final String TAG = "GdxApp";
    private TemplateRenderer mTemplateRenderer;
    private TemplateStage mStage;
    private List<WeakReference<SceneListener>> mSceneListeners = new ArrayList<WeakReference<SceneListener>>();
    private TemplateScene mTemplateScene;
    private GdxListener mGdxListener;
    private TemplateSceneController mSceneController;

    public GdxApp(GdxListener gdxListener) {
        super();
        mGdxListener = gdxListener;
    }

    @Override
    public void create() {
        super.create();
        mStage = new TemplateStage();
        mSceneController = TemplateSceneController.getInstance();
        Gdx.input.setInputProcessor(mSceneController);
        mGdxListener.onGdxCreate();
        mSceneController.initTemplates();
    }

    public void setTemplate(TemplateScene templateScene) {
        this.mTemplateScene = templateScene;
        for (WeakReference<SceneListener> weakReference : mSceneListeners) {
            SceneListener sceneListener = weakReference.get();
            if (sceneListener != null) {
                sceneListener.onSceneChange(templateScene);
            }
        };
        mSceneController.setTemplateScene(mTemplateScene);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Group[] groupForDraw = mSceneController.getGroupForDraw();
        for (Group group : groupForDraw) {
            if (group != null && !group.hasParent()) {
                mStage.addActor(group);
            }
        }
        mStage.draw();
        //mTemplateRenderer.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void addSceneListener(SceneListener listener) {
        mSceneListeners.add(new WeakReference<SceneListener>(listener));
    }

    public TemplateScene getTemplateScene() {
        return mTemplateScene;
    }

    public static interface GdxListener{
        void onGdxCreate();
    }

    public static interface SceneListener{
        void onSceneChange(TemplateScene scene);
    }
}
