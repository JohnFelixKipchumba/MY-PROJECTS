/*
 *  Run all the following statements;
 *  CREATE, USE, and INSERT. 
 *  This initializes the Database, Tables, Views and a few data points.
 *
 */
create database Supermarket_db;
use Supermarket_db;

/* table stores items in the supermarket */
create table item_record (
	Item_barcode bigint unsigned not null,
    Brand_name varchar(20) not null,
    Item_quantity int unsigned not null, check(Item_quantity >= 0),
    Measurement varchar(10) not null,
    primary key(Item_barcode) );
    
/* table stores suppliers details */
 create table suppliers_record (
	Contract_id int unsigned not null auto_increment,
    Enterprise_name varchar(20) not null,
    Licence_no varchar(20) not null,
    Products tinytext not null,
    Contract_yr date not null,
    Contract_duration int unsigned not null ,
	Email varchar(30) not null,
	Phone int unsigned not null,
	Address varchar(30) not null,
    primary key(Contract_id,Licence_no));  

/* table stores general staff details */
create table staff_record (
	Staff_id varchar(20) not null,
    Password varchar(20)  not null,
    First_name varchar(20) not null,
    Last_name varchar(20) not null,
    Other_name varchar(20) null,
    Gender enum("MALE","FEMALE") not null,
    ID int unsigned not null,
    DOB date not null,
    Phone int unsigned not null,
    Email varchar(30) not null,
    Address varchar(30) null,
    Employed_date date not null,
    Title enum("MANAGER","JUNIOR","SENIOR") not null,
    Salary float(2) not null check(Salary >= 0.00),
    Perks float(2) null check(Perks >= 0.00),
    Status enum("ACTIVE","TERMINATED") null default "ACTIVE",
    Termination_date date null,
    primary key(Staff_id,ID) );    
    
/* table stores restocking of items */
create table restocking_record (
	Serial_number int unsigned not null auto_increment,
    Item_barcode bigint unsigned not null,
    Contract_id int unsigned not null,
    Buy_price float(2)  not null, check(Buy_price > 0.00),
	Sell_price float(2) not null,  check(Sell_price > 0.00),
    Batch_no varchar(20) unique not null,
    Buy_quantity int unsigned not null, check(Buy_quantity > 0),
    Expiry date  null,
    Tax float(2)  not null, check(Tax > 0.00),
    Timestamp datetime not null,
    primary key(Serial_number),
    foreign key(Item_barcode) references item_record(Item_barcode),
	foreign key(Contract_id) references suppliers_record(Contract_id)); 
select * from restocking_record;
    
/* table stores item sold */
 create table sales_record (
	Serial_number int unsigned not null auto_increment,
	Item_barcode bigint unsigned not null,
    Sell_quantity int unsigned not null check(Sell_quantity > 0.00),
    Staff_id varchar(20) not null,
    Timestamp datetime not null,
    primary key(Serial_number),
    foreign key(Item_barcode) references item_record(Item_barcode),
    foreign key(Staff_id) references staff_record(Staff_id) );
select * from sales_record;
          
/* table stores the company details */
create table company_record (
	SN int unsigned not null auto_increment primary key,
	Company_name varchar(30) null,
    Motto varchar(50) null,
    Email varchar(30) null,
	Phone int unsigned null,
    Address varchar(30) null,
    Website varchar(50) null );
insert into company_record (sn) values (1); 
select * from company_record;
    
/* table stores the company financial details */ 
create table financial_record (
	SN int unsigned not null primary key, 
	Statutory_fees float(2)  null ,check(Statutory_fees > 0.00),
    Electricity_bill float(2) null check(Electricity_bill > 0.00),
	Water_bill float(2) null check(Water_bill > 0.00),
    Rent_bill float(2) null check(Rent_bill > 0.00),
    Petty_expenses float(2) null check(Petty_expenses > 0.00),
    Mobile_payment  tinytext null,
    Card_payment varchar(50) null,
    Bank_payment tinytext null );
insert into financial_record (sn) values(1);  
select * from financial_record;
    
    /* addItem() */
INSERT INTO item_record VALUES(10000,"Cooking Oil","100","2L"),
							  (10001,"Bar soap","500","500g"),
							  (10002,"Bread","200","100g");
select * from item_record;

	/*addSupplier*/
INSERT INTO suppliers_record(`Contract_id`,`Enterprise_name`,`Licence_no`,`Products`,`Contract_yr`,`Contract_duration`,`Email`,`Phone`,`Address`)        
	VALUES(10001,"Sailom","KAB100","FOOD","2015-09-09",5,"sailom@gmail.com",070139699,"P.O.BOX 10243774, Migori") , 
		  (10002,"Pwani","ACE1002","ELECTRONICS","2012-07-05",5,"pwani@gmail.com",07783565,"P.O.BOX 6534536, Mombassa"), 
		  (10003,"Motors","ERWT199","UTENSILS","2014-07-09",5,"motors@gmail.com",07345665,"P.O.BOX 42374, Nairobi"); 
