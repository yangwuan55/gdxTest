package com.baidu.camera.template.module;

import com.baidu.camera.template.db.Constants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * contact with scene and element
 * Created by yangmengrong on 14-8-27.
 */
@DatabaseTable(tableName = Constants.SceneElementKeys.DATA_BASE_NAME)
public class SceneElement {

    @DatabaseField(generatedId = true,columnName = Constants.SceneElementKeys.ID)
    private int id;

    @DatabaseField(foreignAutoRefresh = true,foreign = true,columnName = Constants.TemplateSceneKeys.ID)
    private TemplateScene scene;

    @DatabaseField(foreignAutoRefresh = true,foreign = true,columnName = Constants.TemplateElementKeys.ID)
    private TemplateElement element;

    @DatabaseField(columnName = Constants.SceneElementKeys.ELEMENT_X)
    private int elementX;

    @DatabaseField(columnName = Constants.SceneElementKeys.ELEMENT_Y)
    private int elementY;

    @DatabaseField(columnName = Constants.SceneElementKeys.SCALE)
    private float elementScale;

    @DatabaseField(columnName = Constants.SceneElementKeys.ROTATION)
    private float rotation;

    @DatabaseField(columnName = Constants.SceneElementKeys.ALPHA)
    private float alpha;

    @DatabaseField(columnName = Constants.SceneElementKeys.Z_ODER)
    private int zOder;

    @DatabaseField(columnName = Constants.SceneElementKeys.UNKNOW)
    private String unKnow;

    public SceneElement(){}

    public SceneElement(TemplateScene scene, TemplateElement element) {
        this.scene = scene;
        this.element = element;
        elementX = element.getX();
        elementY = element.getY();
        elementScale = element.getScale();
        rotation = element.getRotation();
        alpha = element.getAlpha();
        zOder = element.getzOder();
        unKnow = element.getUnKnow();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TemplateScene getScene() {
        return scene;
    }

    public void setScene(TemplateScene scene) {
        this.scene = scene;
    }

    public TemplateElement getElement() {
        element.setSceneElement(this);
        return element;
    }

    public void setElement(TemplateElement element) {
        this.element = element;
    }

    public int getElementX() {
        return elementX;
    }

    public void setElementX(int elementX) {
        this.elementX = elementX;
    }

    public int getElementY() {
        return elementY;
    }

    public void setElementY(int elementY) {
        this.elementY = elementY;
    }

    public float getElementScale() {
        return elementScale;
    }

    public void setElementScale(float elementScale) {
        this.elementScale = elementScale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getzOder() {
        return zOder;
    }

    public void setzOder(int zOder) {
        this.zOder = zOder;
    }

    public String getUnKnow() {
        return unKnow;
    }

    public void setUnKnow(String unKnow) {
        this.unKnow = unKnow;
    }
}
