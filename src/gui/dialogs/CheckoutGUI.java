package gui.dialogs;

import utils.CalcTotals;
import utils.FieldVerification;
import utils.IODB;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles checkout process information gathering
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
    private JTextArea txtaOrderInfo;
    private JButton btnComplete;
    private JButton btnBack3;
    boolean isMens;
    public double tax;
    public double shippingCost;
    public double finalTotal;
    public double itemTotal;
    final static double standard = 5.00;
    final static double priority = 9.45;
    final static double overnight = 21.75;
    //</editor-fold>

    public CheckoutGUI() {
        super("Checkout");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlCheckout);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        tbCheckOut.setEnabledAt(0, true);

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
                tbCheckOut.setEnabledAt(1, false);
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!FieldVerification.verifyPhone(txtPhone.getText())
                        || !FieldVerification.verifyAddress(txtSAddr1.getText())
                        || !FieldVerification.verifyEmail(txtEmail.getText())
                        || !FieldVerification.verifyZip(txtSZip.getText())
                        || !FieldVerification.verifyTextInput(txtFirst.getText())
                        || !FieldVerification.verifyTextInput(txtLast.getText())
                        || !FieldVerification.verifyTextInput(txtSState.getText())
                        || !FieldVerification.verifyTextInput(txtSCity.getText())
                        || !FieldVerification.verifyTextInput(txtSCountry.getText())) {

                } else {
                    tbCheckOut.setEnabledAt(1, true);
                    tbCheckOut.setSelectedIndex(1);
                }
            }
        });
        btnNext2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!FieldVerification.verifyCCName(txtCardName.getText())
                        || !FieldVerification.verifyCCNum(txtCardNum.getText())
                        || !FieldVerification.verifyCCV(txtCardCCV.getText())
                        || !FieldVerification.verifyAddress(txtBAddr1.getText())
                        || !FieldVerification.verifyZip(txtBZip.getText())
                        || !FieldVerification.verifyTextInput(txtBState.getText())
                        || !FieldVerification.verifyTextInput(txtBCity.getText())
                        || !FieldVerification.verifyTextInput(txtBCountry.getText())) {

                } else {
                    tbCheckOut.setEnabledAt(2, true);
                    tbCheckOut.setSelectedIndex(2);
                    updateCust();
                    updatePay();
                    updateOrder();
                }
            }
        });

        btnComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your order has been placed successfully!");

                completeOrder();
            }
        });

        btnBack3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbCheckOut.setSelectedIndex(1);
                tbCheckOut.setEnabledAt(3, false);
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
    public void updateCust() {
        txtaCustInfo.setText("");
        txtaCustInfo.append("Your Info" + "\n" + "-------------------" + "\n" + txtFirst.getText() + "\n" + txtLast.getText() + "\n"
                + txtEmail.getText() + "\n"  + txtPhone.getText() + "\n"  + "Shipping Address" + "\n" + "-------------------" + "\n"
                + txtSAddr1.getText() + "\n" + txtSAddr2.getText() + "\n" + txtSCity.getText() + "\n"  + txtSState.getText() + "\n"
                + txtSZip.getText() + "\n"  + txtSCountry.getText() + "\n");
    }
    public void updatePay() {
        txtaPayInfo.setText("");
        txtaPayInfo.append("Payment Info" + "\n" + "-------------------" + "\n" + txtCardName.getText() + "\n" + txtCardNum.getText()
                + "\n"  + txtCardCCV.getText() + "\n"  + cbMonth.getSelectedItem().toString() + "/" + cbYear.getSelectedItem().toString()
                +  "\n" + "Billing Address" + "\n" + "-------------------" + "\n" + txtBAddr1.getText() + "\n" + txtBAddr2.getText() + "\n" + txtBCity.getText() + "\n"
                + txtBState.getText() + "\n"  + txtBZip.getText() + "\n"  + txtBCountry.getText() + "\n");
    }
    public void updateOrder() {
        String shipping;

        txtaOrderInfo.setText("");
         if(rbOvernight.isSelected()) {
             shipping = rbOvernight.getText();
             setShippingCost(21.75);
         } else if (rbPriority.isSelected()) {
             shipping = rbPriority.getText();
             setShippingCost(9.45);
         } else {
             shipping = rbStandard.getText();
             setShippingCost(4.95);
         }
         setItemTotal(CalcTotals.getItemTotal());
         setTax(CalcTotals.getTax(itemTotal));
         setFinalTotal(CalcTotals.getFinal(shippingCost, tax, itemTotal));

            txtaOrderInfo.append("Shipping Method: " + shipping + "\n"
                                + "Item Total: $" + itemTotal + "\n"
                                + "Shipping Total: $" + shippingCost + "\n"
                                + "Tax: $" + tax + "\n"
                                + "--------------------------\n"
                                + "Order Total: $" + finalTotal + "\n");

    }
    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }
    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }
    public void completeOrder() {
        String custid = FieldVerification.getCustid();

        if (chbShipping.isSelected()) {
            String insertAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + txtSState.getText() + "', '" + txtSCity.getText() + "', '"
                    + txtSCountry.getText() + "', '" + txtSZip.getText() + "', '" + txtSAddr1.getText() + "', '" + custid + "', '" + txtSAddr2.getText() + "')";

            IODB.executeQueries();
        } else {
            String insertSAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + txtSState.getText() + "', '" + txtSCity.getText() + "', '"
                    + txtSCountry.getText() + "', '" + txtSZip.getText() + "', '" + txtSAddr1.getText() + "', '" + custid + "', '" + txtSAddr2.getText() + "')";
            String insertBAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + txtBState.getText() + "', '" + txtBCity.getText() + "', '"
                    + txtBCountry.getText() + "', '" + txtBZip.getText() + "', '" + txtBAddr1.getText() + "', '" + custid + "', '" + txtBAddr2.getText() + "')";

            IODB.executeQueries(insertSAddress);
            IODB.executeQueries(insertBAddress);
        }
        String insertPaymentMethod = "INSERT INTO PAYMENT_METHOD ( CUSTID, PAYMENTMETHOD) VALUES ('" + custid + "', '" + txtCardName.getText() + ", "
                + txtCardNum.getText() + ", " + txtCardCCV.getText() + "')";
        IODB.executeQueries(insertPaymentMethod);

        String insertOrder = "INSERT INTO ORDERS (ORDERSUBTOTAL, ORDERTAX, ORDERTOTAL, CUSTID) VALUES ('" + itemTotal + "', '" + tax + "', '"
                + finalTotal + "', '" + custid + "')";
        IODB.executeQueries(insertOrder);

        String insertPayment = "INSERT INTO PAYMENT (PAYMENTMETHOD) VALUES ('" + txtCardName.getText() + ", " + txtCardNum.getText() + ", " + txtCardCCV.getText() + "')";

        String clearIt = "delete from cart where custid = " + custid;
        IODB.executeQueries(clearIt);
    }
}
