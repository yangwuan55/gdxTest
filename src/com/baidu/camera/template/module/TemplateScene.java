package com.baidu.camera.template.module;

import android.graphics.Bitmap;
import com.baidu.camera.template.RenderAble;
import com.baidu.camera.template.RenderAbleGroupAdapter;
import com.baidu.camera.template.db.Constants;
import com.baidu.camera.template.db.SceneElementManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yangmengrong on 14-8-27.
 */
@DatabaseTable(tableName = Constants.TemplateSceneKeys.DATA_BASE_NAME)
public class TemplateScene extends RenderAbleGroupAdapter{

    @DatabaseField(generatedId = true,columnName = Constants.TemplateSceneKeys.ID)
    private int id = -1;
    
    @DatabaseField(columnName = Constants.TemplateSceneKeys.TITLE)
    private String title;

    private ElimentGroup elements;

    @DatabaseField(columnName = Constants.TemplateSceneKeys.SCENE_TYPE)
    private String sceneType;

    public Bitmap getBitmap() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ElimentGroup getElements() {
        try {
            if (elements == null) {
                elements = SceneElementManager.getInstance().findElementByScene(this);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elements;
    }

    public void setElements(ElimentGroup elements) {
        this.elements = elements;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    @Override
    public String toString() {
        return "TemplateScene{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", elements=" + elements +
                ", sceneType='" + sceneType + '\'' +
                '}';
    }

    @Override
    public List<RenderAble> getRenderAbles() {
        ArrayList<RenderAble> result = new ArrayList<RenderAble>();
        if (elements != null) {
            Collections.copy(result,elements);
        }
        return result;
    }
}
