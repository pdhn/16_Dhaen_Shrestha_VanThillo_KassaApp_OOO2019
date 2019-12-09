package model;

import database.ArtikelTekstLoadSave;

import java.util.ArrayList;
import java.util.List;

public class Winkel implements Subject {
    private static Winkel uniqueInstance;
    private ArtikelTekstLoadSave db = new ArtikelTekstLoadSave();
    private Bestelling bestelling, bestellingOnHold;
    private List<Observer> observers;

    private Winkel(){
        bestelling = new Bestelling();
        bestellingOnHold = null;
        observers = new ArrayList<>();
    }

    public static Winkel getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Winkel();
        }
        return uniqueInstance;
    }

    public void addArtikelToBestelling(int getal){
        Artikel a = db.getArtikel(getal);
        bestelling.addTotaal(a.getPrijs());
        bestelling.voegArtikelToe(a);
        notifyObservers();
    }

    public void removeArtikelFromBestelling(int getal){
        Artikel a = db.getArtikel(getal);
        bestelling.removeTotaal(a.getPrijs());
        bestelling.verwijderArtikel(a);
        notifyObservers();
    }

    public List<Artikel> getArtikelsFromBestellingForKassa(){
        return bestelling.getArtikelsForKassa();
    }

    public List<Artikel> getArtikelsFromBestellingForKlant(){
        return bestelling.getArtikelsForKlant();
    }

    public List<Artikel> getArtikelsFromDb() { return db.getArtikels(); }

    public double getTotaalFromBestelling(){
        return bestelling.getTotaal();
    }

    public void setBestellingOnHold(){
        if(bestellingOnHold != null) throw new ModelException("Er is al een bestelling on hold");
        bestellingOnHold = bestelling;
        bestelling = new Bestelling();
        notifyObservers();
    }

    public void setBestellingOffHold(){
        if (bestellingOnHold == null) throw new ModelException("Er is geen bestelling on hold");
        bestelling = bestellingOnHold;
        notifyObservers();
        bestellingOnHold = null;
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
