package designpatterns.observer;

import java.util.ArrayList;

/**
 * Author: John Russell
 * Date Created: 12/3/2015
 * Georgia Southern University - 900743229
 */
public class Waitlist implements Subject {

    private ArrayList<Observer> observers;
    private int quantity;

    public Waitlist() {
        observers = new ArrayList<Observer>();
    }
    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);

        System.out.println("Unregistered from waitlist");
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers) {
            observer.update(quantity);
        }

    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;

        if (quantity > 0 ) {
            notifyObserver();
        }
    }
}
