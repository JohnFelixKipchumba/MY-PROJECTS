/* 
 * @author John Felix 
 *
 * This class stores the general details pertaining to the Enterprise/company eg.its name
 * It enables the admin change/update these details when required.
 */

package myDuka;

import java.awt.Color;
import java.sql.*;
import javax.swing.JTextPane;
import javax.swing.text.*;
import javax.swing.JOptionPane;

public class Detailer extends Database {
    
    /* class attributes declarations */
    private static String companyTitle;
    private static String companyMotto; 
    private static int companyPhone;
    private static String companyEmail;
    private static String companyAddress;       
    // private static String companyLocation;       
    private static String companyWebsite;
    private static float statutoryFees;
    private static float rentBill;       
    private static float electricityBill;       
    private static float waterBill;       
    private static float pettyBill;       
    private static String mobilePay;
    private static String bankPay;
    private static String cardPay;
    
    /* getters and setters declarations */
    public static String getCompanyTitle() {
        return companyTitle;
    }

    public static void setCompanyTitle(String aCompanyTitle) {
        companyTitle = aCompanyTitle;
    }

    public static String getCompanyMotto() {
        return companyMotto;
    }

    public static void setCompanyMotto(String aCompanyMotto) {
        companyMotto = aCompanyMotto;
    }

    public static int getCompanyPhone() {
        return companyPhone;
    }

    public static void setCompanyPhone(int aCompanyPhone) {
        companyPhone = aCompanyPhone;
    }

    public static String getCompanyEmail() {
        return companyEmail;
    }

    public static void setCompanyEmail(String aCompanyEmail) {
        companyEmail = aCompanyEmail;
    }

    public static String getCompanyAddress() {
        return companyAddress;
    }

    public static void setCompanyAddress(String aCompanyAddress) {
        companyAddress = aCompanyAddress;
    }

    /* public static String getCompanyLocation() {
        return companyLocation;
    }

    public static void setCompanyLocation(String aCompanyLocation) {
        companyLocation = aCompanyLocation;
    } */

    public static String getCompanyWebsite() {
        return companyWebsite;
    }

    public static void setCompanyWebsite(String aCompanyWebsite) {
        companyWebsite = aCompanyWebsite;
    }

    public static float getStatutoryFees() {
        return statutoryFees;
    }

    public static void setStatutoryFees(float aStatutoryFees) {
        statutoryFees = aStatutoryFees;
    }

    public static float getRentBill() {
        return rentBill;
    }

    public static void setRentBill(float aRentBill) {
        rentBill = aRentBill;
    }

    public static float getElectricityBill() {
        return electricityBill;
    }

    public static void setElectricityBill(float aElectricityBill) {
        electricityBill = aElectricityBill;
    }

    public static float getWaterBill() {
        return waterBill;
    }

    public static void setWaterBill(float aWaterBill) {
        waterBill = aWaterBill;
    }

    public static float getPettyBill() {
        return pettyBill;
    }

    public static void setPettyBill(float aPettyBill) {
        pettyBill = aPettyBill;
    }

    public static String getMobilePay() {
        return mobilePay;
    }

    public static void setMobilePay(String aMobilePay) {
        mobilePay = aMobilePay;
    }

    public static String getBankPay() {
        return bankPay;
    }

    public static void setBankPay(String aBankPay) {
        bankPay = aBankPay;
    }

    public static String getCardPay() {
        return cardPay;
    }

    public static void setCardPay(String aCardPay) {
        cardPay = aCardPay;
    }
    
    /* no param class constructor */
    public Detailer() {
    }
    
    /* parameterized class constructor */
    public Detailer(String companyTitle, String companyMotto, String mobilePay, String bankPay, String cardPay) {
        Detailer.companyTitle = companyTitle;
        Detailer.companyMotto = companyMotto;
        Detailer.mobilePay = mobilePay;
        Detailer.bankPay = bankPay;
        Detailer.cardPay = cardPay;
    }
    
    /* function fetches company details from DB */
    public void fetchCompanyDetails() {
        ResultSet rs = null;
    
        try {
            // execute query fetchCompanyDetails
            rs = super.fetchCompanyDetails.executeQuery();
            // set fields with returned data
            while(rs.next()) {
                setCompanyTitle( rs.getString(2) ); // get company title
                setCompanyMotto( rs.getString(3) ); // get company motto
                setCompanyEmail( rs.getString(4) ); // get company email
                setCompanyPhone( rs.getInt(5) ); // get company phone no.                
                setCompanyAddress( rs.getString(6) ); // get company address
                setCompanyWebsite( rs.getString(7) ); // get company website
                
            } // end while            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );            
        } // end catch     
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,
                exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    } // end func fetchCompanyDetails
    
