package model;

import database.ArtikelExcelLoadSave;
import database.ArtikelLoadSaveTemplate;
import database.ArtikelTekstLoadSave;
import model.korting.Geenkorting;
import model.korting.Korting;
import model.korting.KortingEnum;
import model.korting.KortingFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Winkel implements Subject {
    private static Winkel uniqueInstance;
    private ArtikelLoadSaveTemplate db;
    private Bestelling bestelling, bestellingOnHold;
    private List<Observer> observers;
    private Korting korting;

    private static final String FILE_PATH_PROPERTIES = "src\\bestanden\\config.properties";

    private Winkel() {
        bestelling = new Bestelling();
        bestellingOnHold = null;
        observers = new ArrayList<>();
        korting = new Geenkorting();

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

    public void setKorting(String type, int percentage, int bedrag){
        Korting korting = KortingFactory.createKorting(type);
        korting.setPercentage(percentage);
        korting.setBedrag(bedrag);
        this.korting = korting;
    }

    public void addArtikelToBestelling(int artikelCode) {
        Artikel a = db.getArtikel(artikelCode);
        bestelling.voegArtikelToe(a);
        notifyObservers();
    }

    public void removeArtikelFromBestelling(int artikelCode) {
        Artikel a = db.getArtikel(artikelCode);
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

    public List<String> getKortingStrategyList(){
        List<String> list = new ArrayList<>();
        for(KortingEnum korting: KortingEnum.values()){
            list.add(korting.toString());
        }
        return list;
    }

    public double getTotaalFromBestelling() {
        return bestelling.getTotaal();
    }

    public double getKortingForBestelling() { return korting.getKorting(); }

    public double getTotaalMetKorting() { return getTotaalFromBestelling() - getKortingForBestelling();}

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
