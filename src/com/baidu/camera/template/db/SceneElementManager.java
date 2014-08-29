package com.baidu.camera.template.db;

import android.util.Log;
import com.baidu.camera.template.module.ElimentGroup;
import com.baidu.camera.template.module.SceneElement;
import com.baidu.camera.template.module.TemplateElement;
import com.baidu.camera.template.module.TemplateScene;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class SceneElementManager extends DaoManagerImpl<SceneElement> {

    private static final String TAG = "yangmengrong";
    private static SceneElementManager instance;

    private SceneElementManager() {
        super();
    }

    public static SceneElementManager getInstance() {
        if (instance == null) {
            instance = new SceneElementManager();
        }
        return instance;
    }

    public List<SceneElement> findSceneElementByScene(TemplateScene scene) throws SQLException {
        List<SceneElement> result = null;
        QueryBuilder queryBuilder = dao.queryBuilder();
        result = queryBuilder.where().eq(Constants.TemplateSceneKeys.ID, scene.getId()).query();
        return result;
    }

    public ElimentGroup findElementByScene(TemplateScene scene) throws SQLException {
        ElimentGroup result = new ElimentGroup();
        List<SceneElement> sceneElementByScene = findSceneElementByScene(scene);
        if (sceneElementByScene != null && sceneElementByScene.size() > 0)
        for (SceneElement sceneElement : sceneElementByScene) {
            result.add(sceneElement.getElement());
        }
        return result;
    }

    public void deleteByScene(TemplateScene scene) throws SQLException {
        List<SceneElement> sceneElementByScene = findSceneElementByScene(scene);
        for (SceneElement sceneElement : sceneElementByScene) {
            delete(sceneElement);
        }
    }

    public void updateByScene(TemplateScene scene) throws SQLException {
        ElimentGroup oldElement = findElementByScene(scene);
        ListIterator<TemplateElement> elementListIterator = oldElement.listIterator();
        ElimentGroup newElements = scene.getElements();
        ListIterator<TemplateElement> newElementListIterator = newElements.listIterator();

        while (elementListIterator.hasNext() || newElementListIterator.hasNext()) {
            if (elementListIterator.hasNext()) {
                TemplateElement element = elementListIterator.next();
                Log.v(TAG,"element = " + element.getTitle());
                if (!newElements.contains(element)) {
                    Log.v(TAG,"DELETE ELEMENT");
                    delteByElementAndScene(element,scene);
                }
            }

            if (newElementListIterator.hasNext()) {
                TemplateElement newElement = newElementListIterator.next();
                Log.v(TAG,"newElement = " + newElement.getTitle());
                if (!oldElement.contains(newElement)) {
                    Log.v(TAG,"NEW ELEMET");
                    add(new SceneElement(scene, newElement));
                }
            }
        }
    }

    private void delteByElementAndScene(TemplateElement element, TemplateScene scene) {
        try {
            List<SceneElement> query = dao.queryBuilder().where().eq(Constants.TemplateElementKeys.ID, element.getId()).and().eq(Constants.TemplateSceneKeys.ID, scene.getId()).query();
            for (SceneElement sceneElement : query) {
                delete(sceneElement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
