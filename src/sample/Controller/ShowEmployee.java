package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sample.Database.DatabaseConfig;
import sample.Database.DatabaseHandler;
import sample.ModelClasses.Employee;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowEmployee implements Initializable {

    DatabaseHandler databaseHandler;
    @FXML
    private TextField sortField;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> employeeIdCol;

    @FXML
    private TableColumn<Employee, String> employeeNameCol;

    @FXML
    private TableColumn<Employee, String> employeePositionCol;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = new DatabaseHandler(DatabaseConfig.getConnection());
        ObservableList<Employee> list = FXCollections.observableArrayList(
                databaseHandler.getAllEmployee()
        );
        tableView.setItems(list);
        employeeNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeName"));
        employeePositionCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeePosition"));
        employeeIdCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeId"));

    }
}
