/*
 * @author John Felix 
 *
 * This class handles connection to the pharmacy databases.
 * It contains all queries and functions to access the database.
 * It is the super class to classes Authenticator, Customer & PharmaDetails which extend it.
 */

package myPharma;

import java.sql.*;
import javax.swing.JOptionPane;

public class DB {
    
    /* class attributes declarations */      
    private static final String URL = "jdbc:mysql://localhost:3306/my_pharma";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "167456";
    
    private Connection connection = null; // manages database connection   
     
    private PreparedStatement addSale; // adds sales 
    private PreparedStatement addDrug; // adds new drug items  
    private PreparedStatement addStock; // updates drug stock 
    private PreparedStatement addStaff; // adds new staff details 
    private PreparedStatement addFinancialDetails; // adds company's financial details eg.expenses,payment methods
    private PreparedStatement addCompanyDetails; // adds company details eg.title,phone,location
    private PreparedStatement terminateStaff; // updates staff details on termination from job
    private PreparedStatement updateQuantity; // updates quantity by addition
    private PreparedStatement updateQty; // updates quantity by subtraction
    private PreparedStatement updateTitle;
    private PreparedStatement updateSalary; 
    private PreparedStatement updatePerks; 
    private PreparedStatement updateEmail; 
    private PreparedStatement updatePhone;
    private PreparedStatement updatePassword;
    private PreparedStatement updateUsername;
    private PreparedStatement updateCompanyTitle;
    private PreparedStatement updateCompanyMission;
    private PreparedStatement updateCompanyPhone;
    private PreparedStatement updateCompanyEmail;
    private PreparedStatement updateCompanyAddress;
    private PreparedStatement updateCompanyLocation;
    private PreparedStatement updateCompanyWeb;
    private PreparedStatement updateStatutoryFees;
    private PreparedStatement updateRentBill;
    private PreparedStatement updateElectricityBill;
    private PreparedStatement updateWaterBill;
    private PreparedStatement updatePettyBill;
    private PreparedStatement updateMobilePay;
    private PreparedStatement updateCardPay;
    private PreparedStatement updateBankPay;
    protected PreparedStatement confirmDrug; // confirms drug availability before any purchase
    protected PreparedStatement confirmDrugCatalogue; // confirms drug availability before any restock
    protected PreparedStatement fetchDrugId; // fetches drug id of item
    protected PreparedStatement fetchSalesDay; // fetches data on sales today 
    protected PreparedStatement fetchSalesWeek; // fetches data on week sales 
    protected PreparedStatement fetchSalesMonth; // fetches data on month sales 
    protected PreparedStatement fetchSalesYear; // fetches data on year sales 
    protected PreparedStatement fetchSalesDate; // fetches data on date sales 
    protected PreparedStatement fetchAvailableDrugs; // fetches data on available drugs 
    protected PreparedStatement fetchExpiringDay; // fetches drugs expiring today  
    protected PreparedStatement fetchExpiringWeek; // fetches drugs expiring in week 
    protected PreparedStatement fetchExpiringMonth; // fetches drugs expiring in month 
    protected PreparedStatement fetchExpiringYear; // fetches drugs expiring in year 
    protected PreparedStatement fetchExpiringDate; // fetches drugs expiring on date 
    protected PreparedStatement fetchCustomersDay; // fetches customer numbers today
    protected PreparedStatement fetchCustomersWeek; // fetches customer numbers in week
    protected PreparedStatement fetchCustomersMonth; // fetches customer numbers in month
    protected PreparedStatement fetchCustomersYear; // fetches customer numbers in year
    protected PreparedStatement fetchCustomersDate; // fetches customer numbers on date
    protected PreparedStatement fetchStaff; // fetches all staffs' details 
    protected PreparedStatement fetchAStaff; // fetches a staff's details
    protected PreparedStatement fetchPopulation; // fetches staff count in the pharmacy  
    protected PreparedStatement fetchSignIn; // fetches staff sign in details 
    protected PreparedStatement fetchUsername; // fetches a staff's username 
    protected PreparedStatement fetchPassword; // fetches a staff's password 
    protected PreparedStatement fetchMonetaryDetails; // fetches company expenses & purchase methods eg.cards,m-pesa
    protected PreparedStatement fetchCompanyDetails; // fetches company details eg.title,address
    
