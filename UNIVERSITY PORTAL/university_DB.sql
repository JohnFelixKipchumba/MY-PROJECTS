
create database UNIVERSITY_DB;
use UNIVERSITY_DB;

create table course_info (
	Course_id varchar(5) not null,
    Course_name varchar(100) not null,
    Dept_name varchar(100) not null,
    Faculty_name varchar(100) not null,
    primary key(Course_id) );

create table student_details (
	Adm_no varchar(15) not null,
    ID int unsigned not null,
    Name varchar(100) not null,
    Phone int unsigned not null,
    Email varchar(50) null,
    Nationality varchar(50) not null,
    Course_id varchar(5) not null,
    Cartegory varchar(20) not null,
    Admission_yr int not null,
    primary key(Adm_no, ID),
    foreign key(Course_id) references course_info(Course_id) );
    
create table unit_info (
	Course_id varchar(5) not null,
    Unit_code varchar(8) not null,
    Unit_name varchar(50) not null,
    Yr_study int unsigned not null,
    semester int unsigned not null,
    primary key(Unit_code),
    foreign key(Course_id) references course_info(Course_id) );
    
 create table results_details (
	serial_no varchar(15) not null,
	Exam_card_no varchar(8) not null,
    Adm_no varchar(15) not null,
    Unit_code varchar(8) not null,
    CAT_mark int unsigned null,
    Exam_mark int unsigned null,
    Total int unsigned null,
    Grade char(1) null,
    primary key(serial_no),
    foreign key(Adm_no) references student_details(Adm_no),
    foreign key(Unit_code) references unit_info(Unit_code) );
    
create view stud_details as
	select Adm_no, ID, Fname, Lname, Phone, Email, Nationality, Admission_yr, course_name
    from student_details, course_info
    where student_details.course_id = course_info.course_id ;
    
-- select * from stud_details ;    
    
create view stud_results as
	select student_details.Adm_no, unit_info.unit_code, unit_name, Yr_study, semester, grade
    from student_details, unit_info, results_details
    where student_details.Adm_no = results_details.Adm_no
		and unit_info.unit_code = results_details.unit_code ;
        
-- select * from stud_results;       
        
create view courses_offered as
	select Course_name, Dept_name, Faculty_name 
    from course_info ;

-- select * from courses_offered;    
-- select Course_id, Course_name, Dept_name, Faculty_name from course_info;
    

alter table student_details change  Admission_yr  Admission_yr int unsigned not null;
-- drop table student_details ;   
    
create view EXAM_REG As 
	select Adm_no, student_details.Course_id, unit_code, Yr_study,semester 
	from student_details,unit_info 
    where student_details.Course_id=unit_info.Course_id;
    
start transaction;
SELECT * from EXAM_REG;
savepoint EXAM_REG;
commit;

delimiter $$
create function calcTotal( catmark int, exammark int ) 
	returns int
    deterministic
    begin
		declare total int;
 
        set total = catmark + exammark;
        
        return total;
	end
 $$
 delimiter ;
 

 delimiter $$
 create function calcGrade( catmark int, exammark int  )
	returns char(1)
	deterministic
	begin
		declare grade char(1);
        declare total int;
        
        set total = catmark + exammark;
        
        if (total >= 70) then
			set grade = 'A';
        elseif(total >= 60 and total <70) then
			set grade = 'B';
        elseif(total >= 50 and total < 60) then
			set grade = 'C';
        elseif(total >= 40 and total < 50) then
			set grade = 'D';
        elseif(total < 40) then
			set grade = 'E';
		end if; 
        
      return grade;
  	end
 $$
 delimiter ;

delimiter $$
create function generateEmail( admNo varchar(15) ) 
	returns varchar(50)
    deterministic
    begin
		declare substring varchar(35);
        set substring = "@student.ac.ke";
        
        return CONCAT( admNo, substring );
	end
 $$
 delimiter ;
 
--  select serial_no, Adm_no, Exam_card_no, Unit_code, CAT_mark, Exam_mark, calcTotal(CAT_mark, Exam_mark) as Total, 
-- 	calcGrade(CAT_mark, Exam_mark) as Grade
--  from results_details;
 
--  select Adm_no, Fname, Lname, generateEmail( Adm_no ) as student_email from student_details;
          
