package model;

/**
 * @author Sander Van Thillo
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers();
}
