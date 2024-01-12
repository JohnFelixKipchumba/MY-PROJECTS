/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package streetSparkle;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

/**
 *
 * @author John Felix
 */
public class SalesReportPanel extends javax.swing.JPanel {
    
    private final StyledDocument document = new DefaultStyledDocument(); // doc handles reports
    public Database database; // class obj
    public Reports aReport; // class obj

    /**
     * Creates new form SalesReportPanel
     */
    public SalesReportPanel() {
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
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        reportsTextPane = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        printMenuItem = new javax.swing.JMenuItem();
        ProjectsMenu = new javax.swing.JMenu();
        allProjectsMenuItem = new javax.swing.JMenuItem();
        pendingProjMenuItem = new javax.swing.JMenuItem();
        ongoingProjMenuItem = new javax.swing.JMenuItem();
        completeProjMenuItem = new javax.swing.JMenuItem();
        clientsMenu = new javax.swing.JMenu();
        allClientsMenuItem = new javax.swing.JMenuItem();
        suppliersMenu = new javax.swing.JMenu();
        allSuppliersMenuItem = new javax.swing.JMenuItem();

        mainPanel.setBackground(new java.awt.Color(204, 255, 204));

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setBorder(null);
        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jInternalFrame1.setTitle("Select from menu bar a menu item");
        jInternalFrame1.setFrameIcon(null);
        jInternalFrame1.setVisible(true);

        reportsTextPane.setEditable(false);
        reportsTextPane.setBackground(new java.awt.Color(255, 255, 255));
        reportsTextPane.setDocument(document);
        reportsTextPane.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        reportsTextPane.setStyledDocument(document);
        jScrollPane2.setViewportView(reportsTextPane);

        fileMenu.setText("File  ");

        printMenuItem.setText("Print");
        fileMenu.add(printMenuItem);

        jMenuBar1.add(fileMenu);

        ProjectsMenu.setText("Projects");

        allProjectsMenuItem.setText("All Projects");
        allProjectsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProjectsMenuItemsHandler(evt);
            }
        });
        ProjectsMenu.add(allProjectsMenuItem);

        pendingProjMenuItem.setText("Pending Projects");
        pendingProjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProjectsMenuItemsHandler(evt);
            }
        });
        ProjectsMenu.add(pendingProjMenuItem);

        ongoingProjMenuItem.setText("Ongoing Projects");
        ongoingProjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProjectsMenuItemsHandler(evt);
            }
        });
        ProjectsMenu.add(ongoingProjMenuItem);

        completeProjMenuItem.setText("Completed Projects");
        completeProjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProjectsMenuItemsHandler(evt);
            }
        });
        ProjectsMenu.add(completeProjMenuItem);

        jMenuBar1.add(ProjectsMenu);

        clientsMenu.setText("Clients");

        allClientsMenuItem.setText("All clients details");
        allClientsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientsMenuItemHandler(evt);
            }
        });
        clientsMenu.add(allClientsMenuItem);

        jMenuBar1.add(clientsMenu);

        suppliersMenu.setText("Suppliers");

        allSuppliersMenuItem.setText("All suppliers details");
        allSuppliersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppliersMenuItemHandler(evt);
            }
        });
        suppliersMenu.add(allSuppliersMenuItem);

        jMenuBar1.add(suppliersMenu);

        jInternalFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1)
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

    private void ProjectsMenuItemsHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProjectsMenuItemsHandler
        // check if menu item clicked
        if(evt.getSource() == allProjectsMenuItem) {
            // load project details into JTextPane
            aReport.generateProjectDetailsReport(document, reportsTextPane);
        }
        
        // check if menu item clicked
        if(evt.getSource() == pendingProjMenuItem) {
            // load pending project details into JTextPane
            aReport.generatePendProjDetailsReport(document, reportsTextPane);
        }
        
        // check if menu item clicked
        if(evt.getSource() == ongoingProjMenuItem) {
            // load ongoing projects details into JTextPane
            aReport.generateOngoProjDetailsReport(document, reportsTextPane);
        }
        
        // check if menu item clicked
        if(evt.getSource() == completeProjMenuItem) {
            // load complete projects details into JTextPane
            aReport.generateComplProjDetailsReport(document, reportsTextPane);
        }
    }//GEN-LAST:event_ProjectsMenuItemsHandler

    private void clientsMenuItemHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientsMenuItemHandler
        // check if menu item clicked
        if(evt.getSource() == allClientsMenuItem) {
            // load all clients details into JTextPane
            aReport.generateClientDetailsReport(document, reportsTextPane);
        }
    }//GEN-LAST:event_clientsMenuItemHandler

    private void suppliersMenuItemHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppliersMenuItemHandler
        // check if menu item clicked
        if(evt.getSource() == allSuppliersMenuItem) {
            // load all suppliers details into JTextPane
            aReport.generateSupplierDetailsReport(document, reportsTextPane);
        }
    }//GEN-LAST:event_suppliersMenuItemHandler


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ProjectsMenu;
    private javax.swing.JMenuItem allClientsMenuItem;
    private javax.swing.JMenuItem allProjectsMenuItem;
    private javax.swing.JMenuItem allSuppliersMenuItem;
    private javax.swing.JMenu clientsMenu;
    private javax.swing.JMenuItem completeProjMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem ongoingProjMenuItem;
    private javax.swing.JMenuItem pendingProjMenuItem;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JTextPane reportsTextPane;
    private javax.swing.JMenu suppliersMenu;
    // End of variables declaration//GEN-END:variables
}
