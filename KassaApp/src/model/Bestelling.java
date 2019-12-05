package model;

import java.util.ArrayList;
import java.util.List;

public class Bestelling implements Subject {
    private List<Artikel> artikels;
    private double totaal;
    private Artikel laatsteArtikel;
    private List<Observer> observers;

    public Bestelling(){
        artikels = new ArrayList<>();
        totaal = 0;
        observers = new ArrayList<>();
    }

    public void voegArtikelToe(Artikel a){
        if(a == null) throw new ModelException("Geen geldig artikel");
        artikels.add(a);
        laatsteArtikel = a;
        notifyObservers();
    }

    public void verwijderArtikel(Artikel a){
        if(!artikels.contains(a)) throw new ModelException("Artikel kan niet verwijderd worden");
        artikels.remove(a);
        notifyObservers();
    }

    public Artikel getLaatsteArtikel() {
        return this.laatsteArtikel;
    }

    public List<Artikel> getArtikels(){
        return this.artikels;
    }

    public void addTotaal(double prijs){
        if(prijs < 0 ) throw new ModelException("Geen geldige prijs");
        totaal += prijs;
    }

    public void removeTotaal(double prijs){
        if(prijs > totaal) throw new ModelException("Prijs mag niet groter zijn dan totaal");
        totaal -= prijs;
    }

    public double getTotaal(){
        return this.totaal;
    }

    @Override
    public void registerObserver(Observer o) {
        if(o == null){
            throw new IllegalArgumentException("Ongeldige observer");
        }
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++){
            Observer observer = observers.get(i);
            observer.update();
        }
    }
}
