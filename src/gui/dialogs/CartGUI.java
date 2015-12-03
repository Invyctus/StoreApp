package gui.dialogs;

import gui.Home.HomeGUI;
import gui.ShirtsPageGUI;
import utils.FieldVerification;
import utils.IODB;
import utils.Processes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: John Russell
 * Date Created: 10/20/2015
 * Georgia Southern University - 900743229
 */

public class CartGUI extends JFrame {
    //<editor-fold desc="JFrame Objects">
    private JPanel pnlCart;
    private JButton btnNext;
    private JButton btnBack;
    private JTextArea txtaPrice;
    private JTextArea txtaItem;
    private JTextArea txtaQuantity;
    private JButton btnClear;
    private JTable tblCart;

    //</editor-fold>

    public CartGUI() {
        super("Cart");
        setContentPane(pnlCart);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        updateCart();

        //<editor-fold desc="Button Listeners">
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomeGUI homeGUI = new HomeGUI();
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CheckoutGUI checkoutGUI = new CheckoutGUI();
                checkoutGUI.setSize(407, 410);
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtaItem.setText("");
                txtaPrice.setText("");
                txtaQuantity.setText("");
                clearCart();
                btnNext.setEnabled(false);
            }
        });
        //</editor-fold>
    }
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    //<editor-fold desc="Cart Updaters">
    public void updateCart() {
        try {
            String custid = FieldVerification.getCustid();

                String updateDesc = "SELECT ITEMDESC FROM CART WHERE CUSTID = " + custid;
                ArrayList<ArrayList<Object>> getDesc = IODB.getQueryResults(updateDesc);
                for (ArrayList<Object> arrayList1 : getDesc) {
                    for (Object d : arrayList1) {
                        txtaItem.append(d.toString() + "\n");
                    }
                }

                String updatePrice = "SELECT PRICE FROM CART WHERE CUSTID = " + custid;
                ArrayList<ArrayList<Object>> getPrice = IODB.getQueryResults(updatePrice);
                for (ArrayList<Object> arrayList1 : getPrice) {
                    for (Object p : arrayList1) {
                        txtaPrice.append(p.toString() + "\n");
                    }
                }

                String updateQuant = "SELECT QUANTITY FROM CART WHERE CUSTID = " + custid;
                ArrayList<ArrayList<Object>> getQuant = IODB.getQueryResults(updateQuant);
                for (ArrayList<Object> arrayList1 : getQuant) {
                    for (Object q : arrayList1) {
                        txtaQuantity.append("x " + q.toString() + "\n");
                    }
                }

                if (txtaItem.getText().equals("")) {
                    btnNext.setEnabled(false);
                }

        } catch (NullPointerException e) {
            setVisible(false);
            HomeGUI homeGUI = new HomeGUI();
            JOptionPane.showMessageDialog(null, "Cart can't be empty");
        }

    }

    public void clearCart() {
        String custid = FieldVerification.getCustid();

        String clearIt = "delete from cart where custid = " + custid;
        IODB.executeQueries(clearIt);

    }
    //</editor-fold>

}
