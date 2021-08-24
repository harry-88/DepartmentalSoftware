create database generalStore;
use generalStore;
create table Stock(
    barcode varchar(15) not null primary key,
    itemname varchar(20) not null,
    itemcompany varchar(20) not null,
    itemquantity varchar(20) not null,
    weight varchar(20) not null,
    measurein varchar(20) not null,
    mfgDate date not null,
    expDate date not null,
    buyPrice varchar(20) not null,
    retailPrice varchar(20) not null,
    stockDate varchar(30) not null
    
);