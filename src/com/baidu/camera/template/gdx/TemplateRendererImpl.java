package com.baidu.camera.template.gdx;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.List;

/**
 * Created by yangmengrong on 14-8-28.
 */
public class TemplateRendererImpl implements TemplateRenderer {
    private Stage mStage;

    public TemplateRendererImpl(Stage stage,RenderAble renderAble) {
        mStage = stage;
        init(renderAble);
    }

    @Override
    public void init(RenderAble renderAble) {
        if (renderAble instanceof RenderAbleGroup) {
            RenderAbleGroup group = (RenderAbleGroup) renderAble;
            List<RenderAble> renderAbles = group.getRenderAbles();
            for (RenderAble able : renderAbles) {
                init(able);
            }
        } else {
            mStage.addActor(renderAble.getActor());
        }
    }

    @Override
    public void render() {
        mStage.draw();
    }
}
