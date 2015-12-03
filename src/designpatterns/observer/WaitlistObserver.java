package designpatterns.observer;

import javax.swing.*;

/**
 * Represents each observer monitoring waitlist
 */
public class WaitlistObserver implements Observer {

    private int quantity;

    private static int observerIDTracker = 0;

    private int observerID;

    private Subject waitlist;

    public WaitlistObserver(Subject waitlist) {
        this.waitlist = waitlist;
        this.observerID = ++observerID;
        JOptionPane.showMessageDialog(null, "Added to waitlist.");
        waitlist.register(this);
    }

    @Override
    public void update(int quantity) {
        this.quantity = quantity;

        printQuantity();
    }
    public void printQuantity() {
        JOptionPane.showMessageDialog(null, observerID + ": Item now in stock, available quantity: " + quantity);
    }
}
