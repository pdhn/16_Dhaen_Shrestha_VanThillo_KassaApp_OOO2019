package model;

import database.ArtikelExcelLoadSave;
import database.ArtikelLoadSaveTemplate;
import database.ArtikelTekstLoadSave;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Winkel implements Subject {
    private static Winkel uniqueInstance;
    private ArtikelLoadSaveTemplate db;
    private Bestelling bestelling, bestellingOnHold;
    private List<Observer> observers;

    private static final String FILE_PATH_PROPERTIES = "src\\bestanden\\config.properties";

    private Winkel() {
        bestelling = new Bestelling();
        bestellingOnHold = null;
        observers = new ArrayList<>();

        // --- db reads from excel or txt depending on config.properties file ---
        try (InputStream inputStream = new FileInputStream(FILE_PATH_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            if (Boolean.valueOf(properties.getProperty("txt"))) {
                db = new ArtikelTekstLoadSave();
            } else {
                db = new ArtikelExcelLoadSave();
            }
        } catch (IOException e) {
            throw new ModelException("Error reading config file --->\n");
        }
        // ---^db reads from excel or txt depending on config.properties file^---
    }

    public static Winkel getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Winkel();
        }
        return uniqueInstance;
    }

    public void addArtikelToBestelling(int artikelCode) {
        Artikel a = db.getArtikel(artikelCode);
        bestelling.addTotaal(a.getPrijs());
        bestelling.voegArtikelToe(a);
        notifyObservers();
    }

    public void removeArtikelFromBestelling(int artikelCode) {
        Artikel a = db.getArtikel(artikelCode);
        bestelling.removeTotaal(a.getPrijs());
        bestelling.verwijderArtikel(a);
        notifyObservers();
    }

    public List<Artikel> getArtikelsFromBestellingForKassa() {
        return bestelling.getArtikelsForKassa();
    }

    public List<Artikel> getArtikelsFromBestellingForKlant() {
        return bestelling.getArtikelsForKlant();
    }

    public List<Artikel> getArtikelsFromDb() {
        return db.getArtikels();
    }

    public double getTotaalFromBestelling() {
        return bestelling.getTotaal();
    }

    public void setBestellingOnHold() {
        if (bestellingOnHold != null) throw new ModelException("Er is al een bestelling on hold");
        bestellingOnHold = bestelling;
        bestelling = new Bestelling();
        notifyObservers();
    }

    public void setBestellingOffHold() {
        if (bestellingOnHold == null) throw new ModelException("Er is geen bestelling on hold");
        bestelling = bestellingOnHold;
        notifyObservers();
        bestellingOnHold = null;
    }

    @Override
    public void registerObserver(Observer o) {
        if (o == null) {
            throw new IllegalArgumentException("Ongeldige observer");
        }
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update();
        }
    }
}
