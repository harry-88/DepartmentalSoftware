package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ShowEmployee {
    @FXML
    private TextField sortField;

    @FXML
    private TableView<String> tableView;

    @FXML
    private TableColumn<String, String> employeeIdCol;

    @FXML
    private TableColumn<String, String> employeeNameCol;

    @FXML
    private TableColumn<String, String> employeePositionCol;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField employeePositionField;

    @FXML
    private TextField employeeSalaryField;

    @FXML
    private TextField employeeGenderField;

    @FXML
    private TextField employeeDobField;

    @FXML
    private TextField employeeNumberField;

    @FXML
    private TextField employeeAddressField;

    @FXML
    private TextField employeeEmailField;

    @FXML
    void removeField(ActionEvent event) {

    }

    @FXML
    void sortEmployee(KeyEvent event) {

    }

    @FXML
    void viewDetail(ActionEvent event) {

    }
}
