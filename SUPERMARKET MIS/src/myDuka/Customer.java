/* 
 * @author John Felix 
 *
 * This class stores item details in the various fields based on customer purchases.
 * It is used when making a list of all the customer item orders before actual purchase.
*/
package myDuka;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class Customer extends Database {
    
    /* class attributes declarations */
    private final Authenticator authenticator; // class Authenticator obj to retrieve authenticated username
    private List<Customer> customerList; // List declaration for storing Customer objects 
    
    
    private long itemBarcode; // item's barcode
    private String itemName; // item's name
    private String itemMeasurement; // item's weight, volume, length etc
    private float itemPrice; // item's price
    private int saleQuantity; // quntity bought
    
    /* empty class constructor */
    public Customer() {
        authenticator = new Authenticator(); // class Authenticator obj initialization
        customerList = new ArrayList<>(); // List initialization for storing Customer objects
    } // end empty constructor 
    
    /* parameterized class constructor */
    public Customer(long itemBarcode, String itemName, String itemMeasurement, float itemPrice, int saleQuantity) {
        this.itemBarcode = itemBarcode;
        this.itemName = itemName;
        this.itemMeasurement = itemMeasurement;
        this.itemPrice = itemPrice;
        this.saleQuantity = saleQuantity;
        authenticator = new Authenticator(); // class Authenticator obj initialization
        customerList = new ArrayList<>(); // List initialization for storing Customer objects
    } // end parameterized constructor
    
    
    /* attributes' getters and setters */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public long getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(long itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemMeasurement() {
        return itemMeasurement;
    }

    public void setItemMeasurement(String itemMeasurement) {
        this.itemMeasurement = itemMeasurement;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }
    
    /* function calculates total based on item input for sale */
    public float calculateTotal() {
        float total = 0.00f; // total price variable
        // ensure items selected into list before calculations
        if(customerList.isEmpty() == true) {
            // do nothing
        } else {
            // calculate total price based on selected items 
            for(int i = 0; i < customerList.size(); i ++ ) {
                // initialization of customer object 
                Customer cust = customerList.get( i );
                total = total + ( cust.getItemPrice() * (float) cust.getSaleQuantity() );               
            } // end for 
        } // end else
        
        return total;        
    } // end func calcutateTotal
    
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
                total = total + ( cust.getItemPrice() * (float) cust.getSaleQuantity() );               
            } // end for 
        } // end else
        
        return total;        
    } // end func calcutateTotal
    
    /* function for confirming drugs */ 
    public float confirmItem(String itemName, String itemValue, String itemMeasure, int saleQuantity, JTable table) {       
        float subtotal = 0.00f; // unit price variable
        float total = 0.00f; // gross total price variable
        Customer customer; // class Customer object
        ResultSet rs = null;
    
        try {
            // concat itemValue and itemMeasure via Authenticator class function
            String itemMeasurementConcat = Authenticator.concatenate(itemValue, itemMeasure);
            // excecute query to confirm item 
            super.fetchItemDetails.setString(1, itemName);
            super.fetchItemDetails.setString(2, itemMeasurementConcat);
            rs = super.fetchItemDetails.executeQuery();
            // confirm if row returned from database
            if( rs.next() ) {
                // employing do-while so as to ensure storage of results after rs.next() execute before pointer is incremented                                
                do {                             
                    // initialize Customer object into customerList if in database
                    customerList.add( new Customer( rs.getLong(1), // get item barcode
                                                    rs.getString(2), // get item name
                                                    rs.getString(3), // get item measurement   
                                                    rs.getFloat(7), // get item price   
                                                    saleQuantity ) ); // end of add customer to list                    
                } while( rs.next() );               
                /** section displays confirmed item from customerList in JTable **/
                customer = customerList.get( customerList.size() - 1 ); // display item at last index  
                // calculation of unit total price for a table item
                subtotal = subtotal + ( saleQuantity * customer.getItemPrice() );

                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { customer.getItemBarcode(),
                                             customer.getItemName(),
                                             customer.getItemMeasurement(),                                             
                                             customer.getItemPrice(),
                                             customer.getSaleQuantity(),
                                             subtotal } ); // end addRow 
                
            } else { 
                // displaying of No drug message
                JOptionPane.showMessageDialog( null,String.format("The Item:\n %s\nis Not Available currently!", itemName), 
                                "MyDuka", JOptionPane.WARNING_MESSAGE );                
            } // end else             
            // calculation of gross total price of confirmed table items
            for( Customer cus: customerList ) {               
                total = total + ( cus.getItemPrice() * (float) cus.getSaleQuantity() );
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
    } // end method confirmItem
    
    /* function for making purchase */
    public float purchaseItem(float payedAmount, JTable table) {       
        Customer customer; // class Customer object
        int selectedItems[]; // array of selection indices from table
        float balanceAmount = 0.00f; // balance amount
        // confirm items selected from table
        if( customerList.isEmpty() == true ) {
            // displaying of NULL items inputed error message */
            JOptionPane.showMessageDialog( null, "Input Items to Purchase!","Purchase Error- MyDuka", JOptionPane.WARNING_MESSAGE );           
        }
        else {
            // select all rows in table for sale
            selectRows(table);
            // initializing selectedItems[] with selected rows from table
            selectedItems = table.getSelectedRows();
            // loop over selected items and store to database plus calculate balance amount
            for( int i = 0; i < selectedItems.length; i ++ ) {               
                selectedItems[i] = table.convertRowIndexToModel( selectedItems[i] );
                // initialization of customer object
                customer = customerList.get( selectedItems[i] );
                // record sale to database 
                super.addSales( customer.getItemBarcode(),                                
                                customer.getSaleQuantity(),
                                Authenticator.getStaffUsername() ); // end call to function addSale                
                // update quantity change to table catalogue 
                super.updateQtyAfterSellQty( customer.getSaleQuantity(), 
                                             customer.getItemBarcode() ); // end call to function updateQtyAfterSellQty
                                                             
            } // end for            
            // calculate balance amount from payed amount and total paid
            float totalAmount = calculateTotal(table); // call to function calculateTotal
            balanceAmount = payedAmount - totalAmount;
                        
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Item(s) added successfully\nClick \"Clear\" to record new sale", "MyDuka", JOptionPane.PLAIN_MESSAGE );            
        } // end else       
        // clear list for new sale
        customerList.clear();
                        
        return balanceAmount;       
    } // end method purchaseItem
    
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
