package com.baidu.camera.template.module;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.baidu.camera.template.RenderAble;
import com.baidu.camera.template.db.Constants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yangmengrong on 14-8-27.
 */
@DatabaseTable(tableName = Constants.TemplateElementKeys.DATA_BASE_NAME)
public class TemplateElement implements RenderAble{

    public static final float DEFULT_SCALE = 1.0f;
    public static final float DEFULT_ANGLE = 0f;
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

    private float angle = DEFULT_ANGLE;

    private float alpha = DEFULT_ALPHA;

    private int zOder;

    private String unKnow;

    private SceneElement sceneElement;

    private boolean isFirstGetScale = true;
    private boolean isFirstGetAngle = true;

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

    public float getAngle() {
        if (isFirstGetAngle && sceneElement != null) {
            angle = sceneElement.getAngle();
            isFirstGetAngle = false;
        }
        return angle;
    }

    @Override
    public Actor getActor() {
        return null;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getzOder() {
        return zOder;
    }

    public void setzOder(int zOder) {
        this.zOder = zOder;
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
        setAngle(sceneElement.getAngle());
        setAlpha(sceneElement.getAlpha());
        setzOder(sceneElement.getzOder());
        setUnKnow(sceneElement.getUnKnown());
    }
}
