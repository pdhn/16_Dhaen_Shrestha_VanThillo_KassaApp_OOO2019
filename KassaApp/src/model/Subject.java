package model;

import model.Artikel;
import model.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers();
}
