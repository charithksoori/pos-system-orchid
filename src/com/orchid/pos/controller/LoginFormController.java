package com.orchid.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.orchid.pos.dao.DatabaseAccessCode;
import com.orchid.pos.dto.UserDto;
import com.orchid.pos.util.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public JFXTextField txtEmail;

    public AnchorPane context;
    public JFXPasswordField txtPassword;

    public void btnSignInOnAction(ActionEvent actionEvent) {
        //login
        try {
            UserDto userDto = DatabaseAccessCode.findUser(txtEmail.getText());
            if(userDto!=null){
                if(PasswordManager.checkPassword(txtPassword.getText(),userDto.getPassword())){
                    setUi("DashboardForm");
                }else{
                    new Alert(Alert.AlertType.WARNING,"Check your password and try again!").show();
                }

            }else{
                new Alert(Alert.AlertType.WARNING,"User email not found").show();
            }


        }catch(ClassNotFoundException | SQLException | IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnCreateOnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignUpForm");
    }

    private void setUi(String url) throws IOException {
        Stage stage=(Stage)context.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml")))
        );

    }
}
