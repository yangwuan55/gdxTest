package com.baidu.camera.template;

import com.baidu.camera.template.module.TemplateScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class TemplateLoader {
    private TemplateLoadListener mLoadListener;
    private List<TemplateScene> mTemplateScenes = new ArrayList<TemplateScene>();

    public void load() {
        if (mLoadListener != null) {
            mLoadListener.onStart();
        }
        loadFromLocal();
    }

    private void loadFromLocal() {

    }

    public void setTemplateLoadListener(TemplateLoadListener listener) {
        mLoadListener = listener;
    }

    public static interface TemplateLoadListener{
        void onStart();

        void finishLoadAll(List<TemplateScene> templateScenes);

        void finishLoad(TemplateScene templateScene);
    }

}
