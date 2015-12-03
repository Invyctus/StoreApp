package gui.dialogs;

import utils.CalcTotals;
import utils.FieldVerification;
import utils.IODB;
import utils.Processes;

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
    //</editor-fold>

    public CheckoutGUI() {
        super("Checkout");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlCheckout);
        setResizable(false);
        tbCheckOut.setEnabledAt(0, true);
        String[] info = Processes.getCustomerInfo();
        setCustomerInfo(info);
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
                tbCheckOut.setEnabledAt(1, false);
                tbCheckOut.setEnabledAt(0, true);
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] test = {'t'};
                if (Processes.verifySignup(test, test , 1, txtPhone.getText(), txtSAddr1.getText(), txtEmail.getText(), txtSZip.getText(),
                        txtFirst.getText(), txtLast.getText(), txtSState.getText(), txtSCity.getText(), txtSCountry.getText())) {
                    tbCheckOut.setEnabledAt(1, true);
                    tbCheckOut.setSelectedIndex(1);
                    tbCheckOut.setEnabledAt(0, false);

                }
            }
        });
        btnNext2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Processes.verifyPayment(txtCardName.getText(), txtCardNum.getText(), txtCardCCV.getText(), txtBAddr1.getText(), txtBZip.getText(), txtBState.getText(),
                        txtBCity.getText(), txtBCountry.getText())) {
                    tbCheckOut.setEnabledAt(2, true);
                    tbCheckOut.setSelectedIndex(2);
                    tbCheckOut.setEnabledAt(1, false);
                    updateCust();
                    updatePay();
                    updateOrder();
                }
            }
        });

        btnComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double shippingCost = 0;

                if(rbOvernight.isSelected()) {
                    shippingCost = 21.75;
                } else if (rbPriority.isSelected()) {
                    shippingCost = 9.45;
                } else {
                    shippingCost = 4.95;
                }

                double[] totals = Processes.getTotals(shippingCost);
                JOptionPane.showMessageDialog(null, "Your order has been placed successfully!");
                int isShipping = 0;

                if(chbShipping.isSelected()) {
                    isShipping = 1;
                }
                Processes.completeOrder(isShipping, totals, txtSState.getText(), txtSCity.getText(), txtSCountry.getText(), txtSZip.getText(), txtSAddr1.getText(), txtSAddr2.getText(),
                        txtBState.getText(), txtBCity.getText(), txtBCountry.getText(), txtBZip.getText(), txtBAddr1.getText(), txtBAddr2.getText(), txtCardName.getText(),
                        txtCardNum.getText(), txtCardCCV.getText());
            }
        });

        btnBack3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbCheckOut.setSelectedIndex(1);
                tbCheckOut.setEnabledAt(2, false);
                tbCheckOut.setEnabledAt(1, true);
            }
        });
        //</editor-fold>
    }
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    //<editor-fold desc="Set Text Fields">
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
    public void setCustomerInfo(String[] customerInfo) {
        txtFirst.setText(customerInfo[1]);
        txtLast.setText(customerInfo[2]);
        txtEmail.setText(customerInfo[3]);
        txtPhone.setText(customerInfo[4]);
        txtSState.setText(customerInfo[5]);
        txtSCity.setText(customerInfo[6]);
        txtSCountry.setText(customerInfo[7]);
        txtSZip.setText(customerInfo[8]);
        txtSAddr1.setText(customerInfo[9]);
    }
    //</editor-fold>
    //<editor-fold desc="Update Info">
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
         double shippingCost = 0;

         txtaOrderInfo.setText("");
         if(rbOvernight.isSelected()) {
             shipping = rbOvernight.getText();
             shippingCost = 21.75;
         } else if (rbPriority.isSelected()) {
             shipping = rbPriority.getText();
             shippingCost = 9.45;
         } else {
             shipping = rbStandard.getText();
             shippingCost = 4.95;
         }

         double totals[] = Processes.getTotals(shippingCost);

         txtaOrderInfo.append("Shipping Method: " + shipping + "\n"
                                + "Item Total: $" + totals[0] + "\n"
                                + "Shipping Total: $" + shippingCost + "\n"
                                + "Tax: $" + totals[1] + "\n"
                                + "--------------------------\n"
                                + "Order Total: $" + totals[2] + "\n");

    }
    //</editor-fold>


}