select*from suppliers_record;

    /* addStaff() */
INSERT INTO staff_record (`Staff_id`,`Password`,`First_name`,`Last_name`,`Other_name`,`Gender`,`ID`,`DOB`,`Phone`,`Email`,`Address`,`Title`,`Salary`,`Perks`,`Employed_date`)
	VALUES("1001","1234","Juma","Omosh","King","male",75564756,"1992-12-31",0725172591,"omosh@gmail.com","P.O BOX 3460 Nairobi","MANAGER",50000,18000,date(current_date())),
		  ("1002","9876","Mary","Njoki","Wango","female",56725445,"1990-03-21",0715352899,"njoki@yahoo,com","P.O BOX 300 Kisumu","JUNIOR",20000,7000,date(current_date())),
		  ("1003","5432","Musau","Kamau","Amani","male",56726485,"1991-04-20",0710392899,"kamau@yahoo,com","P.O BOX 133 Machakos","SENIOR",30000,10000,date(current_date()));   
select * from staff_record; 

   /* addStock */
INSERT INTO restocking_record(`Item_barcode`,`Contract_id`,`Buy_price`,`Sell_price`,`Batch_no`,`Buy_quantity`,`Expiry`,`Tax`,`Timestamp` )
	VALUES("10000",10001,"500","600","c001","10","2022-11-01","50",now()),
		  ("10001",10002,"100","170","b001","2","2023-11-01","10",now()),
		  ("10002",10003,"50","60","b002","5","2022-10-01","2",now());
select * from restocking_record;  
                
   /*addSales*/
INSERT INTO sales_record(`Item_barcode`,`Sell_quantity`,`Staff_id`,`Timestamp`) 
	VALUES("10000",10,"1001",now()),
		  ("10001",3,"1002",now()),
          ("10002",1,"1003",now());
select * from sales_record; 
                   
/* terminateStaff() */
UPDATE staff_record SET `Status` = "Terminated", `Terminated_date` = current_date() WHERE `ID` = ? ;
select * from staff_record;

/* updateQtyAfterBuyQty */
UPDATE item_record SET `Item_quantity`= `Item_quantity` + ? WHERE `Item_barcode`= ? ;
select *from item_record;

/*updateQtyAfterSellQty */
UPDATE item_record SET `Item_quantity`= `Item_quantity` - ? WHERE `Item_barcode`= ? ;
select *from item_record;

/*update supplier contract*/
UPDATE suppliers_record SET `Contract_yr` = ? , `Contract_duration` = ? WHERE `Contract_id` = ? ;
select * from suppliers_record;

/*update staff title */
UPDATE staff_record SET `Title` = ? WHERE `ID` = ? ;
select *from staff_record;

/*update staff salary*/
UPDATE staff_record SET `Salary` = ? WHERE `ID` = ? ;
select *from staff_record;
  
/*update staff perks*/
UPDATE staff_record SET `Perks` = ? WHERE `ID` = ? ;
select *from staff_record;
    
/*update staff  email*/
UPDATE staff_record SET `Email` = ? WHERE `ID` = ?;
select *from staff_record; 
  
/*update staff phone*/
UPDATE staff_record SET `Phone` = ? WHERE `ID` = ? ;
select *from staff_record; 
  
/*update staff address*/
UPDATE staff_record SET `Address` = ? WHERE `ID` = ?;
select *from staff_record; 
      
/*updates staff password*/   
UPDATE staff_record SET `Password` = ? WHERE `ID` = ? ;
select *from staff_record; 
    
/*update username*/
UPDATE staff_record SET `Staff_id` = ? WHERE `ID` = ? ;
select *from staff_record; 
    
/*Update company_record*/
/* updates company name*/
UPDATE company_record SET `Company_name` = ? ;
      
/*update company motto*/
UPDATE company_record SET `Motto` = ? ;
       
/*updates company email*/
UPDATE company_record SET `Email` = ? ;
       
/*update company phone*/
UPDATE company_record SET `Phone` = ? ;
       
/*update company address*/
UPDATE company_record SET `Address` = ? ;
       
/*update company website*/
UPDATE company_record SET `Website` = ? ;
select * from company_record;
       
/*update financial_record*/
/*update statutoryfees*/
UPDATE financial_record SET `Statutory_fees` = ? ;
          
/*update electricity_bill*/  
UPDATE financial_record SET `Electricity_bill` = ? ;
           
/*updates water_bill */ 
UPDATE financial_record SET `Water_bill` = ? ;
            
/*updates rent_bill*/
UPDATE financial_record SET `Rent_bill` = ? ;
               
/*updates petty_expenses*/
UPDATE financial_record SET `Petty_expenses` = ? ;
               
/*update mobile_payment*/
UPDATE financial_record SET `Mobile_payment` = ? ;
    
/*update card_payment*/
UPDATE financial_record SET `Card_payment` = ? ;

/*update  bank_payment*/
UPDATE financial_record SET `Bank_payment` = ? ;
select * from financial_record;
   
