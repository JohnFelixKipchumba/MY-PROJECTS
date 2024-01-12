/*
 * @author John Felix
 *
 * class identifies attributes and methods operable on an organization staff
 */
package streetSparkle;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Staff extends Database {
    
    private List<Staff> staffList; // List declaration for storing Staff objs 
    
    private String staffId;
    private String staffIdEmpty;
    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private int ID;
    private int phone;
    private String email;
    private String county;
    private String birthDate;
    private float salary;
    private float perks;
    private String employedDate;
    private String title;
    private String status;
    private String profilePic;

    /* no arguments constructor */
    public Staff() {
        staffList = new ArrayList<>(); // List initialization for storing Staff objs
    }
    
    /* constructor for personal details */
    public Staff(String staffId,String firstName, String lastName, String otherName, String gender, int ID, int phone, String email, String county, String title) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.gender = gender;
        this.ID = ID;
        this.phone = phone;
        this.email = email;
        this.county = county;
        this.title = title;
        staffList = new ArrayList<>(); // List initialization for storing Staff objs
    }
    
    /* constructor for financial details */
    public Staff(String staffId, int ID, String birthDate, float salary, float perks, String employedDate, String title, String status) {
        this.staffId = staffId;
        this.ID = ID;
        this.birthDate = birthDate;
        this.salary = salary;
        this.perks = perks;
        this.employedDate = employedDate;
        this.title = title;
        this.status = status;
        staffList = new ArrayList<>(); // List initialization for storing Staff objs
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId() {
        // set staffId to epoch time in millisec
        Calendar cal = Calendar.getInstance();
        long msec = cal.getTimeInMillis();
        this.staffId = "" + msec;
    }
    
    public String getStaffIdEmpty() {
        return staffIdEmpty;
    }

    public void setStaffIdEmpty(String staffId) {
        this.staffIdEmpty = staffId;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getPerks() {
        return perks;
    }

    public void setPerks(float perks) {
        this.perks = perks;
    }

    public String getEmployedDate() {
        return employedDate;
    }

    public void setEmployedDate(String employedDate) {
        this.employedDate = employedDate;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }    
        
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    /* function loads for display staff details into JLabels...called by UserProfilePanel */
    public void displayDetails(int staffID,JLabel staffSNL,JLabel nameL,JLabel genderL,JLabel IdL,JLabel dobL,JLabel phoneL,
            JLabel emailL,JLabel addressL,JLabel countyL,JLabel employedL,JLabel titleL,JLabel salaryL,JLabel perksL) {
        
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // execute query fetchAStaffDetails from class Database
            super.fetchAStaffDetails.setInt(1, staffID);
            rs = super.fetchAStaffDetails.executeQuery();
            
            while(rs.next()) {
                staffSNL.setText(rs.getString(1)); // get staffId from db
                nameL.setText(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));  // get staff names from db      
                genderL.setText(rs.getString(5)); // get staff gender from db       
                IdL.setText("" + rs.getInt(6));  // get staff national Id from db      
                dobL.setText("" + rs.getDate(7)); // get staff birth date from db       
                phoneL.setText("" + rs.getInt(8));  // get staff phone form db      
                emailL.setText(rs.getString(9)); // get staff email from db
                addressL.setText(rs.getString(10)); // get staff address from db       
                countyL.setText(rs.getString(11));  // get staff county from db                            
                titleL.setText(rs.getString(12));  // get staff title form db      
                salaryL.setText("" + rs.getFloat(16)); // get staff salary form db      
                perksL.setText("" + rs.getFloat(17)); // get staff perks from db
                employedL.setText("" + rs.getDate(13)); // get staff employment date form db
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
    } // end function displayDetails
    
    /* function loads for display staff profile pic into JLabel...called by UserProfilePanel */
    public void displayProfilePic(int staffID, JLabel profilePicLabel) {
        ResultSet rs = null; // to store data fetched from database
        byte buff[] = new byte[16777216]; // to store image of size 16 MB
        
        try {
            // execute query fetchAStaffProfilePic from class Database
            super.fetchAStaffProfilePic.setInt(1, staffID);
            rs = super.fetchAStaffProfilePic.executeQuery();
            
            while(rs.next()) {
                Blob aBlob = rs.getBlob(1);
                File newFile = new File("newImage.jpg");
                InputStream fis = aBlob.getBinaryStream();
                FileOutputStream fos = new FileOutputStream(newFile);
                
                for(int b = fis.read(buff); b != -1; b = fis.read(buff)) {
                    fos.write(buff, 0, b);
                }
                // display selected image file into profilepic jlabel
                BufferedImage bufferedImage = ImageIO.read(newFile);
                Image image = bufferedImage.getScaledInstance(170, 170, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(image);
                profilePicLabel.setIcon(icon);
                // clear buffers
                fis.close();
                fos.close();
            }                       
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        } catch (IOException ex) {
            // display warning message
            JOptionPane.showMessageDialog( null, "Problem accessing file!","Profile Warning - Street Sparkle", JOptionPane.WARNING_MESSAGE );
        } catch (IllegalArgumentException | NullPointerException e) {
            // do nothing             
        }  
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }        
    } // end function displayProfilePic 
    
    /* function loads staff details into JTable for selection...called by AdminAuthorizationPanel */
    public void fetchStaffsDetails(JTable table) {
        Staff astaff; // class obj
        ResultSet rs = null;
        
        try {
            // execute query fetchStaffDetails from class Database
            rs = super.fetchStaffDetails.executeQuery();           
            
            while(rs.next()) {
                // initialize Staff obj into staffList from database                  
                staffList.add( new Staff(
                                        rs.getString(1), // get staffId
                                        rs.getString(2), // get fname
                                        rs.getString(3), // get lname
                                        rs.getString(4), // get oname
                                        rs.getString(5), // get gender
                                        rs.getInt(6), // get Id
                                        rs.getInt(7), // get phone
                                        rs.getString(8), // get email
                                        rs.getString(9), // get county
                                        rs.getString(10) // get title
                                        ));
                // section displays confirmed item from staffList into JTable 
                astaff = staffList.get( staffList.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { astaff.getFirstName(),
                                             astaff.getLastName(),
                                             astaff.getOtherName(),
                                             astaff.getGender(),
                                             astaff.getID(),
                                             astaff.getPhone(),
                                             astaff.getEmail(),
                                             astaff.getCounty(),
                                             astaff.getTitle() } ); // end addRow 
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
    } // end function fetchStaffsDetails
    
    /* function returns selected staff ID from JTable...called by AdminAuthorizationPanel */ 
    public int selectedStaffDetails(JTable table) {
        Staff aStaff; // class obj
        int selectedItem; // selected index from table
        int Id = 0; // returned national id from table selection
        ResultSet rs = null; 
        
        // confirm item selected from table
        if( table.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select a Staff from Table!","Update Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        }
        else {
            // initializing selectedItems[] with selected rows from table
            selectedItem = table.getSelectedRow();              
            selectedItem = table.convertRowIndexToModel(selectedItem);
            // initialization of staff object
            aStaff = staffList.get(selectedItem);
            Id = aStaff.getID();    
        }
        return Id; // return select staff id
    } // end function selectedStaffDetails
    
    /* function loads supervisor details into JTable for selection...called by SalesProjectPanel */
    public void fetchSupervisorDetails(JTable table) {
        Staff astaff; // class obj
        ResultSet rs = null;
        
        try {
            // execute query fetchSupervisorDetails from class Database
            rs = super.fetchSupervisorDetails.executeQuery();           
            
            while(rs.next()) {
                // initialize Staff obj into staffList from database                  
                staffList.add( new Staff(
                                        rs.getString(1), // get staffId
                                        rs.getString(2), // get fname
                                        rs.getString(3), // get lname
                                        rs.getString(4), // get oname
                                        rs.getString(5), // get gender
                                        rs.getInt(6), // get Id
                                        rs.getInt(7), // get phone
                                        rs.getString(8), // get email
                                        rs.getString(9), // get county
                                        rs.getString(10) // get title
                                        ));
                // section displays confirmed item from staffList into JTable 
                astaff = staffList.get( staffList.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { astaff.getFirstName(),
                                             astaff.getLastName(),
                                             astaff.getOtherName(),
                                             astaff.getGender(),
                                             astaff.getPhone(),
                                             astaff.getEmail(),
                                             astaff.getCounty(),
                                             astaff.getTitle() } ); // end addRow 
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
    } // end function fetchSupervisorDetails
    
    /* function returns selected staff ID from JTable...called by SalesProjectPanel */
    public String selectedSupervisorDetails(JTable table) {
        Staff aStaff; // class obj
        int selectedItem; // selected index from table
        String staffSN = ""; // returned staffSN from table selection
        
        // confirm item selected from table
        if( table.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select a Staff from Table!","Update Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        }
        else {
            // initializing selectedItems[] with selected rows from table
            selectedItem = table.getSelectedRow();              
            selectedItem = table.convertRowIndexToModel(selectedItem);
            // initialization of staff object
            aStaff = staffList.get(selectedItem);
            staffSN = aStaff.getStaffId();    
        }
        return staffSN; // return select staffSN
    } // end function selectedSupervisorDetails
        
    
} // end class Staff
