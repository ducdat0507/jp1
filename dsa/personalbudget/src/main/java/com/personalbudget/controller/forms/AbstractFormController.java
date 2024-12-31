package com.personalbudget.controller.forms;

import com.personalbudget.controller.PrimaryController;

public abstract class AbstractFormController implements FormController {
    protected PrimaryController parent;

    public void setParent(PrimaryController parent) 
    {
        this.parent = parent;
        onParentSet();
    }

    protected abstract void onParentSet();
}
