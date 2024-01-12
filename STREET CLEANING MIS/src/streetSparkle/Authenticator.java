/* 
 * @author John Felix
 *
 * This class performs authentication for various users.
 * Based on inputs at various textfields, it displays messages on panes and labels.
 * On successfull authentication, it loads UIs based on the user title in the company
 */
package streetSparkle;

import java.sql.*;
import java.time.format.DateTimeParseException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Authenticator extends Database {  
    /* attributes declarations */   
    private String password; // holds the staff password
    private String username; // holds the staff username
    // static ensures access by all class objects
    private static boolean hasUsername = false; // to authenticate staff username
    private static boolean hasPassword = false; // to authenticate password
    private static String staffSN; // holds staff primary key
    private static int staffId; // holds the staff national Id
    private static String title; // to authorize various UI
    private static String county; // holds the authenticated Staff county
    private static String status; // holds the staff status "working/terminated"
    
    // class constructor
    public Authenticator() {
    }

    public static boolean isHasUsername() {
        return hasUsername;
    }

    public static void setHasUsername(boolean aHasUsername) {
        hasUsername = aHasUsername;
    }

    public static boolean isHasPassword() {
        return hasPassword;
    }

    public static void setHasPassword(boolean aHasPassword) {
        hasPassword = aHasPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public static String getStaffSN() {
        return staffSN;
    }

    public static void setStaffSN(String aStaffSN) {
        staffSN = aStaffSN;
    }
    
    public static int getStaffId() {
        return staffId;
    }

    public static void setStaffId(int aStaffId) {
        staffId = aStaffId;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String aTitle) {
        title = aTitle;
    }

    public static String getCounty() {
        return county;
    }

    public static void setCounty(String aCounty) {
        county = aCounty;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String aStatus) {
        status = aStatus;
    }
    
    /* 
    *  function manages all authentication.
    *  called in AuthenticationPanel
    */
    public void loginHandler(String username, String password, JLabel passwordJL, JTextField usernameTF, JPasswordField passwordTF) {
        
        checkUsername(username); // function call to authenticate username
        checkPassword(password); // function call to authenticate password
        // show error message if both username & password false
        if(isHasUsername() == false && isHasPassword() == false){
           JOptionPane.showMessageDialog( null, "Access Denied!\nConsult your Administrator.", 
                   "Street Sparkle- Authentication Error", JOptionPane.ERROR_MESSAGE );
        }
        // show incorrect message label if password false
        else if(isHasUsername() == true && isHasPassword() == false) {
            passwordJL.setText("Incorrect password");
            passwordJL.setVisible(true);  
            JOptionPane.showMessageDialog( null, "Incorrect password","Street Sparkle- Authentication Error", JOptionPane.WARNING_MESSAGE );
        }
        // fetch user details and load UI
        else if(isHasUsername() == true && isHasPassword() == true) {
            // function call to fetch authenticated user's details
            fetchDetails(getUsername(), getPassword());           
            // clear text fields for new input
            usernameTF.setText("");
            passwordTF.setText(""); 
        }   
    } // end function loginHandler
    
    /* 
    *  function manages loading UIs after authentication.
    *  called in MainFrameUI
    */
    public void loginHandler() {
        if(isHasUsername() == true && isHasPassword() == true) {
            loadUI();           
        } 
    } // end overloaded function loginHandler
    
    /* function authenticates staff username against database */
    public void checkUsername(String username) {
        ResultSet rs; // to store data fetched from database
        
        try {
            // execute query fetchUsername from class Database
            super.confirmStaffUsername.setString(1, username);
            rs = super.confirmStaffUsername.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    setUsername(rs.getString(1)); // set confirmed username from database
                    setHasUsername(true);                     
                } while(rs.next());
                
            } else {
                setHasUsername(false);
            }           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        }
    } // end function checkUsername
    
    /* function authenticates staff password against database */
    public void checkPassword(String password) {
        ResultSet rs; // to store data fetched from database
        
        try {
            // execute query fetchUsername from class Database
            super.confirmStaffPassword.setString(1, password);
            rs = super.confirmStaffPassword.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    setPassword(rs.getString(1)); // set confirmed password from database
                    setHasPassword(true);                     
                } while(rs.next());
                
            } else {
                setHasPassword(false);
            }           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        }
    } // end function checkPassword
    
    /* function fetches details of authenticated user */
    public void fetchDetails(String username, String password) {
        ResultSet rs; // to store data fetched from database
        
        try {
            // execute query fetchStaffLogin from class Database
            super.fetchStaffLogin.setString(1, username);
            super.fetchStaffLogin.setString(2, password);
            rs = super.fetchStaffLogin.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    setStaffSN(rs.getString(1)); // set confirmed staff SN from database
                    setStaffId(rs.getInt(2)); // set confirmed staffId from database
                    setCounty(rs.getString(3)); // set confirmed county from database
                    setTitle(rs.getString(4)); // set confirmed title from database
                    setStatus(rs.getString(5)); // set confirmed status  from database
                } while(rs.next());
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        }
    } // end function fetchDetails
    
    /* function loads various UI based on User Title */
    public void loadUI() {
        // check status for working or terminated
        switch (getStatus()) {
            case "ACTIVE" -> {// load UIs based on user's title                   
                switch (getTitle()) {
                    case "SUPERVISOR" -> // Create and display the form
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new SupervisorUI().setVisible(true);
                            }
                        });
                    case "HUMAN RESOURCE" -> // Create and display the form
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new HumanResourceUI().setVisible(true);
                            }
                        });
                    case "SALES & FINANCE" -> // Create and display the form
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new SalesUI().setVisible(true);
                            }
                        });
                    case "TOOLS MANAGER" -> // Create and display the form
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new ToolsManagementUI().setVisible(true);
                            }
                        });
                    case "ADMIN" -> // Create and display the form 
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new AdminUI().setVisible(true);
                            }
                        });
                    default -> {
                    }
                } // end inner switch                     
            } // end case "active"   
            case "TERMINATED" -> // display error message
                JOptionPane.showMessageDialog( null, "Access Denied!\nUser Terminated!\nConsult your Administrator.", 
                                        "Street Sparkle- Authentication Error", JOptionPane.ERROR_MESSAGE );
            default -> {
            }
        } // end outer switch      
    } // end function loadUI
    
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
    
    /* function concatanates two strings */
    public static String concatenate(String a, String b) {
        return a.concat(b);
    }
    
} // end class Authenticator
