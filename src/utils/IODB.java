package utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

/**
 * Serves as connection to oracle database.
 * Handles input and output from the database.
 */
public class IODB {
    private static String user = "invictus";
    private static String password = "password";
    private static String dbName = "ORCL";
    private static String host = "localhost";
    private static String port = "1521";


    private static class DBConnection {
        private static java.sql.Connection conn = createConnection();

        /**
         * Refreshes the connection by closing out and establishing instance of connection again
         */
        private static void refConnection() {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error reloading connection.",
                        JOptionPane.ERROR_MESSAGE);
            }
            conn = createConnection();
        }
    }

    /**
     * Uses a singleton to store the instance of the connection. Will only create the connection once.
     * @return conn
     */
    private static Connection createConnection() {
        Connection conn = null;
        JFrame connMessageFrame = createLoadingFrame("Connecting to database...");

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" +
                            host + ":" + port + ":" + dbName,
                    user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error connecting to the database.",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        connMessageFrame.dispose();
        System.out.println("Connected to database");
        return conn;
    }

    /**
     * Function to create connection loading message
     *
     * @param msg text to display within msgFrame
     * @return JFrame containing msg
     * */
    private static JFrame createLoadingFrame(String msg) {
        final JFrame msgFrame = new JFrame();
        final JPanel pnlConnMsg = (JPanel) msgFrame.getContentPane();
        pnlConnMsg.setBorder(new EmptyBorder(10,10,10,10));
        JLabel lblConnMsg = new JLabel(msg);
        pnlConnMsg.add(lblConnMsg);

        msgFrame.setUndecorated(true);
        msgFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        msgFrame.setSize(250,100);
        msgFrame.setVisible(true);
        msgFrame.setLocationRelativeTo(null);
        return msgFrame;

    }

    /**
     * Refresh connection
     */
    public static void refConnection() { DBConnection.refConnection(); }

    /**
     * Call to create connection
     * @return database connection
     */
    public static Connection getConnection(){ return DBConnection.conn; }


    /**
     * Function for running sql queries on database. Call to the database with this function.
     *
     * Use:
     * To insert a variable use ?.
     * Do NOT include ; at end of query
     *
     * Use Example:
     * ResultSet thisQuery = DBConnection.getQueryResults("SELECT * FROM CUSTOMERS WHERE CUSTID = ?", "1");
     *
     * @param sqlQuery
     * @param args
     * @return ResultSet object to be used to pull the results of the query
     */
    public static ResultSet getQueryResults(String sqlQuery, String... args) {

        Connection conn = getConnection();

        ResultSet results;
        try {
            PreparedStatement stmt = conn.prepareStatement(sqlQuery, ResultSet.CONCUR_UPDATABLE);

            for(int i = 0; i < args.length; i++){
                int index = i + 1;
                stmt.setString(index, args[i]);
            }
            results = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error returning query results",
                    JOptionPane.ERROR_MESSAGE);
            results = null;
        }
        return results;
    }

}

