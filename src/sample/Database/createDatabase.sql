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

create table employee(


    employeeID int  auto_increment primary key,
    employeeName varchar(20) not null,
    employeeAddress varchar(40) not null,
    employeeNumber varchar(20) not null,
    employeeEmail  varchar(30),
    employeeDob varchar (30) not null,
    employeeGender varchar (7) not null,
    employeePosition varchar (30) not null ,
    employeeSalary varchar (30) not null,
    employeePassword varchar (30) not null
);

create table saledetail(
	saleID int primary key auto_increment,
    saleDate varchar(25) not null,
    saleTime varchar(25) not null,
    saleTotalQty varchar(25) not null,
    saleTotalAmount varchar(25) not null,
    saleExtraCharges varchar(25) not null,
    saleDiscount varchar(25) not null,
    saleNetAmount varchar(25) not null,
    saleGivenAmount varchar(25) not null
);

create table soldItem(
    barcode varchar(15) not null ,
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
select * from employee;
select * from stock;
select * from solditem;
select * from saledetail;

