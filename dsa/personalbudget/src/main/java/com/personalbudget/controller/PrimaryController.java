package com.personalbudget.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.personalbudget.App;
import com.personalbudget.constants.DateRange;
import com.personalbudget.controller.forms.EntryEditFormController;
import com.personalbudget.controller.forms.EntryFilterFormController;
import com.personalbudget.controller.forms.FormController;
import com.personalbudget.controller.screens.MonthCalendarScreenController;
import com.personalbudget.controller.screens.MultiYearCalendarScreenController;
import com.personalbudget.controller.screens.RecordScreenController;
import com.personalbudget.controller.screens.ScreenController;
import com.personalbudget.controller.screens.SummaryScreenController;
import com.personalbudget.controller.screens.YearCalendarScreenController;
import com.personalbudget.entities.BudgetEntry;
import com.personalbudget.entities.BudgetRecord;
import com.personalbudget.i18n.I18n;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import net.datafaker.Faker;

public class PrimaryController {

    private DateRange dateRange = DateRange.MONTH;
    private LocalDate dateFrom = YearMonth.now().atDay(1);
    private LocalDate dateTo = YearMonth.now().atDay(1);

    @FXML private HBox titleHolder;

    @FXML private ToggleButton summaryTabButton;
    @FXML private ToggleButton calendarTabButton;
    @FXML private ToggleButton recordTabButton;
    private ToggleButton currentTabButton;

    @FXML
    private Pane screenPane;
    private ScreenController screenController;

    @FXML
    private Pane formPane;
    private FormController formController;

    @FXML private Menu languageMenu;

    public BudgetRecord getRecord() { 
        return App.getRecord(); 
    }
    public DateRange getDateRange() {
        return dateRange;
    }
    public LocalDate getDateFrom() {
        return dateFrom;
    }
    public LocalDate getDateTo() {
        return dateTo;
    }
    

    @FXML
    private void initialize() {

        // update tab group
        ToggleGroup tabGroup = new ToggleGroup();
        tabGroup.getToggles().addAll(summaryTabButton, calendarTabButton, recordTabButton);
        tabGroup.selectedToggleProperty().addListener((prop, from, to) -> {
            setTab((ToggleButton)to);
        });

        // update language
        ToggleGroup langGroup = new ToggleGroup();
        for (Locale locale : I18n.supportedLocales) {
            RadioMenuItem item = new RadioMenuItem(locale.getDisplayLanguage());
            item.setSelected(locale == I18n.getLocale());
            langGroup.getToggles().add(item);
            item.setUserData(locale);
            languageMenu.getItems().add(item);
        }
        langGroup.selectedToggleProperty().addListener((prop, form, to) -> {
            try { App.setLocale((Locale)to.getUserData()); }
            catch (Exception e) { e.printStackTrace(); }
        });

        updateTitle();
        setTab(summaryTabButton);
    }

