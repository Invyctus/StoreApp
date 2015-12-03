package main;

import gui.Home.HomeGUI;
import utils.IODB;
import utils.Processes;

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

        HomeGUI homeScreen = new HomeGUI();

    }
}
