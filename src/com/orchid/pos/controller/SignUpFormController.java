package com.orchid.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.orchid.pos.util.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpFormController {
    public JFXTextField txtEmail;

    public AnchorPane context;
    public JFXPasswordField txtPassword;

    public void btnRegisterNowOnAction(ActionEvent actionEvent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_orchid","root","1241");
            String sql = "INSERT INTO user VALUES (?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,txtEmail.getText());
            preparedStatement.setString(2, PasswordManager.encryptPassword(txtPassword.getText()));

            if(preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved!").show();
                clearFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }




        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }




    }

    private void clearFields() {
        txtEmail.clear();
        txtPassword.clear();
    }

    public void btnAlreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    private void setUi(String url) throws IOException {
        Stage stage=(Stage)context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml")))
        );
        stage.centerOnScreen();
    }
}
