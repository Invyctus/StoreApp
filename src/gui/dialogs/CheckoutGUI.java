package gui.dialogs;

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
/*
    Todo: For CheckOut
        Customer Info Tab:
            State Pattern if logged in do not display register an account
            If not logged in, Register an Account appears BUT NOT REQUIRED!!!
            Do not accept null values in the text areas (form must be filled out)
            Clean up GUI
        Payment Info Tab:
            Strategy Pattern for payment through paypal and credit card or some other means
            Alter GUI to account for the stategy pattern
            Do not allow user to continue with null values in any fields (Check customer info and payment info pane before order summary)
        Order Summary Tab:
            Has nothing on it at the moment
            Will display item price, quantity, taxes, shipping total, a subtotal before taxes & shipping (or just before taxes), an order total for everything
            Will have a submit button that once pressed will finalize the order, add everything to the database and bring up a receipt panel
            Receipt Panel will likely just be a message box with the same info as the order summary in it

        Optional:
            Customer Info Tab:
                Button to allow for login if they have an account but forgot to log in on home page
                Set next button to Checkout as Guest if no account and register and account not filled out
            Payment Info Tab:

            Order Summary Tab:

            Receipt Tab:
                Printable receipt or option to save receipt
 */
public class CheckoutGUI extends JFrame {
    //<editor-fold defaultstate="collapsed" desc="JFrame Objects">
    private JTabbedPane tbCheckOut;
    private JPanel pnlCheckout;
    private JPanel pnlCustomer;
    private JPanel pnlOrder;
    private JLabel lblFirst;
    private JLabel lblLast;
    private JLabel lblPhone;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtLast;
    private JButton btnNext;
    private JButton btnBack;
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
    private JRadioButton rbStandard;
    private JRadioButton rbPriority;
    private JRadioButton rbOvernight;
    private JTextField txtFirst;
    private JTextArea txtaCustInfo;
    private JTextArea txtaPayInfo;
    private JTextField txtaOrderInfo;
    private JButton btnComplete;
    boolean isMens;
    final static double standard = 5.00;
    final static double priority = 9.45;
    final static double overnight = 21.75;
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

        // Set radio buttons into group so only one is selectable
        ButtonGroup shippingGroup = new ButtonGroup();
        shippingGroup.add(rbStandard);
        shippingGroup.add(rbPriority);
        shippingGroup.add(rbOvernight);

       tbCheckOut.addChangeListener(new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent e) {
               if (tbCheckOut.getSelectedIndex() == 0) {
                   setSize(407, 410);
               }
               else if (tbCheckOut.getSelectedIndex() == 1) {
                   setSize(407,535);
               }
               else if (tbCheckOut.getSelectedIndex() == 2) {
                   setSize(407, 575);
               }
               else {
                   setSize(500,500);
               }
           }
       });

       chbShipping.addChangeListener(new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent e) {
               if (chbShipping.isSelected()) {
                   setBillingSameAsShipping();
               } else {
                  setAllBillingNull();
               }
           }
       });

        //<editor-fold defaultstate="collapsed" desc="Button Action Listeners">
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CartGUI cartGUI = new CartGUI();

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
        txtBAddr1.setText(txtSAddr1.getText());
        txtBAddr2.setText(txtSAddr2.getText());
        txtBCountry.setText(txtSCountry.getText());
        txtBZip.setText(txtSZip.getText());
        txtBCity.setText(txtSCity.getText());
        txtBState.setText(txtSState.getText());

    }
    public void setAllBillingNull() {
        txtBAddr1.setText("");
        txtBAddr2.setText("");
        txtBCity.setText("");
        txtBCountry.setText("");
        txtBZip.setText("");
        txtBState.setText("");

    }

}
