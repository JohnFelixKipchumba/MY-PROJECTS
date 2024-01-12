/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package streetSparkle;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

/**
 *
 * @author John Felix
 */
public class MainFrameUI extends javax.swing.JFrame {
    
    public Authenticator authenticator; // class obj
    public JDialog dialog; // dialog frame obj

    /**
     * Creates new form MainFrameUI
     */
    public MainFrameUI() {
        authenticator = new Authenticator();
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

        mainFramePanel = new javax.swing.JPanel();
        mainFrameTitlePanel = new javax.swing.JPanel();
        titleLabel1 = new javax.swing.JLabel();
        titleLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        supervisorButton = new javax.swing.JButton();
        salesButton = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        humanResourceButton = new javax.swing.JButton();
        toolsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main - Street Sparkle");
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(1350, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                windowOpenedHandler(evt);
            }
        });

        mainFramePanel.setBackground(new java.awt.Color(230, 242, 254));
        mainFramePanel.setPreferredSize(new java.awt.Dimension(1393, 813));

        mainFrameTitlePanel.setBackground(new java.awt.Color(204, 255, 0));
        mainFrameTitlePanel.setPreferredSize(new java.awt.Dimension(1393, 69));

        titleLabel1.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        titleLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel1.setText("STREET SPARKLE");
        titleLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titleLabel1.setPreferredSize(new java.awt.Dimension(393, 33));

        titleLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        titleLabel2.setForeground(new java.awt.Color(243, 51, 27));
        titleLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel2.setText("STREET CLEANING MANAGEMENT SYSTEM");
        titleLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout mainFrameTitlePanelLayout = new javax.swing.GroupLayout(mainFrameTitlePanel);
        mainFrameTitlePanel.setLayout(mainFrameTitlePanelLayout);
        mainFrameTitlePanelLayout.setHorizontalGroup(
            mainFrameTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainFrameTitlePanelLayout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addGroup(mainFrameTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(titleLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(500, 500, 500))
        );
        mainFrameTitlePanelLayout.setVerticalGroup(
            mainFrameTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainFrameTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(titleLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Images/image2.jpeg"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1393, 744));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Images/image6.jpeg"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Images/image5.jpeg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 2, true));

        supervisorButton.setBackground(new java.awt.Color(102, 102, 255));
        supervisorButton.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        supervisorButton.setText("Supervisor Interface");
        supervisorButton.setToolTipText("click to load supervisor interface");
        supervisorButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 136, 11), 1, true));
        supervisorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClickedHandler(evt);
            }
        });

        salesButton.setBackground(new java.awt.Color(0, 153, 5));
        salesButton.setFont(new java.awt.Font("Viner Hand ITC", 0, 18)); // NOI18N
        salesButton.setText("Sales & Finance Interface");
        salesButton.setToolTipText("click to load sales & finance interface");
        salesButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 0), 1, true));
        salesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClickedHandler(evt);
            }
        });

        adminButton.setBackground(new java.awt.Color(0, 204, 204));
        adminButton.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        adminButton.setText("Admin");
        adminButton.setToolTipText("click to load admin interface");
        adminButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));
        adminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClickedHandler(evt);
            }
        });

        humanResourceButton.setBackground(new java.awt.Color(255, 153, 255));
        humanResourceButton.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        humanResourceButton.setText("HR Management");
        humanResourceButton.setToolTipText("click to load HR interface");
        humanResourceButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        humanResourceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClickedHandler(evt);
            }
        });

        toolsButton.setBackground(new java.awt.Color(255, 204, 51));
        toolsButton.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        toolsButton.setText("Tools Management");
        toolsButton.setToolTipText("click to load tools management interface");
        toolsButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 1, true));
        toolsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClickedHandler(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(salesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(humanResourceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(supervisorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(toolsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supervisorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toolsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(humanResourceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout mainFramePanelLayout = new javax.swing.GroupLayout(mainFramePanel);
        mainFramePanel.setLayout(mainFramePanelLayout);
        mainFramePanelLayout.setHorizontalGroup(
            mainFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainFramePanelLayout.createSequentialGroup()
                .addGroup(mainFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainFrameTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainFramePanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainFramePanelLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainFramePanelLayout.setVerticalGroup(
            mainFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainFramePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainFrameTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(mainFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainFramePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainFramePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void windowOpenedHandler(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowOpenedHandler
        // set window size to fullscreen when window is opened
        this.setExtendedState(MainFrameUI.MAXIMIZED_BOTH);       
    }//GEN-LAST:event_windowOpenedHandler

    private void buttonClickedHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClickedHandler
        // on click display the authentication interface for login, on success load UI
        if(evt.getSource() == humanResourceButton) {
           authenticatorDialog();  
           authenticator.loginHandler();
           /* if(Authenticator.getTitle().equals("HUMAN RESOURCE")) {
                authenticator.loginHandler("HUMAN RESOURCE");
           } else {
               JOptionPane.showMessageDialog( null, "Access Denied!\nConsult your Administrator.", 
                   "Street Sparkle- Authentication Error", JOptionPane.WARNING_MESSAGE);
           } */
        }
        else if(evt.getSource() == supervisorButton) {
           authenticatorDialog();
           authenticator.loginHandler();
        }
        else if(evt.getSource() == toolsButton) {
           authenticatorDialog();          
           authenticator.loginHandler();
        }
        else if(evt.getSource() == salesButton) {
           authenticatorDialog();           
           authenticator.loginHandler();
        }
        else if(evt.getSource() == adminButton) {
           authenticatorDialog();          
           authenticator.loginHandler();
        }
    }//GEN-LAST:event_buttonClickedHandler

    public void authenticatorDialog() {
        // JDialog obj instatiation
        dialog = new JDialog(this,"Authentication - Street Sparkle",true);
        dialog.getContentPane().add(new AuthenticationPanel());
        dialog.setLocation(340, 100);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
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
            java.util.logging.Logger.getLogger(MainFrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {               
                new MainFrameUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminButton;
    private javax.swing.JButton humanResourceButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainFramePanel;
    private javax.swing.JPanel mainFrameTitlePanel;
    private javax.swing.JButton salesButton;
    private javax.swing.JButton supervisorButton;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JLabel titleLabel2;
    private javax.swing.JButton toolsButton;
    // End of variables declaration//GEN-END:variables
}