    public void setScreen(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("screens/" + fxml + ".fxml"));
        fxmlLoader.setResources(I18n.getBundle());
        Pane form = fxmlLoader.load();
        screenPane.getChildren().setAll(form);
        form.prefWidthProperty().bind(screenPane.widthProperty());
        form.prefHeightProperty().bind(screenPane.heightProperty());
        screenController = fxmlLoader.getController();
        screenController.setParent(this);
    }

    public boolean setScreenSafe(String fxml) {
        try { setScreen(fxml); return true; }
        catch (IOException e) { e.printStackTrace(); return false; }
    }
    
    public <T extends ScreenController> T getCurrentOrSetScreen(Class<T> controllerClass, String fxml) {
        if (screenController != null && controllerClass.isAssignableFrom(screenController.getClass())) return (T)screenController;
        if (setScreenSafe(fxml)) return (T)screenController;
        return null;
    }

    public void updateTabUI() {
        summaryTabButton.setDisable(
            currentTabButton == summaryTabButton
            || dateRange == DateRange.MULTI_YEAR
        );
        calendarTabButton.setDisable(
            currentTabButton == calendarTabButton
            || dateRange == DateRange.DAY
        );
        recordTabButton.setDisable(
            currentTabButton == recordTabButton
            || dateRange == DateRange.MULTI_YEAR
        );
    }

    void addClickTitle(String content, EventHandler<? super MouseEvent> onClick) {
        Label label = new Label(content);
        label.getStyleClass().addAll("nav-title", "clickable");
        label.setOnMouseClicked(onClick);
        titleHolder.getChildren().add(label);
    }
    void addTitle(String content) {
        Label label = new Label(content);
        label.getStyleClass().addAll("nav-title");
        titleHolder.getChildren().add(label);
    }

    public void setDateRange(DateRange range, LocalDate point) {
        this.dateRange = range;
        this.dateFrom = this.dateTo = point;
        if (range == DateRange.MULTI_YEAR) this.dateTo = point.plusYears(15);

        updateTitle();
        validateScreen();
    }

    public void moveDate(int direction) {
        switch (dateRange) {
            case DateRange.DAY:
                dateFrom = dateFrom.plusDays(direction);
                dateTo = dateTo.plusDays(direction);
                break;

            case DateRange.MONTH:
                dateFrom = dateFrom.plusMonths(direction);
                dateTo = dateTo.plusMonths(direction);
                break;

            case DateRange.YEAR:
                dateFrom = dateFrom.plusYears(direction);
                dateTo = dateTo.plusYears(direction);
                break;

            case DateRange.MULTI_YEAR:
                dateFrom = dateFrom.plusYears(16 * direction);
                dateTo = dateTo.plusYears(16 * direction);
                break;
        
            default:
                break;
        }
        updateTitle();
        validateScreen();
    }

    public void handlePreviousButtonClick() {
        moveDate(-1);
    }
    public void handleNextButtonClick() {
        moveDate(1);
    }

    public void updateTitle() {
        String formatText = switch (dateRange) {
            case DateRange.DAY -> I18n.get("main.formats.date_range.day");
            case DateRange.MONTH -> I18n.get("main.formats.date_range.month");
            case DateRange.YEAR -> I18n.get("main.formats.date_range.year");
            case DateRange.MULTI_YEAR -> I18n.get("main.formats.date_range.multi_year");
        };

        Pattern pattern = Pattern.compile("\\{([A-Za-z])([1-2]):([^}]+)\\}|[^{]+");
        Matcher matcher = pattern.matcher(formatText);
        titleHolder.getChildren().clear();

        matcher.results().forEach((result) -> {
            String text = result.group(0);

            System.out.println(result);
    
            if (text.charAt(0) != '{') {
                addTitle(text);
            } else {
                String type = result.group(1);
                String target = result.group(2);
                String frag = result.group(3);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(frag, I18n.getLocale());
                LocalDate date = target.charAt(0) == '1' ? dateFrom : dateTo;
                text = formatter.format(date);
                if (type.charAt(0) == 'D') addClickTitle(text, (ev) -> {
                    setDateRange(DateRange.MONTH, LocalDate.of(date.getYear(), date.getMonth(), 1));
                });
                else if (type.charAt(0) == 'M') addClickTitle(text, (ev) -> {
                    setDateRange(DateRange.YEAR, LocalDate.of(date.getYear(), 1, 1));
                });
                else if (type.charAt(0) == 'Y') addClickTitle(text, (ev) -> {
                    setDateRange(DateRange.MULTI_YEAR, LocalDate.of(date.getYear() / 16 * 16, 1, 1));
                });
                else addTitle(text);
            }
        });
        
    }

    public void setTab(ToggleButton tabButton) {
        currentTabButton = tabButton;
        tabButton.setSelected(true);

        updateScreen();
        updateTabUI();
    }

    public void updateScreen() {
        if (currentTabButton == summaryTabButton) {
            if (getCurrentOrSetScreen(SummaryScreenController.class, "summary") != null) {
                ((SummaryScreenController)screenController).updateSummary();
            }
        }
        else if (currentTabButton == calendarTabButton) {
            if (dateRange == DateRange.MONTH) {
                if (getCurrentOrSetScreen(MonthCalendarScreenController.class, "month-calendar") != null) {
                    ((MonthCalendarScreenController)screenController).updateCalendar();
                }
            } else if (dateRange == DateRange.YEAR) {
                if (getCurrentOrSetScreen(YearCalendarScreenController.class, "year-calendar") != null) {
                    ((YearCalendarScreenController)screenController).updateCalendar();
                }
            } else if (dateRange == DateRange.MULTI_YEAR) {
                if (getCurrentOrSetScreen(MultiYearCalendarScreenController.class, "multi-year-calendar") != null) {
                    ((MultiYearCalendarScreenController)screenController).updateCalendar();
                }
            }
        }
        else if (currentTabButton == recordTabButton) {
            if (getCurrentOrSetScreen(RecordScreenController.class, "records") != null) {
                ((RecordScreenController)screenController).updateTable();
            }
        }

        if (currentTabButton != recordTabButton && formController instanceof EntryFilterFormController) {
            removeForm();
        }
    }

    public void validateScreen() {
        if (dateRange == DateRange.DAY && currentTabButton == calendarTabButton) setTab(recordTabButton);
        else if (dateRange == DateRange.MULTI_YEAR && currentTabButton != calendarTabButton) setTab(calendarTabButton);
        else { updateScreen(); updateTabUI(); }
    }

    public void setForm(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("forms/" + fxml + ".fxml"));
        fxmlLoader.setResources(I18n.getBundle());
        Pane form = fxmlLoader.load();
        formPane.getChildren().setAll(form);
        formPane.setVisible(true);
        formPane.setMaxWidth(formPane.getPrefWidth());
        formPane.getStyleClass().add("active-form");
        form.prefWidthProperty().bind(formPane.widthProperty());
        form.prefHeightProperty().bind(formPane.heightProperty());
        formController = fxmlLoader.getController();
        formController.setParent(this);
    }

    public boolean setFormSafe(String fxml) {
        try { setForm(fxml); return true; }
        catch (IOException e) { e.printStackTrace(); return false; }
    }

    public void removeForm() {
        formController = null;
        formPane.getChildren().clear();
        formPane.setVisible(false);
        formPane.setMaxWidth(0);
        formPane.getStyleClass().remove("active-form");
    }
    
    public <T extends FormController> T getCurrentOrSetForm(Class<T> controllerClass, String fxml) {
        if (formController != null && controllerClass.isAssignableFrom(formController.getClass())) return (T)formController;
        if (setFormSafe(fxml)) return (T)formController;
        return null;
    }

    public void openAddIncomeForm() {
        EntryEditFormController form = getCurrentOrSetForm(EntryEditFormController.class, "entry");
        form.setOldEntry(null);
        form.setIsIncome(true);
    }

    public void openAddExpenseForm() {
        EntryEditFormController form = getCurrentOrSetForm(EntryEditFormController.class, "entry");
        form.setOldEntry(null);
        form.setIsIncome(false);
    }

    public void openEditEntryForm(BudgetEntry entry) {
        EntryEditFormController form = getCurrentOrSetForm(EntryEditFormController.class, "entry");
        form.setOldEntry(entry);
    }

    public void handleSave() {
        App.getRecord().save();
    }

    public void handleGenerateData() {
        BudgetRecord record = App.getRecord();
        List<String> incomeCats = record.getIncomeCategories().stream().toList();
        List<String> expenseCats = record.getExpenseCategories().stream().toList();
        Faker faker = new Faker();
        Instant timeFrom = ZonedDateTime.parse("2024-01-01T00:00:00Z").toInstant();
        for (int a = 0; a < 1000; a++) 
        {
            record.addEntry(new BudgetEntry.Income(
                Instant.ofEpochMilli(faker.number().numberBetween(timeFrom.toEpochMilli(), Instant.now().toEpochMilli())).atZone(ZoneId.of("GMT")).toLocalDate(),
                incomeCats.get(faker.random().nextInt(incomeCats.size())),
                faker.famousLastWords().lastWords() + " " + faker.lorem().paragraph(),
                faker.number().numberBetween(1200, 2000000)
            ));
            record.addEntry(new BudgetEntry.Expense(
                Instant.ofEpochMilli(faker.number().numberBetween(timeFrom.toEpochMilli(), Instant.now().toEpochMilli())).atZone(ZoneId.of("GMT")).toLocalDate(),
                expenseCats.get(faker.random().nextInt(expenseCats.size())),
                faker.famousLastWords().lastWords() + " " + faker.lorem().paragraph(),
                faker.number().numberBetween(1200, 1800000)
            ));
        }
        record.entryStream().forEach(x -> System.out.println(x.toString()));
    }
}
