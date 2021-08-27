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
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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

                    priceCalculation();
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

                            priceCalculation();

                        }

                    }

                    if (flag)
                    {
                        stoke.setItemQuantity(1+"");
                        itemQtyField.setText(tableView.getItems().size() + "");
                        double price = Double.parseDouble(totalAmountField.getText());
                        price += Double.parseDouble(stoke.getItemRetailPrice());
                        totalAmountField.setText(price + "");


                        priceCalculation();

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

        for (int i = 0;i<tableView.getItems().size();i++)
        {
            int quantity = Integer.parseInt(databaseHandler.getCurrantStokeByBarcode(tableView.getItems().get(i).getItemBarcode()).getItemQuantity()) -
                    Integer.parseInt(tableView.getItems().get(i).getItemQuantity());
            databaseHandler.updateStockQuantity(quantity,tableView.getItems().get(i).getItemBarcode());
        }
        tableView.getItems().clear();
        clear();

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

        priceCalculation();


    }

    public void priceCalculation()
    {

        double netPrice = Double.parseDouble(totalAmountField.getText()) + Double.parseDouble(extraChargesField.getText());

        netPrice -= netPrice*(Double.parseDouble(discountField.getText())/100);

        System.out.println("total amount is "+netPrice);

        System.out.println("net price is "+netPrice);
        netAmountField.setText(netPrice+"");
    }

    @FXML
    void discount(KeyEvent event) {

        double amountToSubtract = (Double.parseDouble(netAmountField.getText()) * Double.parseDouble(discountField.getText()))/100;
        if (Double.parseDouble(discountField.getText()) <= 100)
            netAmountField.setText((Double.parseDouble(netAmountField.getText()) - amountToSubtract)+"");
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

        remainingField.setText(""+(Double.parseDouble(givenAmountField.getText())-Double.parseDouble(netAmountField.getText())));
    }

    @FXML
    void printRecipt(ActionEvent event) {

    }

    @FXML
    void sortItem(KeyEvent event) {

        for (int i = 0;i<tableView.getItems().size();i++)
        {
            if (tableView.getItems().get(i).getItemName().startsWith(findItem.getText().trim()) ||
            tableView.getItems().get(i).getItemQuantity().startsWith(findItem.getText().trim())||
            tableView.getItems().get(i).getItemRetailPrice().startsWith(findItem.getText().trim())){

                Stoke stoke = tableView.getItems().get(i);
                tableView.getItems().remove(i);
                tableView.getItems().add(0,stoke);
            }
        }

    }

    public void clear()
    {

        System.out.println("table view size "+tableView.getItems().size());
        totalAmountField.setText("0.0");
        itemQtyField.setText("0");
        netAmountField.setText("0.0");
        extraChargesField.setText("0.0");
        remainingField.setText("0.0");
        taxAmountField.setText("0.0");
        discountField.setText("0");
        givenAmountField.setText("0.0");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = new DatabaseHandler(DatabaseConfig.getConnection());

        clear();

        findItem.setVisible(false);
        btnClose.setVisible(false);
        tableView.setEditable(true);
        itemQtyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        itemQtyCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stoke, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Stoke, String> event) {

                Stoke stoke = event.getRowValue();

                Stoke stokeInDatabase = databaseHandler.getCurrantStokeByBarcode(stoke.getItemBarcode());

                double price = Double.parseDouble(stokeInDatabase.getItemRetailPrice());
                double amountInField = Double.parseDouble(totalAmountField.getText());

                if ((Integer.parseInt(stokeInDatabase.getItemQuantity()))>=Integer.parseInt(event.getNewValue()))
                {
                    if (Integer.parseInt(event.getNewValue()) == 0) {
                        double totalAmount = Double.parseDouble(tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex()).getItemRetailPrice());
                        tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
                        totalAmountField.setText((Double.parseDouble(totalAmountField.getText()) - totalAmount) + "");

                        priceCalculation();
                    } else if (Integer.parseInt(event.getNewValue()) > Integer.parseInt(event.getOldValue())) {
                        amountInField -= Double.parseDouble(stoke.getItemRetailPrice());//the quantity present in textField
                        totalAmountField.setText(amountInField + "");//

                        int qty = Integer.parseInt(event.getNewValue());
                        price = price * qty;
                        stoke.setItemRetailPrice(price + "");
                        double totalPrice = Double.parseDouble(totalAmountField.getText());
                        totalPrice += price;
                        totalAmountField.setText(totalPrice + "");
                        stoke.setItemRetailPrice(price + "");

                        stoke.setItemQuantity(event.getNewValue());
                        tableView.getItems().remove(stoke);
                        tableView.getItems().add(stoke);
                        priceCalculation();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING,"This item have less quantity from the quantity that you entered");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    stokeInDatabase.setItemQuantity(event.getOldValue());
                    stokeInDatabase.setItemRetailPrice(event.getRowValue().getItemRetailPrice());
                    tableView.getItems().add(tableView.getSelectionModel().getSelectedIndex(),stokeInDatabase);
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
                }
            }


        });
    }
}