    /* class DB constructor */
    public DB() {
        // init Components
        try {
            connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
                        
            addSale = connection.prepareStatement("INSERT INTO drug_sales (`Customer_name`,`Drug_id`,`Sale_quantity`,`Staff_id`,`Timestamp`) VALUES(?, ?, ?, ?, now())");
            addDrug = connection.prepareStatement("INSERT INTO drug_catalogue (`Drug_name`, `Drug_type`, `Drug_measurement`, `Drug_quantity`, `Drug_prescription`, `Drug_Description`, `Timestamp`) VALUES( ?, ?, ?, 0, ?, ?, now())"); // zero drug quantity until restocking
            addStock = connection.prepareStatement("INSERT INTO drug_purchases (`Drug_id`,`Batch_no`,`Drug_expiry`,`Buy_quantity`,`Buy_price`,`Sell_price`,`Timestamp`) VALUES( ?, ?, ?, ?, ?, ?, now())");
            addStaff = connection.prepareStatement("INSERT INTO staff_details (`Staff_id`,`Password`,`ID`,`First_name`,`Last_name`,`Other_name`,`Gender`,`DOB`,`Phone`,`Email`,`Title`,`Salary`,`Perks`,`Employed_date`) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, date(CURRENT_DATE()) )");
            addFinancialDetails = connection.prepareStatement("INSERT INTO financial_details (`SN`) VALUES(1)");
            addCompanyDetails = connection.prepareStatement("INSERT INTO company_details (`SN`) VALUES(1)");        
            terminateStaff = connection.prepareStatement("UPDATE staff_details SET `Status` = \"Terminated\", `Terminated_date` = current_date() WHERE `ID` = ? ");
            updateQuantity = connection.prepareStatement("UPDATE drug_catalogue SET `Drug_quantity` = `Drug_quantity` + ? WHERE `Drug_id` = ? ");
            updateQty = connection.prepareStatement("UPDATE drug_catalogue SET `Drug_quantity` = `Drug_quantity` - ? WHERE `Drug_name` = ? AND `Drug_measurement` = ? ");
            updateTitle = connection.prepareStatement("UPDATE staff_details SET `Title` = ? WHERE `ID` = ? ");
            updateSalary = connection.prepareStatement("UPDATE staff_details SET `Salary` = ? WHERE `ID` = ? ");
            updatePerks = connection.prepareStatement("UPDATE staff_details SET `Perks` = ? WHERE `ID` = ? ");
            updateEmail = connection.prepareStatement("UPDATE staff_details SET `Email` = ? WHERE `ID` = ? ");
            updatePhone = connection.prepareStatement("UPDATE staff_details SET `Phone` = ? WHERE `ID` = ? ");
            updatePassword = connection.prepareStatement("UPDATE staff_details SET `Password` = ? WHERE `ID` = ? ");
            updateUsername = connection.prepareStatement("UPDATE staff_details SET `Staff_id` = ? WHERE `ID` = ? ");
            updateCompanyTitle = connection.prepareStatement("UPDATE company_details SET `Company_title` = ? ");
            updateCompanyMission = connection.prepareStatement("UPDATE company_details SET `Mission_title` = ? ");
            updateCompanyPhone = connection.prepareStatement("UPDATE company_details SET `Phone` = ? ");
            updateCompanyEmail = connection.prepareStatement("UPDATE company_details SET `Email` = ? ");
            updateCompanyAddress = connection.prepareStatement("UPDATE company_details SET `Address` = ? ");
            updateCompanyLocation = connection.prepareStatement("UPDATE company_details SET `Location` = ? ");
            updateCompanyWeb = connection.prepareStatement("UPDATE company_details SET `Website` = ? ");
            updateStatutoryFees = connection.prepareStatement("UPDATE financial_details SET `Statutory_fees` = ? ");
            updateRentBill = connection.prepareStatement("UPDATE financial_details SET `Rent_bill` = ? ");
            updateElectricityBill = connection.prepareStatement("UPDATE financial_details SET `Electricity_bill` = ? ");
            updateWaterBill = connection.prepareStatement("UPDATE financial_details SET `Water_bill` = ? ");
            updatePettyBill = connection.prepareStatement("UPDATE financial_details SET `Petty_expenses` = ? ");
            updateMobilePay = connection.prepareStatement("UPDATE financial_details SET `Mobile_payment` = ? ");
            updateCardPay = connection.prepareStatement("UPDATE financial_details SET `Card_payment` = ? ");
            updateBankPay = connection.prepareStatement("UPDATE financial_details SET `Bank_payment` = ? ");
            confirmDrug = connection.prepareStatement("SELECT * FROM confirm_drug WHERE `Drug_name` = ? AND `Drug_measurement` = ? ");
            confirmDrugCatalogue = connection.prepareStatement("SELECT * FROM confirm_drug_catalogue WHERE `Drug_name` = ? AND `Drug_measurement` = ? ");
            fetchDrugId = connection.prepareStatement("SELECT `Drug_id` FROM drug_catalogue WHERE `Drug_name` = ? AND `Drug_measurement` = ? ");
            fetchSalesDay = connection.prepareStatement("SELECT * FROM fetch_sales WHERE day(`Timestamp`) = day(current_date())");
            fetchSalesWeek = connection.prepareStatement("SELECT * FROM fetch_sales WHERE week(`Timestamp`) = week(current_date())");
            fetchSalesMonth = connection.prepareStatement("SELECT * FROM fetch_sales WHERE month(`Timestamp`) = month(current_date())");
            fetchSalesYear = connection.prepareStatement("SELECT * FROM fetch_sales WHERE year(`Timestamp`) = year(current_date())");
            fetchSalesDate = connection.prepareStatement("SELECT * FROM fetch_sales WHERE date(`Timestamp`) = ? ");
            fetchAvailableDrugs = connection.prepareStatement("SELECT `Drug_id`,`Drug_name`,`Drug_measurement`,`Drug_type`,`Drug_quantity` FROM drug_catalogue");
            fetchExpiringDay = connection.prepareStatement("SELECT * FROM fetch_expiring WHERE date(`Drug_expiry`) = date(current_date())");
            fetchExpiringWeek = connection.prepareStatement("SELECT * FROM fetch_expiring WHERE week(`Drug_expiry`) = week(current_date())");
            fetchExpiringMonth = connection.prepareStatement("SELECT * FROM fetch_expiring WHERE month(`Drug_expiry`) = month(current_date())");
            fetchExpiringYear = connection.prepareStatement("SELECT * FROM fetch_expiring WHERE year(`Drug_expiry`) = year(current_date())");
            fetchExpiringDate = connection.prepareStatement("SELECT * FROM fetch_expiring WHERE date(`Drug_expiry`) = ? ");
            fetchCustomersDay = connection.prepareStatement("SELECT * FROM fetch_custToday");
            fetchCustomersWeek = connection.prepareStatement("SELECT * FROM fetch_custWeek");
            fetchCustomersMonth = connection.prepareStatement("SELECT * FROM fetch_custMonth");
            fetchCustomersYear = connection.prepareStatement("SELECT * FROM fetch_custYear");
            fetchCustomersDate = connection.prepareStatement("SELECT DISTINCT count(`Customer_name`) AS `Customer_No`,date(`Timestamp`) AS `Date` FROM drug_sales WHERE date(`Timestamp`) = ? ");
            fetchStaff = connection.prepareStatement("SELECT * FROM fetch_staff");
            fetchAStaff = connection.prepareStatement("SELECT * FROM fetch_staff WHERE `ID` = ? ");
            fetchPopulation = connection.prepareStatement("SELECT * FROM fetch_population");
            fetchSignIn = connection.prepareStatement("SELECT * FROM fetch_signIn WHERE `Staff_id` = ? AND `Password` = ? "); 
            fetchUsername = connection.prepareStatement("SELECT `Staff_id` FROM fetch_username WHERE `Staff_id` = ? ");
            fetchPassword = connection.prepareStatement("SELECT * FROM fetch_password WHERE `Password` = ? ");          
            fetchMonetaryDetails = connection.prepareStatement("SELECT * FROM financial_details");
            fetchCompanyDetails = connection.prepareStatement("SELECT * FROM company_details");
            
            
        } catch(SQLException e) {
            // displaying of connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );                   
        }       
    } // end constructor DB
    
