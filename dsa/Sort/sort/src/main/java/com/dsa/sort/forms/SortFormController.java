package com.dsa.sort.forms;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Supplier;

import com.dsa.sort.entities.Person;
import com.dsa.sort.sorts.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SortFormController extends FormController {

    public SortedMap<String, Supplier<SortAlgorithm<Person>>> sorts = new TreeMap<>();

    public SortedMap<String, Comparator<Person>> criterias = new TreeMap<>();

    @FXML private ChoiceBox<String> sortChoiceBox;
    @FXML private ChoiceBox<String> criteriaChoiceBox;
    @FXML private Label resultLabel;

    private void initMaps() {
        sorts.put(   "Bubble Sort", () -> new BubbleSortAlgorithm<Person>());
        sorts.put("Insertion Sort", () -> new InsertionSortAlgorithm<Person>());
        sorts.put("Selection Sort", () -> new SelectionSortAlgorithm<Person>());
        
        criterias.put(    "Full Name", Comparator.comparing(Person::getSortName));
        criterias.put("Date of Birth", Comparator.comparing(Person::getDateOfBirth));
    }

    @FXML 
    private void initialize() {
        initMaps();

        sortChoiceBox.setItems(FXCollections.observableList(sorts.keySet().stream().toList()));
        criteriaChoiceBox.setItems(FXCollections.observableList(criterias.keySet().stream().toList()));
    }

    @FXML
    private void onSortButtonClick() {
        String sort = sortChoiceBox.getValue();
        String criteria = criteriaChoiceBox.getValue();


        if (sort == null || sort.isEmpty()) {
            showValidationError("Sort is required");
            return;
        }
        if (criteria == null ||criteria.isEmpty()) {
            showValidationError("Criteria is required");
            return;
        }

        getParent().randomizePeople();

        Instant startTime = Instant.now();
        getParent().sortPeople(
            sorts.get(sort).get(),
            criterias.get(criteria)
        );
        Instant endTime = Instant.now();

        resultLabel.setText("Took " + FormUtils.formatDuration(Duration.between(startTime, endTime).toNanos()));
    }
}
