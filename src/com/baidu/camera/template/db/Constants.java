package com.baidu.camera.template.db;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class Constants {

    public static class TemplateSceneKeys {
        public static final String DATA_BASE_NAME = "templatescene";
        public static final String ID = "templatescene_id";
        public static final String TITLE = "title";
        public static final String DATE = "date";
        public static final String SCENE_TYPE = "scene_type";
        public static final String ELEMENTS = "elements";
    }

    public static class TemplateElementKeys {
        public static final String DATA_BASE_NAME = "templateelement";
        public static final String ID = "templateelement_id";
        public static final String TITLE = "title";
        public static final String URL = "url";
        public static final String PATH = "path";
        public static final String DATE = "date";
        public static final String ELEMENT_TYPE = "element_type";
    }

    public static class SceneElementKeys {
        public static final String DATA_BASE_NAME = "sceneelement";
        public static final String ID = "sceneelement_id";
        public static final String ELEMENT_X = "element_x";
        public static final String ELEMENT_Y = "element_y";
        public static final String SCALE = "scale";
        public static final String ANGLE = "angle";
        public static final String ALPHA = "alpha";
        public static final String Z_ODER = "Z_ODER";
        public static final String UNKNOWN = "unknown";
    }

}
