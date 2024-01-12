/* 
 * @author John Felix 
 *
 * This class performs authentication for various users.
 * Based on inputs at various textfields, it displays messages on panes and labels.
 * On successfull authentication, it loads UIs based on the user title in the company
 */
package myDuka;

import java.sql.*;
import java.time.format.DateTimeParseException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Authenticator extends Database {
    
    /* class attributes declarations */
    private boolean hasStaffId = false; // to authenticate staff username
    private boolean hasPassword = false; // to authenticate password
    private static String staffUsername; // holds the authenticated Staff username...static ensures access by all class objects
    private String password; // holds the staff password
    private static int staffId; // holds the staff national Id
    private static String title; // to authorize various UI
    
    /* class constructor */
    public Authenticator() {

    }

    public boolean isHasStaffId() {
        return hasStaffId;
    }

    public void setHasStaffId(boolean hasStaffId) {
        this.hasStaffId = hasStaffId;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public static String getStaffUsername() {
        return staffUsername;
    }

    public static void setStaffUsername(String staffUsername) {
        Authenticator.staffUsername = staffUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static int getStaffId() {
        return staffId;
    }

    public static void setStaffId(int staffId) {
        Authenticator.staffId = staffId;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Authenticator.title = title;
    }

    
    /* method authenticates staffId against database */
    public void checkId(String staffId) {
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // execute query fetchUsername from class DB
            super.fetchUsername.setString(1, staffId);
            rs = super.fetchUsername.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    setStaffUsername(rs.getString(1)); // set confirmed staffId/username from database
                    setHasStaffId(true);                     
                } while(rs.next());
                
            } else {
                setHasStaffId(false);
            }           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyDuka- Database Error", JOptionPane.ERROR_MESSAGE );             
        }
        // method called in staffIdJTF on FocusOut. Remember try..catch and Interger.parseInt() after if(!isBlank()) 
    } // end method checkId
    
    /* method authenticates password against database */
    public void checkPassword(String password) {
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // execute query fetchSignIn from class DB
            super.fetchSignIn.setString(1, getStaffUsername());
            super.fetchSignIn.setString(2, password);
            rs = super.fetchSignIn.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    setTitle(rs.getString(3)); // set confirmed staff's title from database 
                    setStaffId(rs.getInt(5)); // set confirmed staff's Id from database
                    setHasPassword(true);                     
                } while(rs.next());
                
            } else {
                setHasPassword(false);
            }           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyDuka- Database Error", JOptionPane.ERROR_MESSAGE );             
        }
    } // end method checkPassword
    
    /* method loads various UI based on User Title */
    public void loadUI(String username, String password, JLabel passwordJL, JTextField usernameTF, JPasswordField passwordTF) {
        
        checkId(username); // function call to authenticate username
        checkPassword(password); // function call to authenticate password
        // show error message if both staffId & password false
        if(isHasStaffId() == false && isHasPassword() == false){
           JOptionPane.showMessageDialog( null, "Access Denied!\nConsult your Administrator.", 
                   "MyDuka- Authentication Error", JOptionPane.ERROR_MESSAGE );
        }
        // show incorrect message label if password false
        else if(isHasStaffId() == true && isHasPassword() == false) {
            passwordJL.setVisible(true);
            passwordJL.setText("Incorrect password");
            // set its outline to red           
        }
        // show success message and load UI
        else if(isHasStaffId() == true && isHasPassword() == true) {
            // load StaffUI for general staff
            if(getTitle().equals("SENIOR")) {
                new StaffUI().setVisible(true);
                // clear text fields for new input
                usernameTF.setText("");
                passwordTF.setText("");
            }
            // load AdminUI for manager/admin
            else if(getTitle().equals("MANAGER")) {
                new AdminUI().setVisible(true);
                // clear text fields for new input
                usernameTF.setText("");
                passwordTF.setText("");
            }
        }   
    } // end method loadUI
    
    /* function validates drug measurement value */
    public static boolean validateBuyQuantity(String buyQuntity) {
        return buyQuntity.matches("\\d{1,2}");
    } 
    
    /* function validates mobile number value */
    public static boolean validateMobileNo(String mobileNo) {
        return mobileNo.matches("\\d{10}");
    }
    
    /* function validates ID value */
    public static boolean validateID(String ID) {
        return ID.matches("\\d{8}");
    }
    
    /* function validates email value */
    public static boolean validateEmail(String email) {
        return email.matches("([a-z]+\\d*@[a-z]+.[a-z]+)|([a-z]+\\d*@[a-z]+.[a-z]+.[a-z]+.[a-z]+)");
    }
    
    /* function validates date value */
    public static boolean validateDate(String date) throws DateTimeParseException {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    /* function validates barcode value */
    public static boolean validateBarcode(String barcode) {
        return barcode.matches("\\d{13}");
    }
    
    /* function concatanates two strings */
    public static String concatenate(String a, String b) {
        return a.concat(b);
    }
    
} // end class Authenticator
