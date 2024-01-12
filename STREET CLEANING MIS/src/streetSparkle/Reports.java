/*
 * @author John Felix
 *
 * class manages all reports/statistics details for organization
 */
package streetSparkle;

import java.awt.Color;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Reports extends Database {
   
    public Reports() {
    }
    
    /* funtion generates headers in a JTextPane...called in all report JTextpanes */
    public void generateReportHeaders(StyledDocument document,JTextPane textPane) {
        //StyledDocument document = HRreportsPanel.document; // class variable invokation 
        
        try {
            String appTitle = "STREET SPARKLE\n";
            String appDesc = "STREET CLEANING MANAGEMENT SYSTEM\n\n";
            // set text pane attributes                     
            textPane.setBackground(Color.white);
            textPane.setText("");
            // set app title attributes
            SimpleAttributeSet title = new SimpleAttributeSet();            
            StyleConstants.setAlignment(title, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontFamily(title, "Algerian");
            StyleConstants.setFontSize(title, 22);
            StyleConstants.setForeground(title, Color.black);
            StyleConstants.setBold(title, true);
            document.setParagraphAttributes(document.getLength(), appTitle.length(), title, true);
            document.insertString(document.getLength(), appTitle, title);
            // set app title attributes
            SimpleAttributeSet title2 = new SimpleAttributeSet();            
            StyleConstants.setAlignment(title2, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontFamily(title2, "Arial");
            StyleConstants.setFontSize(title2, 14);
            StyleConstants.setForeground(title2, Color.red);
            StyleConstants.setBold(title2, true);
            StyleConstants.setItalic(title2, true);
            document.setParagraphAttributes(document.getLength(), appDesc.length(), title2, true);
            document.insertString(document.getLength(), appDesc, title2);
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end function generateReportHeaders
    
 /********************************************************************************************************************************
  *      SECTION HANDLES HR INTERFACE REPORTS
     * @param document
     * @param textPane
  *******************************************************************************************************************************/
    
    /* function generates all staff details into JTextPane...called by staff details button */
    public void generateStaffDetailsReport(StyledDocument document,JTextPane textPane) {
        //StyledDocument document = HRreportsPanel.document; // class variable invokation 
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        String reportHeading = "ALL STAFF DETAILS REPORT\n";
        String rowHeaders = String.format("%-18s%-18s%-18s%-7s%-13s%-10s%-30s%-20s%-20s\n","First Name","Mid Name","Last Name",
                "Gender","ID","Phone","Email","County","Title");
        String rowHeaders2 = String.format("%-20s%-10s\n","Title","Count");
        String data1, data2, data3, data4, data5,data6, data7, data8, data9;  // variables store rs data
        String point1, point2; // variables store rs2 data
        String count; // variable store rs3 data
        
        try {
            // execute queries            
            rs = super.fetchStaffDetails.executeQuery();
            rs2 = super.fetchStaffTitleCount.executeQuery();
            rs3 = super.fetchStaffCount.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, Color.blue);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader, Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(2)); // get fname
                data2 = String.format("%s", rs.getString(3)); // get lname
                data3 = String.format("%s", rs.getString(4)); // get oname
                data4 = String.format("%s", rs.getString(5)); // get gender
                data5 = String.format("%d", rs.getInt(6)); // get id
                data6 = String.format("%d", rs.getInt(7)); // get phone
                data7 = String.format("%s", rs.getString(8)); // get email
                data8 = String.format("%s", rs.getString(9)); // get county
                data9 = String.format("%s", rs.getString(10)); // get title
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-18s%-18s%-18s%-7s%-13s%-10s%-30s%-20s%-20s\n", data1, data2, data3, data4, data5,data6, data7, data8, data9), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
            // set row header attribute   
            document.insertString(document.getLength(), "\n", rowData);
            SimpleAttributeSet rowHeader2 = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader2, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader2, 4);
            StyleConstants.setSpaceBelow(rowHeader2, 4);
            StyleConstants.setFontSize(rowHeader2, 12); 
            StyleConstants.setForeground(rowHeader2, Color.blue);
            StyleConstants.setBold(rowHeader2, true);
            StyleConstants.setItalic(rowHeader2, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader2, true);
            document.insertString(document.getLength(), rowHeaders2, rowHeader2);
            // set retrieved data attributes
            SimpleAttributeSet rowData2 = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData2, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData2, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData2, true);
            // get returned data from database
            while(rs2.next()) {
                point1 = String.format("%s", rs2.getString(1)); // get title
                point2 = String.format("%d", rs2.getInt(2)); // get count
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-20s%-10s\n",  point1,point2),rowData2); // end insertString                                                                                                                                    
            } // end while 
            // set retrieved data attributes
            SimpleAttributeSet rowData3 = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData3, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData3, 12);  
            StyleConstants.setForeground(rowData3, Color.red);
            document.setParagraphAttributes(document.getLength(), 0, rowData3, true);
            // get returned data from database
            while(rs3.next()) {
                count = String.format("%d", rs3.getInt(1)); // get count
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-20s%-10s\n","Total Staff No:",count),rowData3); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                rs2.close();
                rs3.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }  
    } // end function generateStaffDetailsReport
    
    /* function generates all casual workers details into JTextPane...called by casual details button */
    public void generateCasualDetailsReport(StyledDocument document,JTextPane textPane) {
        //StyledDocument document = HRreportsPanel.document; // class variable invokation 
        ResultSet rs = null;
        ResultSet rs2 = null;        
        String reportHeading = "ALL CASUALS' DETAILS REPORT\n";
        String rowHeaders = String.format("%-18s%-18s%-18s%-7s%-13s%-10s%-30s%-20s%-20s\n","First Name","Mid Name","Last Name",
                "Gender","ID","Phone","Email","County","Hire Date");
        String data1, data2, data3, data4, data5,data6, data7, data8, data9;  // variables store rs data
        String count; // variable store rs2 data
        
        try {
            // execute queries            
            rs = super.fetchCasualDetails.executeQuery();
            rs2 = super.fetchCasualCount.executeQuery();
                       
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, new Color(204,0,204));
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader, new Color(204,0,204));
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(2)); // get fname
                data2 = String.format("%s", rs.getString(3)); // get lname
                data3 = String.format("%s", rs.getString(4)); // get oname
                data4 = String.format("%s", rs.getString(5)); // get gender
                data5 = String.format("%d", rs.getInt(6)); // get id
                data6 = String.format("%d", rs.getInt(7)); // get phone
                data7 = String.format("%s", rs.getString(8)); // get email
                data8 = String.format("%s", rs.getString(9)); // get county
                data9 = String.format("%s", rs.getString(10)); // get hire date
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-18s%-18s%-18s%-7s%-13s%-10s%-30s%-20s%-20s\n", data1, data2, data3, data4, data5,data6, data7, data8, data9), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
            // set retrieved data attributes
            document.insertString(document.getLength(), "\n", rowData);
            SimpleAttributeSet rowData2 = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData2, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData2, 12);  
            StyleConstants.setForeground(rowData2, Color.red);
            document.setParagraphAttributes(document.getLength(), 0, rowData2, true);
            // get returned data from database
            while(rs2.next()) {
                count = String.format("%d", rs2.getInt(1)); // get count
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-20s%-10s\n","Total Casuals No:",count),rowData2); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                rs2.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }  
    } // end function generateCasualDetailsReport
    
    /* function generates A staff's details into JTextPane...called by a staff's details button */
    public void generateAStaffDetailsReport(StyledDocument document,int staffId,JTextPane textPane) {
        //StyledDocument document = HRreportsPanel.document; // class variable invokation 
        ResultSet rs = null;    
        String reportHeading = "A STAFF'S DETAILS REPORT\n";
        String rowHeader1 = String.format("%-20s%-18s%-18s%-18s%-7s%-13s%-10s%-30s%-20s\n","Staff SN","First Name","Mid Name","Last Name",
                "Gender","ID","Phone","Email","County");
        String rowHeader2 = String.format("%-20s%-30s%-20s%-15s%-15s%-20s\n","Birth Date","Address","Title","Hire Date",
                "Status","Termination Date");
        String data1="", data2="", data3="", data4="", data5="",data6="", data7="", data8="", data9="",data10="", data11="", data12="", data13="", data14="", data15="";  // variables store rs data
        
        try {
            // execute queries 
            super.fetchAStaffDetails.setInt(1, staffId);
            rs = super.fetchAStaffDetails.executeQuery();
            
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get staffId
                data2 = String.format("%s", rs.getString(2)); // get fname
                data3 = String.format("%s", rs.getString(3)); // get lname
                data4 = String.format("%s", rs.getString(4)); // get oname
                data5 = String.format("%s", rs.getString(5)); // get gender
                data6 = String.format("%d", rs.getInt(6)); // get id
                data7 = String.format("%s", rs.getString(7)); // get dob
                data8 = String.format("%d", rs.getInt(8)); // get phone
                data9 = String.format("%s", rs.getString(9)); // get email
                data10 = String.format("%s", rs.getString(10)); // get address
                data11 = String.format("%s", rs.getString(11)); // get county
                data12 = String.format("%s", rs.getString(12)); // get title
                data13 = String.format("%s", rs.getString(13)); // get employdate
                data14 = String.format("%s", rs.getString(14)); // get status
                data15 = String.format("%s", rs.getString(15)); // get terminationdate
            } // end while
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeader1.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeader1, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // display data in text pane
            document.insertString(document.getLength(),String.format("%-20s%-18s%-18s%-18s%-7s%-13s%-10s%-30s%-20s\n",
                    data1, data2, data3, data4, data5,data6, data8, data9, data11),rowData); // end insertString
            // display second headers
            document.insertString(document.getLength(), "\n\n", rowData);
            document.insertString(document.getLength(), rowHeader2, rowHeader);
            // display data in text pane
            document.insertString(document.getLength(),String.format("%-20s%-30s%-20s%-15s%-15s%-20s\n",
                    data7, data10, data12, data13, data14,data15),rowData); // end insertString
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        } 
    } // end function generateAStaffDetailsReport
    
    /* function generates A casual's details into JTextPane...called by a casual's details button */
    public void generateACasualDetailsReport(StyledDocument document,int casualId,JTextPane textPane) {
        //StyledDocument document = HRreportsPanel.document; // class variable invokation 
        ResultSet rs = null;        
        String reportHeading = "A CASUAL'S DETAILS REPORT\n";
        String data1="", data2="", data3="", data4="", data5="",data6="", data7="", data8="", data9="",data10="", data11="", data12="",
                data13="", data14="", data15="",data16="", data17="", data18="", data19="";  // variables store rs data
        
        try {
            // execute queries 
            super.fetchACasualDetails.setInt(1, casualId);
            rs = super.fetchACasualDetails.executeQuery();
            
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get casualId
                data2 = String.format("%s", rs.getString(2)); // get fname
                data3 = String.format("%s", rs.getString(3)); // get lname
                data4 = String.format("%s", rs.getString(4)); // get oname
                data5 = String.format("%s", rs.getString(5)); // get gender
                data6 = String.format("%d", rs.getInt(6)); // get id
                data7 = String.format("%s", rs.getString(7)); // get dob
                data8 = String.format("%d", rs.getInt(8)); // get phone
                data9 = String.format("%s", rs.getString(9)); // get email
                data10 = String.format("%s", rs.getString(10)); // get address
                data11 = String.format("%s", rs.getString(11)); // get county
                data12 = String.format("%.2f", rs.getFloat(12)); // get wage
                data13 = String.format("%s", rs.getString(13)); // get Staff id
                data14 = String.format("%s", rs.getString(14)); // get staff fname
                data15 = String.format("%s", rs.getString(15)); // get staff lname
                data16 = String.format("%s", rs.getString(16)); // get staff oname
                data17 = String.format("%s", rs.getString(17)); // get hire date
                data18 = String.format("%s", rs.getString(18)); // get status
                data19 = String.format("%s", rs.getString(19)); // get terminationdate
            } // end while
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,new Color(204,0,204));
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            document.insertString(document.getLength(), "\n\n", reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 14); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), 0, rowHeader, true);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 14); 
            StyleConstants.setForeground(rowData,Color.black);
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // display data in text pane
            document.insertString(document.getLength(),String.format("%-20s","Casual SN:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data1),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Casual Name:"),rowHeader);
            document.insertString(document.getLength(),String.format("%-20s%-20s%-20s\n",data2,data3,data4),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Gender:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data5),rowData);
            document.insertString(document.getLength(),String.format("%-20s","National Id:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data6),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Birth date:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data7),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Phone:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data8),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Email:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data9),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Address:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data10),rowData);
            document.insertString(document.getLength(),String.format("%-20s","County:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data11),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Wage:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data12),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Supervisor SN:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data13),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Supervisor name:"),rowHeader);
            document.insertString(document.getLength(),String.format("%-20s%-20s%-20s\n",data14,data15,data16),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Hire date:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data17),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Status:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data18),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Termination date:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data19),rowData);
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }   
    } // end function generateACasualDetailsReport
        
    
 /********************************************************************************************************************************
  *      SECTION HANDLES SALES INTERFACE REPORTS
     * @param document
     * @param textPane
  *******************************************************************************************************************************/  
    
    /* function generates all projects details into JTextPane...called by all projects JMenu */
    public void generateProjectDetailsReport(StyledDocument document,JTextPane textPane) {
        ResultSet rs = null;        
        String reportHeading = "ALL PROJECTS DETAILS REPORT\n";
        String rowHeaders = String.format("%-25s%-30s%-15s%-8s%-12s%-12s%-11s%-10s%-10s%-20s\n","Project Title","Project Location","County",
                "Staff #","Start Date","End Date","Extension","Status","Vehicle","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;  // variables store rs data
        
        try {
            // execute query 
            rs = super.fetchProjects.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(2)); // get title
                data2 = String.format("%s", rs.getString(4)); // get location
                data3 = String.format("%s", rs.getString(5)); // get county
                data4 = String.format("%d", rs.getInt(6)); // get staff no.
                data5 = String.format("%s", rs.getString(7)); // get start
                data6 = String.format("%s", rs.getString(8)); // get end
                data7 = String.format("%d", rs.getInt(9)); // get extend
                data8 = String.format("%s", rs.getString(12)); // get status
                data9 = String.format("%s", rs.getString(14)); // get vehicle
                data10 = String.format("%s", rs.getString(13)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-25s%-30s%-15s%-8s%-12s%-12s%-11s%-10s%-10s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8,data9, data10), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateProjectDetailsReport
    
    /* function generates all pending project details into JTextPane...called by pending proj details JMenu */
    public void generatePendProjDetailsReport(StyledDocument document,JTextPane textPane) {
        ResultSet rs = null;        
        String reportHeading = "ALL PENDING PROJECTS REPORT\n";
        String rowHeaders = String.format("%-12s%-25s%-30s%-15s%-15s%-15s%-13s%-20s\n","Project SN","Project Title","Project Location",
                "County","Start Date","End Date","Status","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8;  // variables store rs data
        
        try {
            // execute query 
            rs = super.fetchProjPending.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get projectId
                data2 = String.format("%s", rs.getString(2)); // get title
                data3 = String.format("%s", rs.getString(3)); // get location
                data4 = String.format("%s", rs.getString(4)); // get county
                data5 = String.format("%s", rs.getString(5)); // get start
                data6 = String.format("%s", rs.getString(6)); // get end
                data7 = String.format("%s", rs.getString(8)); // get status
                data8 = String.format("%s", rs.getString(9)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-12s%-25s%-30s%-15s%-15s%-15s%-13s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generatePendProjDetailsReport
    
    /* function generates all ongoing project details into JTextPane...called by ongoing proj details JMenu */
    public void generateOngoProjDetailsReport(StyledDocument document,JTextPane textPane) {
        ResultSet rs = null;        
        String reportHeading = "ALL ONGOING PROJECTS REPORT\n";
        String rowHeaders = String.format("%-8s%-25s%-30s%-15s%-13s%-13s%-10s%-10s%-13s%-20s\n","Proj-SN","Project Title","Project Location",
                "County","Start Date","End Date","Staff No.","Vehicle","Status","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;  // variables store rs data
        
        try {
            // execute query 
            rs = super.fetchProjOngoing.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get projectId
                data2 = String.format("%s", rs.getString(2)); // get title
                data3 = String.format("%s", rs.getString(3)); // get location
                data4 = String.format("%s", rs.getString(4)); // get county
                data5 = String.format("%s", rs.getString(5)); // get start
                data6 = String.format("%s", rs.getString(6)); // get end
                data7 = String.format("%s", rs.getString(8)); // get staffing no.
                data8 = String.format("%s", rs.getString(9)); // get vehicle
                data9 = String.format("%s", rs.getString(10)); // get status
                data10 = String.format("%s", rs.getString(11)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-8s%-25s%-30s%-15s%-13s%-13s%-10s%-10s%-13s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8,data9, data10), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateOngoProjDetailsReport
    
    /* function generates all complete project details into JTextPane...called by complete proj details JMenu */
    public void generateComplProjDetailsReport(StyledDocument document,JTextPane textPane) {
        ResultSet rs = null;        
        String reportHeading = "ALL COMPLETE PROJECTS REPORT\n";
        String rowHeaders = String.format("%-10s%-25s%-30s%-15s%-13s%-13s%-12s%-13s%-20s\n","Proj-SN","Project Title","Project Location",
                "County","Start Date","End Date","Extension","Status","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8, data9;  // variables store rs data
        
        try {
            // execute query 
            rs = super.fetchProjComplete.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get projectId
                data2 = String.format("%s", rs.getString(2)); // get title
                data3 = String.format("%s", rs.getString(3)); // get location
                data4 = String.format("%s", rs.getString(4)); // get county
                data5 = String.format("%s", rs.getString(5)); // get start
                data6 = String.format("%s", rs.getString(6)); // get end
                data7 = String.format("%d", rs.getInt(7)); // get extend
                data8 = String.format("%s", rs.getString(9)); // get status
                data9 = String.format("%s", rs.getString(10)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-10s%-25s%-30s%-15s%-13s%-13s%-12s%-13s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8,data9), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateComplProjDetailsReport
    
    /* function generates all clients details into JTextPane...called by all clients details JMenu */
    public void generateClientDetailsReport(StyledDocument document,JTextPane textPane) {
        ResultSet rs = null;        
        String reportHeading = "ALL CLIENTS DETAILS REPORT\n";
        String rowHeaders = String.format("%-14s%-14s%-12s%-27s%-20s%-15s%-30s%-20s\n","First Name","Mid Name","Phone",
                "Email","Address","County","Enterprise","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8;  // variables store rs data
        
        try {
            // execute query 
            rs = super.fetchClientDetails.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,new Color(204,0,204));
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,new Color(204,0,204));
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(2)); // get fname
                data2 = String.format("%s", rs.getString(3)); // get midname
                data3 = String.format("%d", rs.getInt(5)); // get phone
                data4 = String.format("%s", rs.getString(6)); // get email
                data5 = String.format("%s", rs.getString(7)); // get address
                data6 = String.format("%s", rs.getString(8)); // get county
                data7 = String.format("%s", rs.getString(9)); // get enterprise
                data8 = String.format("%s", rs.getString(10)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-14s%-14s%-12s%-27s%-20s%-15s%-30s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateClientDetailsReport
    
    /* function generates all suppliers details into JTextPane...called by all suppliers details JMenu */
    public void generateSupplierDetailsReport(StyledDocument document,JTextPane textPane) {
        ResultSet rs = null; 
        int counter = 1; // variable for serializing rows
        String reportHeading = "ALL SUPPLIERS DETAILS REPORT\n\n";
        String data1, data2, data3, data4, data5, data6, data7, data8;  // variables store rs data
        
        try {
            // execute query 
            rs = super.fetchSupplierDetails.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,new Color(204,0,204));
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), 0, rowHeader, true);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12); 
            StyleConstants.setForeground(rowData, Color.black);
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // set retrieved data attributes
            SimpleAttributeSet rowData2 = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData2, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData2, 12);  
            StyleConstants.setForeground(rowData2, Color.red);
            document.setParagraphAttributes(document.getLength(), 0, rowData2, true);
            // set retrieved data attributes
            SimpleAttributeSet rowData3 = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData3, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData3, 14);  
            StyleConstants.setForeground(rowData3, Color.black);
            StyleConstants.setBackground(rowData3, Color.yellow);
            document.setParagraphAttributes(document.getLength(), 0, rowData3, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(2)); // get companyTitle
                data2 = String.format("%d", rs.getInt(3)); // get phone
                data3 = String.format("%s", rs.getString(4)); // get email
                data4 = String.format("%s", rs.getString(5)); // get address
                data5 = String.format("%s", rs.getString(6)); // get county
                data6 = String.format("%s", rs.getString(7)); // get registerDate
                data7 = String.format("%s", rs.getString(8)); // get terminateDate
                data8 = String.format("%s", rs.getString(9)); // get Procucts
                // display data in text pane
                document.insertString(document.getLength(),String.format("%-12s%d%-2s","ENTERPRISE",counter,":"),rowData2);
                document.insertString(document.getLength(),String.format("%s\n",data1),rowData3);
                document.insertString(document.getLength(), 
                        String.format("%-13s%-40s%-40s%-20s\n", "Phone", "Email", "Address","County" ),
                        rowHeader); // end insertString 
                document.insertString(document.getLength(), 
                        String.format("%-13s%-40s%-40s%-20s\n",data2, data3,data4, data5),rowData); // end insertString    
                document.insertString(document.getLength(), 
                        String.format("%-15s%-15s%s\n", "Registered", "Termination", "Products"),
                        rowHeader); // end insertString 
                document.insertString(document.getLength(), 
                        String.format("%-15s%-15s%s\n\n",data6, data7,data8),rowData); // end insertString 
                ++counter; // increment counter
            } // end while
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateSupplierDetailsReport
    
 /********************************************************************************************************************************
  *      SECTION HANDLES SUPERVISOR INTERFACE REPORTS
     * @param document
     * @param textPane
     * @param staffId
     * @param status
  *******************************************************************************************************************************/ 

    /* function  generateCasualDetailsReport(JTextPane textPane) of HRreportsPanel...called by Casual details JMenu */
    
    /* function  generateACasualDetailsReport(int casualId,JTextPane textPane) of HRreportsPanel...called by Casual details JMenu */
    
    /* function generates casuals attendance details into JTextPane...called by attendance details JMenu */
    public void generateAttendanceDetailsReport(StyledDocument document,JTextPane textPane,String staffId,String status) {
        ResultSet rs = null;        
        String reportHeading = "";
        String rowHeaders = String.format("%-25s%-30s%-10s%-18s%-18s%-15s%-20s\n","Project Title","Project Location","Status",
                "First Name","Mid Name","Days Worked","Timestamp");
        String data1, data2, data3, data4="", data5="",data6="", data7, data8, data9,data10;  // variables store rs data
        
        // set report heading according to status choice
        if(status.equals("ONGOING")) {
            reportHeading = "CASUAL ATTENDANCE FOR ONGOING PROJECT(S)\n";
        } else if(status.equals("COMPLETE")) {
            reportHeading = "CASUAL ATTENDANCE FOR COMPLETED PROJECT(S)\n";
        }
        
        try {
            // execute queries 
            super.fetchCasualAttendance.setString(1, staffId);
            super.fetchCasualAttendance.setString(2, status);
            rs = super.fetchCasualAttendance.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader, Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get title
                data2 = String.format("%s", rs.getString(2)); // get location
                data3 = String.format("%s", rs.getString(3)); // get status
                data4 = String.format("%s", rs.getString(4)); // get staffId
                data5 = String.format("%s", rs.getString(5)); // get staff fname
                data6 = String.format("%s", rs.getString(6)); // get staff lname
                data7 = String.format("%s", rs.getString(8)); // get casual fname
                data8 = String.format("%s", rs.getString(9)); // get casual lname
                data9 = String.format("%d", rs.getInt(11)); // get work days
                data10 = String.format("%s", rs.getString(12)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-25s%-30s%-10s%-18s%-18s%-15s%-20s\n", data1, data2, data3, data7, data8,data9,data10), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
            document.insertString(document.getLength(), "\n\n", reportTitle);
            document.insertString(document.getLength(),String.format("%-20s","Supervisor SN:"),rowHeader);
            document.insertString(document.getLength(),String.format("%s\n",data4),rowData);
            document.insertString(document.getLength(),String.format("%-20s","Supervisor name:"),rowHeader);
            document.insertString(document.getLength(),String.format("%-20s%-20s\n",data5,data6),rowData);
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateAttendanceDetailsReport
    
    /* function generates casuals payment details into JTextPane...called by payment details JMenu */
    public void generatePaymentDetailsReport(StyledDocument document,JTextPane textPane,String staffId,String status,String pay) {
        ResultSet rs = null;        
        String reportHeading = "";
        String rowHeaders = String.format("%-18s%-18s%-18s%-13s%-15s%-10s%-13s%-15s%-10s%-7s\n","First Name","Last Name","Other Name",
                "ID","County","Status","Wage","Days Worked","Total","Payment");
        String data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;  // variables store rs data
        
        // set report heading according to status choice
        if(status.equals("ACTIVE") && pay.equals("PAID")) {
            reportHeading = "CASUALS PAYMENT REPORT - ACTIVE PAID\n";
        }
        else if(status.equals("ACTIVE") && pay.equals("UNPAID")) {
            reportHeading = "CASUALS PAYMENT REPORT - ACTIVE UNPAID\n";
        } 
        else if(status.equals("WAITING") && pay.equals("PAID")) {
            reportHeading = "CASUALS PAYMENT REPORT - WAITING PAID\n";
        }
        else if(status.equals("WAITING") && pay.equals("UNPAID")) {
            reportHeading = "CASUALS PAYMENT REPORT - WAITING UNPAID\n";
        }
        else if(status.equals("TERMINATED") && pay.equals("PAID")) {
            reportHeading = "CASUALS PAYMENT REPORT - TERMINATED PAID\n";
        } 
        else if(status.equals("TERMINATED") && pay.equals("UNPAID")) {
            reportHeading = "CASUALS PAYMENT REPORT - TERMINATED UNPAID\n";
        }
        
        try {
            // execute queries 
            super.fetchCasualPayment.setString(1, status);
            super.fetchCasualPayment.setString(2, pay);
            super.fetchCasualPayment.setString(3, staffId);
            rs = super.fetchCasualPayment.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, new Color(204,0,204));
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader, new Color(204,0,204));
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(2)); // get fname
                data2 = String.format("%s", rs.getString(3)); // get lname
                data3 = String.format("%s", rs.getString(4)); // get oname
                data4 = String.format("%d", rs.getInt(5)); // get Id
                data5 = String.format("%s", rs.getString(6)); // get county
                data6 = String.format("%s", rs.getString(7)); // get status
                data7 = String.format("%.2f", rs.getFloat(8)); // get wage
                data8 = String.format("%d", rs.getInt(9)); // get work days
                data9 = String.format("%.2f", rs.getFloat(10)); // get total
                data10 = String.format("%s", rs.getString(11)); // get money
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-18s%-18s%-18s%-13s%-15s%-10s%-13s%-15s%-10s%-7s\n", data1, data2, data3,data4, data5, data6, data7, data8,data9,data10), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generatePaymentDetailsReport
    
    /* function generates supervisor's pending project details into JTextPane...called by pending proj details JMenu */
    public void generatePendingProjDetailsReport(StyledDocument document,JTextPane textPane,String staffId) {
        ResultSet rs = null;        
        String reportHeading = "YOUR PENDING PROJECTS REPORT\n";
        String rowHeaders = String.format("%-12s%-25s%-30s%-15s%-15s%-15s%-13s%-20s\n","Project SN","Project Title","Project Location",
                "County","Start Date","End Date","Status","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8;  // variables store rs data
        
        try {
            // execute queries 
            super.fetchProjectsPending.setString(1, staffId);
            rs = super.fetchProjectsPending.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get projectId
                data2 = String.format("%s", rs.getString(2)); // get title
                data3 = String.format("%s", rs.getString(3)); // get location
                data4 = String.format("%s", rs.getString(4)); // get county
                data5 = String.format("%s", rs.getString(5)); // get start
                data6 = String.format("%s", rs.getString(6)); // get end
                data7 = String.format("%s", rs.getString(8)); // get status
                data8 = String.format("%s", rs.getString(9)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-12s%-25s%-30s%-15s%-15s%-15s%-13s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generatePendingProjDetailsReport
    
    /* function generates supervisor's ongoing project details into JTextPane...called by ongoing proj details JMenu */
    public void generateOngoingProjDetailsReport(StyledDocument document,JTextPane textPane,String staffId) {
        ResultSet rs = null;        
        String reportHeading = "YOUR ONGOING PROJECTS REPORT\n";
        String rowHeaders = String.format("%-8s%-25s%-30s%-15s%-13s%-13s%-10s%-10s%-13s%-20s\n","Proj-SN","Project Title","Project Location",
                "County","Start Date","End Date","Staff No.","Vehicle","Status","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;  // variables store rs data
        
        try {
            // execute queries 
            super.fetchProjectsOngoing.setString(1, staffId);
            rs = super.fetchProjectsOngoing.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get projectId
                data2 = String.format("%s", rs.getString(2)); // get title
                data3 = String.format("%s", rs.getString(3)); // get location
                data4 = String.format("%s", rs.getString(4)); // get county
                data5 = String.format("%s", rs.getString(5)); // get start
                data6 = String.format("%s", rs.getString(6)); // get end
                data7 = String.format("%s", rs.getString(8)); // get staffing no.
                data8 = String.format("%s", rs.getString(9)); // get vehicle
                data9 = String.format("%s", rs.getString(10)); // get status
                data10 = String.format("%s", rs.getString(11)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-8s%-25s%-30s%-15s%-13s%-13s%-10s%-10s%-13s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8,data9, data10), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateOngoingProjDetailsReport
    
    /* function generates supervisor's complete project details into JTextPane...called by complete proj details JMenu */
    public void generateCompleteProjDetailsReport(StyledDocument document,JTextPane textPane,String staffId) {
        ResultSet rs = null;        
        String reportHeading = "YOUR COMPLETE PROJECTS REPORT\n";
        String rowHeaders = String.format("%-10s%-25s%-30s%-15s%-13s%-13s%-12s%-13s%-20s\n","Proj-SN","Project Title","Project Location",
                "County","Start Date","End Date","Extension","Status","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8, data9;  // variables store rs data
        
        try {
            // execute queries 
            super.fetchProjectsComplete.setString(1, staffId);
            rs = super.fetchProjectsComplete.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get projectId
                data2 = String.format("%s", rs.getString(2)); // get title
                data3 = String.format("%s", rs.getString(3)); // get location
                data4 = String.format("%s", rs.getString(4)); // get county
                data5 = String.format("%s", rs.getString(5)); // get start
                data6 = String.format("%s", rs.getString(6)); // get end
                data7 = String.format("%d", rs.getInt(7)); // get extend
                data8 = String.format("%s", rs.getString(9)); // get status
                data9 = String.format("%s", rs.getString(10)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-10s%-25s%-30s%-15s%-13s%-13s%-12s%-13s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8,data9), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateCompleteProjDetailsReport
    
 /********************************************************************************************************************************
  *      SECTION HANDLES TOOLS INTERFACE REPORTS
     * @param document
     * @param textPane
  *******************************************************************************************************************************/     
   
    /* function generates all tool details into JTextPane...called by all tool details JMenu */
    public void generateToolDetailsReport(StyledDocument document,JTextPane textPane) {
        ResultSet rs = null;        
        String reportHeading = "ALL TOOL DETAILS REPORT\n";
        String rowHeaders = String.format("%-10s%-25s%-8s%-15s%-13s%-10s%-55s%-20s\n","Tool-SN","Tool Name","Size",
                "Price","Buy Date","Quantity","Description","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7,data8;  // variables store rs data
        
        try {
            // execute query 
            rs = super.fetchToolDetails.executeQuery();
            
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%d", rs.getInt(1)); // get toolId
                data2 = String.format("%s", rs.getString(2)); // get name
                data3 = String.format("%s", rs.getString(3)); // get size
                data4 = String.format("%.2f", rs.getFloat(4)); // get price
                data5 = String.format("%s", rs.getString(5)); // get buyDate
                data6 = String.format("%d", rs.getInt(6)); // get quantity
                data7 = String.format("%s", rs.getString(7)); // get description
                data8 = String.format("%s", rs.getString(8)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-10s%-25s%-8s%-15s%-13s%-10s%-55s%-20s\n", data1, data2, data3,data4, data5, data6, data7,data8), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            }       
        }
    } // end function generateToolDetailsReport
    
    /* function generates borrowed tools details into JTextPane...called by tool borrow details JMenu items */
    public void generateToolBorrowDetailsReport(StyledDocument document,JTextPane textPane,ResultSet query, String heading, String date, Boolean flag) {
        ResultSet rs = null;
        String reportHeading = "" + heading + "\n";
        String rowHeaders = String.format("%-10s%-25s%-13s%-15s%-10s%-15s%-20s%-20s%-20s\n","Tool-SN","Name","Good-Qty",
                "Damaged-Qty","Duration","Staff-SN","First Name","Last Name","Timestamp");
        String data1, data2, data3, data4, data5, data6, data7, data8, data9;  // variables store rs data
        
        try {
            // execute query based on flag choice
            if(flag == false) {
                rs = query;
            } else {
                super.fetchToolsBorrowedDate.setDate(1, Date.valueOf(date));
                rs = super.fetchToolsBorrowedDate.executeQuery();
            }
            // set the report headers
            generateReportHeaders(document,textPane);
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setAlignment(reportTitle, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle,Color.blue);
            document.setParagraphAttributes(document.getLength(), reportHeading.length(), reportTitle, true);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setForeground(rowHeader,Color.blue);
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 12);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%d", rs.getInt(1)); // get toolId
                data2 = String.format("%s", rs.getString(2)); // get name
                data3 = String.format("%d", rs.getInt(3)); // get goodQty
                data4 = String.format("%d", rs.getInt(4)); // get damageQty
                data5 = String.format("%d", rs.getInt(5)); // get duration
                data6 = String.format("%s", rs.getString(6)); // get staffId
                data7 = String.format("%s", rs.getString(7)); // get fname
                data8 = String.format("%s", rs.getString(8)); // get lname
                data9 = String.format("%s", rs.getString(9)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-10s%-25s%-13s%-15s%-10s%-15s%-20s%-20s%-20s\n", data1, data2, data3,data4, data5, data6, data7, data8,data9), 
                        rowData); // end insertString                                                                                                                                    
            } // end while 
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Reports Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch       
    } // end function generateToolBorrowDetailsReport
    
/********************************************************************************************************************************
  *      SECTION HANDLES MAPS INTERFACE 
    
  *******************************************************************************************************************************/     
    
    /* function handles hyperlink click events in editorPane...called by MapsPanel */
    
    
} // end class Reports
