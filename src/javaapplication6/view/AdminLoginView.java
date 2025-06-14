package javaapplication6.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ishan-college
 */
public class AdminLoginView extends javax.swing.JFrame {
    
    

    /**
     * Creates new form NewJFrame
     */
    public AdminLoginView() {
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

        jPanel1 = new javax.swing.JPanel();
        passPsf = new javax.swing.JPasswordField();
        showBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        signupLbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        forgetLbl = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(732, 569));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(passPsf, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 246, 30));

        showBtn.setBackground(new java.awt.Color(51, 255, 0));
        showBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        showBtn.setText("Show");
        showBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showBtnMouseClicked(evt);
            }
        });
        jPanel1.add(showBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 90, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Password");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, 30));

        signupLbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        signupLbl.setForeground(new java.awt.Color(102, 204, 0));
        signupLbl.setText("Sign Up");
        jPanel1.add(signupLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, -1, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("bookmyvenue");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 130, 20));
        jPanel1.add(emailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 246, 30));

        forgetLbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        forgetLbl.setForeground(new java.awt.Color(255, 0, 0));
        forgetLbl.setText("Forgot Password ?");
        forgetLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetLblMouseClicked(evt);
            }
        });
        jPanel1.add(forgetLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, -1, -1));

        loginBtn.setBackground(new java.awt.Color(0, 0, 0));
        loginBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("Login");
        loginBtn.setToolTipText("");
        loginBtn.setBorderPainted(false);
        loginBtn.setOpaque(true);
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 250, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Don't have an account?");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 148, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Nepal's leading Venue Booking Platform!!");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 280, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -20, 880, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_showBtnMouseClicked

    private void forgetLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetLblMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_forgetLblMouseClicked

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
            java.util.logging.Logger.getLogger(AdminLoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminLoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminLoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminLoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailTxt;
    private javax.swing.JLabel forgetLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passPsf;
    private javax.swing.JButton showBtn;
    private javax.swing.JLabel signupLbl;
    // End of variables declaration//GEN-END:variables

    public void ShowActionListener(ActionListener listener)
    {
        this.getShowBtn().addActionListener(listener);
    }
    
    public void LoginActionListener(ActionListener listener)
    {
        this.loginBtn.addActionListener(listener);
    }
    
    public void ForgetActionListener(MouseListener listener)
    {
        this.forgetLbl.addMouseListener(listener);
    }
    
    public void SignupActionListener(MouseListener listener)
    {
        this.signupLbl.addMouseListener(listener);
    }

    /**
     * @return the emailTxt
     */
    public javax.swing.JTextField getEmailTxt() {
        return emailTxt;
    }

    /**
     * @param emailTxt the emailTxt to set
     */
    public void setEmailTxt(javax.swing.JTextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    /**
     * @return the passPsf
     */
    public javax.swing.JPasswordField getPassPsf() {
        return passPsf;
    }

    /**
     * @param passPsf the passPsf to set
     */
    public void setPassPsf(javax.swing.JPasswordField passPsf) {
        this.passPsf = passPsf;
    }

    /**
     * @return the showBtn
     */
    public javax.swing.JButton getShowBtn() {
        return showBtn;
    }

    /**
     * @param showBtn the showBtn to set
     */
    public void setShowBtn(javax.swing.JButton showBtn) {
        this.showBtn = showBtn;
    }
    
    

    
    
}
