package gui.Home;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: John Russell
 * Date Created: 10/23/2015
 * Georgia Southern University - 900743229
 */
public class LogInGUI extends JFrame {
    //<editor-fold desc="JFrame Objects">
    private JPanel pnlLogIn;
    private JTextField txtUser;
    private JTextField txtPass;
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
                setVisible(false);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        //</editor-fold>
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
