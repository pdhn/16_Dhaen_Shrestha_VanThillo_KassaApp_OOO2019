package model;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers();
}
