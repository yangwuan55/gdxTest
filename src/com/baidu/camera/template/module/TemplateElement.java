package com.baidu.camera.template.module;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.baidu.camera.template.gdx.ActorManager;
import com.baidu.camera.template.gdx.ElementActor;
import com.baidu.camera.template.gdx.RenderAble;
import com.baidu.camera.template.db.Constants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yangmengrong on 14-8-27.
 */
@DatabaseTable(tableName = Constants.TemplateElementKeys.DATA_BASE_NAME)
public class TemplateElement implements RenderAble,ElementActor.ActorChangeListener {

    public static final float DEFULT_SCALE = 1.0f;
    public static final float DEFULT_ROTATION = 0f;
    public static final float DEFULT_ALPHA = 1.0f;
    @DatabaseField(generatedId = true,columnName = Constants.TemplateElementKeys.ID)
    private int id;

    @DatabaseField(columnName = Constants.TemplateElementKeys.TITLE)
    private String title;

    @DatabaseField(columnName = Constants.TemplateElementKeys.PATH)
    private String path;

    @DatabaseField(columnName = Constants.TemplateElementKeys.URL)
    private String url;

    @DatabaseField(columnName = Constants.TemplateElementKeys.ELEMENT_TYPE)
    private String elementType;

    private int x;

    private int y;

    private float scale = DEFULT_SCALE;

    private float rotation = DEFULT_ROTATION;

    private float alpha = DEFULT_ALPHA;

    private int zIndex;

    private String unKnow;

    private SceneElement sceneElement;

    private boolean isFirstGetScale = true;
    private boolean isFirstGetRotation = true;
    private Actor mActor;

    public void updatePositon(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getScale() {
        if (isFirstGetScale && sceneElement != null) {
            scale = sceneElement.getElementScale();
            isFirstGetScale = false;
        }
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getRotation() {
        if (isFirstGetRotation && sceneElement != null) {
            rotation = sceneElement.getRotation();
            isFirstGetRotation = false;
        }
        return rotation;
    }

    @Override
    public Actor getActor() {
        if (mActor == null) {
            mActor = ActorManager.getInstance().getActor(this);
        }
        return mActor;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public SceneElement getSceneElement() {
        return sceneElement;
    }

    public String getUnKnow() {
        return unKnow;
    }

    public void setUnKnow(String unKnow) {
        this.unKnow = unKnow;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setSceneElement(SceneElement sceneElement) {
        this.sceneElement = sceneElement;
        setX(sceneElement.getElementX());
        setY(sceneElement.getElementY());
        setScale(sceneElement.getElementScale());
        setRotation(sceneElement.getRotation());
        setAlpha(sceneElement.getAlpha());
        setzIndex(sceneElement.getzOder());
        setUnKnow(sceneElement.getUnKnow());
    }

    @Override
    public void onActorTouchUp(TemplateElement element) {
        setX(element.getX());
        setY(element.getY());
        setScale(element.getScale());
        setRotation(element.getRotation());
        setAlpha(element.getAlpha());
        setzIndex(element.getzIndex());
        setUnKnow(element.getUnKnow());
    }
}
