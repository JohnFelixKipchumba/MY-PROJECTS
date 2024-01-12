
create database my_pharma;
use my_pharma;

/* table stores general drug details without concern to prices BUT overall quantities */
create table drug_catalogue (
	Drug_id int unsigned not null,
    Drug_name varchar(30) unique not null,
    Drug_type enum("BRANDED","GENERIC") not null,
    Drug_quantity int unsigned not null check(Drug_quantity >= 0),
    Drug_prescription varchar(5) not null,
    Drug_description tinytext not null,
    Timestamp datetime not null,
    primary key(Drug_id) );

/* table stores general staff details */
create table staff_details (
	Staff_id varchar(30) not null,
    Password varchar(16) unique not null,
    ID int unsigned not null,
    First_name varchar(30) not null,
    Last_name varchar(30) not null,
    Other_name varchar(30) null,
    Gender enum("MALE","FEMALE") not null,
    DOB date not null,
    Phone int unsigned not null,
    Email varchar(60) not null,
    Title enum("STAFF","MANAGER") not null,
    Salary float(2) not null check(Salary >= 10000.00),
    Perks float(2) null check(Perks >= 5000.00),
    Status enum("ACTIVE","TERMINATED") not null default "ACTIVE",
    Employed_date date not null,
    Terminated_date date null,
    primary key(Staff_id,ID) );
    
/* table stores drug purchase/price records. It increments drug quantity in table drug_catalogue */
create table drug_purchases (
	Serial_no int unsigned not null auto_increment,
    Drug_id int unsigned not null,
    Batch_no varchar(10) unique not null,
    Drug_expiry date not null,
    Buy_quantity int unsigned not null check(Buy_quantity > 0),
    Buy_price float(2) not null check(Buy_price > 0.00),
    Sell_price float(2) not null check(Sell_price > 0.00),
    Timestamp datetime not null,
    primary key(Serial_no),
    foreign key(Drug_id) references drug_catalogue(Drug_id) );
   
/* table stores drug sales to customers. It decrements drug quantity in table drug_catalogue */
create table drug_sales (
	Serial_no int unsigned not null auto_increment,
    Customer_name varchar(30) not null,
    Drug_id int unsigned not null,
    Sale_quantity int unsigned not null check(Sale_quantity > 0),
    Staff_id varchar(30) not null,
    Timestamp datetime not null,
    primary key(Serial_no),
    foreign key(Drug_id) references drug_catalogue(Drug_id), 
    foreign key(Staff_id) references staff_details(Staff_id) );
   
/* table stores the company details */
create table company_details (
	SN int unsigned not null primary key,
	Company_title varchar(30) null,
    Mission_title varchar(50) null,
    Phone int unsigned null,
    Email varchar(30) null,
    Address varchar(30) null,
    Location varchar(50) null,
    Website varchar(100) null );
insert into company_details (sn) values (1); 
select * from company_details;

/* table stores the company financial details */ 
create table financial_details (
	SN int unsigned not null primary key, 
	Statutory_fees float(2) null check(Statutory_fees > 0.00),
    Rent_bill float(2) null check(Rent_bill > 0.00),
    Electricity_bill float(2) null check(Electricity_bill > 0.00),
    Water_bill float(2) null check(Water_bill > 0.00),
    Petty_expenses float(2) null check(Petty_expenses > 0.00),
    Mobile_payment varchar(50) null,
    Card_payment set("VISA","MASTERCARD","AMERICAN EXPRESS") null,
    Bank_payment varchar(50) null );
insert into financial_details (sn) values(1);  
select * from financial_details;
    
/* addDrug() */
INSERT INTO drug_catalogue VALUES(10001,"Panadol","branded",20,"1X2","Treat headaches",now()),
								 (10002,"Rob","generic",20,"1X3","Relieves pain",now()),
                                 (10003,"Sonadol","generic",20,"1X2","Treat headaches",now());
select * from drug_catalogue;

/* addStock() */
INSERT INTO drug_purchases (`Drug_id`,`Batch_no`,`Drug_expiry`,`Buy_quantity`,`Buy_price`,`Sell_price`,`Timestamp`)
	VALUES(10001,"123456","2022-11-01",30,5.00,10.00,now()),
		  (10002,"r32t90","2022-08-01",30,10.00,15.00,now()),
		  (10003,"973","2022-10-04",30,7.00,10.00,now());
select * from drug_purchases;
                                 
