package com.baidu.camera.template.db;

import com.baidu.camera.template.module.TemplateElement;

import java.sql.SQLException;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class TemplateElemeteManager extends DaoManagerImpl<TemplateElement> {
    private static TemplateElemeteManager instance;

    private TemplateElemeteManager() {
        super();
    }

    public static TemplateElemeteManager getInstance() {
        if (instance == null) {
            instance = new TemplateElemeteManager();
        }
        return instance;
    }

    @Override
    public void delete(TemplateElement templateElement) throws SQLException {
        //cant delte element
    }
}
