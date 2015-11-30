package designpatterns.observer;

import designpatterns.observer.Observer;

/**
 * Subject interface for observer pattern
 */
public interface Subject {
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObserver();
}
