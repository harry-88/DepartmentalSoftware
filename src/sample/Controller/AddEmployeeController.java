package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;


public class AddEmployeeController implements Initializable {

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField employeeAddressField;

    @FXML
    private TextField employeeNumberField;

    @FXML
    private TextField emploeeEmailField;

    @FXML
    private DatePicker employeeDate;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private ComboBox<String> positionCombo;

    @FXML
    private TextField salaryField;

    @FXML
    void addEmployee(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList list = FXCollections.observableArrayList(
                new String[]{"Manager","Super visor","cashier","computer operator","peon"}
        );
        positionCombo.setItems(list);
    }
}
