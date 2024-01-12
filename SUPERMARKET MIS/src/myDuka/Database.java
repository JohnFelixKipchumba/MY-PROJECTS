/*
 * @author John Felix 
 *
 * This class handles connection to the supermarket/enterprise databases.
 * It contains all queries and functions to access the database.
 * It is the super class to classes Authenticator, Customer & PharmaDetails which extend it.
 */

package myDuka;

import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
       /* class attributes declarations */    
    private static final String URL = "jdbc:mysql://localhost:3306/Supermarket_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "167456";
    
    private Connection connection = null; // manages database connection  
    
    private PreparedStatement addItem; // adds item
    private PreparedStatement addStock;//restocking
    private PreparedStatement addSales;//adds sales
    private PreparedStatement addStaff;//adds new staff details
    private PreparedStatement addSupplier;//adds a supplier
    private PreparedStatement addFinancialRecord;// adds company's financial details eg.expenses,payment methods
    private PreparedStatement addCompanyRecord ;// adds company details eg.title,phone,location
    private PreparedStatement updateQtyAfterBuyQty;
    private PreparedStatement updateQtyAfterSellQty;
    private PreparedStatement terminateStaff; // updates staff details on termination from job
    private PreparedStatement updateSupplier; // updates supplier's contract
    private PreparedStatement updateTitle;
    private PreparedStatement updateSalary; 
    private PreparedStatement updatePerks; 
    private PreparedStatement updateEmail; 
    private PreparedStatement updatePhone;
    private PreparedStatement updateAddress;
    private PreparedStatement updatePassword;
    private PreparedStatement updateUsername;
    private PreparedStatement updateCompanyName;
    private PreparedStatement updateCompanyMotto;
    private PreparedStatement updateCompanyEmail;
    private PreparedStatement updateCompanyPhone;
    private PreparedStatement updateCompanyAddress;
    private PreparedStatement updateCompanyWebsite;
    private PreparedStatement updateStatutoryFees;
    private PreparedStatement updateElectricityBill;
    private PreparedStatement updateWaterBill;
    private PreparedStatement updateRentBill;
    private PreparedStatement updatePettyBill;
    private PreparedStatement updateMobilePay;
    private PreparedStatement updateCardPay;
    private PreparedStatement updateBankPay;
    protected PreparedStatement fetchSalesDay; // fetches data on sales today 
    protected PreparedStatement fetchSalesWeek; // fetches data on week sales 
    protected PreparedStatement fetchSalesMonth; // fetches data on month sales 
    protected PreparedStatement fetchSalesYear; // fetches data on year sales 
    protected PreparedStatement fetchSalesDate; // fetches data on date sales 
    protected PreparedStatement fetchItemDetails; //fetches item details from restocking
    protected PreparedStatement fetchItemDetailsCat; //fetches specific item details from catalogue
    protected PreparedStatement fetchAvailableItems; //fetches item details from catalogue
    protected PreparedStatement fetchStaffDetails; // fetches all staff details 
    protected PreparedStatement fetchAStaff; // fetches a staff's details
    protected PreparedStatement fetchSupplierDetails;//fetches supplier details
    protected PreparedStatement fetchASupplierDetails; //fetches a specific supplier details
    protected PreparedStatement fetchUsername; // fetches a staff's username 
    protected PreparedStatement fetchPassword; // fetches a staff's password
    protected PreparedStatement fetchSignIn; // fetches staff sign in details 
    protected PreparedStatement fetchMonetaryDetails; // fetches company expenses & purchase methods eg.cards,m-pesa
    protected PreparedStatement fetchCompanyDetails; // fetches company details eg.title,address
    
    public Database(){
         try {
            connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
                        
            addItem = connection.prepareStatement("INSERT INTO item_record (`Item_barcode`,`Brand_name`,`Measurement`,`Item_quantity`) VALUES(?, ?, ?, 0)");
            addStock = connection.prepareStatement("INSERT INTO restocking_record(`Item_barcode`,`Contract_id`,`Buy_price`,`Sell_price`,`Batch_no`,`Buy_quantity`,`Expiry`,`Tax`,`Timestamp`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, now())");
            addSales = connection.prepareStatement("INSERT INTO sales_record (`Item_barcode`,`Sell_quantity`,`Staff_id`,`Timestamp`) VALUES(?, ?, ?, now())");
            addStaff = connection.prepareStatement("INSERT INTO staff_record (`Staff_id`,`Password`,`First_name`,`Last_name`,`Other_name`,`Gender`,`ID`,`DOB`,`Phone`,`Email`,`Address`,`Title`,`Salary`,`Perks`,`Employed_date`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, date(current_date()))");
            addSupplier = connection.prepareStatement("INSERT INTO suppliers_record (`Enterprise_name`,`Licence_no`,`Products`,`Contract_yr`,`Contract_duration`,`Email`,`Phone`,`Address`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            addFinancialRecord = connection.prepareStatement("INSERT INTO financial_record (`SN`) VALUES(1)");
            addCompanyRecord = connection.prepareStatement("INSERT INTO company_record (`SN`) VALUES(1)"); 
            updateQtyAfterBuyQty = connection.prepareStatement("UPDATE item_record SET `Item_quantity`= `Item_quantity` + ? WHERE `Item_barcode`= ?  ");
            updateQtyAfterSellQty = connection.prepareStatement("UPDATE item_record SET `Item_quantity`= `Item_quantity` - ? WHERE `Item_barcode`= ?  ");
            terminateStaff = connection.prepareStatement("UPDATE staff_record SET `Status` = \"Terminated\", `Termination_date` = current_date() WHERE `ID` = ? ");
            updateSupplier = connection.prepareStatement("UPDATE suppliers_record SET `Contract_yr` = ? , `Contract_duration` = ? WHERE `Contract_id` = ? ");
            updateTitle = connection.prepareStatement("UPDATE staff_record SET `Title` = ? WHERE `ID` = ? ");
            updateSalary = connection.prepareStatement("UPDATE staff_record SET `Salary` = ? WHERE `ID` = ? ");
            updatePerks = connection.prepareStatement("UPDATE staff_record SET `Perks` = ? WHERE `ID` = ? ");
            updateEmail = connection.prepareStatement("UPDATE staff_record SET `Email` = ? WHERE `ID` = ? ");
            updatePhone = connection.prepareStatement("UPDATE staff_record SET `Phone` = ? WHERE `ID` = ? ");
            updateAddress= connection.prepareStatement("UPDATE staff_record SET `Address` = ? WHERE `ID` = ? ");
            updatePassword = connection.prepareStatement("UPDATE staff_record SET `Password` = ? WHERE `ID` = ? ");
            updateUsername = connection.prepareStatement("UPDATE staff_record SET `Staff_id` = ? WHERE `ID` = ? ");
            updateCompanyName = connection.prepareStatement("UPDATE company_record SET `Company_name` = ? ");
            updateCompanyMotto= connection.prepareStatement("UPDATE company_record SET `Motto` = ? ");
            updateCompanyEmail= connection.prepareStatement("UPDATE company_record SET `Email` = ? ");
            updateCompanyPhone= connection.prepareStatement("UPDATE company_record SET `Phone` = ? ");
            updateCompanyAddress= connection.prepareStatement("UPDATE company_record SET `Address` = ? ");
            updateCompanyWebsite= connection.prepareStatement("UPDATE company_record SET `Website` = ? ");
            updateStatutoryFees = connection.prepareStatement("UPDATE financial_record SET `Statutory_fees` = ? ");
            updateElectricityBill = connection.prepareStatement("UPDATE financial_record SET `Electricity_bill` = ? ");
            updateWaterBill = connection.prepareStatement("UPDATE financial_record SET `Water_bill` = ? ");
            updateRentBill = connection.prepareStatement("UPDATE financial_record SET `Rent_bill` = ? ");
            updatePettyBill = connection.prepareStatement("UPDATE financial_record SET `Petty_expenses` = ? ");
            updateMobilePay = connection.prepareStatement("UPDATE financial_record SET `Mobile_payment` = ? ");
            updateCardPay = connection.prepareStatement("UPDATE financial_record SET `Card_payment` = ? ");
            updateBankPay = connection.prepareStatement("UPDATE financial_record SET `Bank_payment` = ? ");
            fetchSalesDay = connection.prepareStatement("SELECT * FROM fetch_sales WHERE date(`Timestamp`) = date(current_date())");
            fetchSalesWeek = connection.prepareStatement("SELECT * FROM fetch_sales WHERE week(`Timestamp`) = week(current_date())");
            fetchSalesMonth = connection.prepareStatement("SELECT * FROM fetch_sales WHERE month(`Timestamp`) = month(current_date())"); 
            fetchSalesYear = connection.prepareStatement("SELECT * FROM fetch_sales WHERE year(`Timestamp`) = year(current_date())"); 
            fetchSalesDate = connection.prepareStatement("SELECT * FROM fetch_sales WHERE date(`Timestamp`) = ? "); 
            fetchItemDetails = connection.prepareStatement("SELECT * FROM fetch_item_details WHERE `Brand_name` = ? AND `Measurement` = ? "); 
            fetchItemDetailsCat = connection.prepareStatement("SELECT * FROM confirm_item_catalogue WHERE `Brand_name` = ? AND `Measurement` = ? "); 
            fetchAvailableItems = connection.prepareStatement("SELECT * FROM confirm_item_catalogue");
            fetchStaffDetails = connection.prepareStatement("SELECT * FROM fetch_staff_details");
            fetchAStaff = connection.prepareStatement("SELECT * FROM fetch_staff_details WHERE `ID` = ? ");
            fetchSupplierDetails = connection.prepareStatement("SELECT * FROM fetch_supplier_details"); 
            fetchASupplierDetails = connection.prepareStatement("SELECT * FROM fetch_supplier_details WHERE `Enterprise_name` = ? AND `Licence_no` = ? ");
            fetchUsername = connection.prepareStatement("SELECT `Staff_id` FROM fetch_username WHERE `Staff_id` = ? "); 
            fetchPassword = connection.prepareStatement("SELECT * FROM fetch_password WHERE `Password` = ? "); 
            fetchSignIn = connection.prepareStatement("SELECT * FROM fetch_signIn WHERE `Staff_id` = ? AND `Password` = ? ");
            fetchMonetaryDetails = connection.prepareStatement("SELECT * FROM financial_record"); 
            fetchCompanyDetails = connection.prepareStatement("SELECT * FROM company_record"); 
            
        } catch(SQLException e) {
            // displaying of connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );                   
        }       
    } // end constructor DB
   /* function records a new item */ 
    public void addItem(long Item_barcode, String Brand_name, String Measurement) {
        int result = 0; // stores # of rows affected
        
        try {
            addItem.setLong(1, Item_barcode);
            addItem.setString(2, Brand_name);
            addItem.setString(3, Measurement);           
            result = addItem.executeUpdate();
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }        
    } // end method addSale
    
     /* function records a new drug item */        
    public void addStock(long Item_barcode, String Contract_id, float Buy_price, float Sell_price, String Batch_no, int Buy_quantity, String Expiry, float Tax) {
        int result = 0; // stores # of rows affected
        
        try {
            addStock.setLong(1, Item_barcode);
            addStock.setString(2, Contract_id);
            addStock.setFloat(3, Buy_price);
            addStock.setFloat(4, Sell_price);
            addStock.setString(5, Batch_no);
            addStock.setInt(6, Buy_quantity);
            addStock.setDate(7, java.sql.Date.valueOf(Expiry));
            addStock.setFloat(8, Tax);           
            result = addStock.executeUpdate();
            // update available quantity to reflect changes
            updateQtyAfterBuyQty(Buy_quantity, Item_barcode);
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method addStock
       
    /* function records a new sale */ 
    public void addSales(long Item_barcode, int Sell_quantity, String Staff_id) {
        int result = 0; // stores # of rows affected
        
        try {
            addSales.setLong(1, Item_barcode);
            addSales.setInt(2, Sell_quantity);
            addSales.setString(3, Staff_id);                     
            result = addSales.executeUpdate();
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }        
    } // end method addSales 
    
    /* function adds a new staff */
    public void addStaff(String Staff_id, String Password, String First_name, String Last_name, String Other_name, String Gender,
            int ID,String DOB,int Phone,String Email,String Address,String Title,float Salary,float Perks) {
        int result = 0; // stores # of rows affected
        
        try {
            addStaff.setString(1, Staff_id);
            addStaff.setString(2, Password);
            addStaff.setString(3, First_name.toUpperCase());
            addStaff.setString(4, Last_name.toUpperCase());
            addStaff.setString(5, Other_name.toUpperCase());
            addStaff.setString(6, Gender);
            addStaff.setInt(7, ID );
            addStaff.setDate(8, java.sql.Date.valueOf(DOB));
            addStaff.setInt(9, Phone );
            addStaff.setString(10, Email );  
            addStaff.setString(11, Address.toUpperCase());
            addStaff.setString(12, Title );
            addStaff.setFloat(13, Salary );
            addStaff.setFloat(14, Perks );
            result = addStaff.executeUpdate();
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Item(s) added successfully!", "Add New Staff Success- MyDuka", JOptionPane.PLAIN_MESSAGE );            
            
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Staff Error - MyDuka", JOptionPane.WARNING_MESSAGE );
                
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }        
    } // end method addStaff
    
    /* function records a supplier */ 
    public void addSupplier(String Enterprise_name,String Licence_no,String Products,String Contract_yr,int Contract_duration,String Email,int Phone,String Address ) {
        int result = 0; // stores # of rows affected
        
        try {
            addSupplier.setString(1, Enterprise_name.toUpperCase());
            addSupplier.setString(2, Licence_no);
            addSupplier.setString(3, Products);
            addSupplier.setDate(4, java.sql.Date.valueOf(Contract_yr));
            addSupplier.setInt(5, Contract_duration);
            addSupplier.setString(6, Email);
            addSupplier.setInt(7, Phone);
            addSupplier.setString(8, Address.toUpperCase());
            result = addSupplier.executeUpdate();
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }        
    } // end method addSupplier 
    
    /* function sets company financial details */        
    public void addFinancialRecord() {
        int result = 0; // stores # of rows affected
        
        try {
            result = addFinancialRecord.executeUpdate();
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method addFinancialRecord
            
    /* function sets company details */        
    public void addCompanyRecord() {
        int result = 0; // stores # of rows affected
        
        try {
            result = addCompanyRecord.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method addCompanyRecord
    
    /* function for updating quantity after buy quantity*/
    public void updateQtyAfterBuyQty(int itemQuantity, long itemBarcode) {       
	int result=0;
        
	try {
            /* set parameters then execute updateQty */
            updateQtyAfterBuyQty.setInt(1, itemQuantity);
            updateQtyAfterBuyQty.setLong(2, itemBarcode);
            /* insert the new entry; returns # of rows updated */
            result = updateQtyAfterBuyQty.executeUpdate();            
	} //end of try       
	catch( SQLException exception ) {
            /* displaying of connection error message */
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE ); 
        } // end of catch       
    } // end of method updateQtyAfterBuyQty
     
    public void updateQtyAfterSellQty(int itemQuantity, long itemBarcode) {       
	int result=0;
        
	try {
            /* set parameters then execute updateQty */
            updateQtyAfterSellQty.setInt(1, itemQuantity);
            updateQtyAfterSellQty.setLong(2, itemBarcode);
            /* insert the new entry; returns # of rows updated */
            result = updateQtyAfterSellQty.executeUpdate();           
	} //end of try        
	catch( SQLException exception ) {
            /* displaying of connection error message */
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
        } // end of catch        
    } // end of method updateQtyAfterBuyQty
    
    /* function updates staff status when terminated */        
    public void terminateStaff(int staffId) {
        ResultSet rs = null; // to store data fetched from database
        int result = 0; // stores # of rows affected
        
        try {
            // confirm staff id if in database
            fetchAStaff.setInt(1, staffId);
            rs = fetchAStaff.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    rs.getInt(2);
                    terminateStaff.setInt(1, staffId);
                    result = terminateStaff.executeUpdate();
                    // displaying of success message 
                    JOptionPane.showMessageDialog( null, "Staff terminated successfully!", "Terminate Staff Success- MyDuka", JOptionPane.PLAIN_MESSAGE );
                } while(rs.next());
            } else {                
                // displaying of error message 
                JOptionPane.showMessageDialog( null, "Staff Details Not in record!", "Terminate Staff Error- MyDuka", JOptionPane.ERROR_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method terminateStaff 
    
    /* function updates supplier contract */
    public void updateSupplier(String Enterprise_name, String Licence_no, String Contract_yr, int Contract_duration) {
        int result = 0; // stores # of rows affected
        ResultSet rs = null; // to store data fetched from database
        int contractId; // to store fetched supplier id
        
        try {
            // confirm supplier details
            fetchASupplierDetails.setString(1, Enterprise_name);
            fetchASupplierDetails.setString(2, Licence_no);
            rs = fetchASupplierDetails.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    contractId = rs.getInt(1);                   
                } while(rs.next());
                // update supplier
                updateSupplier.setDate(1, java.sql.Date.valueOf(Contract_yr));
                updateSupplier.setInt(2, Contract_duration);
                updateSupplier.setInt(3, contractId);
                result = updateSupplier.executeUpdate();
                // displaying of success message 
                JOptionPane.showMessageDialog( null, "Item(s) updated successfully!", "Update Supplier Success", JOptionPane.PLAIN_MESSAGE );
                
            } else {               
                // displaying of success message 
                    JOptionPane.showMessageDialog( null, "Supplir is NOT in record!\nInput a valid supplier!", "Update Supplier Error", JOptionPane.ERROR_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyDuka- Database Error", JOptionPane.ERROR_MESSAGE ); 
        } 
    } // end function updateSupplier
    
    /* function updates staff title in company */        
    public void updateTitle(String staffTitle, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updateTitle.setString(1, staffTitle);
            updateTitle.setInt(2, staffId);           
            result = updateTitle.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateTitle 
    
     /* function updates staff salary */        
    public void updateSalary(float salary, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updateSalary.setFloat(1, salary);
            updateSalary.setInt(2, staffId);            
            result = updateSalary.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateSalary
        
    /* function updates staff perks */        
    public void updatePerks(float perks, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updatePerks.setFloat(1, perks);
            updatePerks.setInt(2, staffId);           
            result = updatePerks.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updatePerks
    
     /* function updates staff email */        
    public void updateEmail(String email, int staffId) {
       int result = 0; // stores # of rows affected
        
        try {
            updateEmail.setString(1, email);
            updateEmail.setInt(2, staffId);            
            result = updateEmail.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        } 
    } // end method updateEmail
    
    /* function updates staff phone contact */        
    public void updatePhone(int phone, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updatePhone.setInt(1, phone);
            updatePhone.setInt(2, staffId);            
            result = updatePhone.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updatePhone
    
    /* function updates staff's address */ 
    public void updateAddress(String address, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updateAddress.setString(1, address);
            updateAddress.setInt(2, staffId);           
            result = updateAddress.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateAddress
    
     /* function updates staff password */        
    public void updatePassword(String password, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updatePassword.setString(1, password);
            updatePassword.setInt(2, staffId);            
            result = updatePassword.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        } 
    } // end method updatePassword
    
     /* function updates staff username */        
    public void updateUsername(String username, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updateUsername.setString(1, username);
            updateUsername.setInt(2, staffId);        
            result = updateUsername.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        } 
    } // end method updateUsername
    
    /* function updates company name */        
    public void updateCompanyName(String name) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyName.setString(1, name.toUpperCase());            
            result = updateCompanyName.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCompanyName
    
    /* function updates company motto */        
    public void updateCompanyMotto(String motto ) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyMotto.setString(1, motto);            
            result = updateCompanyMotto.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateMotto
    
     /* function updates company  Email */        
    public void updateCompanyEmail(String email ) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyEmail.setString(1, email);            
            result = updateCompanyEmail.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateMotto
    
    /* function updates company  phone */        
    public void updateCompanyPhone(int phone ) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyPhone.setInt(1, phone);           
            result = updateCompanyPhone.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateMotto
    
     /* function updates company  address */        
    public void updateCompanyAddress(String address ) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyAddress.setString(1, address);           
            result = updateCompanyAddress.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updatAddress
    
    /* function updates company website */
    public void updateCompanyWebsite(String website ) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyWebsite.setString(1, website);           
            result = updateCompanyWebsite.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateWebsite
    
    /* function updates statutory fees expenses */        
    public void updateStatutoryFees(float statutories) {
        int result = 0; // stores # of rows affected
        
        try {
            updateStatutoryFees.setFloat(1, statutories);            
            result = updateStatutoryFees.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method upadatStatutoryFees
              
    /* function updates electricity bills expenses */        
    public void updateElectricityBill(float elecFees) {
        int result = 0; // stores # of rows affected
        
        try {
            updateElectricityBill.setFloat(1, elecFees);            
            result = updateElectricityBill.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateElectricityBill
    
     /* function updates water bills expenses */        
    public void updateWaterBill(float waterFees) {
        int result = 0; // stores # of rows affected
        
        try {
            updateWaterBill.setFloat(1, waterFees);            
            result = updateWaterBill.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateWaterBill 
    
     /* function updates rent bills expenses */        
    public void updateRentBill(float rentFees) {
        int result = 0; // stores # of rows affected
        
        try {
            updateRentBill.setFloat(1, rentFees);          
            result = updateRentBill.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateRentBill
    
     /* function updates petty expenses bills */        
    public void updatePettyBill(float petties) {
        int result = 0; // stores # of rows affected
        
        try {
            updatePettyBill.setFloat(1, petties);            
            result = updatePettyBill.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updatePettyBill
    
     /* function updates the mobile payment provider and means */        
    public void updateMobilePay(String mobileMethods) {
        int result = 0; // stores # of rows affected
        
        try {
            updateMobilePay.setString(1, mobileMethods);           
            result = updateMobilePay.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateMobilePay
    
     /* function updates card types for payment */        
    public void updateCardPay(String cardMethods) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCardPay.setString(1, cardMethods);            
            result = updateCardPay.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCardPay
    
     /* function updates banking provider and account */        
    public void updateBankPay(String bankMethods) {
        int result = 0; // stores # of rows affected
        
        try {
            updateBankPay.setString(1, bankMethods);            
            result = updateBankPay.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateBankPay        
    // implement function to close database connection
     public void closeDatabase() {     
        try {
            /* close database connection */
            connection.close();
        } // end of try      
        catch( SQLException exception ) {
            /* displaying of connection error message */
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );            
        } // end of catch   
    } // end of method closeDB  

    /* function fills stock */
    public void fillStock(String brandName, String itemMeasurement, String Contract_id, float Buy_price, float Sell_price, String Batch_no, int Buy_quantity, String Expiry, float Tax) {
        ResultSet rs = null; // to store data fetched from database
        long itemBarcode = 0L; // store fetched barcode
        
        try {
            // confirm drug id from name
            fetchItemDetailsCat.setString(1, brandName);
            fetchItemDetailsCat.setString(2, itemMeasurement);
            rs = fetchItemDetailsCat.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    itemBarcode = rs.getLong(1); // set confirmed drug id from database 
                    
                } while(rs.next());
                // record the restock 
                addStock(itemBarcode, Contract_id, Buy_price, Sell_price, Batch_no, Buy_quantity, Expiry, Tax); 
                // displaying of success message 
                JOptionPane.showMessageDialog( null, "Item(s) added successfully!", "Fill Stock Success", JOptionPane.PLAIN_MESSAGE );
                
            } else {
                // display error message
                JOptionPane.showMessageDialog( null, "Item is Not Available!\nAdd the item first.", "MyDuka- Database Error", JOptionPane.ERROR_MESSAGE );
            }
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Staff Error - MyDuka", JOptionPane.WARNING_MESSAGE );
          
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE ); 
        }                      
    } // end function fillStock
    
    /* function adds new item record */
    public void addNewItem(long itemBarcode, String itemName, String itemMeasurement) {
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // confirm item id from name
            fetchItemDetailsCat.setString(1, itemName);
            fetchItemDetailsCat.setString(2, itemMeasurement);
            rs = fetchItemDetailsCat.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    long barcode = rs.getLong(1);
                    // displaying of success message 
                    JOptionPane.showMessageDialog( null, "Item Already Exist!\nInput a different item!", "Add New Item Error", JOptionPane.ERROR_MESSAGE );
                } while(rs.next());
            } else {
                // record new item
                addItem(itemBarcode, itemName, itemMeasurement);
                // displaying of success message 
                JOptionPane.showMessageDialog( null, "Item(s) added successfully!", "Add New Item Success", JOptionPane.PLAIN_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyDuka- Database Error", JOptionPane.ERROR_MESSAGE ); 
        }     
    } // end method addNewItem
    
    /* function fetches a staff's details */
    public void fetchStaff(int staffId) {
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // confirm staff id if in database
            fetchAStaff.setInt(1, staffId);
            rs = fetchAStaff.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    rs.getString(1);
                    rs.getInt(2);                    
                } while(rs.next());
            } else {                
                // displaying of error message 
                JOptionPane.showMessageDialog( null, "Staff Details Not in record!", "Staff Error!", JOptionPane.ERROR_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Supermarket- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method fetchStaff
    
    /* function adds new supplier record */ 
    public void recordSupplier(String Enterprise_name,String Licence_no,String Products,String Contract_yr,int Contract_duration,String Email,int Phone,String Address) {
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // confirm supplier details
            fetchASupplierDetails.setString(1, Enterprise_name);
            fetchASupplierDetails.setString(2, Licence_no);
            rs = fetchASupplierDetails.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    String contractId = rs.getString(1);
                    // displaying of success message 
                    JOptionPane.showMessageDialog( null, "Item Already Exist!\nInput a new supplier!", "Add New Supplier Error", JOptionPane.ERROR_MESSAGE );
                } while(rs.next());
            } else {
                // record new supplier
                addSupplier(Enterprise_name, Licence_no, Products, Contract_yr, Contract_duration, Email, Phone, Address );
                // displaying of success message 
                JOptionPane.showMessageDialog( null, "Item(s) added successfully!", "Add New Supplier Success", JOptionPane.PLAIN_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyDuka- Database Error", JOptionPane.ERROR_MESSAGE ); 
        } 
    } // end function recordSupplier
    
} // end class Database