/* addStaff() */
INSERT INTO staff_details (`Staff_id`,`Password`,`ID`,`First_name`,`Last_name`,`Other_name`,`Gender`,`DOB`,`Phone`,`Email`,`Title`,`Salary`,`Perks`,`Employed_date`)
	VALUES("1001","1234",3454545,"Juma","Omosh","King","male","1992-12-31",0725172591,"omosh@gmail.com","staff",50000,5000,"2002-02-19"),
		  ("1002","9876",56725445,"Mary","Njoki","Wango","female","1990-03-21",0715352899,"njoki@yahoo,com","manager",100000,20000,"2000-03-18");
insert into staff_details (`Staff_id`,`Password`,`ID`,`First_name`,`Last_name`,`Other_name`,`Gender`,`DOB`,`Phone`,`Email`,`Title`,`Salary`,`Perks`,`Employed_date`) 
	values ("1003","5432",56725445,"Musau","Njoki","Wango","female","1990-03-21",0715352899,"njoki@yahoo,com","staff",100000,20000,date(current_date()));          
select * from staff_details;
                
/* addStaffJob() /
update staff_details set `Title`= ,`Salary`= ,`Perks`= ,`Status`= ,`Employed_date`= where `Staff_id`= ;
*/

/* addSale() */
INSERT INTO drug_sales (`Customer_name`,`Drug_id`,`Sale_quantity`,`Staff_id`,`Timestamp`) 
	VALUES("Chomba",10001,5,1001,now()),
		  ("Justus",10002,3,1002,now()),
          ("Beth",10001,10,1001,now()),
          ("Auma",10002,4,1002,now());
select * from drug_sales;

/* confirmDrug() */
select distinct `Drug_name`,`Drug_quantity`,`Sell_price`,`Drug_prescription`,`Drug_description` 
	from drug_catalogue, drug_purchases 	
    where drug_catalogue.`Drug_id` = drug_purchases.`Drug_id` and `Drug_name` = "panadol"; 

create view confirm_drug as 
	select distinct `Drug_name`,`Drug_quantity`,`Sell_price`,`Drug_prescription`,`Drug_description` 
	from drug_catalogue, drug_purchases 	
    where drug_catalogue.`Drug_id` = drug_purchases.`Drug_id`;
SELECT * FROM confirm_drug WHERE `Drug_name` = ? ;

/* monetaryDetails() */
/* companyDetails() */ 

/* terminateStaff() */
UPDATE staff_details SET `Status` = "Terminated", `Terminated_date` = current_date() WHERE `Staff_id` = ? ;
select * from staff_details;

/* updateQuantity() */ 
UPDATE drug_catalogue SET `Drug_quantity` = ? WHERE `Drug_name` = ? ;
select * from drug_catalogue;

/* updateSalary() */ 
UPDATE staff_details SET `Salary` = ? WHERE `Staff_id` = ? ;
select * from staff_details;

/* updatePerks() */
UPDATE staff_details SET `Perks` = ? WHERE `Staff_id` = ? ;

/* updateEmail() */
UPDATE staff_details SET `Email` = ? WHERE `Staff_id` = ? ;

/* updatePhone() */
UPDATE staff_details SET `Phone` = ? WHERE `Staff_id` = ? ;

/* updatePassword() */
UPDATE staff_details SET `Password` = ? WHERE `Staff_id` = ? ;

/* updateUsername() */
UPDATE staff_details SET `Staff_id` = ? WHERE `Staff_id` = ? ;

/* fetchSales() */ 
select drug_sales.`Drug_id`,`Drug_name`,`Sale_quantity`,`Sell_price`,`Staff_id`, drug_sales.`Timestamp` 
	from drug_catalogue, drug_sales, drug_purchases
    where drug_sales.`Drug_id` = drug_catalogue.`Drug_id` and drug_sales.`Drug_id` = drug_purchases.`Drug_id` and year(current_date());

SELECT sum(`Sale_quantity`) as `Total_Drugs` FROM drug_sales WHERE year(current_date());  /* counts the total number of drugs sold for a given time period */
SELECT sum(`Sale_quantity` * `Sell_price`) as `Total_Profit` FROM drug_sales, drug_purchases WHERE drug_sales.`Drug_id` = drug_purchases.`Drug_id` and year(current_date());

create view fetch_sales as 
	select drug_sales.`Drug_id`,`Drug_name`,  `Sale_quantity`, `Sell_price`, `Staff_id`, drug_sales.`Timestamp` 
	from drug_catalogue, drug_sales, drug_purchases
    where drug_sales.`Drug_id` = drug_catalogue.`Drug_id` and drug_sales.`Drug_id` = drug_purchases.`Drug_id` order by `Timestamp`;
SELECT * FROM fetch_sales WHERE day(`Timestamp`) = day(current_date()); /* fetches today */
SELECT * FROM fetch_sales WHERE week(`Timestamp`) = week(current_date()); /* fetches week */
SELECT * FROM fetch_sales WHERE month(`Timestamp`) = month(current_date()); /* fetches month */
SELECT * FROM fetch_sales WHERE year(`Timestamp`) = year(current_date()); /* fetches year */
SELECT * FROM fetch_sales WHERE date(`Timestamp`) = ? ; /* fetches date */

