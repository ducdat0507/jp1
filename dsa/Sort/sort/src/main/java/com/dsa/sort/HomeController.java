package com.dsa.sort;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;

import com.dsa.sort.entities.Person;
import com.dsa.sort.forms.FormController;
import com.dsa.sort.sorts.SortAlgorithm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class HomeController {

    private ObservableList<Person> people = FXCollections.observableArrayList();

    @FXML private TableView<Person> mainTable;
    @FXML private TableColumn<Person, String> tableFirstNameColumn;
    @FXML private TableColumn<Person, String> tableLastNameColumn;
    @FXML private TableColumn<Person, LocalDate> tableDateOfBirthColumn;

    @FXML private ScrollPane formPane;
    private FormController formController;

    @FXML
    private void initialize() {
        mainTable.setItems(people);
        tableFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        tableLastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        tableDateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("dateOfBirth"));
    }

    private void setForm(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FormController.class.getResource(fxml + ".fxml"));
        Pane form = fxmlLoader.load();
        formPane.setContent(form);
        formController = fxmlLoader.getController();
        formController.setParent(this);
    }

    private void setFormSafe(String fxml) {
        try { setForm(fxml); }
        catch (IOException e) { e.printStackTrace(); }
    }
    
    @FXML
    private void onAddPersonButtonClick() {
        setFormSafe("add-person");
    }
    
    @FXML
    private void onSortButtonClick() {
        setFormSafe("sort");
    }
    
    @FXML
    private void onGenerateButtonClick() {
        setFormSafe("generate");
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void sortPeople(SortAlgorithm<Person> sortAlgorithm, Comparator<Person> comparator) {
        sortAlgorithm.sort(people, comparator);
    }
}
