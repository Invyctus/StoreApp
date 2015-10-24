package gui.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

/**
 * Author: John Russell
 * Date Created: 10/24/2015
 * Georgia Southern University - 900743229
 */
public class SignUpGUI extends JFrame{
    //<editor-fold desc="JFrame Objects">
    private JPanel pnlSignUp;
    private JPanel pnlSignup;
    private JLabel lblFirst;
    private JLabel lblLast;
    private JLabel lblPhone;
    private JLabel lblRegister;
    private JLabel lblUser;
    private JLabel lblPass;
    private JTextField txtPhone;
    private JTextField txtLast;
    private JTextField txtEmail;
    private JLabel lblAddress;
    private JLabel lblState;
    private JLabel lblCity;
    private JLabel lblZip;
    private JTextField txtCity;
    private JTextField txtAddr2;
    private JTextField txtAddr1;
    private JLabel lblCountry;
    private JTextField txtCountry;
    private JTextField txtState;
    private JLabel lblShipping;
    private JTextField txtFirst;
    private JLabel lblInfo;
    private JTextField txtZip;
    private JPasswordField pwdPass;
    private JTextField txtUser;
    private JPasswordField pwdVerify;
    private JButton btnCancel;
    private JButton btnSubmit;
    private JLabel lblVerify;
    //</editor-fold>

    public SignUpGUI() {
        super("SignUp");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(pnlSignUp);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        //<editor-fold desc="Listeners">
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(pwdPass.getPassword(), pwdVerify.getPassword())) {
                    if(pwdPass.getPassword().length != 0) {
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Password can't be empty");
                        pwdPass.grabFocus();
                        lblPass.setForeground(Color.red);
                        lblVerify.setForeground(Color.red);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords don't match");
                    pwdPass.grabFocus();
                    pwdPass.setText(null);
                    pwdVerify.setText(null);
                    lblPass.setForeground(Color.red);
                    lblVerify.setForeground(Color.red);
                }

            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        txtEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                txtUser.setText(txtEmail.getText());
            }
        });
        //</editor-fold>
    }


}
