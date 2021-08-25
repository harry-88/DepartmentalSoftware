package sample.Database;

import sample.ModelClasses.Employee;
import sample.ModelClasses.Stoke;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseHandler {

    Connection connection;

    public DatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public boolean AddStoke(Stoke stoke)
    {

        String query = "INSERT INTO Stock(barcode,itemname,itemcompany" +
                ",itemquantity,weight,mfgDate,expDate,buyPrice,retailPrice,stockDate,measurein)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,stoke.getItemBarcode());
            preparedStatement.setString(2,stoke.getItemName());
            preparedStatement.setString(3,stoke.getItemCompany());
            preparedStatement.setString(4,stoke.getItemQuantity());
            preparedStatement.setString(5,stoke.getItemWeight());
            preparedStatement.setString(6,stoke.getItemmfgDate());
            preparedStatement.setString(7,stoke.getItemexpDate());
            preparedStatement.setString(8,stoke.getItembuyPrice());
            preparedStatement.setString(9,stoke.getItemRetailPrice());
            preparedStatement.setString(10,stoke.getAddDate());
            preparedStatement.setString(11,stoke.getMeasurein());
            if (!preparedStatement.execute())
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Stoke> getAllStoke()
    {

        ArrayList<Stoke> itemArrayList = new ArrayList<>();
        String query = "SELECT * FROM stock" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next() ){
                Stoke item = new Stoke(resultSet.getString(2),resultSet.getString(4),
                        resultSet.getString(3),resultSet.getString(1)
                        ,resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(8),resultSet.getString(7),resultSet.getString(9),
                        resultSet.getString(10),resultSet.getString(11)
                );

                itemArrayList.add(item);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemArrayList;
    }

    public Stoke getCurrantStokeByBarcode(String barcode)
    {
        String query = "SELECT * FROM stock WHERE barcode =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,barcode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next())
            {
                if (resultSet.getString(1).equals(barcode)) {
                    Stoke item = new Stoke(resultSet.getString(2),resultSet.getString(4),
                            resultSet.getString(3),resultSet.getString(1)
                            ,resultSet.getString(5),resultSet.getString(6),
                            resultSet.getString(8),resultSet.getString(7),resultSet.getString(9),
                            resultSet.getString(10),resultSet.getString(11));

                    return item;
                }
            }
            else
                return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public  boolean updateStockQuantity(int quantity,String barcode)
    {
        String query = "UPDATE stock SET itemquantity=? WHERE barcode=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,quantity+"");
            preparedStatement.setString(2,barcode);
            if (!preparedStatement.execute())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addEmployee(Employee employee)
    {

        String query = "INSERT INTO employee(employeeName,employeeAddress,employeeNumber" +
                ",employeeEmail,employeeDob,employeeGender,employeePosition,employeeSalary,employeePassword)VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getEmployeeName());
            preparedStatement.setString(2,employee.getEmployeeAddress());
            preparedStatement.setString(3,employee.getEmployeeNumber());
            preparedStatement.setString(4,employee.getEmployeeEmail());
            preparedStatement.setString(5,employee.getEmployeeDob());
            preparedStatement.setString(6,employee.getEmployeeGender());
            preparedStatement.setString(7,employee.getEmployeePosition());
            preparedStatement.setString(8,employee.getEmployeeSalary());
            preparedStatement.setString(9,employee.getEmployeePassword());
            if (!preparedStatement.execute())
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Employee> getAllEmployee()
    {
        ArrayList<Employee> itemArrayList = new ArrayList<>();
        String query = "SELECT * FROM employee" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next() ){
                Employee item = new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));

                itemArrayList.add(item);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemArrayList;
    }
}
