package com.ceica.firstjavafx;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseButton;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    @FXML
    private TableColumn<Task, Void> clmDelete;

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

        TableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                Task selectedTask = TableView.getSelectionModel().getSelectedItem();
                if (selectedTask != null) {
                    // Aquí puedes hacer lo que necesites con la fila seleccionada
                    System.out.println(selectedTask);
                }
            }
        });

        clmDelete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Eliminar");

            {
                deleteButton.setOnAction(event -> {
                    // Almacenar los estados de las celdas CheckBox antes de la eliminación
                    Map<Task, Boolean> checkBoxStates = new HashMap<>();
                    for (Task task : TableView.getItems()) {
                        checkBoxStates.put(task, task.getBooleanStatus());
                    }

                    Task task = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(task);

                    // Actualizar la tabla
                    TableView.refresh();

                    // Restaurar los estados de las celdas CheckBox después de la actualización
                    for (Map.Entry<Task, Boolean> entry : checkBoxStates.entrySet()) {
                        Task currentTask = entry.getKey();
                        Boolean status = entry.getValue();
                        currentTask.setStatus(status);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
    }

    @FXML
    public void addToTable() {
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(false);
        taskList.add(new Task(txtTitle.getText(), txtDescription.getText(), checkBox.isSelected(), LocalDate.now(), dateDeadline.getValue()));
        TableView.setItems(taskList);
        txtTitle.clear();
        txtDescription.clear();
        dateDeadline.setValue(null);
    }
}
