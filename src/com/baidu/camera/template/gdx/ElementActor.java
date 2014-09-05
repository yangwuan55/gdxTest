package com.baidu.camera.template.gdx;

import android.util.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.baidu.camera.template.module.TemplateElement;


/**
 * Created by yangmengrong on 14-8-29.
 */
public class ElementActor extends Actor {

    public static final String TAG = "ElementActor";
    private final Texture mTexture;
    private final TextureRegion mTextureRegion;
    private final Sprite mSprite;
    private final TemplateElement mElement;
    private float mAlpha;
    private ActorChangeListener mActorChangeListener;

    public ElementActor(TemplateElement element) {
        mElement = element;
        String path = element.getPath();
        mTexture = new Texture(Gdx.files.external(path));
        mTextureRegion = new TextureRegion(mTexture, 0, 0, mTexture.getWidth(), mTexture.getHeight());
        mSprite = new Sprite(mTextureRegion);
        mSprite.setPosition(element.getX(), element.getY());
        mAlpha = element.getAlpha();
        mSprite.setAlpha(mAlpha);
        mSprite.setRotation(element.getRotation());
        mSprite.setScale(element.getScale());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        mSprite.draw(batch,parentAlpha);
        super.draw(batch, parentAlpha);
    }

    public void setActorChangeListener(ActorChangeListener actorChangeListener) {
        this.mActorChangeListener = actorChangeListener;
    }

    public static interface ActorChangeListener {
        void onActorTouchUp(TemplateElement element);
    }
}
