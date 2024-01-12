/*
 * @author John Felix
 *
 * class manages connections to database and methods for manipulating the database
 */
package streetSparkle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Database {
    
    /* class attributes declarations */      
    private static final String URL = "jdbc:mysql://localhost:3306/street_cleaning_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "167456";
    
    private Connection connection = null; // manages database connection
    
    private PreparedStatement addStaff;
    private PreparedStatement addCasual; 
    private PreparedStatement addClient; 
    private PreparedStatement addSupplier;
    private PreparedStatement addProject;
    private PreparedStatement addAprojectStaff;
    private PreparedStatement addTool;
    private PreparedStatement addToolBorrow; 
    private PreparedStatement addToolReturn; 
    private PreparedStatement addAttendance; 
    private PreparedStatement addNotification; 
    private PreparedStatement addLog; 
    private PreparedStatement updateStaffUsername; 
    private PreparedStatement updateStaffPassword; 
    private PreparedStatement updateStaffEmail;
    private PreparedStatement updateStaffPhone;
    private PreparedStatement updateStaffAddress; 
    private PreparedStatement updateStaffCounty; 
    private PreparedStatement updateStaffTitle; 
    private PreparedStatement updateStaffSalary;
    private PreparedStatement updateStaffPerks;
    private PreparedStatement updateStaffStatus; 
    private PreparedStatement updateStaffProfilePic;
    private PreparedStatement updateStaffTerminationDate;
    private PreparedStatement updateCasualPhone;
    private PreparedStatement updateCasualAddress;
    private PreparedStatement updateCasualCounty;
    private PreparedStatement updateCasualWage;
    private PreparedStatement updateCasualWorkDays; 
    private PreparedStatement updateCasualMoney; 
    private PreparedStatement updateCasualStatus;
    private PreparedStatement updateCasualTerminationDate;
    private PreparedStatement updateToolDescription;
    private PreparedStatement updateToolBorrowQty;
    private PreparedStatement updateToolReturnQty; 
    private PreparedStatement updateToolDamagedQty;
    private PreparedStatement updateToolLostQty; 
    private PreparedStatement updateProjectExtend;
    private PreparedStatement updateProjectStaffNo;
    private PreparedStatement updateProjectStatus; 
    private PreparedStatement updateProjectBeforePic; 
    private PreparedStatement updateProjectAfterPic; 
    protected PreparedStatement fetchStaffLogin; 
    protected PreparedStatement fetchAStaffDetails;
    protected PreparedStatement fetchAStaffProfilePic;
    protected PreparedStatement fetchStaffDetails; 
    protected PreparedStatement fetchSupervisorDetails;
    protected PreparedStatement fetchStaffTitleCount;
    protected PreparedStatement fetchStaffCount; 
    protected PreparedStatement fetchACasualDetails;
    protected PreparedStatement fetchCasualDetails; 
    protected PreparedStatement fetchCasualCount; 
    protected PreparedStatement fetchCasualStatus; 
    protected PreparedStatement fetchCasualAttendance; 
    protected PreparedStatement fetchCasualPayment;
    protected PreparedStatement fetchCasualToolDamage;
    protected PreparedStatement fetchCasualToolLost;
    protected PreparedStatement fetchToolDetails; 
    protected PreparedStatement fetchAToolDetails;
    protected PreparedStatement fetchToolsBorrowedDay; 
    protected PreparedStatement fetchToolsBorrowedWeek;
    protected PreparedStatement fetchToolsBorrowedMonth;
    protected PreparedStatement fetchToolsBorrowedYear;
    protected PreparedStatement fetchToolsBorrowedDate;
    protected PreparedStatement fetchToolsBorrowedStaff;
    protected PreparedStatement fetchDamageUnder;
    protected PreparedStatement fetchLostUnder;
    //protected PreparedStatement fetchToolsStatus;
    protected PreparedStatement fetchProjects; 
    protected PreparedStatement fetchAprojectStaff;
    protected PreparedStatement fetchProjectsPending; // for specific supervisor
    protected PreparedStatement fetchProjectsOngoing; // for specific supervisor
    protected PreparedStatement fetchProjectsComplete; // for specific supervisor
    protected PreparedStatement fetchProjPending; // in general
    protected PreparedStatement fetchProjOngoing; // in general
    protected PreparedStatement fetchProjComplete; // in general
    protected PreparedStatement fetchAProject; 
    protected PreparedStatement fetchProjectPic;
    protected PreparedStatement fetchProjectBeforePic;
    protected PreparedStatement fetchProjectAfterPic;
    protected PreparedStatement fetchClientDetails;
    protected PreparedStatement fetchAClientDetails;
    protected PreparedStatement fetchSupplierDetails;
    protected PreparedStatement fetchASupplierDetails;
    protected PreparedStatement fetchNotificationsDay;
    protected PreparedStatement fetchNotificationsWeek;
    protected PreparedStatement fetchNotificationsMonth;
    protected PreparedStatement fetchNotificationsYear;
    protected PreparedStatement fetchNotificationsDate;
    protected PreparedStatement countNotificationsDay;
    protected PreparedStatement countNotificationsWeek;
    protected PreparedStatement countNotificationsMonth;
    protected PreparedStatement countNotificationsYear;
    protected PreparedStatement countNotificationsDate;
    protected PreparedStatement fetchANotification; 
    protected PreparedStatement fetchClientNotifications; 
    protected PreparedStatement fetchLogs; 
    //protected PreparedStatement fetchLogins; 
    protected PreparedStatement confirmStaffID; // to validate updates
    protected PreparedStatement confirmCasualID; // to validate updates 
    protected PreparedStatement confirmToolSN; // to validate updates
    protected PreparedStatement confirmStaffUsername; // to authenticate
    protected PreparedStatement confirmStaffPassword; // to authenticate       

    /* class Database constructor */
    public Database() {
        // init Components
        try {
            connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
            
            addStaff = connection.prepareStatement("INSERT INTO staff_record (`staffId`,`username`,`password`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`DOB`,`phone`,`email`," +
                                                    "`address`,`county`,`title`,`salary`,`perks`,`employedDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,date(current_date()))");
            addCasual = connection.prepareStatement("INSERT INTO casuals_record (`casualId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`DOB`,`phone`,`email`,`address`,`county`,`staffId`,`wage`,`hireDate`)" +
                                                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,date(current_date()))"); 
            addClient = connection.prepareStatement("INSERT INTO clients_record (`firstName`,`lastName`,`otherName`,`phone`,`email`,`address`,`county`,`companyTitle`,`timestamp`) VALUES (?,?,?,?,?,?,?,?,now())"); 
            addSupplier = connection.prepareStatement("INSERT INTO suppliers_record (`companyTitle`,`phone`,`email`,`address`,`county`,`registerDate`,`products`,`terminateDate`) VALUES (?,?,?,?,?,date(current_date()),?,?)");
            addProject = connection.prepareStatement("INSERT INTO project_record (`title`,`staffId`,`location`,`county`,`staffNo`,`start`,`end`,`vehicle`,`timestamp`) VALUES (?,?,?,?,?,?,?,?,now())"); 
            addAprojectStaff = connection.prepareStatement("INSERT INTO project_staffing_record (`projectId`,`staffId`,`casualId`,`timestamp`) VALUES (?,?,?,now())"); 
            addTool = connection.prepareStatement("INSERT INTO tools_record (`name`,`size`,`price`,`buyDate`,`quantity`,`timestamp`) VALUES (?,?,?,?,?,now())"); // zero tool quantity until restocking
            addToolBorrow = connection.prepareStatement("INSERT INTO tool_borrow_record (`staffId`,`toolId`,`goodQty`,`damagedQty`,`duration`,`timestamp`) VALUES (?,?,?,?,?,now())"); 
            addToolReturn = connection.prepareStatement("INSERT INTO tool_return_record (`staffId`,`toolId`,`goodQty`,`timestamp`) VALUES (?,?,?,now())");
            addAttendance = connection.prepareStatement("INSERT INTO attendance_record (`projectId`,`staffId`,`casualId`,`timestamp`) VALUES (?,?,?,now())"); 
            addNotification = connection.prepareStatement("INSERT INTO notifications_record (`staffId`,`clientId`,`sendTo`,`description`,`timestamp`) VALUES (?,?,?,?,now())");
            addLog = connection.prepareStatement("INSERT INTO logs_record (`timestamp`,`username`,`password`) VALUES (now(),?,?)");
            updateStaffUsername = connection.prepareStatement("UPDATE staff_record SET `username` = ? WHERE `ID` = ? "); 
            updateStaffPassword = connection.prepareStatement("UPDATE staff_record SET `password` = ? WHERE `ID` = ? "); 
            updateStaffEmail = connection.prepareStatement("UPDATE staff_record SET `email` = ? WHERE `ID` = ? ");
            updateStaffPhone = connection.prepareStatement("UPDATE staff_record SET `phone` = ? WHERE `ID` = ? ");
            updateStaffAddress = connection.prepareStatement("UPDATE staff_record SET `address` = ? WHERE `ID` = ? "); 
            updateStaffCounty = connection.prepareStatement("UPDATE staff_record SET `county` = ? WHERE `ID` = ? "); 
            updateStaffTitle = connection.prepareStatement("UPDATE staff_record SET `title` = ? WHERE `ID` = ? "); 
            updateStaffSalary = connection.prepareStatement("UPDATE staff_record SET `salary` = ? WHERE `ID` = ? ");
            updateStaffPerks = connection.prepareStatement("UPDATE staff_record SET `perks` = ? WHERE `ID` = ? ");
            updateStaffStatus = connection.prepareStatement("UPDATE staff_record SET `status` = ? WHERE `ID` = ? "); 
            updateStaffProfilePic = connection.prepareStatement("UPDATE staff_record SET `profilePic` = ? WHERE `ID` = ? ");
            updateStaffTerminationDate = connection.prepareStatement("UPDATE staff_record SET `terminationDate` = date(current_date()) WHERE `ID` = ? ");
            updateCasualPhone = connection.prepareStatement("UPDATE casuals_record SET `phone` = ? WHERE `ID` = ? "); 
            updateCasualAddress = connection.prepareStatement("UPDATE casuals_record SET `address` = ? WHERE `ID` = ? ");
            updateCasualCounty = connection.prepareStatement("UPDATE casuals_record SET `county` = ? WHERE `ID` = ? "); 
            updateCasualWage = connection.prepareStatement("UPDATE casuals_record SET `wage` = ? WHERE `ID` = ? ");
            updateCasualWorkDays = connection.prepareStatement("UPDATE casuals_record SET `workDays` = `workDays` + 1 WHERE `ID` = ? "); 
            updateCasualMoney = connection.prepareStatement("UPDATE casuals_record SET `money` = ? WHERE `ID` = ? ");
            updateCasualStatus = connection.prepareStatement("UPDATE casuals_record SET `status` = ? WHERE `ID` = ? ");
            updateCasualTerminationDate = connection.prepareStatement("UPDATE casuals_record SET `terminationDate` = date(current_date()) WHERE `ID` = ? ");
            updateToolDescription = connection.prepareStatement("UPDATE tools_record SET `description` = ? WHERE `toolId` = (SELECT `toolId` FROM (SELECT `toolId` FROM tools_record WHERE `name` = ? ) AS Temp_table )");
            updateToolBorrowQty = connection.prepareStatement("UPDATE tools_record SET `quantity` = `quantity` - ? WHERE `toolId` = ? ");
            updateToolReturnQty = connection.prepareStatement("UPDATE tools_record SET `quantity` = `quantity` + ? WHERE `toolId` = ? ");
            updateToolDamagedQty = connection.prepareStatement("UPDATE tool_return_record SET `damagedQty` = ? ,`damageUnder` = ? WHERE `toolId` = ? AND `serial_no` = (select `serial_no` from (select `serial_no` from tool_return_record as T) as P order by `serial_no` desc limit 1)");
            updateToolLostQty = connection.prepareStatement("UPDATE tool_return_record SET `lostQty` = ? ,`lostUnder` = ? WHERE `toolId` = ? AND `serial_no` = (select `serial_no` from (select `serial_no` from tool_return_record as T) as P order by `serial_no` desc limit 1)"); 
            updateProjectExtend = connection.prepareStatement("UPDATE project_record SET `extend` = ? WHERE `projectId` = ? ");
            updateProjectStaffNo = connection.prepareStatement("UPDATE project_record SET `staffNo` = ? WHERE `projectId` = ? ");
            updateProjectStatus = connection.prepareStatement("UPDATE project_record SET `status` = ? WHERE `projectId` = ? ");
            updateProjectBeforePic = connection.prepareStatement("UPDATE project_record SET `beforePic` = ? WHERE `projectId` = ? "); 
            updateProjectAfterPic = connection.prepareStatement("UPDATE project_record SET `afterPic` = ? WHERE `projectId` = ? "); 
            fetchStaffLogin = connection.prepareStatement("SELECT `staffId`,`ID`,`county`,`title`,`status` FROM staff_record WHERE `username` = ? AND `password` = ? ");
            fetchAStaffDetails = connection.prepareStatement("SELECT * FROM fetch_a_staff_details WHERE `ID` = ? ");
            fetchAStaffProfilePic = connection.prepareStatement("SELECT `profilePic` FROM staff_record WHERE `ID` = ? ");
            fetchStaffDetails = connection.prepareStatement("SELECT `staffId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`phone`,`email`,`county`,`title` FROM staff_record ORDER BY `title`");
            fetchSupervisorDetails = connection.prepareStatement("SELECT * FROM fetch_supervisor_details");
            fetchStaffTitleCount = connection.prepareStatement("SELECT `title`,COUNT(`title`) AS `Number` FROM staff_record GROUP BY `title` ORDER BY `Number`");
            fetchStaffCount = connection.prepareStatement("SELECT COUNT(*) AS `Total` FROM staff_record"); 
            fetchACasualDetails = connection.prepareStatement("SELECT * FROM fetch_a_casual_details WHERE `ID` = ? ");
            fetchCasualDetails = connection.prepareStatement("SELECT `casualId`,`firstName`,`lastName`,`otherName`,`gender`,`ID`,`phone`,`email`,`county`,`hireDate` FROM casuals_record ORDER BY `county`");
            fetchCasualCount = connection.prepareStatement("SELECT COUNT(*) AS `Total` FROM casuals_record");
            fetchCasualStatus = connection.prepareStatement("SELECT * FROM fetch_casual_status WHERE `county` = ? "); 
            fetchCasualAttendance = connection.prepareStatement("SELECT * FROM fetch_casual_attendance WHERE `staffId`= ? AND `status`= ? "); /* project status mostly is ongoing */
            fetchCasualPayment = connection.prepareStatement("SELECT * FROM fetch_casual_payment WHERE `status` = ? AND `money` = ? AND `staffId` = ? ");
            fetchCasualToolDamage = connection.prepareStatement("SELECT * FROM fetch_casual_tool_damage WHERE `ID`= ? AND week(`Timestamp`) = week(current_date())");
            fetchCasualToolLost = connection.prepareStatement("SELECT * FROM fetch_casual_tool_damage WHERE `ID`= ? AND week(`Timestamp`) = week(current_date())");
            fetchToolDetails = connection.prepareStatement("SELECT * FROM tools_record");
            fetchAToolDetails = connection.prepareStatement("SELECT * FROM tools_record WHERE `toolId` = ? ");
            fetchToolsBorrowedDay = connection.prepareStatement("SELECT * FROM fetch_tools_borrowed WHERE day(`timestamp`) = day(current_date())");
            fetchToolsBorrowedWeek = connection.prepareStatement("SELECT * FROM fetch_tools_borrowed WHERE week(`timestamp`) = week(current_date())");
            fetchToolsBorrowedMonth = connection.prepareStatement("SELECT * FROM fetch_tools_borrowed WHERE month(`timestamp`) = month(current_date())");
            fetchToolsBorrowedYear = connection.prepareStatement("SELECT * FROM fetch_tools_borrowed WHERE year(`timestamp`) = year(current_date())");
            fetchToolsBorrowedDate = connection.prepareStatement("SELECT * FROM fetch_tools_borrowed WHERE date(`timestamp`) = ? ");                                    
            fetchToolsBorrowedStaff = connection.prepareStatement("SELECT * FROM fetch_tools_borrowed WHERE `staffId` = ? ");
            fetchDamageUnder = connection.prepareStatement("SELECT `damageUnder` FROM tool_return_record WHERE week(`Timestamp`) = week(current_date())");
            fetchLostUnder = connection.prepareStatement("SELECT `lostUnder` FROM tool_return_record WHERE week(`Timestamp`) = week(current_date())");
            //fetchToolsStatus = connection.prepareStatement("");
            fetchProjects = connection.prepareStatement("SELECT * FROM project_record ORDER BY `status`"); 
            fetchAprojectStaff = connection.prepareStatement("SELECT * FROM fetch_a_project_staff WHERE `projectId` = ? ORDER BY `casualId`");
            fetchProjectsPending = connection.prepareStatement("SELECT * FROM fetch_projects_pending WHERE `staffId` = ? ");
            fetchProjectsOngoing = connection.prepareStatement("SELECT * FROM fetch_projects_ongoing WHERE `staffId` = ? "); 
            fetchProjectsComplete = connection.prepareStatement("SELECT * FROM fetch_projects_complete WHERE `staffId` = ? "); 
            fetchProjPending = connection.prepareStatement("SELECT * FROM fetch_projects_pending");
            fetchProjOngoing = connection.prepareStatement("SELECT * FROM fetch_projects_ongoing"); 
            fetchProjComplete = connection.prepareStatement("SELECT * FROM fetch_projects_complete"); 
            fetchAProject = connection.prepareStatement("SELECT * FROM fetch_a_project WHERE `staffId` = ? ORDER BY `status`"); 
            fetchProjectPic = connection.prepareStatement("SELECT * FROM fetch_project_pic WHERE `staffId` = ? "); 
            fetchProjectBeforePic = connection.prepareStatement("SELECT `beforePic` FROM project_record WHERE `projectId` = ? ");
            fetchProjectAfterPic = connection.prepareStatement("SELECT `afterPic` FROM project_record WHERE `projectId` = ? ");
            fetchClientDetails = connection.prepareStatement("SELECT * FROM clients_record ORDER BY `county`");
            fetchAClientDetails = connection.prepareStatement("SELECT * FROM fetch_a_client_details WHERE `clientId`= ? ");
            fetchSupplierDetails = connection.prepareStatement("SELECT * FROM suppliers_record ORDER BY `county`");
            fetchASupplierDetails = connection.prepareStatement("SELECT * FROM suppliers_record WHERE `companyTitle` = ? ");
            fetchNotificationsDay = connection.prepareStatement("SELECT * FROM notifications_record WHERE day(`timestamp`) = day(current_date())");
            fetchNotificationsWeek = connection.prepareStatement("SELECT * FROM notifications_record WHERE week(`timestamp`) = week(current_date())");
            fetchNotificationsMonth = connection.prepareStatement("SELECT * FROM notifications_record WHERE month(`timestamp`) = month(current_date())");
            fetchNotificationsYear = connection.prepareStatement("SELECT * FROM notifications_record WHERE year(`timestamp`) = year(current_date())");
            fetchNotificationsDate = connection.prepareStatement("SELECT * FROM notifications_record WHERE date(`timestamp`) = ? "); 
            countNotificationsDay = connection.prepareStatement("SELECT count(*) FROM notifications_record WHERE day(`timestamp`) = day(current_date())"); 
            countNotificationsWeek = connection.prepareStatement("SELECT count(*) FROM notifications_record WHERE week(`timestamp`) = week(current_date())");        
            countNotificationsMonth = connection.prepareStatement("SELECT count(*) FROM notifications_record WHERE month(`timestamp`) = month(current_date())");        
            countNotificationsYear = connection.prepareStatement("SELECT count(*) FROM notifications_record WHERE year(`timestamp`) = year(current_date())");        
            countNotificationsDate = connection.prepareStatement("SELECT count(*) FROM notifications_record WHERE date(`timestamp`) = ? ");        
            fetchANotification = connection.prepareStatement("SELECT `staffId`,`sendTo`,`description`,`timestamp` FROM notifications_record WHERE `sendTo` = ? AND `staffId` IS NOT NULL AND day(`timestamp`) = day(current_date())"); 
            fetchClientNotifications = connection.prepareStatement("SELECT `clientId`,`description`,`timestamp` FROM notifications_record WHERE `clientId` IS NOT NULL AND day(`timestamp`) = day(current_date())"); 
            fetchLogs = connection.prepareStatement("SELECT * FROM logs_record"); 
            //fetchLogins = connection.prepareStatement(""); 
            confirmStaffID = connection.prepareStatement("SELECT `staffId`,`ID` FROM staff_record WHERE `ID` = ? ");
            confirmCasualID = connection.prepareStatement("SELECT `ID` FROM casuals_record WHERE `ID` = ? "); 
            confirmToolSN = connection.prepareStatement("SELECT `toolId` FROM tools_record WHERE `toolId` = ? ");
            confirmStaffUsername = connection.prepareStatement("SELECT `username` FROM staff_record WHERE `username` = ? "); 
            confirmStaffPassword = connection.prepareStatement("SELECT `password` FROM staff_record WHERE `password` = ? ");        
            
        } catch(SQLException e) {
            // displaying of connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                   
        }       
    } // end constructor Database
    
    /* function records a staff */
    public void addStaff(String staffId,String username,String password,String firstName,String lastName,String otherName,String gender,
            int ID,String DOB,int phone,String email,String address,String county,String title,float salary,float perks,JButton saveButton) {
        
        try {
            addStaff.setString(1, staffId);
            addStaff.setString(2,username);
            addStaff.setString(3,password);  
            addStaff.setString(4,firstName.toUpperCase());
            addStaff.setString(5,lastName.toUpperCase());
            addStaff.setString(6,otherName.toUpperCase());
            addStaff.setString(7,gender);
            addStaff.setInt(8,ID);
            addStaff.setDate(9,java.sql.Date.valueOf(DOB));
            addStaff.setInt(10,phone);
            addStaff.setString(11,email);
            addStaff.setString(12,address);
            addStaff.setString(13,county);  
            addStaff.setString(14,title);
            addStaff.setFloat(15,salary);
            addStaff.setFloat(16,perks);        
            // execute query
            addStaff.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Staff added successfully!", "Add New Staff Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE  );
            saveButton.setEnabled(false); // disable button till new input
            
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Staff Error - Street Sparkle", JOptionPane.WARNING_MESSAGE );
         
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addStaff
    
    /* function records a casual worker */        
    public void addCasual(String casualId,String firstName,String lastName,String otherName,String gender,int ID,String DOB,int phone,
            String email,String address,String county,String staffId,float wage,JButton saveButton) {
        
        try {
            addCasual.setString(1, casualId);
            addCasual.setString(2,firstName.toUpperCase());
            addCasual.setString(3,lastName.toUpperCase());
            addCasual.setString(4,otherName.toUpperCase());
            addCasual.setString(5,gender);
            addCasual.setInt(6,ID);
            addCasual.setDate(7,java.sql.Date.valueOf(DOB));
            addCasual.setInt(8,phone);
            addCasual.setString(9,email);
            addCasual.setString(10,address);
            addCasual.setString(11,county);  
            addCasual.setString(12,staffId);
            addCasual.setFloat(13,wage);       
            // execute query
            addCasual.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Casual worker added successfully!", "Add New Casual Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE  );
            saveButton.setEnabled(false); // disable button till new input
            
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Casual Error - Street Sparkle", JOptionPane.WARNING_MESSAGE );
         
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addCasual
    
    /* function records a client */        
    public void addClient(String firstName,String lastName,String otherName,int phone,String email,String address,String county,String companyTitle,JButton saveButton) {
        try {
            addClient.setString(1,firstName.toUpperCase());
            addClient.setString(2,lastName.toUpperCase());
            addClient.setString(3,otherName.toUpperCase());
            addClient.setInt(4,phone);
            addClient.setString(5,email);
            addClient.setString(6,address);
            addClient.setString(7,county);  
            addClient.setString(8,companyTitle.toUpperCase());       
            // execute query
            addClient.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Client added successfully!", "Add New Client Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE  );
            saveButton.setEnabled(false); // disable button till new input
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addClient
    
    /* function records a supplier */        
    public void addSupplier(String companyTitle,int phone,String email,String address,String county,String products,String termination,JButton saveButton) {
        try {
            addSupplier.setString(1,companyTitle.toUpperCase());
            addSupplier.setInt(2,phone);
            addSupplier.setString(3,email);
            addSupplier.setString(4,address);
            addSupplier.setString(5,county);  
            addSupplier.setString(6,products);  
            addSupplier.setDate(7,java.sql.Date.valueOf(termination));
            // execute query
            addSupplier.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Supplier added successfully!", "Add New Supplier Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE  );
            saveButton.setEnabled(false); // disable button till new input
        
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Supplier Error - Street Sparkle", JOptionPane.WARNING_MESSAGE );
             
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addSupplier
    
    /* function records a project */        
    public void addProject(String title,String staffId,String location,String county,int staffNo,String start,String end,String vehicle,JButton saveButton) {
        try {
            addProject.setString(1,title);
            addProject.setString(2,staffId);
            addProject.setString(3,location.toUpperCase());
            addProject.setString(4,county);
            addProject.setInt(5,staffNo);  
            addProject.setDate(6,java.sql.Date.valueOf(start));  
            addProject.setDate(7,java.sql.Date.valueOf(end));
            addProject.setString(8,vehicle.toUpperCase());
            // execute query
            addProject.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Project added successfully!", "Add New Project Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE  );
            saveButton.setEnabled(false); // disable button till new input
        
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Project Error - Street Sparkle", JOptionPane.WARNING_MESSAGE );
             
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addProject
    
    /* function records casual workers staffing a project */
    public void addAprojectStaff(int projectId, String staffId, String casualId) {
        try {
            addAprojectStaff.setInt(1, projectId);
            addAprojectStaff.setString(2, staffId);
            addAprojectStaff.setString(3, casualId);
            // execute query in batch
            addAprojectStaff.addBatch();
            addAprojectStaff.executeBatch();

        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addAprojectStaff
    
    /* function records a tool */        
    public void addTool(String name,String size,float price,String buyDate,int quantity,JButton saveButton) {
        try {
            addTool.setString(1,name.toUpperCase());
            addTool.setString(2,size.toUpperCase());
            addTool.setFloat(3,price);
            addTool.setDate(4,java.sql.Date.valueOf(buyDate));
            addTool.setInt(5,quantity);
            // execute query
            addTool.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Tool item added successfully!", "Add New Tool Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE );
            saveButton.setEnabled(false); // disable button till new input
        
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Project Error - Street Sparkle", JOptionPane.WARNING_MESSAGE );
             
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addTool
    
    /* function records a tool borrow transaction */        
    public void addToolBorrow(String staffId,int toolId,int goodQty,int damagedQty,int duration,JButton saveButton) {
        try {
            addToolBorrow.setString(1,staffId);
            addToolBorrow.setInt(2,toolId);
            addToolBorrow.setInt(3,goodQty);
            addToolBorrow.setInt(4,damagedQty);
            addToolBorrow.setInt(5,duration);
            // execute query
            addToolBorrow.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Tool borrow added successfully!", "Add Tool Borrow Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE  );
            saveButton.setEnabled(false); // disable button till new input
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }    
    } // end function addToolBorrow
    
    /* function records a tool return transaction */        
    public void addToolReturn(String staffId,int toolId,int goodQty,JButton saveButton) {
        try {
            addToolReturn.setString(1,staffId);
            addToolReturn.setInt(2,toolId);
            addToolReturn.setInt(3,goodQty);
            // execute query
            addToolReturn.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Tool return added successfully!", "Add Tool Return Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE );
            saveButton.setEnabled(false); // disable button till new input
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addToolReturn
    
    /* function records casual workers attendance per project */        
    public void addAttendance(int projectId,String staffId,String casualId) {
        try {
            addAttendance.setInt(1,projectId);
            addAttendance.setString(2,staffId);
            addAttendance.setString(3,casualId);
            // execute query in batch
            addAttendance.addBatch();
            addAttendance.executeBatch();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addAttendance
    
    /* function records a notification */        
    public void addNotification(String staffId,int clientId,String sendTo,String description) {
        try {
            addNotification.setString(1,staffId);
            addNotification.setInt(2,clientId);
            addNotification.setString(3,sendTo);
            addNotification.setString(4,description);
            // execute query
            addNotification.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addNotification
    
    /* function records a log */        
    public void addLog(String username,String password) {
        try {
            addLog.setString(1,username);
            addLog.setString(2,password);
            // execute query
            addLog.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function addLog
    
    /* function updates a staff's username */        
    public void updateStaffUsername(String username,int ID) {
        try {
            updateStaffUsername.setString(1,username);
            updateStaffUsername.setInt(2,ID);
            // execute query
            updateStaffUsername.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffUsername
    
    /* function updates a staff's password */        
    public void updateStaffPassword(String password,int ID) {
        try {
            updateStaffPassword.setString(1,password);
            updateStaffPassword.setInt(2,ID);
            // execute query
            updateStaffPassword.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffPassword
    
    /* function updates a staff's email */        
    public void updateStaffEmail(String email,int ID) {
        try {
            updateStaffEmail.setString(1,email);
            updateStaffEmail.setInt(2,ID);
            // execute query
            updateStaffEmail.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffEmail
            
    /* function updates a staff's phone no. */        
    public void updateStaffPhone(int phone,int ID) {
        try {
            updateStaffPhone.setInt(1,phone);
            updateStaffPhone.setInt(2,ID);
            // execute query
            updateStaffPhone.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffPhone
    
    /* function updates a staff's address */        
    public void updateStaffAddress(String address,int ID) {
        try {
            updateStaffAddress.setString(1,address);
            updateStaffAddress.setInt(2,ID);
            // execute query
            updateStaffAddress.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffAddress
    
    /* function updates a staff's county */        
    public void updateStaffCounty(String county,int ID) {
        try {
            updateStaffCounty.setString(1,county);
            updateStaffCounty.setInt(2,ID);
            // execute query
            updateStaffCounty.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffCounty
    
    /* function updates a staff's title */        
    public void updateStaffTitle(String title,int ID,JLabel label) {
        try {
            updateStaffTitle.setString(1,title);
            updateStaffTitle.setInt(2,ID);
            // execute query
            updateStaffTitle.executeUpdate();
            label.setText("Updated Successfully");
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffTitle
    
    /* function updates a staff's salary */        
    public void updateStaffSalary(float salary,int ID) {
        try {
            updateStaffSalary.setFloat(1,salary);
            updateStaffSalary.setInt(2,ID);
            // execute query
            updateStaffSalary.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffSalary
    
    /* function updates a staff's perks */        
    public void updateStaffPerks(float perks,int ID) {
        try {
            updateStaffPerks.setFloat(1,perks);
            updateStaffPerks.setInt(2,ID);
            // execute query
            updateStaffPerks.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffPerks
    
    /* function updates a staff's employment status */        
    public void updateStaffStatus(String status,int ID,JLabel label) {
        try {
            updateStaffStatus.setString(1,status);
            updateStaffStatus.setInt(2,ID);            
            // execute query
            updateStaffStatus.executeUpdate();           
            label.setText("Updated Successfully");
            // if terminated, store date
            if(status.equals("TERMINATED")) {
                updateStaffTerminationDate.setInt(1, ID);
                // execute query
                updateStaffTerminationDate.executeUpdate();
            }       
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateStaffStatus
    
    /* function updates a staff's profile picture */        
    public void updateStaffProfilePic(String profilePic,int ID) {
        try {
            // image file input stream
            InputStream fin = new FileInputStream(profilePic);
            updateStaffProfilePic.setBinaryStream(1, fin);
            updateStaffProfilePic.setInt(2,ID);
            // execute query
            updateStaffProfilePic.executeUpdate();
        
        } catch(SQLException | FileNotFoundException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }  
    } // end function updateStaffProfilePic
    
    /* function updates a casual worker's phone no. */        
    public void updateCasualPhone(int phone,int ID) {
        try {
            updateCasualPhone.setInt(1,phone);
            updateCasualPhone.setInt(2,ID);
            // execute query
            updateCasualPhone.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateCasualPhone 
    
    /* updates a casual worker's address */        
    public void updateCasualAddress(String address,int ID) {
        try {
            updateCasualAddress.setString(1,address);
            updateCasualAddress.setInt(2,ID);
            // execute query
            updateCasualAddress.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateCasualAddress
    
    /* function updates a casual worker's county */        
    public void updateCasualCounty(String county,int ID) {
        try {
            updateCasualCounty.setString(1,county);
            updateCasualCounty.setInt(2,ID);
            // execute query
            updateCasualCounty.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateCasualCounty
    
    /* function updates a casual worker's wage */        
    public void updateCasualWage(float wage,int ID) {
        try {
            updateCasualWage.setFloat(1,wage);
            updateCasualWage.setInt(2,ID);
            // execute query
            updateCasualWage.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateCasualWage
    
    /* function updates a casual worker's days of work */        
    public void updateCasualWorkDays(int ID) {
        try {
            updateCasualWorkDays.setInt(1,ID);
            // execute query
            updateCasualWorkDays.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateCasualWorkDays
    
    /* function updates a casual worker's payment status */        
    public void updateCasualMoney(String moneyPaid,int ID) {
        try {
            updateCasualMoney.setString(1,moneyPaid);
            updateCasualMoney.setInt(2,ID);
            // execute query
            updateCasualMoney.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateCasualMoney
    
    /* function updates a casual worker's employment status */        
    public void updateCasualStatus(String status,int ID) {
        try {
            updateCasualStatus.setString(1,status);
            updateCasualStatus.setInt(2,ID);
            // execute query
            updateCasualStatus.executeUpdate();
            // if terminated, store date
            if(status.equals("TERMINATED")) {
                updateCasualTerminationDate.setInt(1, ID);
                // execute query
                updateCasualTerminationDate.executeUpdate();
            } 
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updaateCasualStatus
    
    /* function updates a tool's description */        
    public void updateToolDescription(String description,String toolName) {
        try {
            updateToolDescription.setString(1,description);
            updateToolDescription.setString(2,toolName);
            // execute query
            updateToolDescription.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateToolDescription
    
    /* function updates tool quantity negatively after a borrow */        
    public void updateToolBorrowQty(int quantity,int toolId) {
        try {
            updateToolBorrowQty.setInt(1,quantity);
            updateToolBorrowQty.setInt(2,toolId);
            // execute query
            updateToolBorrowQty.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateToolBorrowQty
    
    /* function updates tool quantity positively after a return */        
    public void updateToolReturnQty(int quantity,int toolId) {
        try {
            updateToolReturnQty.setInt(1,quantity);
            updateToolReturnQty.setInt(2,toolId);
            // execute query
            updateToolReturnQty.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateToolReturnQty
    
    /* function updates quantity of damaged tool */        
    public void updateToolDamagedQty(int damagedQty,String damageUnder,int toolId) {
        try {
            updateToolDamagedQty.setInt(1,damagedQty);
            updateToolDamagedQty.setString(2,damageUnder);
            updateToolDamagedQty.setInt(3,toolId);
            // execute query
            updateToolDamagedQty.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateToolDamageQty
    
    /* function updates quantity of lost tool */        
    public void updateToolLostQty(int lostQty,String lostUnder,int toolId) {
        try {
            updateToolLostQty.setInt(1,lostQty);
            updateToolLostQty.setString(2,lostUnder);
            updateToolLostQty.setInt(3,toolId);
            // execute query
            updateToolLostQty.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateToolLostQty
    
    /* function updates project extension days */        
    public void updateProjectExtend(int extend,int projId) {
        try {
            updateProjectExtend.setInt(1,extend);
            updateProjectExtend.setInt(2,projId);
            // execute query
            updateProjectExtend.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateProjectExtend
    
    /* function updates no. of casual workers for undertaking project */        
    public void updateProjectStaffNo(int staffNo,int projId) {
        try {
            updateProjectStaffNo.setInt(1,staffNo);
            updateProjectStaffNo.setInt(2,projId);
            // execute query
            updateProjectStaffNo.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateProjectStaffNo
    
    /* function updates status of project */        
    public void updateProjectStatus(String status,int projId) {
        try {
            updateProjectStatus.setString(1,status);
            updateProjectStatus.setInt(2,projId);
            // execute query
            updateProjectStatus.executeUpdate();
        
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateProjectStatus
    
    /* function updates before picture of a project */        
    public void updateProjectBeforePic(String beforePic, int projId) {
        try {
            // image file input stream
            InputStream fin = new FileInputStream(beforePic);
            updateProjectBeforePic.setBinaryStream(1,fin);
            updateProjectBeforePic.setInt(2,projId);
            // execute query
            updateProjectBeforePic.executeUpdate();
        
        } catch(SQLException | FileNotFoundException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateProjectBeforePic
    
    /* function updates after picture of a project */        
    public void updateProjectAfterPic(String afterPic, int projId) {
        try {
            // image file input stream
            InputStream fin = new FileInputStream(afterPic);
            updateProjectAfterPic.setBinaryStream(1,fin);
            updateProjectAfterPic.setInt(2,projId);
            // execute query
            updateProjectAfterPic.executeUpdate();
        
        } catch(SQLException | FileNotFoundException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }
    } // end function updateProjectAfterPic
    
    /* function confirms staff's national ID before updates */        
    public boolean confirmStaffID(int staffID) {
        boolean hasID = false;
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // confirm staff id if in database
            confirmStaffID.setInt(1,staffID);
            rs = confirmStaffID.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    hasID = true; // return staff in database                  
                } while(rs.next());
            } else {  
                hasID = false; // return staff not in database                
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );
        }
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        } 
        
        return hasID;
    } // end function confirmStaffID
    
    /* function confirms if casual's Id in database */
    public boolean confirmCasualId(int id) {
        boolean hasId = false;
        ResultSet rs = null; // to store data fetched from database
                
        try {
            // confirm casual id if in database
            confirmCasualID.setInt(1, id);
            rs = confirmCasualID.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    hasId = true; // return id in database                  
                } while(rs.next());                
            } else {                               
                hasId = false; // return id not in database
            } 
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );
        } 
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        } 
        
        return hasId;
    } // end function confirmCasualId
    
    /* function confirms if tool SN in database */
    public boolean confirmToolSN(int toolSN) {
        boolean hasToolSN = false;
        ResultSet rs = null; // to store data fetched from database
                
        try {
            // confirm tool SN if in database
            confirmToolSN.setInt(1, toolSN);
            rs = confirmToolSN.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    hasToolSN = true; // return SN in database                  
                } while(rs.next());                
            } else {                               
                hasToolSN = false; // return SN not in database
            } 
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );
        } 
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        } 
        
        return hasToolSN;
    } // end function confirmToolSN
    
    /* function for closing database connection */
    public void closeDB() {     
        try {
            // close database connection 
            connection.close();
            
        } catch(SQLException e) {
            // display close connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle- Database Error", JOptionPane.ERROR_MESSAGE );          
        }       
    } // end method closeDB
       
    
    
} // end class Database
