/* 
 * @author John Felix 
 *
 * This class stores drug details in the various fields based on customer purchases.
 * It is used when making a list of all the customer drug orders before actual purchase.
*/
package myPharma;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class Customer extends DB {
    
    /* class attributes declarations */
    private final Authenticator authenticator; // class Authenticator obj to retrieve authenticated username
    private List<Customer> customerList; // List declaration for storing Customer objects 
    
    private String customerName; // customer making purchase
    private String staffId; // staff making sale 
    private int drugId; // drug identification
    private String drugName; // drug name 
    private String measurement; // drug measurement g,ml,%w/v etc
    private int availableQty; // available drug quantity in store
    private float price; // sell price of drug
    private String prescription; // general drug prescription 
    private String description; // general drug description
    private int saleQty; // customer buying quantity 
    
    /* empty class constructor */
    public Customer() {
        authenticator = new Authenticator(); // class Authenticator obj initialization
        customerList = new ArrayList<>(); // List initialization for storing Customer objects       
    } // end empty constructor 
    
    /* parameterized class constructor */
    public Customer(int drugId, String drugName, String measurement, int availableQty, float price, String prescription, 
            String description, int saleQty) {
        
        this.drugId = drugId;
        this.drugName = drugName;
        this.measurement = measurement;
        this.availableQty = availableQty;
        this.price = price;
        this.prescription = prescription;
        this.description = description;
        this.saleQty = saleQty;
        authenticator = new Authenticator(); // class Authenticator obj initialization
        customerList = new ArrayList<>(); // List initialization for storing Customer objects
    } // end constructor Customer
    
    /* attributes' getters and setters */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(int saleQty) {
        this.saleQty = saleQty;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
    
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    
    /* function calculates total based on item selected from table */
    public float calculateTotal(JTable table) {
        int selectedItems[]; // array of selection indices from table
        float total = 0.00f; // total price variable
        // ensure items selected from table before calculations
        if(table.getSelectionModel().isSelectionEmpty() == true) {
            // do nothing
        } else {            
            selectedItems = table.getSelectedRows(); // initialize selectedItems[] with selected rows from table
            // calculate total price based on selected items 
            for(int i = 0; i < selectedItems.length; i ++ ) {
                // initialization of customer object                
                Customer cust = customerList.get( selectedItems[i] );  
                selectedItems[i] = table.convertRowIndexToModel( selectedItems[i] );
                total = total + ( cust.getPrice() * (float) cust.getSaleQty() );               
            } // end for 
        } // end else
        
        return total;        
    } // end func calcutateTotal
    
    /* function for confirming drugs */
    public float confirmDrug(String DrugName, String DrugValue, String DrugMeasure, int SaleQuantity, JTable table) {       
        float subtotal = 0.00f; // unit price variable
        float total = 0.00f; // gross total price variable
        Customer customer; // class Customer object
        ResultSet rs = null;
    
        try {
            // concat DrugValue and DrugMeasure via Authenticator class function
            String drugMeasurement = Authenticator.concatenate(DrugValue, DrugMeasure);
            // excecute query confirmDrug 
            super.confirmDrug.setString(1, DrugName);
            super.confirmDrug.setString(2, drugMeasurement);
            rs = super.confirmDrug.executeQuery();
            // confirm if row returned from database
            if( rs.next() ) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented                                
                do {                             
                    // initialize Customer object into customerList if in database
                    customerList.add( new Customer(
                                            rs.getInt(1), // get drugId
                                            rs.getString(2), // get drugName
                                            rs.getString(3), // get drugMeasurement  
                                            rs.getInt(4), // get available drugQuantity  
                                            rs.getFloat(5), // get drugPrice   
                                            rs.getString(6), // get drugPrescription
                                            rs.getString(7), // get drugDescription
                                            SaleQuantity ) ); // end of add customer to list 
                 
                } while( rs.next() );               
                /** section displays confirmed item from customerList in JTable **/
                customer = customerList.get( customerList.size() - 1 ); // display item at last index  
                // calculation of unit total price for a table item
                subtotal = subtotal + ( SaleQuantity * customer.getPrice() );

                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { customer.getDrugName(),
                                             customer.getMeasurement(),
                                             customer.getAvailableQty(),
                                             customer.getPrice(),
                                             customer.getSaleQty(),
                                             subtotal,
                                             customer.getPrescription(),
                                             customer.getDescription() } ); // end addRow                                            
            } else { 
                // displaying of No drug message
                JOptionPane.showMessageDialog( null,String.format("The Item:\n %s\nis Not Available currently!", DrugName), 
                                "MyPharma", JOptionPane.WARNING_MESSAGE );                
            } // end else             
            // calculation of gross total price of confirmed table items
            for( Customer cus: customerList ) {               
                total = total + ( cus.getPrice() * (float) cus.getSaleQty() );
            }  // end for
                            
        } catch( SQLException exception ) {
            // displaying of connection error message 
            JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );            
        } // end catch     
        finally {
            try {           
                rs.close();
                
            } catch( SQLException exception ) {
                // displaying of connection error message
                JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
            } // end catch       
        } // end finally block
    
        return total;        
    } // end method confirmDrug
    
    /* function for making purchase */
    public float purchaseDrug(String customerName, float payedAmount, JTable table, JButton purchaseJB) {       
        Customer customer; // class Customer object
        int selectedItems[]; // array of selection indices from table
        float balanceAmount = 0.00f; // balance amount
        // confirm items selected from table
        if( table.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select Items to Purchase from Table!","Purchase Error- MyPharma", JOptionPane.WARNING_MESSAGE );           
        }
        else {
            // initializing selectedItems[] with selected rows from table
            selectedItems = table.getSelectedRows();
            // loop over selected items and store to database plus calculate balance amount
            for( int i = 0; i < selectedItems.length; i ++ ) {               
                selectedItems[i] = table.convertRowIndexToModel( selectedItems[i] );
                // initialization of customer object
                customer = customerList.get( selectedItems[i] );
                // record sale to database
                super.addSale(  customerName, 
                                customer.getDrugId(),
                                customer.getSaleQty(),
                                Authenticator.getStaffUsername() ); // end call to function addSale                
                // update quantity change to table catalogue 
                super.updateQty( customer.getSaleQty(), 
                                 customer.getDrugName(),
                                 customer.getMeasurement() ); // end call to function updateQty
                
            } // end for            
            // calculate balance amount from payed amount and total paid
            float totalAmount = calculateTotal(table); // call to function calculateTotal
            balanceAmount = payedAmount - totalAmount;
                        
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Item(s) added successfully\nClick \"Clear\" to record new sale", "MyPharma", JOptionPane.PLAIN_MESSAGE ); 
            // disable button until clearing
            purchaseJB.setEnabled(false);
        } // end else       
        // clear list for new sale
        customerList.clear();
                        
        return balanceAmount;       
    } // end method purchaseDrug
    
    /* function selects all table rows */
    public void selectRows(JTable table) {
        int rows = table.getRowCount(); // get rows in table
        ListSelectionModel model = table.getSelectionModel();
        model.clearSelection();
        // loop through, selecting rows
        for(int row = 0; row < rows; row++) {
            model.addSelectionInterval(row, row);
        } // end for
        
    } // end function selectRows

    
} // end class Customer
