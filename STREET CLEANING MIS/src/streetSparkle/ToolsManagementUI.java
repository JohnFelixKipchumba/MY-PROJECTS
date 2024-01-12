/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package streetSparkle;

import java.awt.CardLayout;
import java.awt.Color;

/**
 *
 * @author John Felix
 */
public class ToolsManagementUI extends javax.swing.JFrame {

    public CardLayout cardLayout; // layout manager for changing panels on button click
    public ToolsPanel toolsPanel; // panel obj
    public UserProfilePanel profilePanel; // panel obj
    public ToolsReportPanel reportPanel; // panel obj
    
    /**
     * Creates new form ToolsManagementUI
     */
    public ToolsManagementUI() {
        /* variable instatiation */
        cardLayout = new CardLayout();
        toolsPanel = new ToolsPanel();
        profilePanel = new UserProfilePanel();
        reportPanel = new ToolsReportPanel();
        
        initComponents(); // method invokation to set GUI components
        
        toolsMainPanel.setLayout(cardLayout);
        toolsMainPanel.add(toolsPanel,"panel1"); // add panel obj for tools button click
        toolsMainPanel.add(reportPanel,"panel2"); // add panel obj for reports button click
        toolsMainPanel.add(profilePanel,"panel3"); // add panel obj for profil button click
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolsFramePanel = new javax.swing.JPanel();
        toolsNavigationPanel = new javax.swing.JPanel();
        navReportsButton = new javax.swing.JButton();
        navProfileButton = new javax.swing.JButton();
        navToolsButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        toolsMainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tools Management - Street Sparkle");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(1350, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                windowOpenedHandler(evt);
            }
        });

        toolsFramePanel.setBackground(new java.awt.Color(255, 255, 255));

        toolsNavigationPanel.setBackground(new java.awt.Color(255, 204, 51));
        toolsNavigationPanel.setPreferredSize(new java.awt.Dimension(201, 801));

        navReportsButton.setBackground(new java.awt.Color(255, 204, 51));
        navReportsButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        navReportsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Icons/chart-histogram.png"))); // NOI18N
        navReportsButton.setText("Reports");
        navReportsButton.setToolTipText("click to view tool reports");
        navReportsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51), 3));
        navReportsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        navReportsButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                navigationButtonFocusGainedHandler(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                navigationButtonFocusLostHandler(evt);
            }
        });
        navReportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navReportsButtonHandler(evt);
            }
        });

        navProfileButton.setBackground(new java.awt.Color(255, 204, 51));
        navProfileButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        navProfileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Icons/user.png"))); // NOI18N
        navProfileButton.setText("Profile");
        navProfileButton.setToolTipText("click to view your profile");
        navProfileButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51), 3));
        navProfileButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        navProfileButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                navigationButtonFocusGainedHandler(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                navigationButtonFocusLostHandler(evt);
            }
        });
        navProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navProfileButtonHandler(evt);
            }
        });

        navToolsButton.setBackground(new java.awt.Color(255, 204, 51));
        navToolsButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        navToolsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Icons/wrench-simple(1).png"))); // NOI18N
        navToolsButton.setText("Tools");
        navToolsButton.setToolTipText("click to manage tools");
        navToolsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51), 3));
        navToolsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        navToolsButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                navigationButtonFocusGainedHandler(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                navigationButtonFocusLostHandler(evt);
            }
        });
        navToolsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navToolsButtonHandler(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel1.setText("Tools Management");

        javax.swing.GroupLayout toolsNavigationPanelLayout = new javax.swing.GroupLayout(toolsNavigationPanel);
        toolsNavigationPanel.setLayout(toolsNavigationPanelLayout);
        toolsNavigationPanelLayout.setHorizontalGroup(
            toolsNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navReportsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navToolsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(toolsNavigationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        toolsNavigationPanelLayout.setVerticalGroup(
            toolsNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolsNavigationPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(navToolsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(navReportsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(navProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );

        toolsMainPanel.setBackground(new java.awt.Color(255, 244, 214));
        toolsMainPanel.setToolTipText("");
        toolsMainPanel.setPreferredSize(new java.awt.Dimension(1150, 700));

        javax.swing.GroupLayout toolsMainPanelLayout = new javax.swing.GroupLayout(toolsMainPanel);
        toolsMainPanel.setLayout(toolsMainPanelLayout);
        toolsMainPanelLayout.setHorizontalGroup(
            toolsMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1174, Short.MAX_VALUE)
        );
        toolsMainPanelLayout.setVerticalGroup(
            toolsMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout toolsFramePanelLayout = new javax.swing.GroupLayout(toolsFramePanel);
        toolsFramePanel.setLayout(toolsFramePanelLayout);
        toolsFramePanelLayout.setHorizontalGroup(
            toolsFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolsFramePanelLayout.createSequentialGroup()
                .addComponent(toolsNavigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolsMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE))
        );
        toolsFramePanelLayout.setVerticalGroup(
            toolsFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolsNavigationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolsMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolsFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolsFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void windowOpenedHandler(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowOpenedHandler
        // set window size to fullscreen when window is opened
        this.setExtendedState(ToolsManagementUI.MAXIMIZED_BOTH); 
    }//GEN-LAST:event_windowOpenedHandler

    private void navigationButtonFocusGainedHandler(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_navigationButtonFocusGainedHandler
        // change colour on focus
        if(evt.getSource() == navToolsButton) {
           navToolsButton.setBackground(new Color(0,204,255));
        }
        else if(evt.getSource() == navReportsButton) {
           navReportsButton.setBackground(new Color(0,204,255)); 
        }
        else if(evt.getSource() == navProfileButton) {
           navProfileButton.setBackground(new Color(0,204,255)); 
        }
    }//GEN-LAST:event_navigationButtonFocusGainedHandler

    private void navigationButtonFocusLostHandler(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_navigationButtonFocusLostHandler
        // change colour on focus lost
        if(evt.getSource() == navToolsButton) {
            navToolsButton.setBackground(new Color(255,204,51));
        }
        else if(evt.getSource() == navReportsButton) {
            navReportsButton.setBackground(new Color(255,204,51));
        }
        else if(evt.getSource() == navProfileButton) {
            navProfileButton.setBackground(new Color(255,204,51));
        }
    }//GEN-LAST:event_navigationButtonFocusLostHandler

    private void navToolsButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navToolsButtonHandler
        // display panel1
        cardLayout.show(toolsMainPanel,"panel1");
    }//GEN-LAST:event_navToolsButtonHandler

    private void navReportsButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navReportsButtonHandler
        // display panel2
        cardLayout.show(toolsMainPanel,"panel2");
    }//GEN-LAST:event_navReportsButtonHandler

    private void navProfileButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navProfileButtonHandler
        // display panel3
        cardLayout.show(toolsMainPanel,"panel3");
    }//GEN-LAST:event_navProfileButtonHandler

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ToolsManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToolsManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToolsManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToolsManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToolsManagementUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton navProfileButton;
    private javax.swing.JButton navReportsButton;
    private javax.swing.JButton navToolsButton;
    private javax.swing.JPanel toolsFramePanel;
    private javax.swing.JPanel toolsMainPanel;
    private javax.swing.JPanel toolsNavigationPanel;
    // End of variables declaration//GEN-END:variables
}
