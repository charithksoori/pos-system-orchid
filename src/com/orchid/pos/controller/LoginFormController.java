package com.orchid.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.protocol.Resultset;
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_orchid","root","1241");
            String sql = "SELECT  * FROM user WHERE email = ?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,txtEmail.getText());

            ResultSet set = preparedStatement.executeQuery();
            if(set.next()){
                if(PasswordManager.checkPassword(txtPassword.getText(),set.getString("password"))){
                    System.out.println("Completed");
                }else{
                    new Alert(Alert.AlertType.WARNING,"CHeck your password and try again!").show();
                }

            }else{
                new Alert(Alert.AlertType.WARNING,"User email not found").show();
            }


        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

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
