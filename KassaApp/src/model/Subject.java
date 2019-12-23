package model;

/**
 * @author 16_Dhaen_Shrestha_VanThillo_KassaApp_OOO2019
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers();
}
