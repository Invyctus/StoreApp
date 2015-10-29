package utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.util.ArrayList;

/**
 * Serves as connection to oracle database.
 * Handles input and output from the database.
 */
public class IODB {
    //<editor-fold desc="DB Connection Information">
    private static String user = "masteruser";
    private static String password = "password";
    private static String dbName = "ORCL";
    private static String host = "oodproject.cyt9gmujiooj.us-west-2.rds.amazonaws.com";
    private static String port = "1521";
    //</editor-fold>

    private static class DBConnection {
        private static java.sql.Connection conn = null;

        /**
         * Singleton: create only one instance of connection
         * @return conn
         */
        private static void createConnection() {
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
        }

        public static void closeConnection() {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error reloading connection.",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        public static Connection getConnection() {
            if (conn != null)
                return conn;
            else
                createConnection();
            return conn;
        }
    }

    public static void instantiate() {
        DBConnection.createConnection();
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
     * Take the sql query and get data from database
     * @param sqlQuery
     * @return results
     */
    public static ArrayList<ArrayList<Object>> getQueryResults(String sqlQuery) {
        ArrayList<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>(10);

        try {
            ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sqlQuery);

            if(!rs.next()) {

            } else {
                do{
                    ArrayList<Object> tempRow = new ArrayList<>(10);
                    for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        tempRow.add(rs.getObject(i));
                    }
                    results.add(tempRow);
                } while (rs.next());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Insert, Update, Delete, Alter, etc... data within the database
     * @param queries
     * @return results
     */
    public static int[] executeQueries(String ... queries) {

        try {
            Statement stmt = DBConnection.getConnection().createStatement();
           for(String query: queries) {
               stmt.addBatch(query);
           }

            int[] results = stmt.executeBatch();
            System.out.println("Query executed successfully:");
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

