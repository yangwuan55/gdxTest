package com.baidu.camera.template;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.baidu.camera.template.module.TemplateScene;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by yangmengrong on 14-8-26.
 */
public class GdxApp extends ApplicationAdapter {

    private TemplateRenderer mTemplateRenderer;
    private Stage mStage;

    public GdxApp() {
        super();
    }

    @Override
    public void create() {
        super.create();
        mStage = new Stage();
    }

    public void setTemplate(TemplateScene templateScene) {
        mTemplateRenderer = new TemplateRendererImpl(mStage,templateScene);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        mTemplateRenderer.render();
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
}