-- select * from view_examcard;
        
  create view VIEW_TRANSCRIPT as
	select student_details.Adm_no, Exam_card_no, unit_info.Unit_code, Unit_name, Yr_study, semester, calcTotal(CAT_mark, Exam_mark) as Total, calcGrade(CAT_mark, Exam_mark) as Grade
    from student_details, unit_info, results_details
    where student_details.Adm_no = 'CCS/00001/019'
		and unit_info.unit_code = results_details.unit_code;     
        
 -- select * from  VIEW_TRANSCRIPT; 
    
 -- select * from results_details;
        
 -- select host,user from mysql.user;
  
--   create role student;
--   
--   create role lecturer;
--   
--   create role dean;
--   
--   grant select on  stud_details
-- 	to student;
--     
--     grant select on  stud_results
-- 	to student;
--     
-- 	grant select on  courses_offered
-- 	to student;
--     
-- 	grant  insert on results_details
-- 	to lecturer;
--     
-- 	grant  update on results_details
-- 	to lecturer;
--         
-- 	grant  insert on course_info
-- 	to dean; 
--     
--     grant  update on course_info
-- 	to dean;
--     
--     grant  delete on course_info
-- 	to dean;
--     
--      select host,user from mysql.user;
--          
--     show grants;
--     show grants for current_user();
    
-- 	create user 'John' @ '192.168.1.5' identified by 'password';
--     
--  grant all privileges on university_db.* to 'john' @'192.168.1.5';

	
    DELIMITER $$
    CREATE TRIGGER `gradeTrigger`
    BEFORE UPDATE 
    ON results_details
    FOR EACH ROW
    
    BEGIN 
    SET NEW.TOTAL = NEW.CAT_mark + NEW.Exam_mark;
    
    IF NEW.TOTAL  >= 70 THEN 
		SET NEW.Grade = 'A';
	ELSEIF NEW.Total >60 AND NEW.Total < 70 THEN
		SET NEW.Grade = 'B';
	ELSEIF NEW.Total >50 AND NEW.Total < 60 THEN
		SET NEW.Grade = 'C';
	ELSEIF NEW.Total >40 AND NEW.Total < 50 THEN
		SET NEW.Grade = 'D';
	ELSE SET NEW.Grade = 'F';
    
    END IF;
    END;
    $$
    DELIMITER ;
    
    START TRANSACTION;
	select distinct student_details.Adm_no, Exam_card_no, unit_info.Unit_code, Unit_name, Yr_study, semester, calcGrade(CAT_mark, Exam_mark) as Grade
    /* into results_details */
    from student_details, unit_info, results_details
    where student_details.Adm_no = results_details.Adm_no
		and unit_info.unit_code = results_details.unit_code
        AND student_details.Adm_no = 'CCS/00001/019';  
    COMMIT;
    
    
--    select student_details.Adm_no, Exam_card_no, unit_info.Unit_code, Unit_name, Yr_study, semester, calcTotal(CAT_mark, Exam_mark) as Total, calcGrade(CAT_mark, Exam_mark) as Grade
--     from student_details, unit_info, results_details
--     where student_details.Adm_no = results_details.Adm_no
-- 		and unit_info.unit_code = results_details.unit_code
--         AND student_details.Adm_no = 'CCS/00001/019';  
    
   create view stud_transcript as
	select student_details.Adm_no, Fname, Lname, Exam_card_no, unit_info.Unit_code, unit_info.Unit_name, calcGrade(CAT_mark, Exam_mark) as Grade
    from student_details, unit_info,results_details
    where unit_info.Unit_code = results_details.Unit_code
    and student_details.Adm_no = results_details.Adm_no;
    
-- select * from stud_transcript where Adm_no = 'CCS/00001/019'; 
-- select student_details.Adm_no, Fname, Lname, Exam_card_no, unit_info.Unit_code, unit_info.Unit_name, Grade
--     from student_details, unit_info,results_details
--     where unit_info.Unit_code = results_details.Unit_code
--     and student_details.Adm_no = results_details.Adm_no
--     and student_details.Adm_no = 'CCS/00001/019' and Yr_study =1;

