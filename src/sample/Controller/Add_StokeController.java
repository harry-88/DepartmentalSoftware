package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Database.DatabaseConfig;
import sample.Database.DatabaseHandler;
import sample.ModelClasses.Stoke;

//import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class Add_StokeController implements Initializable {

    DatabaseHandler databaseHandler;
    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemQtyField;

    @FXML
    private TextField itemCompanyField;

    @FXML
    private DatePicker mfgDatePicker;

    @FXML
    private DatePicker expDatePicker;

    @FXML
    private ComboBox<?> measureInCombo;

    @FXML
    private TextField barcodeField;

    @FXML
    private TextField retailPricePicker;


    @FXML
    private TextField itemweightField;

    @FXML
    private TextField buyPricdPicker;

    @FXML
    private TableView<Stoke> tableview;

    @FXML
    private TableColumn<Stoke,String> itemNameCol;

    @FXML
    private TableColumn<Stoke,String> itemCompanyCol;

    @FXML
    private TableColumn<Stoke,String> itemQtyCol;

    @FXML
    private TableColumn<Stoke,String> itemWeightCol;

    @FXML
    private TableColumn<Stoke,String> itemBarcodeCol;

    @FXML
    private TableColumn<Stoke,String> itemMfgCol;

    @FXML
    private TableColumn<Stoke,String> itemExpCol;

    @FXML
    private TableColumn<Stoke,String> itemBuyPriceCol;

    @FXML
    private TableColumn<Stoke,String> itemRetailPriceCol;


    @FXML
    void addStock(ActionEvent event) {

        if (itemNameField.getText().isEmpty() || itemCompanyField.getText().isEmpty() ||
        itemQtyField.getText().isEmpty() || barcodeField.getText().isEmpty() ||
        retailPricePicker.getText().isEmpty() || buyPricdPicker.getText().isEmpty() || mfgDatePicker.getEditor().getText().isEmpty() ||
        expDatePicker.getEditor().getText().isEmpty() || itemweightField.getText().isEmpty() ||
        measureInCombo.getEditor().getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"All fields are required.");
            alert.setHeaderText(null);
            alert.setTitle("fill all fields");
            alert.showAndWait();
        }
        else {
            LocalDate mfgDate = mfgDatePicker.getValue();
            int mfgYear = mfgDate.getYear();
            int mfgMonth = mfgDate.getMonthValue();


            LocalDate expDate = expDatePicker.getValue();
            int expYear = expDate.getYear();
            int expMonth = expDate.getMonthValue();

            if (mfgYear > expYear)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,"You entered invalid dates");
                alert.setHeaderText(null);
                alert.setTitle("Check the dates");
                alert.showAndWait();
            }else if(mfgMonth > expMonth)
            {

                Alert alert = new Alert(Alert.AlertType.WARNING,"You entered invalid dates");
                alert.setHeaderText(null);
                alert.setTitle("Check the dates");
                alert.showAndWait();
            }
            else
            {
                String weight = itemweightField.getText();

                Date date = new Date();

                Stoke stoke = new Stoke(itemNameField.getText(),itemQtyField.getText(),itemCompanyField.getText()
                        ,barcodeField.getText(),weight,measureInCombo.getSelectionModel().getSelectedItem()+"",expDate+"",mfgDate+"",
                        buyPricdPicker.getText(),retailPricePicker.getText(),date+"");
                if(tableview.getItems().size() > -1)
                {
                    boolean flag = true;
                    for (int i = 0;i<tableview.getItems().size();i++)
                    {
                        if (barcodeField.getText().equals(tableview.getItems().get(i).getItemBarcode()))
                        {
                            int quantityInDatabase = Integer.parseInt(tableview.getItems().get(i).getItemQuantity());
                            int quantityEntered= Integer.parseInt(itemQtyField.getText());
                            databaseHandler.updateStockQuantity((quantityEntered+quantityInDatabase),barcodeField.getText());
                            stoke.setItemQuantity((Integer.parseInt(stoke.getItemQuantity())+(quantityInDatabase) )+"");

                            tableview.getItems().set(i,stoke);
                            flag = false;
                        }

                    }
                    if (flag){

                        databaseHandler.AddStoke(stoke);
                        tableview.getItems().add(stoke);
                        itemNameCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemName"));
                        itemCompanyCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemCompany"));
                        itemQtyCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemQuantity"));
                        itemBarcodeCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemBarcode"));
                        itemWeightCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemWeight"));
                        itemExpCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemexpDate"));
                        itemMfgCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemmfgDate"));
                        itemBuyPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itembuyPrice"));
                        itemRetailPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemRetailPrice"));
                    }

                }else {
                    databaseHandler.AddStoke(stoke);
                    tableview.getItems().add(stoke);
                    itemNameCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemName"));
                    itemCompanyCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemCompany"));
                    itemQtyCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemQuantity"));
                    itemBarcodeCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemBarcode"));
                    itemWeightCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemWeight"));
                    itemExpCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemexpDate"));
                    itemMfgCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemmfgDate"));
                    itemBuyPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itembuyPrice"));
                    itemRetailPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemRetailPrice"));
                }
            }
        }
        clearEnteredFields();
    }

    @FXML
    void clearFields(ActionEvent event) {

        clearEnteredFields();

    }



    public void clearEnteredFields()
    {

        itemNameField.clear();
        itemCompanyField.clear();
        itemQtyField.clear();
        barcodeField.clear();
        buyPricdPicker.clear();
        retailPricePicker.clear();
        mfgDatePicker.getEditor().clear();
        expDatePicker.getEditor().clear();
        measureInCombo.getEditor().clear();
        itemweightField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        databaseHandler = new DatabaseHandler(DatabaseConfig.getConnection());

        ObservableList list = FXCollections.observableArrayList(
                databaseHandler.getAllStoke()
        );

        tableview.setItems(list);
        tableview.setEditable(true);

        itemNameCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemName"));
        itemCompanyCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemCompany"));
        itemQtyCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemQuantity"));
        itemQtyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        itemQtyCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stoke, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Stoke, String> event) {
                Stoke stoke = event.getRowValue();
                if (Integer.parseInt(event.getNewValue()) > 0)
                    databaseHandler.updateStockQuantity(Integer.parseInt(event.getNewValue()),stoke.getItemBarcode());
                else if (Integer.parseInt(event.getNewValue()) == 0) {
                    databaseHandler.deleteStock(stoke.getItemBarcode());
                    tableview.getItems().remove(tableview.getSelectionModel().getSelectedIndex());
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"You have entered an invalid quantity");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    tableview.getItems().add(stoke);
                    tableview.getItems().remove(tableview.getSelectionModel().getSelectedIndex());
                }

            }
        });

        itemBarcodeCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemBarcode"));
        itemWeightCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemWeight"));
        itemExpCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemexpDate"));
        itemMfgCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemmfgDate"));
        itemBuyPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itembuyPrice"));
        itemRetailPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke,String>("itemRetailPrice"));

        ObservableList comboOption = FXCollections.observableArrayList(
                new String[]{"gram","kilo-gram","letter","mili-letter"}
        );

        measureInCombo.setItems(comboOption);

    }

    public void onBarcodeScan(javafx.scene.input.KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER)
        {
            Stoke stoke = databaseHandler.getCurrantStokeByBarcode(barcodeField.getText());
            if (stoke != null)
            {
                itemNameField.setText(stoke.getItemName());
                itemCompanyField.setText(stoke.getItemCompany());
                itemweightField.setText(stoke.getItemWeight());
                retailPricePicker.setText(stoke.getItemRetailPrice());
                buyPricdPicker.setText(stoke.getItembuyPrice());
                expDatePicker.getEditor().setText(stoke.getItemexpDate());
//                expDatePicker.setValue((LocalDate)stoke.getItemexpDate());
                mfgDatePicker.getEditor().setText(stoke.getItemmfgDate());

//                mfgDatePicker.setValue((LocalDate)stoke.getItemmfgDate());
            }
        }
    }
}
