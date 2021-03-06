package gui.Home;

import utils.Processes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles Logging In to the app
 */
public class LogInGUI extends JFrame {
    //<editor-fold desc="JFrame Objects">
    private JPanel pnlLogIn;
    private JTextField txtUser;
    private JPasswordField pwdPass;
    private JButton btnCancel;
    private JButton btnLogIn;
    //</editor-fold>

    public LogInGUI() {
        super("LogIn");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(pnlLogIn);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //<editor-fold desc="Button Listeners">
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = new String(pwdPass.getPassword());

                if(Processes.verifyLogin(txtUser.getText(), pass)) {
                    HomeGUI.setLogged(true);
                    setVisible(false);
                    HomeGUI homeGUI = new HomeGUI();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomeGUI homeGUI = new HomeGUI();
            }
        });
        //</editor-fold>
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
