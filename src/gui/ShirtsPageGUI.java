package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: John Russell
 * Date Created: 10/19/2015
 * Georgia Southern University - 900743229
 */
public class ShirtsPageGUI extends JFrame{
    private JPanel pnlShirts;
    private JComboBox cbColor;
    private JComboBox cbSize;
    private JLabel lblBack;
    private JLabel lblSize;
    private JLabel lblColor;
    private JLabel lblPicture;
    private JButton btnBack;
    private JButton btnAdd;
    private JLabel lblFront;
    private JComboBox cbFront;
    private JComboBox cbBack;
    private JButton btnView;
    private JTextArea txtarFeatures;
    private JTextArea txtarDescription;
    private boolean isMens;
    private boolean isRotate;

    public ShirtsPageGUI() {
        super("Design your shirt");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlShirts);
        setSize(800,800);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomeGUI homeGUI = new HomeGUI();
            }
        });
        /**
         * Todo: USE A STATE PATTERN HERE!!!!!! PLEASE
         * Have a men's state and women's state for the gui so
         * the pictures display appropriately
         */
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
    }

    public void getMensIcon() {
        ImageIcon mensIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\MensShirtImgs\\rsz_mens_shirt_385x409.jpg");
        lblPicture.setIcon(mensIcon);

    }
    public void getWomensIcon() {
        ImageIcon womensIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\WomensShirtImgs\\rsz_womens_shirt_385x409.jpg");
        lblPicture.setIcon(womensIcon);
    }
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
    public void setIsMens(boolean b) {
        isMens = b;
    }

}