/*
 *  Run all the following statements;
 *  CREATE, USE, and INSERT. 
 *  This initializes the Database, Tables, Views, Functions and a few data points.
 *
 */
 
 CREATE DATABASE street_cleaning_db;
 USE street_cleaning_db;
/*drop tables staff_record, casuals_record, attendance_record, project_record, project_staffing_record, tools_record, tool_borrow_record, tool_return_record, notifications_record; */
 /* table stores organization staff details */ 
 create table staff_record (
	staffId varchar(20) not null,
    username varchar(20) not null,
    password varchar(20) not null,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    otherName varchar(20) not null,
    gender enum("MALE","FEMALE") not null,
    ID int unsigned not null,
    DOB date not null,
    phone int unsigned not null,
    email varchar(30) not null,
    address varchar(30) null,
    county varchar(20) not null,
    title varchar(30) not null,
    salary float(2) not null check(salary > 0.00),
    perks float(2) null check(perks > 0.00),
    employedDate date not null,
    status enum("ACTIVE","TERMINATED") not null default "ACTIVE",
    terminationDate date null,
    profilePic mediumblob null,
    primary key(staffId, ID));
    
/* table stores casual workers details */
create table casuals_record (
	casualId varchar(20) not null,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    otherName varchar(20) not null,
    gender enum("MALE","FEMALE") not null,
    ID int unsigned not null,
    DOB date not null,
    phone int unsigned not null,
    email varchar(30) not null,
    address varchar(30) null,
    county varchar(20) not null,
    staffId varchar(20) not null,
    wage float(2) not null check(wage > 0.00),
    workDays int unsigned not null default 0,
    money enum("PAID","UNPAID") not null default "UNPAID",
    hireDate date not null,
    status enum("ACTIVE","WAITING","TERMINATED") not null default "WAITING",
	terminationDate date null,
    primary key(casualId, ID),
    foreign key(staffId) references staff_record(staffId) 
    on update cascade
    on delete cascade );

/* table stores project details */
create table project_record (
	projectId int unsigned not null auto_increment,
    title tinytext not null,
    staffId varchar(20) not null,
    location tinytext not null,
    county varchar(20) not null,
    staffNo int unsigned not null,
    start date not null,
    end date not null,
    extend int unsigned null,
    beforePic mediumblob null,
    afterPic mediumblob null,
    status enum("PENDING","ONGOING","COMPLETE") not null default "PENDING",
    timestamp datetime not null,
    vehicle varchar(10) null,
    primary key(projectId),
    foreign key(staffId) references staff_record(staffId) 
    on update cascade
    on delete cascade );
 
 /* table strores staffing details for each project */
 create table project_staffing_record(
	serial_no int unsigned not null auto_increment,
    projectId int unsigned not null,
    staffId varchar(20) not null,
    casualId varchar(20) not null,
    timestamp datetime not null,
    primary key(serial_no),
    foreign key(projectId) references project_record(projectId),
    foreign key(staffId) references staff_record(staffId),
    foreign key(casualId) references casuals_record(casualId) );
 
/* table stores attendance details of casual workers in projects */
create table attendance_record (
	serial_no int unsigned not null auto_increment,
	projectId int unsigned not null,
	staffId varchar(20) not null,
	casualId varchar(20) not null,
	timestamp datetime not null,
	primary key(serial_no),
	foreign key(staffId) references staff_record(staffId),
	foreign key(projectId) references project_record(projectId),
    foreign key(casualId) references casuals_record(casualId)
    on update cascade
    on delete cascade );
 drop tables tools_record, tool_return_record, tool_borrow_record; 
/* table stores of available tools and their quantities */
create table tools_record (
	toolId int unsigned not null auto_increment,
    name varchar(30) not null,
    size enum("SMALL","MEDIUM","LARGE") null,
    price float(2) not null check(price > 0.00),
    buyDate date not null,
    quantity int unsigned not null check(quantity >= 0),
    description text null,
    timestamp datetime not null,
    primary key(toolId) );
    
