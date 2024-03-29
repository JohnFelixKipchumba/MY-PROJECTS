/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package streetSparkle;

import javax.swing.JOptionPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

/**
 *
 * @author John Felix
 */
public class SUPreportsPanel extends javax.swing.JPanel {
    
    private final StyledDocument document = new DefaultStyledDocument(); // doc handles reports
    public Database database; // class obj
    public Reports aReport; // class obj

    /**
     * Creates new form SUPreportsPanel
     */
    public SUPreportsPanel() {
        /* variables instantiation */
        database = new Database();
        aReport = new Reports();
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        editorPanel = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        reportsTextPane = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        printMenuItem = new javax.swing.JMenuItem();
        casualsMenu = new javax.swing.JMenu();
        allCasualsMenuItem = new javax.swing.JMenuItem();
        aCasualMenuItem = new javax.swing.JMenuItem();
        attendanceMenu = new javax.swing.JMenu();
        ongoingMenuItem = new javax.swing.JMenuItem();
        completedMenuItem = new javax.swing.JMenuItem();
        paymentMenu = new javax.swing.JMenu();
        activePaidMenuItem = new javax.swing.JMenuItem();
        activeUnpaidMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        waitingPaidMenuItem = new javax.swing.JMenuItem();
        waitingUnpaidMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        termPaidMenuItem = new javax.swing.JMenuItem();
        termUnpaidMenuItem = new javax.swing.JMenuItem();
        projectMenu = new javax.swing.JMenu();
        pendProjMenuItem = new javax.swing.JMenuItem();
        onProjMenuItem = new javax.swing.JMenuItem();
        compProjMenuItem = new javax.swing.JMenuItem();

        mainPanel.setBackground(new java.awt.Color(216, 235, 254));

        editorPanel.setBackground(new java.awt.Color(255, 255, 255));

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setBorder(null);
        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jInternalFrame1.setTitle("Select from menu bar a report item");
        jInternalFrame1.setFrameIcon(null);
        jInternalFrame1.setVisible(true);

        reportsTextPane.setEditable(false);
        reportsTextPane.setBackground(new java.awt.Color(255, 255, 255));
        reportsTextPane.setDocument(document);
        reportsTextPane.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        reportsTextPane.setStyledDocument(document);
        jScrollPane2.setViewportView(reportsTextPane);

        jMenuBar1.setBorder(null);
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        fileMenu.setText("File  ");

        printMenuItem.setText("Print");
        fileMenu.add(printMenuItem);

        jMenuBar1.add(fileMenu);

        casualsMenu.setText("Casual details");

        allCasualsMenuItem.setText("All casuals details");
        allCasualsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casualsMenuItemsHandler(evt);
            }
        });
        casualsMenu.add(allCasualsMenuItem);

        aCasualMenuItem.setText("A casual's details");
        aCasualMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casualsMenuItemsHandler(evt);
            }
        });
        casualsMenu.add(aCasualMenuItem);

        jMenuBar1.add(casualsMenu);

        attendanceMenu.setText("Attendance details");

        ongoingMenuItem.setText("Ongoing project(s)");
        ongoingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceMenuItemsHandler(evt);
            }
        });
        attendanceMenu.add(ongoingMenuItem);

        completedMenuItem.setText("Completed project(s)");
        completedMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceMenuItemsHandler(evt);
            }
        });
        attendanceMenu.add(completedMenuItem);

        jMenuBar1.add(attendanceMenu);

        paymentMenu.setText("Payment details");

        activePaidMenuItem.setText("Active paid");
        activePaidMenuItem.setToolTipText("casual in a current project and paid");
        activePaidMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMenuItemsHandler(evt);
            }
        });
        paymentMenu.add(activePaidMenuItem);

        activeUnpaidMenuItem.setText("Active unpaid");
        activeUnpaidMenuItem.setToolTipText("casual in a project but unpaid yet");
        activeUnpaidMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMenuItemsHandler(evt);
            }
        });
        paymentMenu.add(activeUnpaidMenuItem);
        paymentMenu.add(jSeparator1);

        waitingPaidMenuItem.setText("Waiting paid");
        waitingPaidMenuItem.setToolTipText("casual awaiting project but paid for previous ");
        waitingPaidMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMenuItemsHandler(evt);
            }
        });
        paymentMenu.add(waitingPaidMenuItem);

        waitingUnpaidMenuItem.setText("Waiting unpaid");
        waitingUnpaidMenuItem.setToolTipText("casual awaiting project but not paid previous");
        waitingUnpaidMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMenuItemsHandler(evt);
            }
        });
        paymentMenu.add(waitingUnpaidMenuItem);
        paymentMenu.add(jSeparator2);

        termPaidMenuItem.setText("Terminated paid");
        termPaidMenuItem.setToolTipText("casual terminated but paid");
        termPaidMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMenuItemsHandler(evt);
            }
        });
        paymentMenu.add(termPaidMenuItem);

        termUnpaidMenuItem.setText("Terminated unpaid");
        termUnpaidMenuItem.setToolTipText("casual terminated but unpaid yet");
        termUnpaidMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMenuItemsHandler(evt);
            }
        });
        paymentMenu.add(termUnpaidMenuItem);

        jMenuBar1.add(paymentMenu);

        projectMenu.setText("Project details");

        pendProjMenuItem.setText("Pending project(s)");
        pendProjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectMenuItemsHandler(evt);
            }
        });
        projectMenu.add(pendProjMenuItem);

        onProjMenuItem.setText("Ongoing project(s)");
        onProjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectMenuItemsHandler(evt);
            }
        });
        projectMenu.add(onProjMenuItem);

        compProjMenuItem.setText("Completed project(s)");
        compProjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectMenuItemsHandler(evt);
            }
        });
        projectMenu.add(compProjMenuItem);

        jMenuBar1.add(projectMenu);

        jInternalFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout editorPanelLayout = new javax.swing.GroupLayout(editorPanel);
        editorPanel.setLayout(editorPanelLayout);
        editorPanelLayout.setHorizontalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editorPanelLayout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        editorPanelLayout.setVerticalGroup(
            editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(editorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void casualsMenuItemsHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casualsMenuItemsHandler
        // check if all casuals details menu item clicked
        if(evt.getSource() == allCasualsMenuItem) {
            // load casual details into JTextPane
            aReport.generateCasualDetailsReport(document,reportsTextPane);
        }
        
        // check if a casual's details menu item clicked
        if(evt.getSource() == aCasualMenuItem) {
            try {                
                // get search id value from user
                String idInput = JOptionPane.showInputDialog(null, "Input the Casual National ID.", "Street Sparkle- Input the search ID", JOptionPane.QUESTION_MESSAGE);
                // validate the input date
                if(idInput.isBlank() || idInput.equals("")) {
                    // do nothing
                }
                // validate input id value
                else if(Authenticator.validateID(idInput) == false) {
                    // display error message
                    JOptionPane.showMessageDialog( null, "Please input a valid ID number (8 digits)!", "Reports Error - Street Sparkle", JOptionPane.WARNING_MESSAGE );
                }
                // confirm if staff ID in database
                else if(database.confirmCasualId(Integer.parseInt(idInput)) == false) {
                    // displaying of error message 
                    JOptionPane.showMessageDialog( null, "Casual Details Not in record!", "Reports Error- Street Sparkle", JOptionPane.ERROR_MESSAGE );
                }
                // load staff details into JTextpane
                else {
                    aReport.generateACasualDetailsReport(document,Integer.parseInt(idInput),reportsTextPane);
                }
            } catch(NullPointerException e) {
                // do nothing
            }
        }
    }//GEN-LAST:event_casualsMenuItemsHandler

    private void attendanceMenuItemsHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceMenuItemsHandler
        // check if ongoing projects menu item clicked
        if(evt.getSource() == ongoingMenuItem) {
            // load attendance details
            aReport.generateAttendanceDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"ONGOING"); 
        }
        
        // check if completed projects menu item clicked
        if(evt.getSource() == completedMenuItem) {
            // load attendance details
            aReport.generateAttendanceDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"COMPLETE");
        }
    }//GEN-LAST:event_attendanceMenuItemsHandler

    private void paymentMenuItemsHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentMenuItemsHandler
        // check if  menu item clicked
        if(evt.getSource() == activePaidMenuItem) {
            // load payment details
            aReport.generatePaymentDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"ACTIVE","PAID");
        }
        
        // check if  menu item clicked
        if(evt.getSource() == activeUnpaidMenuItem) {
            // load payment details
            aReport.generatePaymentDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"ACTIVE","UNPAID");
        }
        
        // check if  menu item clicked
        if(evt.getSource() == waitingPaidMenuItem) {
            // load payment details
            aReport.generatePaymentDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"WAITING","PAID");
        }
        
        // check if  menu item clicked
        if(evt.getSource() == waitingUnpaidMenuItem) {
            // load payment details
            aReport.generatePaymentDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"WAITING","UNPAID");
        }
        
        // check if  menu item clicked
        if(evt.getSource() == termPaidMenuItem) {
            // load payment details
            aReport.generatePaymentDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"TERMINATED","PAID");
        }
        
        // check if  menu item clicked
        if(evt.getSource() == termUnpaidMenuItem) {
            // load payment details
            aReport.generatePaymentDetailsReport(document,reportsTextPane,Authenticator.getStaffSN(),"TERMINATED","UNPAID");
        }
    }//GEN-LAST:event_paymentMenuItemsHandler

    private void projectMenuItemsHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectMenuItemsHandler
        // check if  menu item clicked
        if(evt.getSource() == pendProjMenuItem) {
            // load pending project details
            aReport.generatePendingProjDetailsReport(document,reportsTextPane,Authenticator.getStaffSN());
        }
        
        // check if  menu item clicked
        if(evt.getSource() == onProjMenuItem) {
            // load ongoing project details
            aReport.generateOngoingProjDetailsReport(document,reportsTextPane,Authenticator.getStaffSN());
        }
        
        // check if  menu item clicked
        if(evt.getSource() == compProjMenuItem) {
            // load completed project details
            aReport.generateCompleteProjDetailsReport(document,reportsTextPane,Authenticator.getStaffSN());
        }
    }//GEN-LAST:event_projectMenuItemsHandler


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aCasualMenuItem;
    private javax.swing.JMenuItem activePaidMenuItem;
    private javax.swing.JMenuItem activeUnpaidMenuItem;
    private javax.swing.JMenuItem allCasualsMenuItem;
    private javax.swing.JMenu attendanceMenu;
    private javax.swing.JMenu casualsMenu;
    private javax.swing.JMenuItem compProjMenuItem;
    private javax.swing.JMenuItem completedMenuItem;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem onProjMenuItem;
    private javax.swing.JMenuItem ongoingMenuItem;
    private javax.swing.JMenu paymentMenu;
    private javax.swing.JMenuItem pendProjMenuItem;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JTextPane reportsTextPane;
    private javax.swing.JMenuItem termPaidMenuItem;
    private javax.swing.JMenuItem termUnpaidMenuItem;
    private javax.swing.JMenuItem waitingPaidMenuItem;
    private javax.swing.JMenuItem waitingUnpaidMenuItem;
    // End of variables declaration//GEN-END:variables
}
