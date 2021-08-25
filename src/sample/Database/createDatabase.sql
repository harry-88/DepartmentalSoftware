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


    employeeID int auto increment ,
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

select * from employee;