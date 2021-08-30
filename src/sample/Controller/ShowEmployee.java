package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sample.Database.DatabaseConfig;
import sample.Database.DatabaseHandler;
import sample.ModelClasses.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowEmployee implements Initializable {

    DatabaseHandler databaseHandler;


    @FXML
    private TextField employeeId;

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
        if (employeeId.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"You have not selected any record to delete.\n" +
                    "Select the record to delete.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            databaseHandler.deleteEmployee(Integer.parseInt(employeeId.getText().trim()));
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
            clearScreen();
        }

    }

    public void clearScreen()
    {
        employeeNameField.clear();
        employeeAddressField.clear();
        employeeNumberField.clear();
        employeeEmailField.clear();
        employeeGenderField.clear();
        employeePositionField.clear();
        employeeDobField.clear();
        employeeSalaryField.clear();
        employeeId.clear();

    }

    @FXML
    void sortEmployee(KeyEvent event) {
        for (int i = 0;i<tableView.getItems().size();i++)
        {
            if (tableView.getItems().get(i).getEmployeeName().startsWith(sortField.getText().trim()) ||
            tableView.getItems().get(i).getEmployeeId().startsWith(sortField.getText().trim()) ||
            tableView.getItems().get(i).getEmployeePosition().startsWith(sortField.getText().trim()))
            {
                System.out.println("in function");
                Employee employee = tableView.getItems().get(i);
                tableView.getItems().remove(i);
                tableView.getItems().add(0,employee);

                employeeNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeName"));
                employeePositionCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeePosition"));
                employeeIdCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeId"));
            }
        }

    }

    @FXML
    void viewDetail(ActionEvent event) {
        Employee employee = tableView.getSelectionModel().getSelectedItem();
        if (employee == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have not selected any row in table.\nto display the record of employee.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else{
            employeeNameField.setText(employee.getEmployeeName());
            employeeAddressField.setText(employee.getEmployeeAddress());
            employeeNumberField.setText(employee.getEmployeeNumber());
            employeeEmailField.setText(employee.getEmployeeEmail());
            employeeGenderField.setText(employee.getEmployeeGender());
            employeePositionField.setText(employee.getEmployeePosition());
            employeeDobField.setText(employee.getEmployeeDob());
            employeeSalaryField.setText(employee.getEmployeeSalary());
            employeeId.setText(employee.getEmployeeId());
        }

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

    public static class SaleDetailController {
    }
}
