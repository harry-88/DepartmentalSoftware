package sample.Database;

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

        String query = "INSERT INTO Stoke(barcode,itemname,itemcompany" +
                ",itemquantity,weight,mfgDate,expDate,buyPrice,retailPrice)VALUES(?,?,?,?,?,?,?,?,?)";
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
        String query = "SELECT * FROM stoke" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Stoke item = new Stoke(resultSet.getString(2),resultSet.getString(4),
                        resultSet.getString(3),resultSet.getString(1)
                        ,resultSet.getString(5),resultSet.getString(7),
                        resultSet.getString(6),resultSet.getString(8)
                        ,resultSet.getString(9));

                itemArrayList.add(item);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemArrayList;
    }


    public Stoke getCurrantStokeByBarcode(String barcode)
    {
        String query = "SELECT * FROM stoke WHERE barcode =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,barcode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.absolute(1);
            if (resultSet != null)
            {
                if (resultSet.getString(1).equals(barcode)) {
                    Stoke item = new Stoke(resultSet.getString(2),resultSet.getString(4),
                            resultSet.getString(3),resultSet.getString(1)
                            ,resultSet.getString(5),resultSet.getString(7),
                            resultSet.getString(6),resultSet.getString(8)
                            ,resultSet.getString(9));

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
}
