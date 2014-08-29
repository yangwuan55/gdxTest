package com.baidu.camera.template.db;

import com.baidu.camera.template.module.SceneElement;
import com.baidu.camera.template.module.TemplateElement;
import com.baidu.camera.template.module.TemplateScene;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class TemplateSceneManager extends DaoManagerImpl<TemplateScene> {
    private static TemplateSceneManager instance;

    private TemplateSceneManager() {
        super();
    }

    public static TemplateSceneManager getInstance() {
        if (instance == null) {
            instance = new TemplateSceneManager();
        }
        return instance;
    }

    @Override
    public void add(TemplateScene scene) throws SQLException {
        TemplateScene forSaveScene = new TemplateScene();
        if (scene.getId() != -1) {
            forSaveScene.setTitle(scene.getTitle());
            forSaveScene.setSceneType(scene.getSceneType());
            forSaveScene.setElements(scene.getElements());
        } else {
            forSaveScene = scene;
        }
        saveElemets(forSaveScene);
        super.add(forSaveScene);
    }

    public void saveNewScene(TemplateScene scene) throws SQLException {
        TemplateScene forSaveScene = new TemplateScene();
        forSaveScene.setTitle(scene.getTitle());
        forSaveScene.setSceneType(scene.getSceneType());
        forSaveScene.setElements(scene.getElements());
        add(forSaveScene);
        saveElemets(forSaveScene);
    }

    private void saveElemets(TemplateScene forSaveScene) throws SQLException {
        List<TemplateElement> elements = forSaveScene.getElements();
        for (TemplateElement element : elements) {
            SceneElementManager.getInstance().add(new SceneElement(forSaveScene,element));
        }
    }

    @Override
    public void delete(TemplateScene scene) throws SQLException {
        super.delete(scene);
        SceneElementManager.getInstance().deleteByScene(scene);
    }

    @Override
    public void update(TemplateScene scene) throws SQLException {
        super.update(scene);
        SceneElementManager.getInstance().updateByScene(scene);
    }
}