/* file modification stage starts here */ 
/* drop table teaches then perform this alter */    
alter table staff_details change Staff_Id Staff_Id int unsigned not null auto_increment;
alter table staff_details change Name Fname varchar(30) not null;
alter table results_details change Exam_card_no Exam_card_no varchar(10) not null; 
-- alter table results_details change CAT_mark CAT_mark int unsigned null check(CAT_mark between 0 and 30);
-- alter table results_details change Exam_mark Exam_mark int unsigned null check(Exam_mark between 0 and 70); 
alter table student_details drop Cartegory;
alter table student_details add Cartegory enum('KUCCPS','SELF-SPONSORED') not null after Admission_yr; 
alter table student_details add Lname varchar(30) not null after Fname;
alter table student_details add Password varchar(30) not null after Adm_no;
alter table student_details add Gender enum('MALE','FEMALE') not null after Lname;
alter table results_details add foreign key(Exam_card_no) references stud_registered_units(Exam_card_no);
-- drop table stud_registered_units; /* then recreate it with the new changes */
-- describe student_details;
/* drop function generateExamCardNo and recreate it with the new changes */

create table staff_details (
	Staff_Id int unsigned not null,
    Fname varchar(30) not null,
    Lname varchar(30) not null,
    ID int unsigned not null,
    Phone int unsigned not null,
    Email varchar(50) not null,
    Nationality varchar(30) not null,
    Title enum("Lecturer","Dean") not null,
    YoS int unsigned not null, 
    primary key(Staff_Id, ID));
    
alter table staff_details add Password varchar(30) not null after Staff_Id;
alter table staff_details add Gender enum('MALE','FEMALE') not null after ID;
-- describe staff_details;
 
drop table teaches; 
create table teaches (
	Staff_Id int unsigned not null,
    Unit_code varchar(8) not null,
    primary key(Unit_code),
    foreign key(Staff_Id) references staff_details(Staff_Id),
    foreign key(Unit_code) references unit_info(Unit_code) );
				
 drop table stud_registered_units; /* drop the table so as to make the new changes */  
create table stud_registered_units (
	Adm_no varchar(15) not null,
    Course_id varchar(5) not null,
	Yr_study int unsigned not null,
    semester int unsigned not null,
    Serial_no int unsigned not null auto_increment,
    Exam_card_no varchar(10) null default "null",
    Unit_code1 varchar(8) null default "null",
    Unit_code2 varchar(8) null default "null",
    Unit_code3 varchar(8) null default "null",
    Unit_code4 varchar(8) null default "null",
    Unit_code5 varchar(8) null default "null",
    Unit_code6 varchar(8) null default "null",
    Unit_code7 varchar(8) null default "null",
    Unit_code8 varchar(8) null default "null",
    Date datetime,
    primary key(Serial_no, Adm_no),
    foreign key(Adm_no) references student_details(Adm_no),
    foreign key(Course_id) references course_info(Course_id),
    foreign key(Unit_code1) references unit_info(Unit_code),
    foreign key(Unit_code2) references unit_info(Unit_code),
    foreign key(Unit_code3) references unit_info(Unit_code),
    foreign key(Unit_code4) references unit_info(Unit_code),
    foreign key(Unit_code5) references unit_info(Unit_code),
    foreign key(Unit_code6) references unit_info(Unit_code),
    foreign key(Unit_code7) references unit_info(Unit_code)
    );
--     describe stud_registered_units;
 
 drop table results_details; /* after drop, create this new table */
create table results_details (
	serial_no varchar(18) not null,
	Exam_card_no varchar(10) not null,
    Adm_no varchar(15) not null,
    Unit_code varchar(8) not null,
    CAT_mark int unsigned null,
    Exam_mark int unsigned null,
    Total int unsigned null,
    Grade char(1) null,
    primary key(serial_no, Adm_no),
    foreign key(Adm_no) references student_details(Adm_no),
    foreign key(Unit_code) references unit_info(Unit_code) );
    
