package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import sample.Database.DatabaseConfig;
import sample.Database.DatabaseHandler;
import sample.ModelClasses.Employee;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class AddEmployeeController implements Initializable {


    DatabaseHandler databaseHandler;
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
    private PasswordField passwordField;

    @FXML
    private Label showPassLabel;

    @FXML
    void addEmployee(ActionEvent event) {


        if (employeeNameField.getText().isEmpty() ||employeeAddressField.getText().isEmpty() ||
        employeeNumberField.getText().isEmpty() || employeeDate.getValue() == null || !(male.isSelected() || female.isSelected())
                ||positionCombo.getSelectionModel().getSelectedItem() == null || salaryField.getText().isEmpty() ||passwordField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"All fields are required");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");

            String date = employeeDate.getValue().format(formatter);

            String gender = null;
            if (male.isSelected())
                gender = "male";
            else
                gender = "female";
            System.out.println("date is "+date);
            Employee employee = new Employee("1",employeeNameField.getText(),employeeAddressField.getText(),employeeNumberField.getText(),
                    emploeeEmailField.getText(),date,gender,positionCombo.getValue(),salaryField.getText(),passwordField.getText());
            databaseHandler.addEmployee(employee);
            clearField();



        }
    }


    public void clearField()
    {
        employeeNameField.clear();
        employeeAddressField.clear();
        employeeNumberField.clear();
        emploeeEmailField.clear();
        salaryField.clear();
        passwordField.clear();
        employeeDate.getEditor().clear();
        positionCombo.getEditor().clear();
    }

    @FXML
    void ShowPassword(ActionEvent event) {

        showPassLabel.setVisible(!showPassLabel.isVisible());

    }


    @FXML
    void showPassFun(KeyEvent event) {
        showPassLabel.setText(passwordField.getText());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showPassLabel.setVisible(false);
        databaseHandler = new DatabaseHandler(DatabaseConfig.getConnection());
        ObservableList list = FXCollections.observableArrayList(
                new String[]{"Manager","Super visor","cashier","computer operator","peon"}
        );
        positionCombo.setItems(list);
    }
}
