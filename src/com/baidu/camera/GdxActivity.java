package com.baidu.camera;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.baidu.camera.template.TemplateController;
import com.baidu.camera.template.db.SceneElementManager;
import com.baidu.camera.template.db.TemplateElemeteManager;
import com.baidu.camera.template.db.TemplateSceneManager;
import com.baidu.camera.template.gdx.GdxApp;
import com.baidu.camera.template.module.SceneElement;
import com.baidu.camera.template.module.TemplateElement;
import com.baidu.camera.template.module.TemplateScene;
import com.example.ormliteMTMtest.R;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangmengrong on 14-9-3.
 */
public class GdxActivity extends AndroidApplication {

    private static final String TAG = "GdxActivity";
    private TemplateController mTemplateController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gdx_layout);
        addTest(TemplateSceneManager.getInstance(),TemplateElemeteManager.getInstance(),SceneElementManager.getInstance());
        mTemplateController = TemplateController.getInstance(this, (android.view.ViewGroup) findViewById(R.id.gdx_layout));
        mTemplateController.open();
        findViewById(R.id.pre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTemplateController.pre();
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTemplateController.next();
            }
        });
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

            sceneElementManager.add(new SceneElement(t,templateElement));
            templateElement.setX(50);
            sceneElementManager.add(new SceneElement(t,templateElement));
            templateElement.setX(100);
            sceneElementManager.add(new SceneElement(t,templateElement));
            templateElement.setX(200);
            sceneElementManager.add(new SceneElement(t,templateElement));

            List<SceneElement> sceneElementByScene = sceneElementManager.findSceneElementByScene(t);
            for (SceneElement sceneElement : sceneElementByScene) {
                Log.v(TAG, "sceneElement.element = " + sceneElement.getElement().getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
