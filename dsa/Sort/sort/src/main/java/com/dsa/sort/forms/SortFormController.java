package com.dsa.sort.forms;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.function.Supplier;

import com.dsa.sort.entities.Person;
import com.dsa.sort.sorts.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class SortFormController extends FormController {

    public Map<String, Supplier<SortAlgorithm<Person>>> sorts = Map.ofEntries(
        Map.entry("Bubble Sort", () -> new BubbleSortAlgorithm<Person>()),
        Map.entry("Insertion Sort", () -> new InsertionSortAlgorithm<Person>())
    );

    public Map<String, Comparator<Person>> criterias = Map.ofEntries(
        Map.entry("Full Name", Comparator.comparing(Person::getFullName)),
        Map.entry("Date of Birth", Comparator.comparing(Person::getDateOfBirth))
    );

    @FXML private ChoiceBox<String> sortChoiceBox;
    @FXML private ChoiceBox<String> criteriaChoiceBox;


    @FXML 
    private void initialize() {
        sortChoiceBox.setItems(FXCollections.observableList(sorts.keySet().stream().toList()));
        criteriaChoiceBox.setItems(FXCollections.observableList(criterias.keySet().stream().toList()));
    }

    @FXML
    private void onSortButtonClick() {
        String sort = sortChoiceBox.getValue();
        String criteria = criteriaChoiceBox.getValue();


        if (sort == null || sort.isEmpty()) {
            showPopupError("Validation failed", "Sort is required");
            return;
        }
        if (criteria == null ||criteria.isEmpty()) {
            showPopupError("Validation failed", "Criteria is required");
            return;
        }

        getParent().sortPeople(
            sorts.get(sort).get(),
            criterias.get(criteria)
        );
    }
}
