/*
 * @author John Felix
 *
 * class identifies attributes and methods for management of casual workers
 */
package streetSparkle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class Casuals extends Database {
    
    public Projects aProject; // class obj
    private List<Casuals> casualList1; // list for storing casuals
    private List<Casuals> casualList2; // list for storing casuals
    
    private String staffId;
    private String casualId;
    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private int ID;
    private int phone;
    private String email;
    private String address;
    private String county;
    private String birthDate;
    private float wage;
    private int workDays;
    private String paymentStatus;
    private String hireDate;
    private String status;
    
    /* empty constructor */
    public Casuals() {
        casualList1 = new ArrayList<>(); // List initialization for storing casual obj
        casualList2 = new ArrayList<>(); // List initialization for storing casual obj
        aProject = new Projects();
    }

    /* constructor for personal details1 */
    public Casuals(String casualId, String firstName, String lastName, String otherName, String gender, int ID, int phone, 
            String county, int workDays, String status) {
        this.casualId = casualId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.gender = gender;
        this.ID = ID;
        this.phone = phone;
        this.county = county;
        this.workDays = workDays;
        this.status = status;
        casualList1 = new ArrayList<>(); // List initialization for storing casual obj
        casualList2 = new ArrayList<>(); // List initialization for storing casual obj
        aProject = new Projects();
    }

    /* constructor for personal details2 */
    public Casuals(String casualId, String firstName, String lastName, String otherName, String gender, int ID, int phone, 
            String email, String county, String staffId, String hireDate, String status) {
        this.casualId = casualId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.gender = gender;
        this.ID = ID;
        this.phone = phone;
        this.email = email;
        this.county = county;
        this.staffId = staffId;
        this.hireDate = hireDate;
        this.status = status;
        casualList1 = new ArrayList<>(); // List initialization for storing casual obj
        casualList2 = new ArrayList<>(); // List initialization for storing casual obj
        aProject = new Projects();
    }
    
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCasualId() {
        return casualId;
    }

    public void setCasualId() {
        // set casualId to epoch time in millisec
        Calendar cal = Calendar.getInstance();
        long msec = cal.getTimeInMillis();
        this.casualId = "" + msec;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    public int getWorkDays() {
        return workDays;
    }

    public void setWorkDays(int workDays) {
        this.workDays = workDays;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public List<Casuals> getCasualList1() {
        return casualList1;
    }

    public void setCasualList1(List<Casuals> casualList) {
        this.casualList1 = casualList;
    }
    
    public List<Casuals> getCasualList2() {
        return casualList2;
    }

    public void setCasualList2(List<Casuals> casualList) {
        this.casualList2 = casualList;
    }
    
    /* function loads casual worker details registered for specified project, into JTable for selection...called in SUPattendancePanel */
    public void fetchAttendanceCasuals(int projectId, JTable table) {
        Casuals aCasual; // class obj
        ResultSet rs = null;
        
        try {
            fetchAprojectStaff.setInt(1, projectId);
            // execute query fetchAprojectStaff from class Database
            rs = super.fetchAprojectStaff.executeQuery();           
          
            while(rs.next()) {
                // initialize Casuals obj into casualList from database  
                casualList1.add( new Casuals(
                                        rs.getString(3), // get casualId
                                        rs.getString(4), // get fname
                                        rs.getString(5), // get lname
                                        rs.getString(6), // get oname
                                        rs.getString(7), // get gender
                                        rs.getInt(8), // get id
                                        rs.getInt(9), // get phone
                                        rs.getString(10), // get county
                                        rs.getInt(11), // get workDays
                                        rs.getString(13) // get status
                                        ));
                // section displays confirmed item from casualList into JTable 
                aCasual = casualList1.get( casualList1.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aCasual.getFirstName(),
                                             aCasual.getLastName(),
                                             aCasual.getOtherName(),
                                             aCasual.getGender(),
                                             aCasual.getID(),
                                             aCasual.getPhone(), 
                                             aCasual.getWorkDays() } ); // end addRow                                             
            } // end while
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        }  
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        } 
    } // end function fetchAttendanceCasuals
    
    /* function records attendance in batch into database...called in SUPattendancePanel */
    public void recordAttendance(JTable projectTable, JTable casualsTable, JButton saveButton) {
        Casuals aCasual; // class obj
        int selectedItems[]; // array of selection indices from table
        int projectId; // stores selected project id from projectTable
        String staffSN = Authenticator.getStaffSN(); // get staff SN from login details

        // confirm items selected from table
        if(projectTable.getSelectionModel().isSelectionEmpty() == true) {
            // displaying of NULL items selected error message 
            JOptionPane.showMessageDialog( null, "Select Project from Table!","Attendance Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        } else if(casualsTable.getSelectionModel().isSelectionEmpty() == true) {
            // displaying of NULL items selected error message 
            JOptionPane.showMessageDialog( null, "Select Casual Worker(s) from Table!","Attendance Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );
        } 
        else {
            // get selected project id from table
            aProject.fetchAttendanceProjects(staffSN,projectTable);
            projectId = aProject.selectedProjectDetails(projectTable);
            // initializing selectedItems[] with selected rows from table
            selectedItems = casualsTable.getSelectedRows();
            // loop over selected items and store to database plus calculate balance amount
            for( int i = 0; i < selectedItems.length; i ++ ) {               
                selectedItems[i] = casualsTable.convertRowIndexToModel( selectedItems[i] );
                // initialization of customer object
                 aCasual = casualList1.get( selectedItems[i] );
                // record attendance to database
                super.addAttendance(projectId, staffSN , aCasual.getCasualId());  
                // update the casual's worked days for payment
                super.updateCasualWorkDays(aCasual.getID());
            }
            // display success message
            JOptionPane.showMessageDialog( null, "Casual workers attendance added successfully!", "Add New Casual Attendance Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE );
            saveButton.setEnabled(false); // disable button till new input
        }
    } // end function recordAttendance
    
    /* function loads casual workers details for staffing a project, into Jtable...called in SUPprojectsPanel */
    public void fetchStaffingCasuals(String county, JTable table) {
        Casuals aCasual; // class obj
        ResultSet rs = null;
        
        try {
            fetchCasualStatus.setString(1, county);
            // execute query fetchCasualStatus from class Database
            rs = super.fetchCasualStatus.executeQuery();           
    
            while(rs.next()) {
                // initialize Casuals obj into casualList from database 
                casualList2.add( new Casuals(
                                        rs.getString(1), // get casualId
                                        rs.getString(2), // get fname
                                        rs.getString(3), // get lname
                                        rs.getString(4), // get oname
                                        rs.getString(5), // get gender
                                        rs.getInt(6), // get id
                                        rs.getInt(7), // get phone
                                        rs.getString(8), // get email
                                        rs.getString(9), // get county
                                        rs.getString(10), // get staffId
                                        rs.getString(11), // get hireDate
                                        rs.getString(12) // get status
                                        ));
                // section displays confirmed item from casualList into JTable 
                aCasual = casualList2.get( casualList2.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aCasual.getFirstName(),
                                             aCasual.getLastName(),
                                             aCasual.getOtherName(),
                                             aCasual.getGender(),
                                             aCasual.getID(),
                                             aCasual.getPhone(), 
                                             aCasual.getCounty(), 
                                             aCasual.getHireDate(), 
                                             aCasual.getStatus() } ); // end addRow                                             
            } // end while
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        }  
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        } 
    } // end function fetchStaffingCasuals
    
    /* function records selected casual workers for a project in batch into database...called in SUPprojectsPanel */
    public void recordStaffingCasuals(JTable projectTable, JTable casualsTable, JButton saveButton) {
        Casuals aCasual; // class obj
        int selectedItems[]; // array of selection indices from table
        int projectId; // stores selected project id from projectTable
        String staffSN = Authenticator.getStaffSN(); // get staff SN from login details

        // confirm items selected from table
        if(projectTable.getSelectionModel().isSelectionEmpty() == true) {
            // displaying of NULL items selected error message 
            JOptionPane.showMessageDialog( null, "Select Project from Table!","Project Staffing Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        } else if(casualsTable.getSelectionModel().isSelectionEmpty() == true) {
            // displaying of NULL items selected error message 
            JOptionPane.showMessageDialog( null, "Select Casual Worker(s) from Table!","Project Staffing Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );
        } 
        else {
            // get selected project id from table
            aProject.fetchAttendanceProjects(staffSN,projectTable);
            projectId = aProject.selectedProjectDetails(projectTable);
            // initializing selectedItems[] with selected rows from table
            selectedItems = casualsTable.getSelectedRows();
            // loop over selected items and store to database plus calculate balance amount
            for( int i = 0; i < selectedItems.length; i ++ ) {               
                selectedItems[i] = casualsTable.convertRowIndexToModel( selectedItems[i] );
                // initialization of customer object
                 aCasual = casualList2.get( selectedItems[i] );
                // record attendance to database and update status to working
                super.addAprojectStaff(projectId, staffSN , aCasual.getCasualId()); 
                super.updateCasualStatus("ACTIVE", aCasual.getID());
            }
            // display success message
            JOptionPane.showMessageDialog( null, "Project Casual workers added successfully!", "Project Staffing Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE );
            saveButton.setEnabled(false); // disable button till new input
        }
    } // end function recordStaffingCasuals
    
    /* function selects all table rows */
    public void selectRows(JTable table) {
        int rows = table.getRowCount(); // get rows in table
        ListSelectionModel model = table.getSelectionModel();
        model.clearSelection();
        // loop through, selecting rows
        for(int row = 0; row < rows; row++) {
            model.addSelectionInterval(row, row);
        }       
    } // end function selectRows  
    
        
} // end class Casuals
