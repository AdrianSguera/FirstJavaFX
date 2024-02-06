package com.ceica.firstjavafx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserController {

    @FXML
    private MenuItem mnCloseSession;

    @FXML
    private TextField txtTitle, txtNombre, txtCiudad;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private Button btnAddNew;

    @FXML
    private DatePicker dpkrDeadline;

    @FXML
    private TableView<Persona> tblvTaskList;

    @FXML
    private TableColumn<Persona, String> clmTaskTitle;

    @FXML
    private TableColumn<Persona, Integer> clmTaskDescription;

    @FXML
    private TableColumn<Persona, String> clmCiudad;

    private ObservableList<Persona> personasList = FXCollections.observableArrayList();

    @FXML
    protected void closeSession() {
        System.out.println("anda bien");
    }

    @FXML
    protected void elBoton() {
        System.out.println(txtTitle.getText());
        System.out.println(dpkrDeadline.getValue());
    }

    @FXML
    public void lista() {
        personasList.add(new Persona(txtNombre.getText(), spinner.getValue(), txtCiudad.getText()));
        tblvTaskList.setItems(personasList);
        txtNombre.clear();
        txtCiudad.clear();
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    @FXML
    public void initialize() {
        clmTaskTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        clmTaskDescription.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());
        clmCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));

        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }
}
