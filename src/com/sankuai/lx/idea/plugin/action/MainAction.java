package com.sankuai.lx.idea.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.sankuai.lx.idea.plugin.component.MyComponent;

/**
 * Description: MainAction
 * Author: liangxiao03
 * Create: 2016-02-25 17:13
 */
public class MainAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Application application = ApplicationManager.getApplication();

        MyComponent myComponent = application.getComponent(MyComponent.class);

        myComponent.init(e);


    }
}
