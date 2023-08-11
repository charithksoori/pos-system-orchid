package com.orchid.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtEmail;

    public AnchorPane context;
    public JFXPasswordField txtPassword;

    public void btnSignInOnAction(ActionEvent actionEvent) {
        //login
    }

    public void btnCreateOnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignUpForm");
    }

    private void setUi(String url) throws IOException {
        Stage stage=(Stage)context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml")))
        );
        stage.centerOnScreen();
    }
}
