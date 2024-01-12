/*
 * @author John Felix
 *
 * class identifies attributes and methods for managing projects
 */
package streetSparkle;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Projects extends Database {
    
    private List<Casuals> casualList; // list for storing casuals assigned to project
    private List<Projects> projectList; // list for storing project obj
    private List<Projects> projectList2; // list for storing project obj
    
    private int projectId;
    private String title;
    private String location;  
    private String county;
    private int staffNo; // # of casuals for project
    private String startDate;
    private String endDate;
    private int extend; // # of extension days 
    private String beforePic;
    private String afterPic;
    private String status;
    private String vehicle;

    
    /* empty constructor */
    public Projects() {
        projectList = new ArrayList<>(); // List initialization for storing project objs
        projectList2 = new ArrayList<>(); // List initialization for storing project objs
    }
    
    /* project initializer constructor */
    public Projects(int projectId, String title, String location, String county, int staffNo, String startDate, String endDate, int extend, String status) {
        this.projectId = projectId;
        this.title = title;
        this.location = location;
        this.county = county;
        this.staffNo = staffNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extend = extend;
        this.status = status;
        projectList = new ArrayList<>(); // List initialization for storing project objs
        projectList2 = new ArrayList<>(); // List initialization for storing project objs
    }

    /* project initializer constructor */
    public Projects(int projectId, String title, String location, String county, String startDate, String endDate, String beforePic, String afterPic, String status) {
        this.projectId = projectId;
        this.title = title;
        this.location = location;
        this.county = county;
        this.startDate = startDate;
        this.endDate = endDate;
        this.beforePic = beforePic;
        this.afterPic = afterPic;
        this.status = status;
        projectList = new ArrayList<>(); // List initialization for storing project objs
        projectList2 = new ArrayList<>(); // List initialization for storing project objs
    }
    
    public List<Casuals> getCasualList() {
        return casualList;
    }

    public void setCasualList(List<Casuals> casualList) {
        this.casualList = casualList;
    }
    
    public List<Projects> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Projects> projectList) {
        this.projectList = projectList;
    }

    public List<Projects> getProjectList2() {
        return projectList2;
    }

    public void setProjectList2(List<Projects> projectList) {
        this.projectList2 = projectList;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getExtend() {
        return extend;
    }

    public void setExtend(int extend) {
        this.extend = extend;
    }

    public String getBeforePic() {
        return beforePic;
    }

    public void setBeforePic(String beforePic) {
        this.beforePic = beforePic;
    }

    public String getAfterPic() {
        return afterPic;
    }

    public void setAfterPic(String afterPic) {
        this.afterPic = afterPic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
    
    /* function loads project details into JTable for selection...called in SUPattendancePanel */
    public void fetchAttendanceProjects(String staffSN, JTable table) {
        Projects aProject; // class obj
        ResultSet rs = null;
        
        try {
            super.fetchAProject.setString(1, staffSN);
            // execute query fetchAProject from class Database
            rs = super.fetchAProject.executeQuery();           
            
            while(rs.next()) {
                // initialize Projects obj into projectList from database 
                projectList.add( new Projects(
                                        rs.getInt(1), // get projectId
                                        rs.getString(2), // get title
                                        rs.getString(3), // get location
                                        rs.getString(4), // get county
                                        rs.getInt(9), // get staff no.
                                        rs.getString(5), // get startDate
                                        rs.getString(6), // get endDate
                                        rs.getInt(7), // get extend
                                        rs.getString(12) // get status
                                        ));
                // section displays confirmed item from projectList into JTable 
                aProject = projectList.get( projectList.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aProject.getProjectId(),
                                             aProject.getTitle(),
                                             aProject.getLocation(),
                                             aProject.getCounty() } ); // end addRow 
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
    } // end function fetchAttendanceProjects

    /* function returns selected projectId from JTable...called in SUPattendancePanel, SUPprojectsPanel, SalesProjectPanel */ 
    public int selectedProjectDetails(JTable table) {
        Projects aProject; // class obj
        int selectedItem; // selected index from table
        int projId = 0; // returned projectId from table selection
        
        // confirm item selected from table
        if( table.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select a Project from Table!","Casual attendance Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        }
        else {
            // initializing selectedItems[] with selected rows from table
            selectedItem = table.getSelectedRow();              
            selectedItem = table.convertRowIndexToModel(selectedItem);
            // initialization of project object
            aProject = projectList.get(selectedItem);
            projId = aProject.getProjectId();    
        }
        return projId; 
    } // end function selectedProjectDetails
    
    /* function loads project details into JTable for selection...called in SUPprojectsPanel */
    public void fetchStaffingProject(String staffSN, JTable table) {
        Projects aProject; // class obj
        ResultSet rs = null;
        
        try {
            super.fetchAProject.setString(1, staffSN);
            // execute query fetchAProject from class Database
            rs = super.fetchAProject.executeQuery();           
            
            while(rs.next()) {
                // initialize Projects obj into projectList from database    
                projectList.add( new Projects(
                                        rs.getInt(1), // get projectId
                                        rs.getString(2), // get title
                                        rs.getString(3), // get location
                                        rs.getString(4), // get county
                                        rs.getInt(9), // get staff no.
                                        rs.getString(5), // get startDate
                                        rs.getString(6), // get endDate
                                        rs.getInt(7), // get extend
                                        rs.getString(11) // get status
                                        ));
                // section displays confirmed item from projectList into JTable 
                aProject = projectList.get( projectList.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aProject.getTitle(),
                                             aProject.getLocation(),
                                             aProject.getCounty(),
                                             aProject.getStartDate(),
                                             aProject.getEndDate(),
                                             aProject.getStaffNo(),
                                             aProject.getStatus() } ); // end addRow 
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
    } // end of function fetchStaffingProject
    
    /* function loads project images details into JTable for selection...called in SUPprojectsPanel */
    public void fetchProjectImages(String staffSN, JTable table) {
        Projects aProject; // class obj
        ResultSet rs = null;
        
        try {
            super.fetchProjectPic.setString(1, staffSN);
            // execute query fetchProjectPic from class Database
            rs = super.fetchProjectPic.executeQuery(); 
         
            while(rs.next()) {
                // initialize Projects obj into projectList from database    
                projectList2.add( new Projects(
                                        rs.getInt(1), // get projectId
                                        rs.getString(3), // get title
                                        rs.getString(4), // get location
                                        rs.getString(5), // get county
                                        rs.getString(6), // get startDate
                                        rs.getString(7), // get endDate
                                        rs.getString(8), // get beforePic
                                        rs.getString(9), // get afterPic
                                        rs.getString(10) // get status
                                        ));
                // section displays confirmed item from projectList2 into JTable 
                aProject = projectList2.get( projectList2.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aProject.getTitle(),
                                             aProject.getLocation(),
                                             aProject.getCounty(),
                                             aProject.getStartDate(),
                                             aProject.getEndDate(),                                            
                                             aProject.getStatus(),
                                             aProject.getBeforePic(),
                                             aProject.getAfterPic()} ); // end addRow 
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
    } // end function fetchProjectImages
    
    /* function returns selected projectId from JTable...called in SUPprojectsPanel */ 
    public int selectedProjectImages(JTable table) {
        Projects aProject; // class obj
        int selectedItem; // selected index from table
        int projId; // returned projectId from table selection
 
        // initializing selectedItems[] with selected rows from table
        selectedItem = table.getSelectedRow();              
        selectedItem = table.convertRowIndexToModel(selectedItem);
        // initialization of project object
        aProject = projectList2.get(selectedItem);
        projId = aProject.getProjectId();    

        return projId; 
    } // end function selectedProjectImages
    
     /* function loads project details into JTable for selection on update...called in SalesProjectPanel */
    public void fetchUpdateProject(JTable table) {
        Projects aProject; // class obj
        ResultSet rs = null;
        
        try {
            // execute query fetchProjects from class Database
            rs = super.fetchProjects.executeQuery();           
            
            while(rs.next()) {
                // initialize Projects obj into projectList from database 
                projectList.add( new Projects(
                                        rs.getInt(1), // get projectId
                                        rs.getString(2), // get title
                                        rs.getString(4), // get location
                                        rs.getString(5), // get county
                                        rs.getInt(6), // get staff no.
                                        rs.getString(7), // get startDate
                                        rs.getString(8), // get endDate
                                        rs.getInt(9), // get extend
                                        rs.getString(12) // get status
                                        ));
                // section displays confirmed item from projectList into JTable 
                aProject = projectList.get( projectList.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aProject.getTitle(),
                                             aProject.getLocation(),
                                             aProject.getCounty(),
                                             aProject.getStartDate(),
                                             aProject.getEndDate(),
                                             aProject.getStaffNo(),
                                             aProject.getExtend(),
                                             aProject.getStatus() } ); // end addRow 
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
    } // end function fetchUpdateProject
    
    /* function loads before picture for selected project...called in SalesProjectPanel */
    public void loadBeforePic(int projId, JLabel picLabel) {
        ResultSet rs = null;
        byte buff[] = new byte[16777216]; // to store image of size 16 MB
        
        try {
            super.fetchProjectBeforePic.setInt(1, projId);
            // execute query fetchProjectBeforePic from class Database
            rs = super.fetchProjectBeforePic.executeQuery();
            
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
                Image image = bufferedImage.getScaledInstance(450, 300, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(image);
                picLabel.setIcon(icon);
                // clear buffers
                fis.close();
                fos.close();
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        } catch (IOException ex) {
            // display warning message
            JOptionPane.showMessageDialog( null, "Problem accessing file!","Projects Warning - Street Sparkle", JOptionPane.WARNING_MESSAGE );
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
    } // end function loadBeforePid
    
    /* function loads after picture for selected project...called in SalesProjectPanel */
    public void loadAfterPic(int projId, JLabel picLabel) {
        ResultSet rs = null;
        byte buff[] = new byte[16777216]; // to store image of size 16 MB
        
        try {
            super.fetchProjectAfterPic.setInt(1, projId);
            // execute query fetchProjectAfterPic from class Database
            rs = super.fetchProjectAfterPic.executeQuery();
            
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
                Image image = bufferedImage.getScaledInstance(450, 300, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(image);
                picLabel.setIcon(icon);
                // clear buffers
                fis.close();
                fos.close();
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
        } catch (IOException ex) {
            // display warning message
            JOptionPane.showMessageDialog( null, "Problem accessing file!","Projects Warning - Street Sparkle", JOptionPane.WARNING_MESSAGE );
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
    } // end function loadAfterPic
    
    
} // end class Projects
