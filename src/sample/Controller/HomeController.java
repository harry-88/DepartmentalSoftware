package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private AnchorPane home;

    @FXML
    void aboutApp(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Departmental Store\n" +
                "Build #IU-212.4746.92, built on July 27, 2021\n" +
                "Licensed to Muhammad Haris\n" +
                "Subscription is active until August 10, 2022.\n" +
                "Runtime version: 11.0.11+9-b1504.13 amd64\n" +
                "VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.\n" +
                "Windows 10 10.0\n" +
                "GC: G1 Young Generation, G1 Old Generation\n" +
                "Memory: 1010M\n" +
                "Cores: 4\n" +
                "\n" +
                "Kotlin: 212-1.5.10-release-IJ4746.92");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void addEmployee(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/AddEmployee.fxml"));
            home.getChildren().clear();
            home.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnSaleClicked(ActionEvent event) {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/SaleItem.fxml"));

            home.getChildren().clear();
            home.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnStockClicked(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/Add_Stoke.fxml"));

            home.getChildren().clear();
            home.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void saleDetail(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/SaleDetail.fxml"));

            home.getChildren().clear();
            home.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void setting(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"You don't need to make the setting of the app");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void showEmployee(ActionEvent event) {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/ShowEmployee.fxml"));

            home.getChildren().clear();
            home.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void stockDetail(ActionEvent event) {

    }



    @FXML
    void clearScreen(ActionEvent event) {
        home.getChildren().clear();

    }



    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) home.getScene().getWindow();
        stage.close();
    }

    @FXML
    void newWindow(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/FXML/Home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }
}
