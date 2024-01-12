/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package streetSparkle;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author John Felix
 */
public class SUPattendancePanel extends javax.swing.JPanel {
    
    public Database database; // class obj
    public Projects aProject; // class obj
    public Casuals aCasual; // class obj

    /**
     * Creates new form SUPattendancePanel
     */
    public SUPattendancePanel() {
        /* variable instantiations */
        database = new Database();
        aProject = new Projects();
        aCasual = new Casuals();
        
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

        attendanceMainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        projectTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        casualsTable = new javax.swing.JTable();
        submitButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        selectAllCheckBox = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(1150, 700));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                componentHiddenHandler(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                componentShownHandler(evt);
            }
        });

        attendanceMainPanel.setBackground(new java.awt.Color(216, 235, 254));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 0, 0), new java.awt.Color(255, 102, 51), new java.awt.Color(255, 204, 51)), "Select a Project, then select casuals in attendance before Submitting", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 51, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Select A Project");

        projectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project S/N", "Title", "Location", "County"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        projectTable.setGridColor(new java.awt.Color(255, 51, 0));
        projectTable.setSelectionBackground(new java.awt.Color(255, 153, 0));
        projectTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        projectTable.setShowGrid(true);
        projectTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                projectTableMouseClickedHandler(evt);
            }
        });
        jScrollPane1.setViewportView(projectTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Select Casuals in attendance");

        casualsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Mid Name", "Surname", "Gender", "ID", "Phone", "Days Worked"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        casualsTable.setGridColor(new java.awt.Color(0, 255, 255));
        casualsTable.setSelectionBackground(new java.awt.Color(255, 132, 255));
        casualsTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        casualsTable.setShowGrid(true);
        jScrollPane2.setViewportView(casualsTable);

        submitButton.setBackground(new java.awt.Color(102, 255, 204));
        submitButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Icons/folder-download.png"))); // NOI18N
        submitButton.setText("Submit");
        submitButton.setToolTipText("click to submit attendance");
        submitButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonHandler(evt);
            }
        });

        resetButton.setBackground(new java.awt.Color(102, 255, 204));
        resetButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Icons/refresh.png"))); // NOI18N
        resetButton.setText("Reset");
        resetButton.setToolTipText("click to reset all fields");
        resetButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonHandler(evt);
            }
        });

        selectAllCheckBox.setFont(new java.awt.Font("News706 BT", 0, 12)); // NOI18N
        selectAllCheckBox.setForeground(new java.awt.Color(204, 0, 204));
        selectAllCheckBox.setText("Select all Casual workers");
        selectAllCheckBox.setToolTipText("click to select all casual workers in table");
        selectAllCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAllCheckBoxHandler(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectAllCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectAllCheckBox)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout attendanceMainPanelLayout = new javax.swing.GroupLayout(attendanceMainPanel);
        attendanceMainPanel.setLayout(attendanceMainPanelLayout);
        attendanceMainPanelLayout.setHorizontalGroup(
            attendanceMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attendanceMainPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        attendanceMainPanelLayout.setVerticalGroup(
            attendanceMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attendanceMainPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attendanceMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attendanceMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void componentShownHandler(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_componentShownHandler
        // clear project table data 
        DefaultTableModel model1 = ( DefaultTableModel ) projectTable.getModel();
        model1.getDataVector().removeAllElements();
        model1.fireTableDataChanged(); // notifies the JTable that the model has changed
        // clear casuals table data 
        DefaultTableModel model2 = ( DefaultTableModel ) casualsTable.getModel();
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged(); // notifies the JTable that the model has changed
        // load data into project table
        aProject.fetchAttendanceProjects(Authenticator.getStaffSN(),projectTable);
    }//GEN-LAST:event_componentShownHandler

    private void componentHiddenHandler(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_componentHiddenHandler
        // clear table and list data
        aProject.getProjectList().clear(); // clear list objects
        aCasual.getCasualList1().clear(); // clear list objects
        selectAllCheckBox.setSelected(false);
        // clear project table data 
        DefaultTableModel model1 = ( DefaultTableModel ) projectTable.getModel();
        model1.getDataVector().removeAllElements();
        model1.fireTableDataChanged(); // notifies the JTable that the model has changed
        // clear casuals table data 
        DefaultTableModel model2 = ( DefaultTableModel ) casualsTable.getModel();
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged(); // notifies the JTable that the model has changed
        // close database connection
        database.closeDB();
    }//GEN-LAST:event_componentHiddenHandler

    private void selectAllCheckBoxHandler(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAllCheckBoxHandler
        // call class Customer func to select all table rows
        if(evt.getSource() == selectAllCheckBox) {
            if(selectAllCheckBox.isSelected()) {
                aCasual.selectRows(casualsTable); // select all rows
            } else {
                ListSelectionModel model = casualsTable.getSelectionModel();
                model.clearSelection(); // clear selections
            }
        }
    }//GEN-LAST:event_selectAllCheckBoxHandler

    private void resetButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonHandler
        // clear table and list data
        aProject.getProjectList().clear(); // clear list objects
        aCasual.getCasualList1().clear(); // clear list objects
        // clear project table data 
        DefaultTableModel model1 = ( DefaultTableModel ) projectTable.getModel();
        model1.getDataVector().removeAllElements();
        model1.fireTableDataChanged(); // notifies the JTable that the model has changed
        // clear casuals table data 
        DefaultTableModel model2 = ( DefaultTableModel ) casualsTable.getModel();
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged(); // notifies the JTable that the model has changed
        // enable save button
        submitButton.setEnabled(true);
        selectAllCheckBox.setSelected(false);
    }//GEN-LAST:event_resetButtonHandler

    private void submitButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonHandler
        // record attendance to database
        aCasual.recordAttendance(projectTable, casualsTable, submitButton);
    }//GEN-LAST:event_submitButtonHandler

    private void projectTableMouseClickedHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projectTableMouseClickedHandler
        // clear casuals table data 
        DefaultTableModel model2 = ( DefaultTableModel ) casualsTable.getModel();
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged(); // notifies the JTable that the model has changed
        // load into casuals table registered casuals for the project
        int projId = aProject.selectedProjectDetails(projectTable);
        aCasual.fetchAttendanceCasuals(projId, casualsTable);
    }//GEN-LAST:event_projectTableMouseClickedHandler


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel attendanceMainPanel;
    private javax.swing.JTable casualsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable projectTable;
    private javax.swing.JButton resetButton;
    private javax.swing.JCheckBox selectAllCheckBox;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