    /* function records a new sale */ 
    public void addSale(String customerName, int drugId, int saleQuantity, String staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            addSale.setString(1, customerName);
            addSale.setInt(2, drugId);
            addSale.setInt(3, saleQuantity);
            addSale.setString(4, staffId);
            
            result = addSale.executeUpdate();
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );                      
        }        
    } // end method addSale
            
    /* function records a new drug item */        
    public void addDrug(String drugName, String drugType, String drugMeasurement, String drugPrescription, String drugDescription) {
        int result = 0; // stores # of rows affected
        
        try {
            addDrug.setString(1, drugName);
            addDrug.setString(2, drugType);
            addDrug.setString(3, drugMeasurement);
            addDrug.setString(4, drugPrescription);
            addDrug.setString(5, drugDescription);
            
            result = addDrug.executeUpdate();
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method addDrug
            
    /* function records stock made */        
    public void addStock(int drugId, String batchNo, String drugExpiry, int buyQuantity, float buyPrice, float sellPrice) {
        int result = 0; // stores # of rows affected
        
        try {
            addStock.setInt(1, drugId);
            addStock.setString(2, batchNo);
            addStock.setDate(3, Date.valueOf(drugExpiry));
            addStock.setInt(4, buyQuantity);
            addStock.setFloat(5, buyPrice);
            addStock.setFloat(6, sellPrice);           
            result = addStock.executeUpdate();
            // update available quantity to reflect changes
            updateQuantity(buyQuantity, drugId);
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }              
    } // end method addStock
            
    /* function records new staff */        
    public void addStaff(String staffId, String password, int id, String firstName, String lastName, String otherName, String gender,
            String DOB, int phone, String email, String title, float salary, float perks) {
        
        int result = 0; // stores # of rows affected
        
        try {
            addStaff.setString(1, staffId);
            addStaff.setString(2, password);
            addStaff.setInt(3, id);
            addStaff.setString(4, firstName.toUpperCase());
            addStaff.setString(5, lastName.toUpperCase());
            addStaff.setString(6, otherName.toUpperCase());
            addStaff.setString(7, gender);
            addStaff.setDate(8, java.sql.Date.valueOf(DOB));
            addStaff.setInt(9, phone);
            addStaff.setString(10, email);
            addStaff.setString(11, title);
            addStaff.setFloat(12, salary);
            addStaff.setFloat(13, perks);
            
            result = addStaff.executeUpdate(); 
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Item(s) added successfully!", "Add New Staff Success- MyPharma", JOptionPane.PLAIN_MESSAGE );
        
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Staff Error - MyPharma", JOptionPane.WARNING_MESSAGE );
          
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method addStaff
            
    /* function sets company financial details */        
    public void addFinancialDetails() {
        int result = 0; // stores # of rows affected
        
        try {
            result = addFinancialDetails.executeUpdate();
            
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method addFinancialDetails 
            
    /* function sets company details */        
    public void addCompanyDetails() {
        int result = 0; // stores # of rows affected
        
        try {
            result = addCompanyDetails.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method addCompanyDetails
     
    /* function updates general drug item's quantity positively */        
    public void updateQuantity(int buyQuantity, int drugId) {
        int result = 0; // stores # of rows affected
        
        try {
            updateQuantity.setInt(1, buyQuantity); 
            updateQuantity.setInt(2, drugId);
            result = updateQuantity.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateQuantity 
    
    /* function updates general drug item's quantity negatively */        
    public void updateQty(int drugQuantity, String drugName, String drugMeasurement) {
        int result = 0; // stores # of rows affected
        
        try {
            updateQty.setInt(1, drugQuantity);
            updateQty.setString(2, drugName);
            updateQty.setString(3, drugMeasurement);
            result = updateQty.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateQty
            
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
                    JOptionPane.showMessageDialog( null, "Staff terminated successfully!", "Terminate Staff Success- MyPharma", JOptionPane.PLAIN_MESSAGE );
                } while(rs.next());
            } else {                
                // displaying of error message 
                JOptionPane.showMessageDialog( null, "Staff Details Not in record!", "Terminate Staff Error- MyPharma", JOptionPane.ERROR_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method terminateStaff 
                          
    /* function updates staff title in company */        
    public void updateTitle(String staffTitle, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updateTitle.setString(1, staffTitle);
            updateTitle.setInt(2, staffId);
            
            result = updateTitle.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updatePhone
            
    /* function updates staff password */        
    public void updatePassword(String password, int staffId) {
        int result = 0; // stores # of rows affected
        
        try {
            updatePassword.setString(1, password);
            updatePassword.setInt(2, staffId);
            
            result = updatePassword.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        } 
    } // end mehtod updateUsername
            
    /* function updates company title/name */        
    public void updateCompanyTitle(String title) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyTitle.setString(1, title.toUpperCase());
            
            result = updateCompanyTitle.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCompanyTitle
            
    /* function updates company mission statement */        
    public void updateCompanyMission(String mission) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyMission.setString(1, mission);
            
            result = updateCompanyMission.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCompanyMission
            
    /* function updates company phone contacts details */        
    public void updateCompanyPhone(int phone) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyPhone.setInt(1, phone);
            
            result = updateCompanyPhone.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCompanyPhone
            
    /* function updates company email details*/        
    public void updateCompanyEmail(String email) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyEmail.setString(1, email);
            
            result = updateCompanyEmail.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end mehtod updateCompanyEmail
            
    /* function updates company address details */        
    public void updateCompanyAddress(String address) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyAddress.setString(1, address.toUpperCase());
            
            result = updateCompanyAddress.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCompanyAddress
            
    /* function updates company location details */        
    public void updateCompanyLocation(String location) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyLocation.setString(1, location.toUpperCase());
            
            result = updateCompanyLocation.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCompanyLocation
            
    /* function updates company website details */        
    public void updateCompanyWeb(String website) {
        int result = 0; // stores # of rows affected
        
        try {
            updateCompanyWeb.setString(1, website);
            
            result = updateCompanyWeb.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCompanyWeb
            
    /* function updates statutory fees expenses */        
    public void updateStatutoryFees(float statutories) {
        int result = 0; // stores # of rows affected
        
        try {
            updateStatutoryFees.setFloat(1, statutories);
            
            result = updateStatutoryFees.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method upadatStatutoryFees
            
    /* function updates rent bills expenses */        
    public void updateRentBill(float rentFees) {
        int result = 0; // stores # of rows affected
        
        try {
            updateRentBill.setFloat(1, rentFees);
            
            result = updateRentBill.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateRentBill
            
    /* function updates electricity bills expenses */        
    public void updateElectricityBill(float elecFees) {
        int result = 0; // stores # of rows affected
        
        try {
            updateElectricityBill.setFloat(1, elecFees);
            
            result = updateElectricityBill.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateWaterBill 
            
    /* function updates petty expenses bills */        
    public void updatePettyBill(float petties) {
        int result = 0; // stores # of rows affected
        
        try {
            updatePettyBill.setFloat(1, petties);
            
            result = updatePettyBill.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updatePettyBill
            
    /* function updates the mobile payment provider and means */        
    public void updateMobilePay(String mobileMethods) {
        int result = 0; // stores # of rows affected
        
        try {
            updateMobilePay.setString(1, mobileMethods.toUpperCase());
            
            result = updateMobilePay.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateCardPay
            
    /* function updates banking provider and account */        
    public void updateBankPay(String bankMethods) {
        int result = 0; // stores # of rows affected
        
        try {
            updateBankPay.setString(1, bankMethods.toUpperCase());
            
            result = updateBankPay.executeUpdate();
           
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method updateBankPay        
            
    /* function for closing database connection */
    public void closeDB() {     
        try {
            // close database connection 
            connection.close();
            
        } catch(SQLException e) {
            // display close connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );          
        }       
    } // end method closeDB       
    
    /* function fills stock */
    public void fillStock(String drugName, String drugMeasurement, String batchNo, String drugExpiry, int buyQuantity, float buyPrice, float sellPrice) {
        ResultSet rs = null; // to store data fetched from database
        int drugId = 0; // store fetched drug Id
        
        try {
            // confirm drug id from name
            confirmDrugCatalogue.setString(1, drugName);
            confirmDrugCatalogue.setString(2, drugMeasurement);
            rs = confirmDrugCatalogue.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    drugId = rs.getInt(1); // set confirmed drug id from database 
                    
                } while(rs.next());
                // record the restock
                addStock(drugId, batchNo, drugExpiry, buyQuantity, buyPrice, sellPrice); 
                // displaying of success message 
                JOptionPane.showMessageDialog( null, "Item(s) added successfully!", "Fill Stock Success- MyPharma", JOptionPane.PLAIN_MESSAGE );
                
            } else {
                // display error message
                JOptionPane.showMessageDialog( null, "Item is Not Available!\nAdd the item first.", "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
            } 
        } catch(IllegalArgumentException e) {
            // display error message
            JOptionPane.showMessageDialog( null, "Please input valid Date!", "Add New Staff Error - MyPharma", JOptionPane.WARNING_MESSAGE );
          
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE ); 
        }                      
    } // end function fillStock
    
    /* function adds new item record */
    public void addNewItem(String drugName, String drugType, String drugMeasurement, String drugPrescription, String drugDescription) {
        ResultSet rs = null; // to store data fetched from database
        int drugId; // store fetched drug Id
        
        try {
            // confirm drug id from name
            fetchDrugId.setString(1, drugName);
            fetchDrugId.setString(2, drugMeasurement);
            rs = fetchDrugId.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    drugId = rs.getInt(1);
                    // displaying of success message 
                    JOptionPane.showMessageDialog( null, "Item Already Exist!\nInput a different item!", "Add New Item Error- MyPharma", JOptionPane.ERROR_MESSAGE );
                } while(rs.next());
            } else {
                // record new item
                addDrug(drugName, drugType, drugMeasurement, drugPrescription, drugDescription);
                // displaying of success message 
                JOptionPane.showMessageDialog( null, "Item(s) added successfully!", "Add New Item Success- MyPharma", JOptionPane.PLAIN_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE ); 
        }     
    } // end method addNewItem
    
    /* function fetches a staff's details */
    public void fetchStaff(int staffId) {
        ResultSet rs = null; // to store data fetched from database
        
        try {
            // confirm staff id if in database
            fetchStaff.setInt(1, staffId);
            rs = fetchStaff.executeQuery();
            // confirm if row returned from database
            if(rs.next()) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented
                do {
                    rs.getString(1);
                    rs.getInt(2);                    
                } while(rs.next());
            } else {                
                // displaying of error message 
                JOptionPane.showMessageDialog( null, "Staff Details Not in record!", "Staff Error- MyPharma", JOptionPane.ERROR_MESSAGE );
            }
        } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "MyPharma- Database Error", JOptionPane.ERROR_MESSAGE );
        }
    } // end method fetchStaff
    
} // end class DB
