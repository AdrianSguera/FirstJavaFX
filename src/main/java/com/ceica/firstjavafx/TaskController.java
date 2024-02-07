package com.ceica.firstjavafx;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskController {
    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtDescription;

    @FXML
    private DatePicker dateDeadline;

    @FXML
    private TableView<Task> TableView;

    @FXML
    private TableColumn<Task, LocalDate> clmCreationDate, clmDeadline;

    @FXML
    private TableColumn<Task, String> clmTitle;

    @FXML
    private TableColumn<Task, Boolean> clmStatus;

    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        clmCreationDate.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCreation_time()));
        clmTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        clmDeadline.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDeadline()));
        clmStatus.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isStatus()));
    }

    @FXML
    public void addToTable() {
        taskList.add(new Task(txtTitle.getText(),txtDescription.getText(),false,LocalDate.now(),dateDeadline.getValue()));
        TableView.setItems(taskList);
        txtTitle.clear();
        txtDescription.clear();
        dateDeadline.setValue(null);
    }
}
