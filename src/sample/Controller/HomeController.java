package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private AnchorPane home;

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
    void clearScreen(ActionEvent event) {
        home.getChildren().clear();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }
}