package com.orchid.pos.dao;

import com.orchid.pos.dto.CustomerDto;
import com.orchid.pos.dto.UserDto;
import com.orchid.pos.util.PasswordManager;

import java.sql.*;
import java.util.List;

public class DatabaseAccessCode {

    //user management
    public static boolean createUser(String email, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_orchid","root","1241");
        String sql = "INSERT INTO user VALUES (?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2, PasswordManager.encryptPassword(password));
        return preparedStatement.executeUpdate()>0;
    }

    public static UserDto findUser(String email) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_orchid","root","1241");
        String sql = "SELECT  * FROM user WHERE email = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,email);

        ResultSet set = preparedStatement.executeQuery();
        if(set.next()){
            return new UserDto(set.getString(1), set.getString(2));
        }
        return null;
    }


    //customer management
    public static boolean createCustomer(String email, String name, String contact,double salary) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_orchid","root","1241");
        String sql = "INSERT INTO customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, contact);
        preparedStatement.setDouble(4, salary);
        return preparedStatement.executeUpdate()>0;
    }

    public static boolean updateCustomer(String email, String name, String contact,double salary){
        return false;
    }

    public static CustomerDto findCustomer(String email){
        return null;
    }

    public static boolean deleteCustomer(String email){
        return false;
    }

    public static List<CustomerDto> findAllCustomers(){
        return null;
    }
    public static List<CustomerDto> searchCustomers(String searchText){
        return null;
    }



}


