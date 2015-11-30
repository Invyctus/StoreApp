package main;

import gui.Home.HomeGUI;
import utils.IODB;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Author: John Russell
 * Date Created: 10/17/2015
 * Georgia Southern University - 900743229
 *
 * Serves as container for main method within our StoreApp
 */


public class Main {

    public static void main(String[] args) {

        // Changes look and feel to HiFiLook and feel of JTattoo Library
        // If not installed will revert to system theme
        try
        {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Failed to load look and feel. You do not have JTattoo installed. " +
                    "Using system theme instead.");
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        IODB.instantiate();


        /* Example insert data to database

        String insertQuery = "INSERT INTO CUSTOMERS (CUSTFIRSTNAME, CUSTLASTNAME, CUSTEMAIL, CUSTPHONE) VALUES ('Inserted', 'FromJava', 'insertfromjava@works.com', '123-098-7645')";
        IODB.executeQueries(insertQuery);

        */
            // Example pull data from database

        /*
        String query = "SELECT * FROM ITEMS";
        ArrayList<ArrayList<Object>> test = IODB.getQueryResults(query);

        for (ArrayList<Object> arrayList : test) {
            for (Object o : arrayList) {
                System.out.printf("%s\n", o.toString());

            }
        }

        */
        HomeGUI homeScreen = new HomeGUI();



    }
}
