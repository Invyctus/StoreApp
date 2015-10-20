package gui.dialogs;

import gui.HomeGUI;
import gui.ShirtsPageGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: John Russell
 * Date Created: 10/20/2015
 * Georgia Southern University - 900743229
 */
public class CheckoutGUI extends JFrame {
    //<editor-fold desc="JFrame Objects">
    private JTabbedPane tbCheckOut;
    private JPanel pnlCheckout;
    private JPanel pnlCustomer;
    private JPanel pnlOrder;
    private JPanel pnlReceipt;
    private JTextField txtFirst;
    private JLabel lblFirst;
    private JLabel lblLast;
    private JLabel lblPhone;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblRegister;
    private JLabel lblUser;
    private JLabel lblPass;
    private JTextField textField1;
    private JTextField txtPhone;
    private JTextField txtLast;
    private JButton btnNext;
    private JButton btnBack;
    private JTextField txtUser;
    private JTextField txtSState;
    private JLabel lblCountry;
    private JLabel lblZip;
    private JLabel lblState;
    private JLabel lblAddress;
    private JLabel lblCity;
    private JTextField txtSZip;
    private JTextField txtSCountry;
    private JTextField txtSCity;
    private JTextField txtSAddr2;
    private JTextField txtSAddr1;
    private JLabel lblShipping;
    private JLabel lblInfo;
    private JPanel pnlPayment;
    private JComboBox cbYear;
    private JComboBox cbMonth;
    private JButton btnNext2;
    private JButton btnBack2;
    private JTextField txtCardName;
    private JTextField txtCardNum;
    private JTextField txtCardCCV;
    private JTextField txtBAddr1;
    private JTextField txtBAddr2;
    private JTextField txtBCity;
    private JTextField txtBCountry;
    private JTextField txtBState;
    private JTextField txtBZip;
    private JCheckBox chbShipping;
    boolean isMens;
    //</editor-fold>

    /**
     * Todo: State pattern if logged in do not display register an account
     * Button: Next if logged in
     * Button: Continue as guest if not logged in
     * Todo: Payment method strategy pattern for accepting Paypal & CreditCards (master,visa, aex,etc...)
     */
    public CheckoutGUI() {
        super("Checkout");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlCheckout);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

       tbCheckOut.addChangeListener(new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent e) {
               if (tbCheckOut.getSelectedIndex() == 0) {
                   setSize(407, 538);
               }
               else if (tbCheckOut.getSelectedIndex() == 1) {
                   setSize(407,425);
               }
               else if (tbCheckOut.getSelectedIndex() == 2) {
                   setSize(500,500);
               }
               else {
                   setSize(500,500);
               }
           }
       });
        /**
         * Todo: if is checked populate billing with shipping info
         */
        chbShipping.isSelected();
        //<editor-fold desc="Button Action Listeners">
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomeGUI homeGUI = new HomeGUI();

            }
        });
        btnBack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbCheckOut.setSelectedIndex(0);

            }
        });
        /**
         * Todo: Should only be allowable once all fields contain input
         * Registration fields not required
         */
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbCheckOut.setEnabledAt(1, true);
                tbCheckOut.setSelectedIndex(1);

            }
        });
        btnNext2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbCheckOut.setEnabledAt(2, true);
                tbCheckOut.setSelectedIndex(2);


            }
        });
        //</editor-fold>
    }
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    /**
     * Method to set the billing same as shipping
     */
    public void setBillingSameAsShipping() {
        txtBAddr1.setText(String.valueOf(txtSAddr1));
        txtBAddr2.setText(String.valueOf(txtSAddr2));
        txtBCountry.setText(String.valueOf(txtSCountry));
        txtBZip.setText(String.valueOf(txtSZip));
        txtBCity.setText(String.valueOf(txtSCity));

    }

}
