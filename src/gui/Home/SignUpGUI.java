package gui.Home;

import utils.FieldVerification;
import utils.IODB;

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

               if(!FieldVerification.verifyPhone(txtPhone.getText())
                       || !FieldVerification.verifyAddress(txtAddr1.getText())
                       || !FieldVerification.verifyEmail(txtEmail.getText())
                       || !FieldVerification.verifyZip(txtZip.getText())
                       || !FieldVerification.verifyTextInput(txtFirst.getText())
                       || !FieldVerification.verifyTextInput(txtLast.getText())
                       || !FieldVerification.verifyTextInput(txtState.getText())
                       || !FieldVerification.verifyTextInput(txtCity.getText())
                       || !FieldVerification.verifyTextInput(txtCountry.getText())
                       || !FieldVerification.verifyPassword(pwdPass.getPassword(), pwdVerify.getPassword())) {


               } else {
                   String insertCustomer = "INSERT INTO CUSTOMERS (CUSTFIRSTNAME, CUSTLASTNAME, CUSTEMAIL, CUSTPHONE) VALUES ('" + txtFirst.getText() + "',"
                           + " '" + txtLast.getText() + "'," + " '" + txtEmail.getText() + "'," + " '" + txtPhone.getText() + "')";
                   IODB.executeQueries(insertCustomer);
                   char[] pass = pwdPass.getPassword();
                   String passString = new String(pass);

                   String getCustID = "SELECT MAX(CUSTID) FROM CUSTOMERS";
                   ArrayList<ArrayList<Object>> retrieveID = IODB.getQueryResults(getCustID);

                   for (ArrayList<Object> arrayList : retrieveID) {
                       for (Object o : arrayList) {
                           String insertAccount = "INSERT INTO ACCOUNT (CUSTEMAIL, PASSWORD, CUSTID)  VALUES ('" + txtEmail.getText() + "',"
                                   + " '" + passString + "'," + " '" + o.toString() + "')";

                           String insertAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + txtState.getText() + "',"
                                   + " '" + txtCity.getText() + "'," + " '" + txtCountry.getText() +  "'," + " '" + txtZip.getText() + "'," + " '" + txtAddr1.getText() + "',"
                                   +  " '" + o.toString() + "'," + " '" + txtAddr2.getText() + "')";

                           IODB.executeQueries(insertAccount);
                           IODB.executeQueries(insertAddress);
                       }
                   }
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
