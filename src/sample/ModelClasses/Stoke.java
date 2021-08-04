package sample.ModelClasses;

public class Stoke {
    private String itemName;
    private String itemQuantity;
    private String itemCompany;
    private String itemBarcode;
    private String itemWeight;
    private String itemexpDate;
    private String itemmfgDate;
    private String itembuyPrice;
    private String itemRetailPrice;

    public Stoke(String itemName, String itemQuantity, String itemCompany, String itemBarcode, String itemWeight, String itemexpDate, String itemmfgDate, String itembuyPrice, String itemRetailPrice) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemCompany = itemCompany;
        this.itemBarcode = itemBarcode;
        this.itemWeight = itemWeight;
        this.itemexpDate = itemexpDate;
        this.itemmfgDate = itemmfgDate;
        this.itembuyPrice = itembuyPrice;
        this.itemRetailPrice = itemRetailPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemCompany() {
        return itemCompany;
    }

    public void setItemCompany(String itemCompany) {
        this.itemCompany = itemCompany;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemexpDate() {
        return itemexpDate;
    }

    public void setItemexpDate(String itemexpDate) {
        this.itemexpDate = itemexpDate;
    }

    public String getItemmfgDate() {
        return itemmfgDate;
    }

    public void setItemmfgDate(String itemmfgDate) {
        this.itemmfgDate = itemmfgDate;
    }

    public String getItembuyPrice() {
        return itembuyPrice;
    }

    public void setItembuyPrice(String itembuyPrice) {
        this.itembuyPrice = itembuyPrice;
    }

    public String getItemRetailPrice() {
        return itemRetailPrice;
    }

    public void setItemRetailPrice(String itemRetailPrice) {
        this.itemRetailPrice = itemRetailPrice;
    }

    @Override
    public String toString() {
        return "Stoke{" +
                "itemName='" + itemName + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                ", itemCompany='" + itemCompany + '\'' +
                ", itemBarcode='" + itemBarcode + '\'' +
                ", itemWeight='" + itemWeight + '\'' +
                ", itemexpDate='" + itemexpDate + '\'' +
                ", itemmfgDate='" + itemmfgDate + '\'' +
                ", itembuyPrice='" + itembuyPrice + '\'' +
                ", itemRetailPrice='" + itemRetailPrice + '\'' +
                '}';
    }
}
