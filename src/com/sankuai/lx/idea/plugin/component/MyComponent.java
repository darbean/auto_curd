package com.sankuai.lx.idea.plugin.component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import com.sankuai.lx.idea.plugin.ui.DbSettingForm;
import com.sankuai.lx.idea.plugin.utils.GlobelPathContainer;
import org.jetbrains.annotations.NotNull;

/**
 * Description: MainComponent
 * Author: liangxiao03
 * Create: 2016-02-25 17:10
 */
public class MyComponent implements ApplicationComponent {
    public MyComponent() {

    }



    public void initComponent() {

        // TODO: insert component initialization logic here

    }



    public void disposeComponent() {

        // TODO: insert component disposal logic here

    }



    @NotNull

    public String getComponentName() {

        return "MyComponent";

    }



    public void init(AnActionEvent e) {

        // Show dialog with message
        GlobelPathContainer.getInstance().setBaseProjectPath(e.getProject().getBasePath());
       new DbSettingForm().show();

    }
}
