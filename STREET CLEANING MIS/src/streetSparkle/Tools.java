/*
 * @author John Felix
 *
 * class identifies attributes and methods for tools management
 */
package streetSparkle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Tools extends Database {
    
    private List<Tools> toolList1; // list for storing tool obj
    private List<Tools> toolList2; // list for storing tool obj
    
    private int toolId;
    private String toolName;
    private String toolSize;
    private float toolPrice;       
    private String buyDate;
    private int quantity; // # of specific tool in store      
    private String toolDescription;  
    private int goodQty;
    private int damagedQty;
    private int duration;
    private String timestamp;
    private String staffId;
    private String fname;
    private String lname;

    
    /* empty constructor */
    public Tools() {
        toolList1 = new ArrayList<>(); // List initialization for storing tool objs
        toolList2 = new ArrayList<>(); // List initialization for storing tool objs
    }

    /* initialization constructor */
    public Tools(int toolId, String toolName, int quantity, String toolDescription) {
        this.toolId = toolId;
        this.toolName = toolName;
        this.quantity = quantity;
        this.toolDescription = toolDescription;
        toolList1 = new ArrayList<>(); // List initialization for storing tool objs
    }
    
    /* initialization constructor */
    public Tools(int toolId, String toolName, int goodQty, int damagedQty, int duration, String staffId, String fname,
            String lname, String timestamp) {
        this.toolId = toolId;
        this.toolName = toolName;
        this.goodQty = goodQty;
        this.damagedQty = damagedQty;
        this.duration = duration;
        this.timestamp = timestamp;
        this.staffId = staffId;
        this.fname = fname;
        this.lname = lname;
        toolList2 = new ArrayList<>(); // List initialization for storing tool objs       
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolSize() {
        return toolSize;
    }

    public void setToolSize(String toolSize) {
        this.toolSize = toolSize;
    }

    public float getToolPrice() {
        return toolPrice;
    }

    public void setToolPrice(float toolPrice) {
        this.toolPrice = toolPrice;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getToolDescription() {
        return toolDescription;
    }

    public void setToolDescription(String toolDescription) {
        this.toolDescription = toolDescription;
    }
    
    public int getGoodQty() {
        return goodQty;
    }

    public void setGoodQty(int goodQty) {
        this.goodQty = goodQty;
    }

    public int getDamagedQty() {
        return damagedQty;
    }

    public void setDamagedQty(int damagedQty) {
        this.damagedQty = damagedQty;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
            
    public List<Tools> getToolList1() {
        return toolList1;
    }

    public void setToolList1(List<Tools> toolList1) {
        this.toolList1 = toolList1;
    }

    public List<Tools> getToolList2() {
        return toolList2;
    }

    public void setToolList2(List<Tools> toolList2) {
        this.toolList2 = toolList2;
    }     
          
    /* function loads tool details into JTable for selection...called in ToolsPanel */
    public void fetchToolDetails(JTable table) {
        Tools aTool; // class obj
        ResultSet rs = null;
        
        try {
            // execute query fetchToolDetails from class Database
            rs = super.fetchToolDetails.executeQuery();           
          
            while(rs.next()) {
                // initialize Tool obj into toolList1 from database 
                toolList1.add( new Tools(
                                        rs.getInt(1), // get toolId
                                        rs.getString(2), // get tool name
                                        rs.getInt(6), // get quantity
                                        rs.getString(7) // get tool descr
                                        ));
                // section displays confirmed item from toolList1 into JTable 
                aTool = toolList1.get( toolList1.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aTool.getToolId(),
                                             aTool.getToolName(),
                                             aTool.getQuantity(),
                                             aTool.getToolDescription() } ); // end addRow 
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
    } // end function fetchToolDetails
    
    /* function returns selected toolId from JTable...called in ToolsPanel */ 
    public int selectedToolDetails(JTable table) {
        Tools aTool; // class obj
        int selectedItem; // selected index from table
        int toolSN = 0; // returned toolId from table selection
        
        // confirm item selected from table
        if( table.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select a Tool from Table!","Tool Borrow Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        }
        else {
            // initializing selectedItems[] with selected rows from table
            selectedItem = table.getSelectedRow();              
            selectedItem = table.convertRowIndexToModel(selectedItem);
            // initialization of tool object
            aTool = toolList1.get(selectedItem);
            toolSN = aTool.getToolId();    
        }
        return toolSN; 
    } // end function selectedToolDetails
    
    /* function loads tool borrow details into JTable for selection...called in ToolsPanel */
    public void fetchToolBorrows(JTable table) {
        Tools aTool; // class obj
        ResultSet rs = null;
        
        try {
            // execute query fetchToolsBorrowedMonth from class Database
            rs = super.fetchToolsBorrowedMonth.executeQuery();           
        
            while(rs.next()) {
                // initialize Tool obj into toolList1 from database 
                toolList2.add( new Tools(
                                        rs.getInt(1), // get toolId
                                        rs.getString(2), // get tool name
                                        rs.getInt(3), // get goodQty
                                        rs.getInt(4), // get damagedQty
                                        rs.getInt(5), // get duration
                                        rs.getString(6), // get staffId
                                        rs.getString(7), // get fname
                                        rs.getString(8), // get lname
                                        rs.getString(9) // get timestamp
                                        ));
                // section displays confirmed item from toolList2 into JTable 
                aTool = toolList2.get( toolList2.size() - 1 ); // display item at last index
                // populate table with fetched data
                DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                model.addRow( new Object[] { aTool.getToolId(),
                                             aTool.getToolName(),
                                             aTool.getGoodQty(),
                                             aTool.getDamagedQty(),
                                             aTool.getDuration(),
                                             aTool.getFname(),
                                             aTool.getLname(),
                                             aTool.getTimestamp()} ); // end addRow 
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
    } // end function fetchToolBorrows
    
    /* function returns selected toolId from borrow JTable...called in ToolsPanel */ 
    public int selectedToolBorrow(JTable table) {
        Tools aTool; // class obj
        int selectedItem; // selected index from table
        int toolSN = 0; // returned toolId from table selection
        
        // confirm item selected from table
        if( table.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select a Tool from Table!","Tool Borrow Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        }
        else {
            // initializing selectedItems[] with selected rows from table
            selectedItem = table.getSelectedRow();              
            selectedItem = table.convertRowIndexToModel(selectedItem);
            // initialization of tool object
            aTool = toolList2.get(selectedItem);
            toolSN = aTool.getToolId();    
        }
        return toolSN; 
    } // end function selectedToolBorrow
    
    /* function adds a tool borrow transaction into database...called in ToolsPanel */
    public void addToolBorrow(int staffId,int goodQty,int damagedQty,int duration,JTable table,JButton saveButton) {
        ResultSet rs = null;
        String staffSN = "";
        int toolSN = selectedToolBorrow(table); // get selected tool S/N
        // confirm if staff ID in database
        if(super.confirmStaffID(staffId) == true) {
            try {
                // get staffSN from ID
                super.confirmStaffID.setInt(1, staffId);
                // execute query confirmStaffID from class Database
                rs = super.confirmStaffID.executeQuery();                           
                
                while(rs.next()) {
                    staffSN = rs.getString(1);
                }
                // record into database
                super.addToolBorrow(staffSN, toolSN, goodQty, damagedQty, duration, saveButton);
                // record quantity drop after borrow
                super.updateToolBorrowQty((goodQty + damagedQty),toolSN);
                
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
        } else {
            // displaying of error message 
            JOptionPane.showMessageDialog( null, "Staff Details Not in record!", "Staff Error- Street Sparkle", JOptionPane.ERROR_MESSAGE );
        }        
    } // end function addToolBorrow
    
    /* function adds a tool return transaction into database...called in ToolsPanel */
    public void addToolReturn(int goodQty, JTable table, JButton saveButton) {
        Tools aTool; // class obj
        int selectedItem; // selected index from table
        int toolSN; // returned toolId from table selection
        String staffSN; // returned staff SN from table selection
        // initializing selectedItems with selected row from table
        selectedItem = table.getSelectedRow();              
        selectedItem = table.convertRowIndexToModel(selectedItem);
        // initialization of tool object
        aTool = toolList2.get(selectedItem);
        toolSN = aTool.getToolId();
        staffSN = aTool.getStaffId();
        // record into database 
        super.addToolReturn(staffSN, toolSN, goodQty, saveButton);
        // record quantity rise after return
        super.updateToolReturnQty(goodQty, toolSN);
        
    } // end function addToolReturn
    
    /* function loads a staff tool borrow details into JTable for selection...called in ToolsPanel */
    public void fetchStaffToolBorrow(int staffId, JTable table) {
        Tools aTool; // class obj
        ResultSet rs = null;
        ResultSet rs2 = null;
        String staffSN = "";
        // confirm if staff ID in database
        if(super.confirmStaffID(staffId) == true) {
            try {
                // get staffSN from ID
                super.confirmStaffID.setInt(1, staffId);
                // execute query confirmStaffID from class Database
                rs = super.confirmStaffID.executeQuery(); 
                               
                while(rs.next()) {
                    staffSN = rs.getString(1);
                }
                // execute query fetchToolsBorrowedStaff from class Database
                super.fetchToolsBorrowedStaff.setString(1, staffSN);
                rs2 = super.fetchToolsBorrowedStaff.executeQuery();

                while(rs2.next()) {
                    // initialize Tool obj into toolList1 from database 
                    toolList2.clear();
                    toolList2.add( new Tools(
                                            rs2.getInt(1), // get toolId
                                            rs2.getString(2), // get tool name
                                            rs2.getInt(3), // get goodQty
                                            rs2.getInt(4), // get damagedQty
                                            rs2.getInt(5), // get duration
                                            rs2.getString(6), // get staffId
                                            rs2.getString(7), // get fname
                                            rs2.getString(8), // get lname
                                            rs2.getString(9) // get timestamp
                                            ));
                    // section displays confirmed item from toolList2 into JTable 
                    aTool = toolList2.get( toolList2.size() - 1 ); // display item at last index
                    // populate table with fetched data
                    DefaultTableModel model = ( DefaultTableModel ) table.getModel();
                    model.addRow( new Object[] { aTool.getToolId(),
                                                 aTool.getToolName(),
                                                 aTool.getGoodQty(),
                                                 aTool.getDamagedQty(),
                                                 aTool.getDuration(),
                                                 aTool.getFname(),
                                                 aTool.getLname(),
                                                 aTool.getTimestamp()} ); // end addRow 
                } // end while
            } catch(SQLException e) {
            // display connection error message
            JOptionPane.showMessageDialog( null, e.getMessage(), "Street Sparkle - Database Error", JOptionPane.ERROR_MESSAGE );             
            }  
            finally {
                try {           
                    rs.close();
                    rs2.close();
                } catch( SQLException exception ) {
                    // displaying of connection error message
                    JOptionPane.showMessageDialog( null, exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
                }       
            }
        } else {
            // displaying of error message 
            JOptionPane.showMessageDialog( null, "Staff Details Not in record!", "Staff Error- Street Sparkle", JOptionPane.ERROR_MESSAGE );
        }
    } // end function fetchStaffToolBorrow
    
    /* function adds a tool damage transaction into database...called in ToolsPanel */
    public void updateToolDamage(int toolSN, int damageQty, String damageId, JButton saveButton) {
        // confirm tool SN in database
        if(super.confirmToolSN(toolSN) == false) {
            // displaying of error message 
            JOptionPane.showMessageDialog( null, "Tool S/N Not in record!\nGet borrowed tool S/N from above table!", "Tool Damage Error- Street Sparkle", JOptionPane.ERROR_MESSAGE );
        }
        // confirm casual id in database
        else if(super.confirmCasualId(Integer.parseInt(damageId)) == false) {
            // displaying of error message 
            JOptionPane.showMessageDialog( null, "ID Details Not in record!", "Tool Damage Error- Street Sparkle", JOptionPane.ERROR_MESSAGE );
        }
        // record damage details into database
        else {
            super.updateToolDamagedQty(damageQty, damageId, toolSN);
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Details submitted successfully!", "Tool Damage Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE );
            saveButton.setEnabled(false);
        }
    } // end function updateToolDamage
    
    /* function adds a tool lost transaction into database...called in ToolsPanel */
    public void updateToolLost(int toolSN, int damageQty, String damageId, JButton saveButton) {
        // confirm tool SN in database
        if(super.confirmToolSN(toolSN) == false) {
            // displaying of error message 
            JOptionPane.showMessageDialog( null, "Tool S/N Not in record!\nGet borrowed tool S/N from above table!", "Tool Lost Error- Street Sparkle", JOptionPane.ERROR_MESSAGE );
        }
        // confirm casual id in database
        else if(super.confirmCasualId(Integer.parseInt(damageId)) == false) {
            // displaying of error message 
            JOptionPane.showMessageDialog( null, "ID Details Not in record!", "Tool Lost Error- Street Sparkle", JOptionPane.ERROR_MESSAGE );
        }
        // record lost details into database
        else {
            super.updateToolLostQty(damageQty, damageId, toolSN);
            // update tool quantity negatively for losses
            super.updateToolBorrowQty(damageQty, toolSN);
            // displaying of success message 
            JOptionPane.showMessageDialog( null, "Details submitted successfully!", "Tool Lost Success- Street Sparkle", JOptionPane.INFORMATION_MESSAGE );
            saveButton.setEnabled(false);
        }
    } // end function updateToolLost
    
} // end class Tools
