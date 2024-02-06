package com.ceica.firstjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblMessage;

    public void onLoginClick(ActionEvent actionEvent) {
        if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("1234")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage currentStage = (Stage) lblMessage.getScene().getWindow();
                currentStage.close();
                Stage stage = new Stage();
                stage.setTitle("User menu");
                stage.setScene(scene);
                stage.show();

            } catch (IOException ignored) {
            }
        } else
            lblMessage.setText("Incorrect user or password");
    }
}
