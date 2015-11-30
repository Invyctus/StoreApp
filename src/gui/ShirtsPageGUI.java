package gui;

import gui.Home.HomeGUI;
import gui.dialogs.CartGUI;
import utils.FieldVerification;
import utils.IODB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Author: John Russell
 * Date Created: 10/19/2015
 * Georgia Southern University - 900743229
 */

public class ShirtsPageGUI extends JFrame{
    //<editor-fold defaultstate="collapsed" desc="JFrame Objects">
    private JPanel pnlShirts;
    private JComboBox cbColor;
    private JComboBox cbSize;
    private JLabel lblSide;
    private JLabel lblSize;
    private JLabel lblColor;
    private JLabel lblPicture;
    private JButton btnBack;
    private JButton btnAdd;
    private JLabel lblPattern;
    private JComboBox cbPattern;
    private JComboBox cbSide;
    private JButton btnView;
    private JTextArea txtarFeatures;
    private JButton btnCheckout;
    private JPanel MensShirt;
    private JLabel lblQuantity;
    private JComboBox cbQuantity;
    private JLabel lblPrice;
    private boolean isMens = true;
    private boolean isRotate;
    private static int count = 0;
    public String desc;
    //</editor-fold>

    public ShirtsPageGUI() {
        super("Design your shirt");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlShirts);
        setSize(800,800);
        setResizable(false);
        //pnlShirts.add(WomensShirt);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        getFeatures();

        //<editor-fold defaultstate="collapsed" desc="Button Listeners">

        cbPattern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbPattern.getSelectedIndex() == 0) {
                    lblPrice.setText("10.00");
                } else {
                    lblPrice.setText("15.00");
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomeGUI homeGUI = new HomeGUI();
            }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMens) {
                    if (isRotate) {
                        getMensIcon();
                        isRotate = false;
                    } else {
                            ImageIcon mensIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\MensShirtImgs\\rsz_mens_shirt_back_385x409.jpg");
                            lblPicture.setIcon(mensIcon);
                            isRotate = true;
                        }
                } else {
                    if(isRotate) {
                        getWomensIcon();
                        isRotate = false;
                    } else {
                        ImageIcon womensIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\WomensShirtImgs\\rsz_womens_shirt_back_385x409.jpg");
                        lblPicture.setIcon(womensIcon);
                        isRotate = true;
                    }
                }
            }
        });

        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    CartGUI cartGUI = new CartGUI();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDesc();
                addToCart();

                btnCheckout.setEnabled(true);
            }
        });
        //</editor-fold>
    }

    public void getMensIcon() {
        ImageIcon mensIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\MensShirtImgs\\rsz_mens_shirt_385x409.jpg");
        lblPicture.setIcon(mensIcon);
    }
    public void getWomensIcon() {
        ImageIcon womensIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\WomensShirtImgs\\rsz_womens_shirt_385x409.jpg");
        lblPicture.setIcon(womensIcon);

    }
    public void getFeatures() {
        String retrieveFeatures = "SELECT ITEMFEATURES FROM ITEMS WHERE ITEMID = 1";

        ArrayList<ArrayList<Object>> features = IODB.getQueryResults(retrieveFeatures);

        for (ArrayList<Object> arrayList : features) {
            for (Object o : arrayList) {
                String[] parts = o.toString().split(", ");
                txtarFeatures.setText(parts[0] + "\n" + parts[1] + "\n" + parts[2]);

            }
        }
    }
    public void updateDesc() {
        if (cbPattern.getSelectedItem().toString().equals("plain")) {
            desc = cbColor.getSelectedItem().toString() + ", " + cbSize.getSelectedItem().toString()
                    + ", " + cbPattern.getSelectedItem().toString();
        } else {
            desc = cbColor.getSelectedItem().toString() + ", " + cbSize.getSelectedItem().toString()
                    + ", " + cbPattern.getSelectedItem().toString() + ", " + cbSide.getSelectedItem().toString();
        }

    }
    public int getCount() {
        return count;
    }
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
    public void setIsMens(boolean b) {
        isMens = b;
    }

    public void addToCart() {
        String custid = FieldVerification.getCustid();

                String insertCart = "INSERT INTO CART (ITEMDESC, PRICE, QUANTITY, CUSTID) VALUES ('" + desc + "', " + "'" + lblPrice.getText() + "', "
                        + "'" + cbQuantity.getSelectedItem().toString() + "', " + "'" + custid + "')";

                IODB.executeQueries(insertCart);

    }
}