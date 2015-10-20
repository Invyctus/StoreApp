package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: John Russell
 * Date Created: 10/17/2015
 * Georgia Southern University - 900743229
 *
 *
 * Instance of Home Screen to be displayed
 */
public class HomeGUI extends JFrame {

    private JPanel pnlHome;
    private JLabel lblTitle;
    private JButton btnMen;
    private JLabel lblWomen;
    private JButton btnWomen;
    private JLabel lblMen;

    public HomeGUI() {
        super("Store GUI");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlHome);
        setSize(445,400);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        btnMen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ShirtsPageGUI shirtsPage = new ShirtsPageGUI();
                shirtsPage.getMensIcon();
                shirtsPage.setVisible(true);
                shirtsPage.setIsMens(true);
            }
        });
        btnWomen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ShirtsPageGUI shirtsPage = new ShirtsPageGUI();
                shirtsPage.getWomensIcon();
                shirtsPage.setVisible(true);
                shirtsPage.setIsMens(false);
            }
        });
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
