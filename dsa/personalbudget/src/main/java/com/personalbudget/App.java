package com.personalbudget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

import com.personalbudget.entities.BudgetRecord;
import com.personalbudget.i18n.I18n;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage stage;
    private static Scene scene;

    private static String currentFXML;
    
    private static BudgetRecord record = new BudgetRecord();
    public static BudgetRecord getRecord() {
        return record;
    }
    public static void setRecord(BudgetRecord record) {
        App.record = record;
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        start(Locale.ENGLISH);
    } 
    public void start(Locale locale) throws IOException {
        I18n.load(locale);
        scene = new Scene(loadFXML(currentFXML = "start"), 960, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Personal Budget Manager");
        stage.setMinHeight(520); stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();
    }

    public static void setLocale(Locale locale) throws IOException {
        I18n.load(locale);
        setRoot(currentFXML);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(currentFXML = fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setResources(I18n.getBundle());
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}