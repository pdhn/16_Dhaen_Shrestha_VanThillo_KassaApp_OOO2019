package model;

import java.util.ArrayList;
import java.util.List;

public class Bestelling implements Subject {
    private List<Artikel> artikels;
    private double totaal;
    private List<Observer> observers;

    public Bestelling(){
        artikels = new ArrayList<>();
        totaal = 0;
        observers = new ArrayList<>();
    }

    public void voegArtikelToe(Artikel a){
        if(a == null) throw new ModelException("Geen geldig artikel");
        if(artikels.contains(a)){
            for(Artikel artikel: artikels){
                if(artikel.equals(a)){
                    artikel.verhoogAantal();
                }
            }
        }
        else artikels.add(a);
        notifyObservers();
    }

    public void verwijderArtikel(Artikel a){
        if(!artikels.contains(a)) throw new ModelException("Artikel kan niet verwijderd worden");
        Artikel artikel = null;
        for(Artikel artikel1: artikels){
            if(artikel1.equals(a)){
                artikel = a;
            }
        }
        if(artikel.getAantal() > 1){
            artikel.verlaagAantal();
        }
        else artikels.remove(a);
        notifyObservers();
    }

    public List<Artikel> getArtikels(){
        return this.artikels;
    }

    public void setArtikels(List<Artikel> artikels){ this.artikels = artikels;}

    public void setTotaal(double totaal) { this.totaal = totaal;}

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
