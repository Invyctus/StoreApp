package gui.dialogs;

import gui.HomeGUI;
import gui.ShirtsPageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: John Russell
 * Date Created: 10/20/2015
 * Georgia Southern University - 900743229
 */
public class CheckoutGUI extends JFrame {
    private JTabbedPane tbCheckOut;
    private JPanel pnlCheckout;
    private JPanel pnlPayment;
    private JPanel pnlCustomer;
    private JPanel pnlOrder;
    private JPanel pnlReceipt;
    private JTextField txtFirst;
    private JLabel lblFirst;
    private JLabel lblLast;
    private JLabel lblPhone;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JPanel pnlAddress;
    private JLabel lblRegister;
    private JLabel lblUser;
    private JLabel lblPass;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton btnNext;
    private JButton btnBack;
    private JTextField txtUser;
    boolean isMens;

    /**
     * Todo: State pattern if logged in do not display register an account
     * Button: Next if logged in
     * Button: Continue as guest if not logged in
     */
    public CheckoutGUI() {
        super("Checkout");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlCheckout);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


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
                tbCheckOut.setEnabledAt(1, true);
                tbCheckOut.setSelectedIndex(1);
            }
        });
    }
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }


}