create table stud_sem_register (
    Adm_no varchar(15) not null,
    Course_id varchar(5) not null,
	Yr_study int unsigned not null,
    semester int unsigned not null,
    Date datetime,
    primary key(Adm_no),
    foreign key(Course_id) references course_info(Course_id));
    
 delimiter $$
 create function generateSerialNo( Exam_card_no varchar(10), Unit_code varchar(8) ) 
	returns varchar(18)
    deterministic
    begin        
        return CONCAT( Exam_card_no, Unit_code );
	end
 $$
 delimiter ;  
 
 /* drop and rerun this function */
 delimiter $$
 create function generateExamCardNo( Serial_no int ) 
	returns varchar(10)
    deterministic
    begin
		declare substring varchar(4);
        set substring = "REGS";
               
        return CONCAT( substring, Serial_no );
	end
 $$
 delimiter ;
 
create view units_offered as
		select distinct unit_info.Course_id,unit_code,unit_name,Yr_study,semester 
        from unit_info, course_info
        where course_info.Course_id = unit_info.Course_id;
        
--  select * from units_offered where Course_id = "CCS" and Yr_study = 1 and semester = 1;  
  
  drop view view_examcard; /* drop this view and recreate it with new changes */
 create view VIEW_EXAMCARD as
	select stud_registered_units.Adm_no, Course_name, stud_registered_units.Yr_study, stud_registered_units.semester, Exam_card_no, Unit_code, unit_info.Unit_name,
		Unit_code1,Unit_code2,Unit_code3,Unit_code4,Unit_code5,Unit_code6,Unit_code7
	from student_details, unit_info, course_info, stud_registered_units
    where course_info.Course_id = stud_registered_units.Course_id
	and unit_info.Unit_code in(Unit_code1,Unit_code2,Unit_code3,Unit_code4,Unit_code5,Unit_code6,Unit_code7)
    and stud_registered_units.Adm_no = student_details.Adm_no;
    
--  select Adm_no, Course_name, Yr_study, semester, Exam_card_no, Unit_code, Unit_name
-- 	from view_examcard where Adm_no = 'CCS/00001/019' and Yr_study = 1 and semester = 1 ;
 
    
create view view_transcript as 
		select student_details.Adm_no, Fname, Lname, stud_registered_units.Exam_card_no, unit_info.Unit_code, unit_info.Unit_name, Grade, stud_registered_units.Yr_study 
		from student_details, unit_info, stud_registered_units, results_details 
		where unit_info.Unit_code = results_details.Unit_code
		and	student_details.Adm_no = stud_registered_units.Adm_no 
		and student_details.Adm_no = results_details.Adm_no;
        
-- select * from view_transcript where Adm_no = 'CCS/00001/019' and Yr_study =1;
		  
-- select * from stud_registered_units;
 INSERT INTO stud_registered_units (`Adm_no`,`Course_id`,`Yr_study`,`semester`,`Serial_no`,`Exam_card_no`,`Unit_code1`,
                    `Unit_code2`,`Unit_code3`,`Unit_code4`,`Unit_code5`,`Unit_code6`,`Unit_code7`,`Date`) 
                    VALUES('CCS/00001/019','CCS',1,1,100001,generateExamCardNo(Serial_no),'CCS 101','CCS 102','CCS 103','CCS 104','CCS 105','CCS 106','CCS 107',now());
 
--  select stud_registered_units.Adm_no, Course_name, stud_registered_units.Yr_study, stud_registered_units.semester, Exam_card_no, Unit_code, unit_info.Unit_name 
-- 	from student_details, unit_info, course_info, stud_registered_units
--     where course_info.Course_id = stud_registered_units.Course_id
-- 	and unit_info.Unit_code in(Unit_code1,Unit_code2,Unit_code3,Unit_code4,Unit_code5,Unit_code6,Unit_code7)
--     and stud_registered_units.Adm_no = student_details.Adm_no;
 
 insert into staff_details (Staff_Id,`Password`,`Fname`,`Lname`,`ID`,`Gender`,`Phone`,`Email`, `Nationality`,`Title`,`YoS`) 
	VALUES(10001,'1234','Chomba','Musili',3453260987,'Male',78453426,'chomba@yahoo','Kenyan','Lecturer',7);
               
--  select * from stud_sem_register;
--  select * from staff_details;
--  describe staff_details;
--  select * from student_details;
--  select * from results_details;
--  select * from stud_registered_units;
--  select * from unit_info;
--  select * from stud_sem_register;
--  select * from stud_registered_units;
--  select * from staff_details; 
 