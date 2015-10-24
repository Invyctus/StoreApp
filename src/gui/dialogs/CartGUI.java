package gui.dialogs;

import gui.Home.HomeGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: John Russell
 * Date Created: 10/20/2015
 * Georgia Southern University - 900743229
 */

/*
    Todo: For Carts Page
        Have Items info, Price, and Quantity pulled from database into JTable (or some other means not certain yet, for now jtable)
        Have quantity editable such that the user can edit if need be in cart
        This would update contents within the database
        Quantity must not be < 0
        Refresh button to refresh cart after quantity updated
        Proceed to Checkout button disabled if no items in cart
        Clean up GUI Form

        Optional:
 */
public class CartGUI extends JFrame {
    //<editor-fold desc="JFrame Objects">
    private JPanel pnlCart;
    private JButton btnNext;
    private JButton btnBack;
    private JTable tblCart;
    //</editor-fold>

    public CartGUI() {
        super("Cart");
        setContentPane(pnlCart);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

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
        //</editor-fold>
    }
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
