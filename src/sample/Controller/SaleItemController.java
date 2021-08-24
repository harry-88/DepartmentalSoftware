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

import java.net.URL;
import java.util.ResourceBundle;

public class SaleItemController  implements Initializable{


    DatabaseHandler databaseHandler;
    @FXML
    private TableView<Stoke> tableView;

    @FXML
    private TableColumn<Stoke, String> itemNameCol;

    @FXML
    private TableColumn<Stoke, String> itemQtyCol;

    @FXML
    private TableColumn<Stoke, String> itemPriceCol;

    @FXML
    private TextField barcodefield;

    @FXML
    private TextField itemQtyField;

    @FXML
    private TextField totalAmountField;

    @FXML
    private TextField extraChargesField;

    @FXML
    private TextField discountField;

    @FXML
    private TextField taxAmountField;

    @FXML
    private TextField netAmountField;

    @FXML
    private TextField givenAmountField;

    @FXML
    private TextField remainingField;

    @FXML
    private TextField findItem;


    @FXML
    private Button btnClose;


    @FXML
    void barcodeRead(KeyEvent event) {


        if (event.getCode() == KeyCode.ENTER)
        {
            Stoke stoke = databaseHandler.getCurrantStokeByBarcode(barcodefield.getText());
            if (stoke != null)
            {
                stoke.setItemQuantity("1");

                if(tableView.getItems().size()==0) {
                    tableView.getItems().add(stoke);
                    itemNameCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemName"));
                    itemQtyCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemQuantity"));
                    itemPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemRetailPrice"));

                    itemQtyField.setText(tableView.getItems().size() + "");
                    double price = Double.parseDouble(totalAmountField.getText());
                    price += Double.parseDouble(stoke.getItemRetailPrice());
                    totalAmountField.setText(price + "");
                }
                else
                {

                    boolean flag = true;
                    for (int i = 0;i<tableView.getItems().size();i++)
                    {
                        if (barcodefield.getText().equals(tableView.getItems().get(i).getItemBarcode()))
                        {
                            flag = false;
                            int qty = Integer.parseInt(tableView.getItems().get(i).getItemQuantity());
                            ++qty;
                            stoke.setItemQuantity(qty+"");

                            itemQtyField.setText(tableView.getItems().size() + "");
                            double price = Double.parseDouble(totalAmountField.getText());
                            price += Double.parseDouble(stoke.getItemRetailPrice());
                            totalAmountField.setText(price + "");


                            stoke.setItemRetailPrice((Double.parseDouble(stoke.getItemRetailPrice())*qty )+"");

                            tableView.getItems().set(i,stoke);
                            itemNameCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemName"));
                            itemQtyCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemQuantity"));
                            itemPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemRetailPrice"));


                        }

                    }

                    if (flag)
                    {
                        stoke.setItemQuantity(1+"");
                        itemQtyField.setText(tableView.getItems().size() + "");
                        double price = Double.parseDouble(totalAmountField.getText());
                        price += Double.parseDouble(stoke.getItemRetailPrice());
                        totalAmountField.setText(price + "");


                        tableView.getItems().add(stoke);
                        itemNameCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemName"));
                        itemQtyCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemQuantity"));
                        itemPriceCol.setCellValueFactory(new PropertyValueFactory<Stoke, String>("itemRetailPrice"));
                    }
                }

            }

            barcodefield.clear();
        }

    }

    @FXML
    void clearScreen(ActionEvent event) {

        tableView.getItems().clear();
        totalAmountField.clear();
        discountField.clear();
        extraChargesField.clear();
        taxAmountField.clear();
        netAmountField.clear();
        itemQtyField.clear();
        givenAmountField.clear();
        remainingField.clear();

    }

    @FXML
    void closeFind(ActionEvent event) {

        findItem.setVisible(false);
        btnClose.setVisible(false);
    }

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @FXML
    void extraCharges(KeyEvent event) {


        netAmountField.setText((Double.parseDouble(netAmountField.getText()) + Double.parseDouble(extraChargesField.getText()))+"");

    }


    @FXML
    void discount(KeyEvent event) {

        if (Double.parseDouble(discountField.getText()) <= 100)
            netAmountField.setText(((Double.parseDouble(netAmountField.getText()) * Double.parseDouble(discountField.getText()))/100)+"");
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"you have entered an in-valid discount");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }



    @FXML
    void findItem(ActionEvent event) {
        btnClose.setVisible(true);
        findItem.setVisible(true);

    }

    @FXML
    void givenAmount(KeyEvent event) {

        remainingField.setText(""+(Double.parseDouble(netAmountField.getText())-Double.parseDouble(givenAmountField.getText())));
    }

    @FXML
    void printRecipt(ActionEvent event) {

    }

    @FXML
    void sortItem(KeyEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = new DatabaseHandler(DatabaseConfig.getConnection());

        System.out.println("table view size "+tableView.getItems().size());
        totalAmountField.setText("0.0");
        itemQtyField.setText("0");
        netAmountField.setText("0.0");
        extraChargesField.setText("0.0");
        remainingField.setText("0.0");
        taxAmountField.setText("0.0");
        discountField.setText("0.0");

        findItem.setVisible(false);
        btnClose.setVisible(false);
        tableView.setEditable(true);
        itemQtyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        itemQtyCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stoke, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Stoke, String> event) {

                Stoke stoke = event.getRowValue();


                double price = Double.parseDouble(databaseHandler.getCurrantStokeByBarcode(stoke.getItemBarcode()).getItemRetailPrice());
                double amountInField = Double.parseDouble(totalAmountField.getText());

                if(Integer.parseInt(event.getNewValue()) > Integer.parseInt(event.getOldValue())) {
                    amountInField -= Double.parseDouble(stoke.getItemRetailPrice());//the quantity present in textField
                    totalAmountField.setText(amountInField + "");//

                    int qty = Integer.parseInt(event.getNewValue());
                    price = price * qty;
                    stoke.setItemRetailPrice(price + "");
                    double totalPrice = Double.parseDouble(totalAmountField.getText());
                    totalPrice += price;
                    totalAmountField.setText(totalPrice + "");
                    stoke.setItemRetailPrice(price+"");

                    stoke.setItemQuantity(event.getNewValue());
                    tableView.getItems().remove(stoke);
                    tableView.getItems().add(stoke);
                }
            }


        });
    }
}