/* fetchSales() */ 
select item_record.`Item_barcode`,`Brand_name`,`Measurement`,`Sell_quantity`,`Sell_price`,`Staff_id`,sales_record.`Timestamp`
from item_record, sales_record, restocking_record
where sales_record.`Item_barcode` = item_record.`Item_barcode` and sales_record.`Item_barcode` = restocking_record.`Item_barcode` order by `Timestamp`;

create view fetch_sales as 
select item_record.`Item_barcode`,`Brand_name`,`Measurement`,`Sell_quantity`,`Sell_price`,`Staff_id`,sales_record.`Timestamp`
from item_record, sales_record, restocking_record
where sales_record.`Item_barcode` = item_record.`Item_barcode` and sales_record.`Item_barcode` = restocking_record.`Item_barcode` order by `Timestamp`;
select * from fetch_sales;

SELECT * FROM fetch_sales WHERE date(`Timestamp`) = date(current_date()); /* fetches today */
SELECT * FROM fetch_sales WHERE week(`Timestamp`) = week(current_date()); /* fetches week */
SELECT * FROM fetch_sales WHERE month(`Timestamp`) = month(current_date()); /* fetches month */
SELECT * FROM fetch_sales WHERE year(`Timestamp`) = year(current_date()); /* fetches year */
SELECT * FROM fetch_sales WHERE date(`Timestamp`) = ? ; /* fetches date */

/* fetch itemDetails from restocking */
select  item_record.`Item_barcode`,`Brand_name`,`Measurement`,`Batch_no`,`Item_quantity`,`Buy_price`,`Sell_price`,restocking_record.`Contract_id`
	 from item_record, restocking_record, suppliers_record
     where item_record.`Item_barcode`= restocking_record.`Item_barcode` and restocking_record.`Contract_id`= suppliers_record.`Contract_id`;

create view fetch_item_details as
     select  item_record.`Item_barcode`,`Brand_name`,`Measurement`,`Batch_no`,`Item_quantity`,`Buy_price`,`Sell_price`,restocking_record.`Contract_id`
	 from item_record, restocking_record, suppliers_record
     where item_record.`Item_barcode`= restocking_record.`Item_barcode` and restocking_record.`Contract_id`= suppliers_record.`Contract_id`;
SELECT * FROM fetch_item_details WHERE `Brand_name` = ? AND `Measurement` = ? ;

/* confirmItemCat() */
select distinct `Item_barcode`, `Brand_name`, `Measurement`, `Item_quantity`
	from item_record where `Brand_name` = "bar soap" and `Measurement` = "500g";
    
create view confirm_item_catalogue as 
	select distinct `Item_barcode`, `Brand_name`, `Measurement`, `Item_quantity`
	from item_record;
SELECT * FROM confirm_item_catalogue WHERE `Brand_name` = ? AND `Measurement` = ? ;
      
/*fetch staffDetails()*/
select `Staff_id`,`ID`,`First_name`,`Last_name`,`Other_name`,`Gender`,`DOB`,`Phone`,`Email`,`Address`,`Title`,`Salary`,`Perks`,`Status`,`Employed_date`,`Termination_date`
from staff_record;

create view fetch_staff_details as
	 select `Staff_id`,`ID`,`First_name`,`Last_name`,`Other_name`,`Gender`,`DOB`,`Phone`,`Email`,`Address`,`Title`,`Salary`,`Perks`,`Status`,`Employed_date`,`Termination_date`
	 from staff_record;
SELECT * FROM fetch_staff_details;
SELECT * FROM fetch_staff_details WHERE `ID` = ? ;
        
/*fetch supplierDetails*/
create view fetch_supplier_details as
    select * from suppliers_record;
SELECT * FROM fetch_supplier_details;
SELECT * FROM fetch_supplier_details WHERE `Enterprise_name` = ? AND `Licence_no` = ? ;

select `Staff_id`,`password` from Staff_record where `Password`= ? ;
   
/*fetchUsername*/
create view fetch_username as
	select `Staff_id`, `ID`, `Status` from staff_record where `Status` not in("terminated");
SELECT `Staff_id` FROM fetch_username WHERE `Staff_id` = ? ;
select * from fetch_username where staff_id = 1002;

/* fetchPassword() */ 
create view fetch_password as	
	select `Password` from staff_record;
SELECT * FROM fetch_password WHERE `Password` = ? ;    
select * from fetch_password where `Password`=1234;
 
 /* fetchSignIn() */
select `Staff_id`,`Password`,`Title`,`Status`,`ID` from staff_record where `Staff_id` = 1001;

create view fetch_signIn as 
	select `Staff_id`,`Password`,`Title`,`Status`,`ID` from staff_record where `Status` not in("terminated");
SELECT * FROM fetch_signIn WHERE `Staff_id` = ? AND `Password` = ? ;
   
/*fetchMonetaryDetails*/
SELECT * FROM financial_record;

/*fetchCompanyDetails*/
SELECT * FROM company_record;

   