package com.dsa.sort.forms;

import com.dsa.sort.HomeController;

import javafx.scene.control.Alert;

public abstract class FormController {
    private HomeController parent;
    
    public void setParent(HomeController parent)
    {
        this.parent = parent;
    }

    public HomeController getParent() {
        return parent;
    }

    protected void showPopupError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    protected void showValidationError(String content) {
        showPopupError("Validation failed", content);
    }
}
