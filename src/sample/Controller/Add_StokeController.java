package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

//import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Add_StokeController implements Initializable {


    @FXML
    private TextField barcodeField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onBarcodeScan(javafx.scene.input.KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER)
        {
            barcodeField.setText(keyEvent.getText()+"");
        }
    }
}