/* table stores details of borrowed tools */
create table tool_borrow_record (
	serial_no int unsigned not null auto_increment,
    staffId varchar(20) not null,
    toolId int unsigned not null,
    goodQty int unsigned null,
    damagedQty int unsigned null,
    duration int unsigned not null,
    timestamp datetime not null,
    primary key(serial_no),
    foreign key(staffId) references staff_record(staffId),
    foreign key(toolId) references tools_record(toolId) 
    on update cascade
    on delete cascade );

/* table stores details of returned tools */
create table tool_return_record (
	serial_no int unsigned not null auto_increment,
    staffId varchar(20) not null,
    toolId int unsigned not null,
    goodQty int unsigned null,
    damagedQty int unsigned null,
    lostQty int unsigned null,
    damageUnder tinytext null,
    lostUnder tinytext null,
    timestamp datetime not null,
    primary key(serial_no),
    foreign key(staffId) references staff_record(staffId),
    foreign key(toolId) references tools_record(toolId) 
    on update cascade
    on delete cascade );
    
 /* table stores details on clients */
 create table clients_record (
	clientId int unsigned not null auto_increment,
    firstName varchar(20) null,
    lastName varchar(20) null, 
    otherName varchar(20) null,
    phone int unsigned not null,
    email varchar(30)  not null,
    address varchar(30) null,
    county varchar(20) not null, 
    companyTitle tinytext null,
    timestamp datetime not null,
	primary key(clientId) );
    
/* table stores details on suppliers */
create table suppliers_record (
	supplierId int unsigned not null auto_increment,
    companyTitle tinytext not null,
    phone int unsigned not null,
    email varchar(30) not null,
    address varchar(30) not null,
    county varchar(20) not null,
    registerDate date not null,
    terminateDate date null,
    products text not null,
    primary key(supplierId) );
    
/* table stores notifications details */
create table notifications_record (
	notificationId int unsigned not null auto_increment,
    staffId varchar(20) null,
    clientId int unsigned null,
    sendTo tinytext not null,
    description text null,
    timestamp datetime not null,
    primary key(notificationId),
    foreign key(staffId) references staff_record(staffId),
    foreign key(clientId) references clients_record(clientId) 
    on update cascade
    on delete cascade );
    
/* table stores log details */
create table logs_record (
	timestamp timestamp not null,
    username varchar(20) not null,
    password varchar(20) not null,
    primary key(timestamp) );
    

/* addStaff() */
INSERT INTO staff_record (`staffId`,`username`,`password`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`DOB`,`phone`,`email`,
	`address`,`county`,`title`,`salary`,`perks`,`employedDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,date(current_date()));
insert into staff_record (`staffId`,`username`,`password`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`DOB`,`phone`,`email`,`address`,`county`,`title`,`salary`,`perks`,`employedDate`)
    values  ("1001","1001","123456","Beryl","Mary","Njoki","FEMALE",457847843,"1949-02-21",0704343235,"beryl@gmail.com","23432-004 NAIROBI","NAIROBI","HUMAN RESOURCE",80000.00,5000.00,date(current_date())),
			("1002","1002","678901","Simon","Odhiambo","Pius","MALE",298534990,"1982-04-02",0798252896,"pius@yahoo.com","978-002 NAIROBI","NAIROBI","SUPERVISOR",50000.00,2000.00,date(current_date())),
            ("1003","1003","234567","Wycliff","Nduu","Oparanya","MALE",454633415,"1988-11-19",0722830032,"nduu23@gmail.com","456-400 THIKA","NAIROBI","TOOLS MANAGER",60000.00,2000.00,date(current_date())),
            ("1004","1004","345888","Moses","Tyson","Tula","MALE",808654604,"1993-04-30",0721831111,"moses10@gmail.com","208-005 NAIROBI","NAIROBI","DRIVER",24000.00,2000.00,date(current_date())),
            ("1005","1005","300678","Peter","Nzioka","Njuge","MALE",764015464,"1995-07-15",0705333111,"njuge@gmail.com","232S-007 KIAMBU","KIAMBU","SUPERVISOR",50000.00,2000.00,date(current_date())),
            ("1006","1006","890123","Olivia","Silantoi","Basigwa","FEMALE",123455644,"1990-09-23",0707342895,"olivia@gmail.com","8372-323 MACHAKOS","NAIROBI","SALES & FINANCE",70000.00,3000.00,date(current_date())),
            ("1007","1007","345678","Musa","Oluor","Oliver","MALE",789654604,"1984-07-10",0707831111,"musa10@gmail.com","2320-005 NAIROBI","NAIROBI","ADMIN",120000.00,10000.00,date(current_date()));
 select * from staff_record;   

/* addCasual() */
INSERT INTO casuals_record (`casualId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`DOB`,`phone`,`email`,`address`,`county`,`staffId`,`wage`,`hireDate`)
	VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,date(current_date()));
