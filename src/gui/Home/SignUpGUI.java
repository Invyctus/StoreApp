package gui.Home;

import utils.FieldVerification;
import utils.IODB;
import utils.Processes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles SignUp process
 * Verification on fields
 * Adds customer to database upon success
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

                if(Processes.verifySignup(pwdPass.getPassword(), pwdVerify.getPassword(), 0, txtPhone.getText(), txtAddr1.getText(), txtEmail.getText(), txtZip.getText(),
                                        txtFirst.getText(), txtLast.getText(), txtState.getText(), txtCity.getText(), txtCountry.getText(), txtAddr2.getText())) {
                    setVisible(false);
                    HomeGUI homeGUI = new HomeGUI();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomeGUI homeGUI = new HomeGUI();
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

    private void invalid(String ... args) {

    }


}
