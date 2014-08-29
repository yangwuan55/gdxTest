package com.baidu.camera.template.module;

import java.util.ArrayList;

/**
 * Created by yangmengrong on 14-8-29.
 */
public class ElimentGroup extends ArrayList<TemplateElement> {

    @Override
    public boolean contains(Object object) {
        if (object != null && object instanceof TemplateElement) {
            TemplateElement element = (TemplateElement) object;
            for (TemplateElement templateElement : this) {
                if (templateElement.getId() == element.getId()) {
                    return true;
                }
            }
        }
        return super.contains(object);
    }
}