insert into casuals_record (`casualId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`DOB`,`phone`,`email`,`address`,`county`,`staffId`,`wage`,`hireDate`)
	values  ("10001","Erastus","Maa","Moo","MALE",485630382,"1997-03-30",0700321909,"erastus@gmail.com","3238-0032 NAIROBI","NAIROBI","1002",500.00,date(current_date())),
			("10002","Jane","Chebet","Cherono","FEMALE",783739322,"1999-07-21",0723989209,"chebet@gmail.com","3432-0042 NAIROBI","NAIROBI","1002",500.00,date(current_date())),
            ("10003","Ben","Paul","Maina","MALE",45325454,"2000-04-03",0702355393,"paul29@gmail.com","323-909 KIAMBU","KIAMBU","1005",500.00,date(current_date())),
            ("10004","Milka","Achieng","Awor","FEMALE",235344325,"1998-11-24",0111345983,"milka@gmail.com","34-999 KIAMBU","KIAMBU","1005",500.00,date(current_date()));
select * from casuals_record;

/* addClient() create separate interfaces for individuals/companies */
INSERT INTO clients_record (`firstName`,`lastName`,`otherName`,`phone`,`email`,`address`,`county`,`companyTitle`,`timestamp`) VALUES (?,?,?,?,?,?,?,?,now());
insert into clients_record (`firstName`,`lastName`,`otherName`,`phone`,`email`,`address`,`county`,`companyTitle`,`timestamp`)
	values  ("Joy","Mitch","Munene",0734123532,"munene@gmail.com","3442-0030 NAIROBI","NAIROBI","",now()),
			("","","",0723799325,"venturebins@vbs.com","2344-99 NAIROBI","NAIROBI","VENTURE BINS LTD",now());
select * from clients_record;

/* addSupplier() */
INSERT INTO suppliers_record (`companyTitle`,`phone`,`email`,`address`,`county`,`registerDate`,`products`,`terminateDate`) VALUES (?,?,?,?,?,date(current_date()),?,?);
insert into suppliers_record (`companyTitle`,`phone`,`email`,`address`,`county`,`registerDate`,`terminateDate`,`products`)
	values  ("BOCSH ACCESSORIES",0722454889,"contact@boshaccessories.com","343-00304 NAIROBI","NAIROBI",date(current_date()),"2023-12-30","MACHINERY,TOOLS"),
			("FAYAT TEXTILES",0700343222,"info@fayattextiles.com","5666-0031 NAIROBI","NAIROBI",date(current_date()),"2023-06-30","PPE,APPAREL");
select * from suppliers_record;

/* addProject() */
INSERT INTO project_record (`title`,`staffId`,`location`,`county`,`staffNo`,`start`,`end`,`vehicle`,`timestamp`) VALUES (?,?,?,?,?,?,?,?,now());
insert into project_record (`title`,`staffId`,`location`,`county`,`staffNo`,`start`,`end`,`vehicle`,`timestamp`)
	values  ("Street Cleaning","1002","Kenyatta Avenue","NAIROBI",10,"2023-02-01","2023-02-06",null,now()),
			("Trash Collection","1002","Kenyatta Market","NAIROBI",10,"2023-02-15","2023-02-15",null,now()),
            ("Graffiti Removal","1005","Thika County Hall Building","KIAMBU",5,"2023-02-10","2023-02-12",null,now()),
            ("Sidewalk Maintenance","1005","Limuru Road","KIAMBU",5,"2023-02-01","2023-02-11",null,now());
select * from project_record;

/* addAprojectStaff() */
INSERT INTO project_staffing_record (`projectId`,`staffId`,`casualId`,`timestamp`) VALUES (?,?,?,now());
insert into project_staffing_record (`projectId`,`staffId`,`casualId`,`timestamp`)
	values  (1,"1002","10001",now()),
			(1,"1002","10002",now());
select * from project_staffing_record;

/* addTool() */
INSERT INTO tools_record (`name`,`size`,`price`,`buyDate`,`quantity`,`timestamp`) VALUES (?,?,?,?,0,now());
insert into tools_record (`name`,`price`,`buyDate`,`quantity`,`timestamp`) 
	values  ("HARD BROOM",150.00,"2023-01-01",20,now()),
			("WHEELBARROW",2500.00,"2023-01-01",10,now()),
            ("CART",7000.00,"2023-01-01",5,now()),
            ("WIRE BRUSH",200.00,"2023-01-01",5,now());
 select * from tools_record;        

/* addToolBorrow() */
INSERT INTO tool_borrow_record (`staffId`,`toolId`,`goodQty`,`damagedQty`,`duration`,`timestamp`) VALUES (?,?,?,?,?,now());
insert into tool_borrow_record (`staffId`,`toolId`,`goodQty`,`damagedQty`,`duration`,`timestamp`)
	values  ("1002",1,5,0,5,now()),
			("1002",2,2,0,5,now()),
            ("1005",4,4,0,3,now()),
            ("1005",1,4,0,3,now());
select * from tool_borrow_record;

/* addToolReturn() */
INSERT INTO tool_return_record (`staffId`,`toolId`,`goodQty`,`timestamp`) VALUES (?,?,?,now());
insert into tool_return_record (`staffId`,`toolId`,`goodQty`,`timestamp`) 
	values  ("1002",1,3,now()),
			("1005",4,2,now());
select * from tool_return_record;            

/* addAttendance() */
INSERT INTO attendance_record (`projectId`,`staffId`,`casualId`,`timestamp`) VALUES (?,?,?,now());
insert into attendance_record (`projectId`,`staffId`,`casualId`,`timestamp`)
	values  (1,"1002","10001",now()),
			(3,"1005","10003",now());
 select * from attendance_record;           

/* addNotification() */
INSERT INTO notifications_record (`staffId`,`clientId`,`sendTo`,`description`,`timestamp`) VALUES (?,?,?,?,now());

/* addLog() */
INSERT INTO logs_record (`timestamp`,`username`,`password`) VALUES (now(),?,?);

/* updateStaffUsername() */
UPDATE staff_record SET `username` = ? WHERE `ID` = ? ;

/* updateStaffPassword() */
UPDATE staff_record SET `password` = ? WHERE `ID` = ? ;

/* updateStaffEmail() */
UPDATE staff_record SET `email` = ? WHERE `ID` = ? ;

/* updateStaffPhone() */
UPDATE staff_record SET `phone` = ? WHERE `ID` = ? ;

/* updateStaffAddress() */
UPDATE staff_record SET `address` = ? WHERE `ID` = ? ;

/* updateStaffCounty() */
UPDATE staff_record SET `county` = ? WHERE `ID` = ? ;

/* updateStaffTitle() */
UPDATE staff_record SET `title` = ? WHERE `ID` = ? ;

/* updateStaffSalary() */
UPDATE staff_record SET `salary` = ? WHERE `ID` = ? ;

/* updateStaffPerks() */
UPDATE staff_record SET `perks` = ? WHERE `ID` = ? ;

/* updateStaffStatus() */
UPDATE staff_record SET `status` = ? WHERE `ID` = ? ;

/* updateStaffProfilePic() */
UPDATE staff_record SET `profilePic` = ? WHERE `ID` = ? ;

/* updateStaffTerminationDate() */
UPDATE staff_record SET `terminationDate` = date(current_date()) WHERE `ID` = ? ;

/* updateCasualPhone() */
UPDATE casuals_record SET `phone` = ? WHERE `ID` = ? ;

/* updateCasualAddress() */
UPDATE casuals_record SET `address` = ? WHERE `ID` = ? ;

/* updateCasualCounty() */
UPDATE casuals_record SET `county` = ? WHERE `ID` = ? ;

/* updateCasualWage() */
UPDATE casuals_record SET `wage` = ? WHERE `ID` = ? ;

/* updateCasualWorkDays() called in attendance roll call */
UPDATE casuals_record SET `workDays` = `workDays` + 1 WHERE `ID` = ? ;

/* updateCasualMoney() */
UPDATE casuals_record SET `money` = ? WHERE `ID` = ? ;

/* updateCasualStatus() */
UPDATE casuals_record SET `status` = ? WHERE `ID` = ? ;

/* updateCasualTerminationDate() */
UPDATE casuals_record SET `terminationDate` = date(current_date()) WHERE `ID` = ? ;

/* updateSupplierRegisterDate() /
UPDATE suppliers_record SET `registerDate` = ? WHERE `supplierId` = ;
/* updateSupplierTerminateDate() /
UPDATE SET `` = ? WHERE
/* updateSupplierProducts() /
UPDATE SET `` = ? WHERE
*/

/* updateToolDescription() */
UPDATE tools_record SET `description` = ? WHERE `toolId` = (SELECT `toolId` FROM (SELECT `toolId` FROM tools_record WHERE `name` = ? ) AS Temp_table ); 

/* updateToolBorrowQty() */
UPDATE tools_record SET `quantity` = `quantity` - ? WHERE `toolId` = ? ;

/* updateToolReturnQty() */
UPDATE tools_record SET `quantity` = `quantity` + ? WHERE `toolId` = ? ;

/* updateToolDamagedQty() set damagQty & damagUnder */
UPDATE tool_return_record SET `damagedQty` = ? ,`damageUnder` = ? WHERE `toolId` = ? AND `serial_no` = (select `serial_no` from (select `serial_no` from tool_return_record as T) as P order by `serial_no` desc limit 1);

/* updateToolLostQty() set lostQty & lostUnder */
UPDATE tool_return_record SET `lostQty` = ? ,`lostUnder` = ? WHERE `toolId` = ? AND `serial_no` = (select `serial_no` from (select `serial_no` from tool_return_record as T) as P order by `serial_no` desc limit 1);

/* updateProjectExtend() */
UPDATE project_record SET `extend` = ? WHERE `projectId` = ? ;

/* updateProjectStaffNo() */
UPDATE project_record SET `staffNo` = ? WHERE `projectId` = ? ;

/* updateProjectStatus() */
UPDATE project_record SET `status` = ? WHERE `projectId` = ? ;

/* updateProjectBeforePic() */
UPDATE project_record SET `beforePic` = ? WHERE `title` = ? AND `staffId` = ? AND `location` = ? ;

/* updateProjectAfterPic() */
UPDATE project_record SET `afterPic` = ? WHERE `title` = ? AND `staffId` = ? AND `location` = ? ;

/* fetchStaffLogin() */
SELECT `staffId`,`ID`,`county`,`title`,`status` FROM staff_record WHERE `username` = ? AND `password` = ? ;

/* fetchAStaffDetails() */
create view fetch_a_staff_details as
	select `staffId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`DOB`,`phone`,`email`,`address`,`county`,`title`,`employedDate`,`status`,`terminationDate`,`salary`,`perks`
	from staff_record;
SELECT * FROM fetch_a_staff_details WHERE `ID` = ? ;

/* fetchStaffDetails() */
SELECT `staffId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`phone`,`email`,`county`,`title` FROM staff_record ORDER BY `title`;

/* fetchSupervisorDetails() for projects */
create view fetch_supervisor_details as
	select `staffId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`phone`,`email`,`county`,`title`
    from staff_record
    where `title` = "SUPERVISOR" order by `county`;
SELECT * FROM fetch_supervisor_details;

/* fetchStaffTitleCount() */
SELECT `title`,COUNT(`title`) AS `Number` FROM staff_record GROUP BY `title` ORDER BY `Number`;

/* fetchStaffCount() */
SELECT COUNT(*) AS `Total` FROM staff_record;

/* fetchACasualDetails() */
create view fetch_a_casual_details as
	select `casualId`,casuals_record.`firstName`,casuals_record.`lastName`,casuals_record.`otherName`,casuals_record.`gender`,casuals_record.`ID`,casuals_record.`DOB`,casuals_record.`phone`,
		casuals_record.`email`,casuals_record.`address`,casuals_record.`county`,`wage`,casuals_record.`staffId`,staff_record.`firstName`as `fname`,staff_record.`lastName`as `midname`,staff_record.`otherName`as `surname`,
		`hireDate`,casuals_record.`status`,casuals_record.`terminationDate`
	from casuals_record, staff_record
	where casuals_record.`staffId` = staff_record.`staffId`;
SELECT * FROM fetch_a_casual_details WHERE `ID` = ? ;

/* fetchCasualDetails() */
SELECT `casualId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`phone`,`email`,`county`,`hireDate` FROM casuals_record ORDER BY `county`;

/* fetchCasualCount() */
SELECT COUNT(*) AS `Total` FROM casuals_record;

/* fetchCasualStatus() for staffing proj */
create view fetch_casual_status as
	select `casualId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`phone`,`email`,`county`,`staffId`,`hireDate`,`status`
	from casuals_record 
	where `status` = "WAITING" order by `gender` asc;
SELECT * FROM fetch_casual_status WHERE `county` = ? ;

/* fetchCasualAttendance() */
create view fetch_casual_attendance as
	select project_record.`title`,`location`,project_record.`status`,attendance_record.`staffId`,staff_record.`firstName`as `fname`,staff_record.`lastName`as `lname`,
		attendance_record.`casualId`,casuals_record.`firstName`,casuals_record.`lastName`,casuals_record.`otherName`,`workDays`,attendance_record.`timestamp`
	from project_record, staff_record, attendance_record, casuals_record
    where staff_record.`staffId` = project_record.`staffId` and project_record.`projectId` = attendance_record.`projectId` and  attendance_record.`casualId`= casuals_record.`casualId` order by attendance_record.`timestamp`;
SELECT * FROM fetch_casual_attendance WHERE `staffId`= ? AND `status`= ? ; /* project status mostly is ongoing */

/* fetchCasualPayment() */
create view fetch_casual_payment as
	select `staffId`,`firstName`,`lastName`,`otherName`,`ID`,`county`,`status`,`wage`,`workDays`,calcTotal( `wage`, `workDays`) as total, `money` 
	from casuals_record order by `county`; 
SELECT * FROM fetch_casual_payment WHERE `status` = ? AND `money` = ? AND `staffId` = ? ; /* status values and money values to be chosen from menu */

delimiter $$
create function calcTotal( wage float, workDays int ) /* function calculates total money payable to a casual worker */
	returns float
    deterministic
    begin
		declare total float;
 
        set total = wage * workDays;
        
        return total;
	end
 $$
 delimiter ;

/* fetchCasualToolDamage() */
	/* fetchDamageUnder() */
SELECT `damageUnder` FROM tool_return_record WHERE week(`Timestamp`) = week(current_date()); /* fetches weekly as payment weekly */ /* store these IDs in a list then run next query */

create view fetch_casual_tool_damage as
	select casuals_record.`firstName`,casuals_record.`lastName`,casuals_record.`otherName`,casuals_record.`ID`,casuals_record.`phone`,casuals_record.`county`,
    staff_record.`firstName`as staff_firstName,staff_record.`lastName`as staff_lastName,staff_record.`ID`as staff_ID,tools_record.`name`,tool_return_record.`timestamp` 
	from casuals_record, staff_record, tool_return_record, tools_record
	where tool_return_record.`toolId`=tools_record.`toolId` and tool_return_record.`staffId`=casuals_record.`staffId` and casuals_record.`staffId`=staff_record.`staffId`;
 SELECT * FROM fetch_casual_tool_damage WHERE `ID`= ? AND week(`Timestamp`) = week(current_date()); /* fetches weekly as payment weekly */

/* fetchCasualToolLost() */
	/* fetchLostUnder() */
SELECT `lostUnder` FROM tool_return_record WHERE week(`Timestamp`) = week(current_date()); /* store these IDs in a list then run next query */
SELECT * FROM fetch_casual_tool_damage WHERE `ID`= ? AND week(`Timestamp`) = week(current_date()); /* fetches weekly as payment weekly */

/* fetchToolDetails() */
SELECT * FROM tools_record;

/* fetchAToolDetails() to display toolPic */
SELECT * FROM tools_record WHERE `toolId` = ? ;

/* fetchToolsBorrowed() */
create view fetch_tools_borrowed as
	select tool_borrow_record.`toolId`,`name`,`goodQty`,`damagedQty`,`duration`,tool_borrow_record.`staffId`,`firstName`,`lastName`,tool_borrow_record.`timestamp`
	from tools_record, staff_record, tool_borrow_record
    where tools_record.`toolId` = tool_borrow_record.`toolId` and staff_record.`staffId` = tool_borrow_record.`staffId` order by tool_borrow_record.`timestamp`;
SELECT * FROM fetch_tools_borrowed WHERE day(`timestamp`) = day(current_date()); /* fetches today */
SELECT * FROM fetch_tools_borrowed WHERE week(`timestamp`) = week(current_date()); /* fetches week */
SELECT * FROM fetch_tools_borrowed WHERE month(`timestamp`) = month(current_date()); /* fetches month */
SELECT * FROM fetch_tools_borrowed WHERE year(`timestamp`) = year(current_date()); /* fetches year */
SELECT * FROM fetch_tools_borrowed WHERE date(`timestamp`) = ? ; /* fetches date */
SELECT * FROM fetch_tools_borrowed WHERE `staffId` = ? ; /* fetches for a staff */

/* fetchToolsStatus() from returned group by good,damag,lost and count plus sum of counts */


/* fetchProjects() */
SELECT * FROM project_record ORDER BY `status`;

/* fetchAprojectStaff() proj not complete, casual in waiting list */
create view fetch_a_project_staff as
	select project_staffing_record.`projectId`,project_staffing_record.`staffId`,project_staffing_record.`casualId`,casuals_record.`firstName`,casuals_record.`lastName`,casuals_record.`otherName`,
	casuals_record.`gender`,casuals_record.`ID`,casuals_record.`phone`,casuals_record.`county`,`workDays`,project_record.`status`,casuals_record.`status` as `casual_status`
	from project_staffing_record, project_record, staff_record, casuals_record
	where project_staffing_record.`staffId`=staff_record.`staffId` and project_staffing_record.`projectId`=project_record.`projectId` and project_staffing_record.`casualId`=casuals_record.`casualId` 
		and casuals_record.`staffId`=staff_record.`staffId` and project_record.`status`!="COMPLETE" and casuals_record.`status`="WAITING";
SELECT * FROM fetch_a_project_staff WHERE `projectId` = ? ORDER BY `casualId` ;

/* fetchProjectsPending() */
create view fetch_projects_pending as
	select `projectId`,`title`,`location`,`county`,`start`,`end`,`staffId`,`status`,`timestamp`
	from project_record
    where `status` = "PENDING" order by `county`;  
SELECT * FROM fetch_projects_pending WHERE `staffId` = ? ;

/* fetchProjectsOngoing() */
create view fetch_projects_ongoing as
	select `projectId`,`title`,`location`,`county`,`start`,`end`,`staffId`,`staffNo`,`vehicle`,`status`,`timestamp`
	from project_record
    where `status` = "ONGOING" order by `county`; 
SELECT * FROM fetch_projects_ongoing WHERE `staffId` = ? ;

/* fetchProjectsComplete() */
create view fetch_projects_complete as
	select `projectId`,`title`,`location`,`county`,`start`,`end`,`extend`,`staffId`,`status`,`timestamp`,`beforePic`,`afterPic`
	from project_record
    where `status` = "COMPLETE" order by `county`; 
SELECT * FROM fetch_projects_complete  WHERE `staffId` = ? ;

/* fetchAProject() where staffid and status not complete */
create view fetch_a_project as
	select `projectId`,`title`,`location`,`county`,`start`,`end`,`extend`,`staffId`,`staffNo`,`vehicle`,`status`,`timestamp`
	from project_record
    where `status` not in ("COMPLETE"); 
SELECT * FROM fetch_a_project WHERE `staffId` = ? ORDER BY `status`;

/* fetchProjectPic() */
create view fetch_project_pic as
	select `projectId`,`staffId`,`title`,`location`,`county`,`start`,`end`,left(`beforePic`,20) AS `beforePic`,left(`afterPic`,20) AS `afterPic`,`status`,`timestamp`
	from project_record; 
SELECT * FROM fetch_project_pic WHERE `staffId` = ? ;

/* fetchProjectBeforePic() */
SELECT `beforePic` FROM project_record WHERE `projectId` = ? ;

/* fetchProjectAfterPic() */
SELECT `afterPic` FROM project_record WHERE `projectId` = ? ;

/* fetchClientDetails() */
SELECT * FROM clients_record ORDER BY `county`;

/* fetchAClientDetails() for notific to be chosen from table */
create view fetch_a_client_details as
	select `clientId`,`firstName`,`lastName`,`otherName`,`phone`,`email`
	from clients_record;
SELECT * FROM fetch_a_client_details WHERE `clientId`= ? ;

/* fetchSupplierDetails() */
SELECT * FROM suppliers_record ORDER BY `county`;

/* fetchASupplierDetails() */
SELECT * FROM suppliers_record WHERE `companyTitle` = ? ;

/* fetchNotifications() plus counts */
SELECT * FROM notifications_record WHERE day(`timestamp`) = day(current_date()); /* fetches today */
SELECT * FROM notifications_record WHERE week(`timestamp`) = week(current_date()); /* fetches week */
SELECT * FROM notifications_record WHERE month(`timestamp`) = month(current_date()); /* fetches month */
SELECT * FROM notifications_record WHERE year(`timestamp`) = year(current_date()); /* fetches year */
SELECT * FROM notifications_record WHERE date(`timestamp`) = ? ; /* fetches date */
SELECT count(*) FROM notifications_record WHERE day(`timestamp`) = day(current_date()); /* fetches today */;
SELECT count(*) FROM notifications_record WHERE week(`timestamp`) = week(current_date()); /* fetches week */;
SELECT count(*) FROM notifications_record WHERE month(`timestamp`) = month(current_date()); /* fetches month */;
SELECT count(*) FROM notifications_record WHERE year(`timestamp`) = year(current_date()); /* fetches year */;
SELECT count(*) FROM notifications_record WHERE date(`timestamp`) = ? ; /* fetches date */;

/* fetchANotification() where staffid */
SELECT `staffId`,`sendTo`,`description`,`timestamp` FROM notifications_record WHERE `sendTo` = ? AND `staffId` IS NOT NULL AND day(`timestamp`) = day(current_date()); /* fetches today */
 
/* fetchClientNotifications() make default sendTo be Sales&Finance manager for clients in app */
SELECT `clientId`,`description`,`timestamp` FROM notifications_record WHERE `clientId` IS NOT NULL AND day(`timestamp`) = day(current_date()); /* fetches today */

/* fetchLogs() */
SELECT * FROM logs_record;

/* fetchLogins() dubious -from difference of logs & staff tbls joins */
select logs_record.`username`, logs_record.`password`,`timestamp`
from logs_record, staff_record  /* to be tested further!! */
where (logs_record.`username`,logs_record.`password`) != (staff_record.`username`,staff_record.`password`);

/* confirmStaffID() to validate updates */
SELECT `staffId`,`ID` FROM staff_record WHERE `ID` = ? ;

/* confirmCasualID() to validate updates */  
SELECT `ID` FROM casuals_record WHERE `ID` = ? ;  

/* confirmToolSN() to validate updates */  
SELECT `toolId` FROM tools_record WHERE `toolId` = ? ; 

/* confirmStaffUsername() */
SELECT `username` FROM staff_record WHERE `username` = ? ;

/* confirmStaffPassword() */ 
SELECT `password` FROM staff_record WHERE `password` = ? ;
    
