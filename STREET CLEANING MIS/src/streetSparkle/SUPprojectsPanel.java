/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package streetSparkle;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author John Felix
 */
public class SUPprojectsPanel extends javax.swing.JPanel {
    
    public Database database; // class obj
    public Projects aProject; // class obj
    public Casuals aCasual; // class obj

    /**
     * Creates new form SUPprojectsPanel
     */
    public SUPprojectsPanel() {
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

        fileChooser = new javax.swing.JFileChooser();
        projectsMainPanel = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        projectStaffingTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        casualStaffingTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        resetStaffingButton = new javax.swing.JButton();
        submitStaffingButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        projectPicTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        beforePicButton = new javax.swing.JButton();
        afterPicButton = new javax.swing.JButton();
        resetPicButton = new javax.swing.JButton();

        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fileChooser.setDialogTitle("Upload Project Pictures");
        fileChooser.setFileFilter(new MyCustomFilter());
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                componentHiddenHandler(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                componentShownHandler(evt);
            }
        });

        projectsMainPanel.setBackground(new java.awt.Color(216, 235, 254));

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 255, 0), new java.awt.Color(51, 204, 0)), "Select a Project, then select Casuals to staff the project", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        projectStaffingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Location", "County", "Start", "End", "Staffing No.", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        projectStaffingTable.setGridColor(new java.awt.Color(255, 0, 51));
        projectStaffingTable.setSelectionBackground(new java.awt.Color(255, 102, 204));
        projectStaffingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        projectStaffingTable.setShowGrid(true);
        projectStaffingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                projectStaffingTableMouserClickedHandler(evt);
            }
        });
        jScrollPane1.setViewportView(projectStaffingTable);

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 0));
        jLabel1.setText("Select a Project");

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 0));
        jLabel2.setText("Select Casuals to staff above selected project");

        casualStaffingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Mid Name", "Surname", "Gender", "ID", "Phone", "County", "Hired Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        casualStaffingTable.setGridColor(new java.awt.Color(255, 0, 0));
        casualStaffingTable.setSelectionBackground(new java.awt.Color(255, 153, 51));
        casualStaffingTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        casualStaffingTable.setShowGrid(true);
        jScrollPane2.setViewportView(casualStaffingTable);

        jSeparator1.setBackground(new java.awt.Color(255, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(255, 0, 0));

        resetStaffingButton.setBackground(new java.awt.Color(255, 204, 0));
        resetStaffingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resetStaffingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Icons/refresh(1).png"))); // NOI18N
        resetStaffingButton.setText("Reset");
        resetStaffingButton.setToolTipText("click to reset tables");
        resetStaffingButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        resetStaffingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetStaffingButtonHandler(evt);
            }
        });

        submitStaffingButton.setBackground(new java.awt.Color(255, 204, 0));
        submitStaffingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        submitStaffingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Icons/folder-download(1).png"))); // NOI18N
        submitStaffingButton.setText("Submit");
        submitStaffingButton.setToolTipText("click to submit selected casuals for project");
        submitStaffingButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        submitStaffingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitStaffingButtonHandler(evt);
            }
        });

        projectPicTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Location", "County", "Start", "End", "Status", "Before Pic", "After Pic"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        projectPicTable.setGridColor(new java.awt.Color(0, 255, 0));
        projectPicTable.setSelectionBackground(new java.awt.Color(51, 153, 0));
        projectPicTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        projectPicTable.setShowGrid(true);
        jScrollPane3.setViewportView(projectPicTable);

        jLabel3.setFont(new java.awt.Font("Monotype Corsiva", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("From this table select a Project to update its Images");

        beforePicButton.setBackground(new java.awt.Color(204, 255, 102));
        beforePicButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        beforePicButton.setText("Upload Before Pic");
        beforePicButton.setToolTipText("click to upload before pic of selected project");
        beforePicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beforePicButtonHandler(evt);
            }
        });

        afterPicButton.setBackground(new java.awt.Color(204, 255, 102));
        afterPicButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        afterPicButton.setText("Upload After Pic");
        afterPicButton.setToolTipText("click to upload after pic for selected project");
        afterPicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afterPicButtonHandler(evt);
            }
        });

        resetPicButton.setBackground(new java.awt.Color(204, 255, 102));
        resetPicButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resetPicButton.setText("Reset");
        resetPicButton.setToolTipText("click to reset table selection");
        resetPicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPicButtonHandler(evt);
            }
        });

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tablePanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tablePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resetStaffingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211)
                .addComponent(submitStaffingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(beforePicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addGap(855, 855, 855)
                        .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(afterPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(submitStaffingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(resetStaffingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(beforePicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(afterPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout projectsMainPanelLayout = new javax.swing.GroupLayout(projectsMainPanel);
        projectsMainPanel.setLayout(projectsMainPanelLayout);
        projectsMainPanelLayout.setHorizontalGroup(
            projectsMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectsMainPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        projectsMainPanelLayout.setVerticalGroup(
            projectsMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectsMainPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projectsMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projectsMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void beforePicButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beforePicButtonHandler
        // confirm item selected from table
        if( projectPicTable.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select a Project from Table!","Project Images Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        } else {
            // get project id to submit image from selected project
            int projectId = aProject.selectedProjectImages(projectPicTable);
            // call to new file chooser
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // display selected image file into profilepic jlabel
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = bufferedImage.getScaledInstance(170, 170, Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(image);
                    //profilePicLabel.setIcon(icon);
                    // store selected image file into database
                    database.updateProjectBeforePic(file.toString(), projectId);

                } catch (IOException ex) {
                    // display warning message
                    JOptionPane.showMessageDialog( null, "Problem accessing file: " + file.getAbsolutePath(),"Project Warning - Street Sparkle", JOptionPane.WARNING_MESSAGE );
                } catch (IllegalArgumentException | NullPointerException e) {
                    // display warning message
                    JOptionPane.showMessageDialog( null, "Please select Image Files Only!","Project Warning - Street Sparkle", JOptionPane.WARNING_MESSAGE );              
                }
            } else {
                //System.out.println("File access cancelled by user.");
                // do nothing
            }
        } // end outer else
        //Reset the file chooser for the next time it's shown.
        fileChooser.setSelectedFile(null);

    }//GEN-LAST:event_beforePicButtonHandler

    private void afterPicButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afterPicButtonHandler
        // confirm item selected from table
        if( projectPicTable.getSelectionModel().isSelectionEmpty() == true ) {
            // displaying of NULL items selected error message */
            JOptionPane.showMessageDialog( null, "Select a Project from Table!","Project Images Error- Street Sparkle", JOptionPane.WARNING_MESSAGE );           
        } else {
            // get project id to submit image from selected project
            int projectId = aProject.selectedProjectImages(projectPicTable);
            // call to new file chooser
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // display selected image file into profilepic jlabel
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = bufferedImage.getScaledInstance(170, 170, Image.SCALE_DEFAULT);
                    ImageIcon icon = new ImageIcon(image);
                    //profilePicLabel.setIcon(icon);
                    // store selected image file into database
                    database.updateProjectAfterPic(file.toString(), projectId);

                } catch (IOException ex) {
                    // display warning message
                    JOptionPane.showMessageDialog( null, "Problem accessing file: " + file.getAbsolutePath(),"Project Warning - Street Sparkle", JOptionPane.WARNING_MESSAGE );
                } catch (IllegalArgumentException | NullPointerException e) {
                    // display warning message
                    JOptionPane.showMessageDialog( null, "Please select Image Files Only!","Project Warning - Street Sparkle", JOptionPane.WARNING_MESSAGE );              
                } 
            } else {
                //System.out.println("File access cancelled by user.");
                // do nothing
            }
        } // end outer else
        //Reset the file chooser for the next time it's shown.
        fileChooser.setSelectedFile(null);
    }//GEN-LAST:event_afterPicButtonHandler

    private void componentShownHandler(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_componentShownHandler
        // load data into project table
        aProject.fetchStaffingProject(Authenticator.getStaffSN(),projectStaffingTable);
        aProject.fetchProjectImages(Authenticator.getStaffSN(), projectPicTable);
    }//GEN-LAST:event_componentShownHandler

    private void componentHiddenHandler(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_componentHiddenHandler
        // clear table and list data
        aProject.getProjectList().clear(); // clear list objects
        aProject.getProjectList2().clear(); // clear list objects
        aCasual.getCasualList2().clear(); // clear list objects
        // clear project table data 
        DefaultTableModel model1 = ( DefaultTableModel ) projectStaffingTable.getModel();
        model1.getDataVector().removeAllElements();
        model1.fireTableDataChanged(); // notifies the JTable that the model has changed
        // clear casuals table data 
        DefaultTableModel model2 = ( DefaultTableModel ) casualStaffingTable.getModel();
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged(); // notifies the JTable that the model has changed
        // clear project pic table data 
        DefaultTableModel model3 = ( DefaultTableModel ) projectPicTable.getModel();
        model3.getDataVector().removeAllElements();
        model3.fireTableDataChanged(); // notifies the JTable that the model has changed
        // close database connection
        //database.closeDB();
    }//GEN-LAST:event_componentHiddenHandler

    private void projectStaffingTableMouserClickedHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projectStaffingTableMouserClickedHandler
        // clear casuals table data 
        DefaultTableModel model2 = ( DefaultTableModel ) casualStaffingTable.getModel();
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged(); // notifies the JTable that the model has changed
        // load into casuals table waiting casuals
        //int projId = aProject.selectedProjectDetails(projectStaffingTable);
        aCasual.fetchStaffingCasuals(Authenticator.getCounty(), casualStaffingTable);
    }//GEN-LAST:event_projectStaffingTableMouserClickedHandler

    private void resetStaffingButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetStaffingButtonHandler
        // clear table and list data
        aProject.getProjectList().clear(); // clear list objects
        aCasual.getCasualList2().clear(); // clear list objects
        // clear project table data 
        DefaultTableModel model1 = ( DefaultTableModel ) projectStaffingTable.getModel();
        model1.getDataVector().removeAllElements();
        model1.fireTableDataChanged(); // notifies the JTable that the model has changed
        // clear casuals table data 
        DefaultTableModel model2 = ( DefaultTableModel ) casualStaffingTable.getModel();
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged(); // notifies the JTable that the model has changed
        // enable save button
        submitStaffingButton.setEnabled(true);
    }//GEN-LAST:event_resetStaffingButtonHandler

    private void submitStaffingButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitStaffingButtonHandler
        // record project staffing to database
        aCasual.recordStaffingCasuals(projectStaffingTable, casualStaffingTable, submitStaffingButton);
    }//GEN-LAST:event_submitStaffingButtonHandler

    private void resetPicButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPicButtonHandler
        // clear table and list data
        aProject.getProjectList2().clear(); // clear list objects
        // clear casuals table data 
        DefaultTableModel model = ( DefaultTableModel ) projectPicTable.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); // notifies the JTable that the model has changed
        // enable update buttons
        beforePicButton.setEnabled(true);
        afterPicButton.setEnabled(true);
    }//GEN-LAST:event_resetPicButtonHandler


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton afterPicButton;
    private javax.swing.JButton beforePicButton;
    private javax.swing.JTable casualStaffingTable;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable projectPicTable;
    private javax.swing.JTable projectStaffingTable;
    private javax.swing.JPanel projectsMainPanel;
    private javax.swing.JButton resetPicButton;
    private javax.swing.JButton resetStaffingButton;
    private javax.swing.JButton submitStaffingButton;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}


/**
*   new MyCustomFilter() class
**/
class MyCustomFilter extends javax.swing.filechooser.FileFilter {

    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String TIFF = "tiff";
    public final static String TIF = "tif";
    public final static String PNG = "png";

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    //Accept all directories and all gif, jpg, tiff, or png files
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            return extension.equals(TIFF) ||
                    extension.equals(TIF) ||
                    extension.equals(GIF) ||
                    extension.equals(JPEG) ||
                    extension.equals(JPG) ||
                    extension.equals(PNG);
        }

        return false;
    }

    //The description of this filter
    @Override
    public String getDescription() {
        return "Just Images";
    }

 } // end class MyCustomFilter