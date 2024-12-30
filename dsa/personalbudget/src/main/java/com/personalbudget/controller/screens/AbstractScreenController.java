package com.personalbudget.controller.screens;

import com.personalbudget.controller.PrimaryController;

public abstract class AbstractScreenController implements ScreenController {
    protected PrimaryController parent;

    public void setParent(PrimaryController parent) 
    {
        this.parent = parent;
        onParentSet();
    }

    protected abstract void onParentSet();
}
