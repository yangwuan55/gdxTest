package com.baidu.camera;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.baidu.camera.template.db.SceneElementManager;
import com.baidu.camera.template.db.TemplateElemeteManager;
import com.baidu.camera.template.db.TemplateSceneManager;
import com.baidu.camera.template.module.ElementGroup;
import com.baidu.camera.template.module.SceneElement;
import com.baidu.camera.template.module.TemplateElement;
import com.baidu.camera.template.module.TemplateScene;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class TestActivity extends Activity{

    private static final String TAG = "yangmengrong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TextView(this));
        TemplateSceneManager templateSceneDaoManager = TemplateSceneManager.getInstance();
        TemplateElemeteManager templateElemeteManager = TemplateElemeteManager.getInstance();
        SceneElementManager sceneElementManager = SceneElementManager.getInstance();
        addTest(templateSceneDaoManager, templateElemeteManager, sceneElementManager);
        updateTest(templateSceneDaoManager, templateElemeteManager, sceneElementManager);
        deleteTest(templateSceneDaoManager, templateElemeteManager, sceneElementManager);
    }

    private void updateTest(TemplateSceneManager templateSceneDaoManager, TemplateElemeteManager templateElemeteManager, SceneElementManager sceneElementManager) {
        try {
            List<TemplateScene> all = templateSceneDaoManager.findAll();
            TemplateScene scene = all.get(0);
            ElementGroup elements = scene.getElements();
            TemplateElement element = new TemplateElement();
            element.setTitle("add----------");
            templateElemeteManager.add(element);
            elements.add(element);
            scene.setElements(elements);
            templateSceneDaoManager.update(scene);

            List<TemplateScene> templatescenes = templateSceneDaoManager.findAll();
            Log.v(TAG,"templatescenes = " + templatescenes.size());
            for (TemplateScene templatescene : templatescenes) {
                Log.v(TAG,"templatescene = " + templatescene.getTitle());
                for (TemplateElement templateElement : templatescene.getElements()) {
                    Log.v(TAG,"templateElement = " + templateElement.getTitle());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteTest(TemplateSceneManager templateSceneDaoManager, TemplateElemeteManager templateElemeteManager, SceneElementManager sceneElementManager) {
        try {
            List<TemplateScene> templatescenes = templateSceneDaoManager.findAll();
            Log.v(TAG,"templatescenes = " + templatescenes.size());
            for (TemplateScene templatescene : templatescenes) {
                Log.v(TAG,"templatescene = " + templatescene.getTitle());
            }
            templateSceneDaoManager.deleteAll(templatescenes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<SceneElement> all = sceneElementManager.findAll();
            if (all != null && all.size() > 0)
            for (SceneElement sceneElement : all) {
                Log.v(TAG, "sceneElement = " + sceneElement.getElement().getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addTest(TemplateSceneManager templateSceneDaoManager, TemplateElemeteManager templateElemeteManager, SceneElementManager sceneElementManager) {
        try {
            TemplateScene t = new TemplateScene();
            t.setSceneType("aaa");
            t.setTitle("bbb");
            templateSceneDaoManager.add(t);

            TemplateElement templateElement = new TemplateElement();
            templateElement.setTitle("templateElement1");
            templateElement.setElementType("templateElement1");
            templateElement.setPath("test.png");
            templateElement.setY(10);
            templateElemeteManager.add(templateElement);

            TemplateElement templateElement2 = new TemplateElement();
            templateElement2.setTitle("templateElement2");
            templateElement2.setElementType("templateElement2");
            templateElement2.setPath("test.png");
            templateElement2.setY(20);
            templateElemeteManager.add(templateElement2);

            TemplateElement templateElement3 = new TemplateElement();
            templateElement3.setTitle("templateElement3");
            templateElement3.setElementType("templateElement3");
            templateElement3.setPath("test.png");
            templateElement3.setY(30);
            templateElemeteManager.add(templateElement3);

            TemplateElement templateElement4 = new TemplateElement();
            templateElement4.setTitle("templateElement4");
            templateElement4.setElementType("templateElement4");
            templateElement4.setPath("test.png");
            templateElement4.setY(40);
            templateElemeteManager.add(templateElement4);

            sceneElementManager.add(new SceneElement(t,templateElement));
            sceneElementManager.add(new SceneElement(t,templateElement2));
            sceneElementManager.add(new SceneElement(t,templateElement3));
            sceneElementManager.add(new SceneElement(t,templateElement4));

            List<SceneElement> sceneElementByScene = sceneElementManager.findSceneElementByScene(t);
            for (SceneElement sceneElement : sceneElementByScene) {
                Log.v(TAG,"sceneElement.element = " + sceneElement.getElement().getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