    /* function fetches company monetary details from DB */
    public void fetchMonetaryDetails() {
        ResultSet rs = null;
    
        try {
            // execute query fetchCompanyDetails
            rs = super.fetchMonetaryDetails.executeQuery();
            // set fields with returned data
            while(rs.next()) {
                setStatutoryFees( rs.getFloat(2) ); // get statutory fees
                setElectricityBill( rs.getFloat(3) ); // get electricity bill
                setWaterBill( rs.getFloat(4) ); // get water bill
                setRentBill( rs.getFloat(5) ); // get rent bill                              
                setPettyBill( rs.getFloat(6) ); // get petty expenses
                setMobilePay( rs.getString(7) ); // get mobile payment method
                setCardPay( rs.getString(8) ); // get card payment method
                setBankPay( rs.getString(9) ); // get bank payment method
                
            } // end while            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );            
        } // end catch     
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,
                exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    } // end func fetchMonetaryDetails
    
    /* funtion generates headers in a JTextPane */
    public void generateReportHeaders(JTextPane textPane) {
        try {
            String enterpriseTitle = String.format("%s\n", getCompanyTitle());
            String headers = String.format("Phone: %d\nEmail: %s\nAddress: %s\nWebsite: %s\n\n",getCompanyPhone(),
                                                                                                getCompanyEmail(),
                                                                                                getCompanyAddress(),
                                                                                                getCompanyWebsite() 
                                                                                                ); // end String headers
            // set text pane attributes
            StyledDocument document = AdminUI.document;            
            textPane.setBackground(Color.white);
            textPane.setText("");
            
            // set company title attributes
            SimpleAttributeSet title = new SimpleAttributeSet();            
            StyleConstants.setAlignment(title, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(title, 22);
            StyleConstants.setBold(title, true);
            document.setParagraphAttributes(document.getLength(), enterpriseTitle.length(), title, true);
            document.insertString(document.getLength(), enterpriseTitle, title);
            // set header attributes
            SimpleAttributeSet headings = new SimpleAttributeSet();
            //StyleConstants.setAlignment(headers, StyleConstants.ALIGN_CENTER);
            StyleConstants.setSpaceAbove(headings, 2);
            StyleConstants.setSpaceBelow(headings, 2);
            StyleConstants.setFontSize(headings, 14);
            StyleConstants.setForeground(headings, Color.blue);
            StyleConstants.setItalic(headings, true);
            document.insertString(document.getLength(), headers, headings);
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Display Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end func generateReportHeaders
    
    /* function generates sales details report in a JTextPane */
    public void generateSalesDetailsReport(ResultSet query, String heading, JTextPane textPane, String date, Boolean flag) {
        ResultSet rs = null;
        String reportHeading = "" + heading + "\n";
        String rowHeaders = String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Item-Barcode", "Item-Name", "Item-Measurement",
                    "Sale-Quantity", "Sell-Price", "Staff-id", "Timestamp");
        String data1, data2, data3, data4, data5, data6, data7;  // variables store resultset data
                                                                                                          
        try {
            // execute query based on flag choice
            if(flag == false) {
                rs = query;
            } else {
                super.fetchSalesDate.setDate(1, Date.valueOf(date));
                rs = super.fetchSalesDate.executeQuery();
            }
            // set the report headers
            generateReportHeaders(textPane);           
            // set text pane attributes
            StyledDocument document = AdminUI.document;  
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, Color.red);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 14); 
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 14);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%d", rs.getLong(1)); // get item barcode
                data2 = String.format("%s", rs.getString(2)); // get item name
                data3 = String.format("%s", rs.getString(3)); // get item measurement
                data4 = String.format("%d", rs.getInt(4)); // get sale quantity
                data5 = String.format("%,.2f", rs.getFloat(5)); // get sell price
                data6 = String.format("%s", rs.getString(6)); // get staff username/id
                data7 = String.format("%s", rs.getTimestamp(7)); // get timestamp
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", data1, data2, data3, data4, data5, data6, data7), 
                        rowData); // end insertString                                                                                                                                    
            } // end while            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Display Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,
                exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    } // end func generateSalesDetailsReport
    
    /* function generates all recorded items details report in a JTextPane */
    public void generateAvailableItemsReport(JTextPane textPane) {
        ResultSet rs = null;
        String reportHeading = "ALL RECORDED ITEMS REPORT\n";
        String rowHeaders = String.format("%-30s%-30s%-30s%-30s\n", "Item-Barcode","Brand-Name","Item-Measurement","Number-Available");
        String data1, data2, data3, data4;  // variables store resultset data
                                                                                                          
        try {
            // execute query            
            rs = super.fetchAvailableItems.executeQuery();
            
            // set the report headers
            generateReportHeaders(textPane);           
            // set text pane attributes
            StyledDocument document = AdminUI.document;  
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, Color.red);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 14); 
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 14);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%d", rs.getLong(1)); // get item barcode
                data2 = String.format("%s", rs.getString(2)); // get brand name
                data3 = String.format("%s", rs.getString(3)); // get item measurement
                data4 = String.format("%d", rs.getInt(4)); // get item quantity
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-30s%-30s%-30s%-30s\n", data1, data2, data3, data4), 
                        rowData); // end insertString                                                                                                                                    
            } // end while            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Display Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,
                exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    } // end func generateAvailableItemsReport
    
    /* function generates staff's personal details report in a JTextPane */
    public void generateStaffPersonalDetailsReport(ResultSet query, String heading, JTextPane textPane, int ID, Boolean flag) {
        ResultSet rs = null;
        String reportHeading = "" + heading + "\n";
        String rowHeaders = String.format("%-10s%-10s%-15s%-15s%-15s%-7s%-11s%-11s%-25s%-30s%-11s%-12s%-11s\n", 
                "Staff-id","ID","First-Name","Middle-Name","Sur-Name","Gender","Birth-Date","Phone","Email","Address","Status","Employed","Terminated");
        String data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,data13;  // variables store resultset data
                                                                                                          
        try {
            // execute query based on flag choice
            if(flag == false) {
                rs = query;
            } else {
                super.fetchAStaff.setInt(1, ID);
                rs = super.fetchAStaff.executeQuery();
            }
            // set the report headers
            generateReportHeaders(textPane);           
            // set text pane attributes
            StyledDocument document = AdminUI.document;  
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, Color.red);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setForeground(rowHeader, Color.MAGENTA);
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
                data1 = String.format("%s", rs.getString(1)); // get staff_id 
                data2 = String.format("%d", rs.getInt(2)); // get national id 
                data3 = String.format("%s", rs.getString(3)); // get Fname
                data4 = String.format("%s", rs.getString(4)); // get Lname
                data5 = String.format("%s", rs.getString(5)); // get Oname
                data6 = String.format("%s", rs.getString(6)); // get gender
                data7 = String.format("%s", rs.getDate(7)); // get DoB
                data8 = String.format("%d", rs.getInt(8)); // get phone
                data9 = String.format("%s", rs.getString(9)); // get email
                data10 = String.format("%s", rs.getString(10)); // get address
                data11 = String.format("%s", rs.getString(14)); // get status
                data12 = String.format("%s", rs.getDate(15)); // get employed date
                data13 = String.format("%s", rs.getDate(16)); // get terminated date
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-10s%-10s%-15s%-15s%-15s%-7s%-11s%-11s%-25s%-30s%-11s%-12s%-11s\n",
                                        data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,data13), 
                        rowData); // end insertString                                                                                                                                    
            } // end while            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Display Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,
                exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    } // end func generateStaffPersonalDetailsReport
    
    /* function generates staff's personal details report in a JTextPane */
    public void generateStaffFinancialDetailsReport(ResultSet query, String heading, JTextPane textPane, int ID, Boolean flag) {
        ResultSet rs = null;
        String reportHeading = "" + heading + "\n";
        String rowHeaders = String.format("%-10s%-10s%-15s%-15s%-15s%-10s%-17s%-30s%-30s%-11s%-11s%-11s\n", 
                "Staff-id","ID","First-Name","Middle-Name","Sur-Name","Gender","Title","Salary","Perks","Status","Employed","Terminated");
        String data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,data13;  // variables store resultset data
                                                                                                          
        try {
            // execute query based on flag choice
            if(flag == false) {
                rs = query;
            } else {
                super.fetchAStaff.setInt(1, ID);
                rs = super.fetchAStaff.executeQuery();
            }
            // set the report headers
            generateReportHeaders(textPane);           
            // set text pane attributes
            StyledDocument document = AdminUI.document;  
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, Color.red);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 12); 
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setForeground(rowHeader, Color.MAGENTA);
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
                data1 = String.format("%s", rs.getString(1)); // get staff_id 
                data2 = String.format("%d", rs.getInt(2)); // get national id 
                data3 = String.format("%s", rs.getString(3)); // get Fname
                data4 = String.format("%s", rs.getString(4)); // get Lname
                data5 = String.format("%s", rs.getString(5)); // get Oname
                data6 = String.format("%s", rs.getString(6)); // get gender
                data7 = String.format("%s", rs.getString(11)); // get title
                data8 = String.format("%,.2f", rs.getFloat(12)); // get salary
                data9 = String.format("%,.2f", rs.getFloat(13)); // get perks
                data10 = String.format("%s", rs.getString(14)); // get status
                data11 = String.format("%s", rs.getDate(15)); // get employed date
                data12 = String.format("%s", rs.getDate(16)); // get terminated date
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-10s%-10s%-15s%-15s%-15s%-10s%-17s%-30s%-30s%-11s%-11s%-11s\n",
                                        data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12), 
                        rowData); // end insertString                                                                                                                                    
            } // end while            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Display Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,
                exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    } // end func generateStaffFinancialDetailsReport
    
    /* function generates all suppliers details report in a JTextPane */
    public void generateSuppliersDetailsReport(JTextPane textPane) {
        ResultSet rs = null;
        String reportHeading = "ALL RECORDED SUPPLIERS REPORT\n";
        String rowHeaders = String.format("%-15s%-30s%-15s%-35s%-15s%-12s%-30s%-11s\n", /*%-30s*/ 
                "Contract-Id","Enterprise-Name","Licence-No.","Products","Contract-Yr","For-Months","Email","Phone","Address");
        String data1,data2,data3,data4,data5,data6,data7,data8 /*,data9*/ ;  // variables store resultset data
                                                                                                          
        try {
            // execute query            
            rs = super.fetchSupplierDetails.executeQuery();
            
            // set the report headers
            generateReportHeaders(textPane);           
            // set text pane attributes
            StyledDocument document = AdminUI.document;  
            // set report title/heading
            SimpleAttributeSet reportTitle = new SimpleAttributeSet();
            StyleConstants.setFontSize(reportTitle, 18); 
            StyleConstants.setBold(reportTitle, true);
            StyleConstants.setForeground(reportTitle, Color.red);
            document.insertString(document.getLength(), reportHeading, reportTitle);
            // set row header attribute            
            SimpleAttributeSet rowHeader = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowHeader, StyleConstants.ALIGN_LEFT);
            StyleConstants.setSpaceAbove(rowHeader, 4);
            StyleConstants.setSpaceBelow(rowHeader, 4);
            StyleConstants.setFontSize(rowHeader, 14); 
            StyleConstants.setBold(rowHeader, true);
            StyleConstants.setItalic(rowHeader, true);
            document.setParagraphAttributes(document.getLength(), rowHeaders.length(), rowHeader, true);
            document.insertString(document.getLength(), rowHeaders, rowHeader);
            // set retrieved data attributes
            SimpleAttributeSet rowData = new SimpleAttributeSet();
            StyleConstants.setAlignment(rowData, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(rowData, 14);   
            document.setParagraphAttributes(document.getLength(), 0, rowData, true);
            // get returned data from database
            while(rs.next()) {
                data1 = String.format("%s", rs.getString(1)); // get contract id
                data2 = String.format("%s", rs.getString(2)); // get enterprise name
                data3 = String.format("%s", rs.getString(3)); // get licence no.
                data4 = String.format("%s", rs.getString(4)); // get products
                data5 = String.format("%s", rs.getDate(5)); // get contract yr
                data6 = String.format("%d", rs.getInt(6)); // get contract duration
                data7 = String.format("%s", rs.getString(7)); // get email
                data8 = String.format("%d", rs.getInt(8)); // get phone
                //data9 = String.format("%s", rs.getString(9)); // get address
                // display data in text pane
                document.insertString(document.getLength(), 
                        String.format("%-15s%-30s%-15s%-35s%-15s%-12s%-30s%-11s\n", /*%-30s*/ 
                                data1, data2, data3, data4, data5, data6, data7, data8 /*,data9*/ ), 
                        rowData); // end insertString                                                                                                                                    
            } // end while            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            
        } catch( BadLocationException e ) {
            // displaying of textpane error message 
            JOptionPane.showMessageDialog( null, e.getMessage(), "Display Error", JOptionPane.ERROR_MESSAGE );
            
        } // end catch
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null,
                exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    } // end func generateSuppliersDetailsReport
    
    
} // end class Detailer
