package com.orchid.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.orchid.pos.dao.DatabaseAccessCode;
import com.orchid.pos.dto.CustomerDto;
import com.orchid.pos.view.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;

public class CustomerFormController {
    public AnchorPane context;
    public JFXTextField txtEmail;
    public JFXTextField txtContact;
    public JFXTextField txtSalary;
    public JFXTextField txtName;
    public JFXButton btnSaveUpdate;
    public TextField txtSearch;
    public TableView<CustomerTm> tbl;
    public TableColumn colId;
    public TableColumn colEmail;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colOperate;


    private String searchText="";

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        loadAllCustomers(searchText);
    }

    private void loadAllCustomers(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
        int counter = 1;
        for(CustomerDto customerDto : DatabaseAccessCode.searchCustomers(searchText)){
            Button btn = new Button("Delete");
            CustomerTm customerTm = new CustomerTm(counter,customerDto.getEmail(),customerDto.getName(),customerDto.getContact(),customerDto.getSalary(),btn);
            observableList.add(customerTm);
            counter++;
        }
        tbl.setItems(observableList);
    }

    public void btnSaveUpdateOnAction(ActionEvent actionEvent) {
        try{
            if(DatabaseAccessCode.createCustomer(txtEmail.getText(),txtName.getText(),txtContact.getText(), Double.parseDouble(txtSalary.getText()))){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
                clearFields();
                loadAllCustomers(searchText);
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (SQLException| ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtEmail.clear();
        txtName.clear();
        txtContact.clear();
        txtSalary.clear();
    }

    private void setUi(String url) throws IOException {
        Stage stage=(Stage)context.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml")))
        );

    }

    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }
}