/* fetchAvailableDrugs() */
SELECT `Drug_id`,`Drug_name`,`Drug_type`,`Drug_quantity` FROM drug_catalogue;
 
/* fetchExpiring() */ 
select drug_catalogue.`Drug_id`,`Drug_name`,`Drug_type`,`Drug_quantity`,`Buy_quantity`,`Drug_expiry` 
	from drug_catalogue, drug_purchases
    where drug_catalogue.`Drug_id` = drug_purchases.`Drug_id`;
    
create view fetch_expiring as
	select drug_catalogue.`Drug_id`,`Drug_name`,`Drug_type`,`Drug_quantity`,`Buy_quantity`,`Drug_expiry` 
	from drug_catalogue, drug_purchases
    where drug_catalogue.`Drug_id` = drug_purchases.`Drug_id` order by `Drug_expiry`;
SELECT * FROM fetch_expiring WHERE day(`Drug_expiry`) = day(current_date()); /* fetches today */ 
SELECT * FROM fetch_expiring WHERE week(`Drug_expiry`) = week(current_date()); /* fetches week */
SELECT * FROM fetch_expiring WHERE month(`Drug_expiry`) = month(current_date()); /* fetches month */
SELECT * FROM fetch_expiring WHERE year(`Drug_expiry`) = year(current_date()); /* fetches year */
SELECT * FROM fetch_expiring WHERE date(`Drug_expiry`) = ? ; /* fetches date */

/* fetchCustomers() */ 
create view fetch_custToday as 
	select distinct count(`Customer_name`) as `Customer_No`,date(`Timestamp`) as `Date` from drug_sales where day(`Timestamp`) = day(current_date()); /* fetches day */
SELECT * FROM fetch_custToday;

create view fetch_custWeek as
	select distinct count(`Customer_name`) as `Customer_No`,date(`Timestamp`) as `Date` from drug_sales where week(`Timestamp`) = week(current_date()); /* fetches week */
SELECT * FROM fetch_custWeek;

create view fetch_custMonth as
	select distinct count(`Customer_name`) as `Customer_No`,date(`Timestamp`) as `Date` from drug_sales where month(`Timestamp`) = month(current_date()); /* fetches month */
SELECT * FROM fetch_custMonth;

create view fetch_custYear as
	select distinct count(`Customer_name`) as `Customer_No`,date(`Timestamp`) as `Date` from drug_sales where year(`Timestamp`) = year(current_date()); /* fetches year */
SELECT * FROM fetch_custYear;

SELECT DISTINCT count(`Customer_name`) AS `Customer_No`,date(`Timestamp`) AS `Date` FROM drug_sales WHERE date(`Timestamp`) = ? ; /* fetches date */

/* fetchStaff() */
select `Staff_id`,`ID`,`First_name`,`Last_name`,`Other_name`,`Gender`,`DOB`,`Phone`,`Email`,`Title`,`Salary`,`Perks`,`Status`,`Employed_date`,`Terminated_date`
	from staff_details where `Staff_id` = 1001;

create view fetch_staff as 
	select `Staff_id`,`ID`,`First_name`,`Last_name`,`Other_name`,`Gender`,`DOB`,`Phone`,`Email`,`Title`,`Salary`,`Perks`,`Status`,`Employed_date`,`Terminated_date`
	from staff_details;
SELECT * FROM fetch_staff WHERE `Staff_id` = ? ;

/* fetchPopulation() */
select count(`Staff_id`) as `Total_staff`, `Status` from staff_details where `Status` = "ACTIVE";

create view fetch_population as
	select count(`Staff_id`) as `Total_staff`, `Status` from staff_details group by `Status`;
SELECT * FROM fetch_population;

/* fetchSignIn() */
select `Staff_id`,`Password`,`Title`,`Status` from staff_details where `Staff_id` = 1001;

create view fetch_signIn as 
	select `Staff_id`,`Password`,`Title`,`Status` from staff_details where `Status` not in("terminated");
SELECT * FROM fetch_signIn WHERE `Staff_id` = ? AND `Password` = ? ;

/* fetchUsername() */
create view fetch_username as
	select `Staff_id`, `Status` from staff_details where `Status` not in("terminated");
SELECT `Staff_id` FROM fetch_username WHERE `Staff_id` = ? ;

/* fetchPassword() */ 
create view fetch_password as	
	select `Password` from staff_details;
SELECT * FROM fetch_password WHERE `Password` = ? ;                               
    
   
    
    
    
    
    
    
    
    
    

    
