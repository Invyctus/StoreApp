package gui;

import designpatterns.observer.Waitlist;
import designpatterns.observer.WaitlistObserver;
import gui.Home.HomeGUI;
import gui.dialogs.CartGUI;
import utils.FieldVerification;
import utils.IODB;
import utils.Processes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
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
    private JLabel lblWaitlist;
    private JLabel lblDecal;
    public static boolean isMens = true;
    public static boolean isRotate = false;
    public static String desc;
    public static String pic;
    //</editor-fold>

    public ShirtsPageGUI() {
        super("Design your shirt");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlShirts);
        setSize(800,800);
        setResizable(false);
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
                setNewIcon(isMens, isRotate, cbColor.getSelectedIndex(), cbPattern.getSelectedIndex());
                if (Processes.isWaitlisted(cbColor.getSelectedItem().toString(), cbSize.getSelectedItem().toString(), isMens, Integer.parseInt(cbQuantity.getSelectedItem().toString()))) {
                    lblWaitlist.setVisible(false);
                    btnAdd.setEnabled(true);
                } else {
                    lblWaitlist.setVisible(true);
                    lblWaitlist.setForeground(Color.cyan);
                    btnAdd.setEnabled(false);
                }
            }
        });
        cbQuantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Processes.isWaitlisted(cbColor.getSelectedItem().toString(), cbSize.getSelectedItem().toString(), isMens, Integer.parseInt(cbQuantity.getSelectedItem().toString()))) {
                    lblWaitlist.setVisible(false);
                    btnAdd.setEnabled(true);
                } else {
                    lblWaitlist.setVisible(true);
                    lblWaitlist.setForeground(Color.cyan);
                    btnAdd.setEnabled(false);

                }
            }
        });
        cbColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNewIcon(isMens, isRotate, cbColor.getSelectedIndex(), cbPattern.getSelectedIndex());
                if (Processes.isWaitlisted(cbColor.getSelectedItem().toString(), cbSize.getSelectedItem().toString(), isMens, Integer.parseInt(cbQuantity.getSelectedItem().toString()))) {
                    lblWaitlist.setVisible(false);
                    btnAdd.setEnabled(true);
                } else {
                    lblWaitlist.setVisible(true);
                    lblWaitlist.setForeground(Color.cyan);
                    btnAdd.setEnabled(false);
                    Processes.stockUpdate(cbColor.getSelectedItem().toString(), cbSize.getSelectedItem().toString(), isMens);
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
                isRotate = !isRotate;
                setNewIcon(isMens, isRotate, cbColor.getSelectedIndex(), cbPattern.getSelectedIndex());

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
                Processes.addToCart(desc, lblPrice.getText(), cbQuantity.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, "Item successfully added to cart.");
                btnCheckout.setEnabled(true);
            }
        });

        lblWaitlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Waitlist waitlist = new Waitlist();
                WaitlistObserver observer = new WaitlistObserver(waitlist);
            }
        });
        //</editor-fold>
    }

    public void setMensIcon(String img) {
        ImageIcon mensIcon = new ImageIcon(img);
        lblPicture.setIcon(mensIcon);
    }
    public void setWomensIcon(String img) {
        ImageIcon womensIcon = new ImageIcon(img);
        lblPicture.setIcon(womensIcon);

    }
    public void setNewIcon(boolean isMens, boolean isRotate, int color, int pattern) {
        String[] icons = Processes.setImages(isMens, isRotate, color, pattern);

        try {
            if(pattern != 0) {
                BufferedImage shirt = ImageIO.read(new File(icons[0]));
                BufferedImage decal = ImageIO.read(new File(icons[1]));

                int w = Math.max(shirt.getWidth(), decal.getWidth());
                int h = Math.max(shirt.getHeight(), decal.getHeight());
                BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

                Graphics g = combined.getGraphics();

                g.drawImage(shirt, 0, 0, null);
                g.drawImage(decal, 0, 0, null);
                ImageIcon newIcon = new ImageIcon(combined);
                lblPicture.setIcon(newIcon);
            } else {
                ImageIcon newIcon = new ImageIcon(icons[0]);
                lblPicture.setIcon(newIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
    public void setIsMens(boolean b) {
        isMens = b;
    }
}