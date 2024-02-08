package com.ceica.firstjavafx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseButton;

import java.time.LocalDate;
import java.util.Optional;

public class TaskController {
    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtDescription;

    @FXML
    private DatePicker dateDeadline;

    @FXML
    private CheckBox checkBoxStatus;

    @FXML
    private Button btnSave, btnAdd, btnCancel;

    @FXML
    private TableView<Task> tableView;

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
        clmStatus.setCellFactory(column -> new CheckBoxTableCell<>());
        clmStatus.setEditable(true);
        clmStatus.setCellValueFactory(cellData -> {
            Task task = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(task.getStatus());
            property.addListener((observable, oldValue, newValue) -> {
                task.setStatus(newValue);
            });
            return property;
        });

        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Task selectedTask = tableView.getSelectionModel().getSelectedItem();
                if (selectedTask != null) {
                    // Aquí puedes hacer lo que necesites con la fila seleccionada
                    txtTitle.setText(selectedTask.getTitle());
                    txtDescription.setText(selectedTask.getDescription());
                    dateDeadline.setValue(selectedTask.getDeadline());
                    checkBoxStatus.setSelected(selectedTask.getStatus());
                    btnAdd.setVisible(false);
                    btnAdd.setDisable(true);
                    btnSave.setVisible(true);
                    btnSave.setDisable(false);
                    btnCancel.setVisible(true);
                    btnCancel.setDisable(false);
                    checkBoxStatus.setDisable(false);
                }
            } else if (event.getButton().equals(MouseButton.SECONDARY) && event.getClickCount() == 1) {
                Task selectedTask = tableView.getSelectionModel().getSelectedItem();
                if (selectedTask != null) {
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem deleteMenuItem = new MenuItem("Delete");
                    deleteMenuItem.setOnAction(e -> {
                        taskList.remove(selectedTask);
                        tableView.refresh();
                    });
                    contextMenu.getItems().add(deleteMenuItem);
                    contextMenu.show(tableView, event.getScreenX(), event.getScreenY());
                }
            }
        });
    }

    @FXML
    public void addToTable() {
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(false);
        taskList.add(new Task(txtTitle.getText(), txtDescription.getText(), checkBox.isSelected(), LocalDate.now(), dateDeadline.getValue()));
        tableView.setItems(taskList);
        txtTitle.clear();
        txtDescription.clear();
        dateDeadline.setValue(null);
    }

    @FXML
    public void saveToTable() {
        Task selectedTask = tableView.getSelectionModel().getSelectedItem();
        selectedTask.setTitle(txtTitle.getText());
        selectedTask.setDescription(txtDescription.getText());
        selectedTask.setDeadline(dateDeadline.getValue());
        selectedTask.setStatus(checkBoxStatus.isSelected());
        tableView.refresh();
        onCancel();
    }

    @FXML
    public void onCancel() {
        btnAdd.setVisible(true);
        btnAdd.setDisable(false);
        btnSave.setVisible(false);
        btnSave.setDisable(true);
        btnCancel.setVisible(false);
        btnCancel.setDisable(true);
        txtTitle.clear();
        txtDescription.clear();
        dateDeadline.setValue(null);
        checkBoxStatus.setSelected(false);
        checkBoxStatus.setDisable(true);
    }
}
