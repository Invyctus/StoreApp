package gui.Home;

import designpatterns.state.*;
import gui.ShirtsPageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: John Russell
 * Date Created: 10/17/2015
 * Georgia Southern University - 900743229
 *
 * Instance of Home Screen to be displayed
 */

public class HomeGUI extends JFrame {

    //<editor-fold defaultstate="collapsed" desc="JFrame Objects">
    private JPanel pnlHome;
    private JLabel lblTitle;
    private JButton btnMen;
    private JLabel lblWomen;
    private JButton btnWomen;
    private JLabel lblMen;
    private JButton btnSignUp;
    private JButton btnLogIn;
    private String string;
    Context context = new Context();
    LoggedOut loggedOut = new LoggedOut();
    LoggedIn loggedIn = new LoggedIn();
    static boolean logged = false;
    ActionListener logIn = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogInGUI logInGUI = new LogInGUI();
            setVisible(false);
        }
    };
    ActionListener logOut = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Successfully logged out");
            setLogged(false);
            refreshHome();
        }
    };
    //</editor-fold>

    public HomeGUI() {
        super("Store GUI");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(pnlHome);
        setSize(445,400);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        refreshHome();

        //<editor-fold defaultstate="collapsed" desc="Button Listeners">
        btnMen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ShirtsPageGUI shirtsPage = new ShirtsPageGUI();
                shirtsPage.setMensIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mwhitefront.png");
                shirtsPage.setIsMens(true);
                shirtsPage.setVisible(true);
            }
        });
        btnWomen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ShirtsPageGUI shirtsPage = new ShirtsPageGUI();
                shirtsPage.setWomensIcon("C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wwhitefront.png");
                shirtsPage.setIsMens(false);
                shirtsPage.setVisible(true);
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    SignUpGUI signUpGUI = new SignUpGUI();
            }
        });


        //</editor-fold>
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
    public static void setLogged(boolean b) { logged = b; }
    public void refreshHome() {
        btnLogIn.removeActionListener(logIn);
        btnLogIn.removeActionListener(logOut);

        if (logged) {
            context.setState(loggedIn);
            loggedIn.doAction(context);
            btnLogIn.setText(context.getState().toString());
            btnLogIn.addActionListener(logOut);
            btnSignUp.setEnabled(false);
            btnMen.setEnabled(true);
            btnWomen.setEnabled(true);
        } else {
            context.setState(loggedOut);
            loggedOut.doAction(context);
            btnLogIn.setText(context.getState().toString());
            btnLogIn.addActionListener(logIn);
            btnSignUp.setEnabled(true);
            btnMen.setEnabled(false);
            btnWomen.setEnabled(false);
        }
    }
}
