package model;

import database.ArtikelDB;
import model.kassabon.KassaBon;
import model.kassabon.KassaBonFactory;
import model.korting.Geenkorting;
import model.korting.Korting;
import model.korting.KortingEnum;
import model.korting.KortingFactory;

import java.util.ArrayList;
import java.util.List;

public class Winkel implements Subject {
    private static Winkel uniqueInstance;
    private ArtikelDB db;
    private List<Bestelling> bestellingen;
    private List<Observer> observers;
    private Korting korting;

    private List<Artikel> artikelsFromOnHold;
    private int onHoldCounter;

    private Winkel() {
        this.db = new ArtikelDB();
        this.bestellingen = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.korting = new Geenkorting();
        voegBestellingToe(new Bestelling(this.korting));
        this.onHoldCounter = 0;
    }

    public static Winkel getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Winkel();
        }
        return uniqueInstance;
    }

    public void voegBestellingToe(Bestelling bestelling){
        if(bestelling == null) throw new ModelException("Geen geldige bestelling");
        setAantalFromArtikels();
        this.bestellingen.add(bestelling);
    }

    public void sluitBestellingAf(){
        getActieveBestelling().sluitAf();
    }

    public void annuleerBestelling(Bestelling bestelling){
        if(bestelling == null) throw new ModelException("Geen geldige bestelling");
        this.bestellingen.remove(bestelling);
        voegBestellingToe(new Bestelling(this.korting));
        notifyObservers();
    }

    public void betaalBestelling(){
        pasVoorraadAan();
        KassaBon kassaBon = KassaBonFactory.createKassaBon();
        System.out.println(kassaBon.printKassaBon(this));
        setBestellingBetaald();
        voegBestellingToe(new Bestelling(this.korting));
        if(this.onHoldCounter!=0){
            this.onHoldCounter--;
            if(this.onHoldCounter==0){
                bestellingen.remove(getBestellingOnHold());
            }
        }
        notifyObservers();
    }

    private void pasVoorraadAan() {
        for(Artikel a: getAfsluitBestelling().getArtikelsForKlant()){
            for(Artikel artikel: getArtikelsFromDb()){
                if(a.equals(artikel)){
                    artikel.setVoorraad(artikel.getVoorraad()-a.getAantal());
                }
            }
        }
    }

    public Bestelling getActieveBestelling(){
        Bestelling bestelling = null;
        for(Bestelling b: this.bestellingen){
            if(b.getState().equals(b.getActief())){
                bestelling = b;
            }
        }
        return bestelling;
    }

    public Bestelling getAfsluitBestelling(){
        Bestelling bestelling = null;
        for(Bestelling b: this.bestellingen){
            if(b.getState().equals(b.getSluitAf())){
                bestelling = b;
            }
        }
        return bestelling;
    }

    public void setBestellingOnHold() {
        if (getBestellingOnHold() != null) throw new ModelException("Er is al een bestelling on hold");
        setArtikelsFromOnHold(getActieveBestelling().getArtikelsForKassa());
        getActieveBestelling().zetOnHold();
        this.onHoldCounter = 3;
        voegBestellingToe(new Bestelling(this.korting));
        notifyObservers();
    }

    private void setAantalFromArtikels() {
        for(Artikel a : this.db.getArtikels()){
            a.setAantal(1);
        }
    }

    private void setArtikelsFromOnHold(List<Artikel> artikels) {
        this.artikelsFromOnHold = new ArrayList<>(artikels);
    }

    public void setBestellingOffHold() {
        if( getBestellingOnHold() == null) throw new ModelException("Er is geen bestelling on hold");
        this.bestellingen.remove(getActieveBestelling());
        getBestellingOnHold().zetOffHold();
        getActieveBestelling().setArtikels(new ArrayList<>());
        setAantalFromArtikels();
        getActieveBestelling().setArtikelsForKassa(this.artikelsFromOnHold);
        notifyObservers();
    }

    private Bestelling getBestellingOnHold(){
        Bestelling bestelling = null;
        for(Bestelling b: this.bestellingen){
            if(b.getState().equals(b.getOnHold())){
                bestelling = b;
            }
        }
        return bestelling;
    }

    public void setBestellingBetaald(){
        getAfsluitBestelling().betaal();
    }

    public void setKorting(String type, int percentage, int bedrag){
        Korting korting = KortingFactory.createKorting(type);
        korting.setPercentage(percentage);
        korting.setBedrag(bedrag);
        this.korting = korting;
        getActieveBestelling().setKorting(this.korting);
    }

    public void addArtikelToBestelling(int artikelCode) {
        Artikel a = this.db.getArtikel(artikelCode);
        getActieveBestelling().voegArtikelToe(a);
        notifyObservers();
    }

    public void removeArtikelFromBestelling(int artikelCode) {
        Artikel a = this.db.getArtikel(artikelCode);
        if(getActieveBestelling() == null){
            getAfsluitBestelling().verwijderArtikel(a);
        }
        else getActieveBestelling().verwijderArtikel(a);
        notifyObservers();
    }

    public List<Artikel> getArtikelsFromBestellingForKassa() {
        if(getActieveBestelling() == null){
            return getAfsluitBestelling().getArtikelsForKassa();
        }
        return getActieveBestelling().getArtikelsForKassa();
    }

    public List<Artikel> getArtikelsFromBestellingForKlant() {
        if(getActieveBestelling() == null){
            return getAfsluitBestelling().getArtikelsForKlant();
        }
        return getActieveBestelling().getArtikelsForKlant();
    }

    public List<Artikel> getArtikelsFromDb() {
        return this.db.getArtikels();
    }

    public List<String> getKortingStrategyList(){
        List<String> list = new ArrayList<>();
        for(KortingEnum korting: KortingEnum.values()){
            list.add(korting.toString());
        }
        return list;
    }

    public String getTotaalString(Bestelling bestelling){
        return "Totaal: " + bestelling.getTotaal() + " - Korting: " + bestelling.getKorting() + " = " + bestelling.getTotaalMinKorting();
    }

    public String getTotaalStringFromBetaaldeBestellingen(){
        String result = "";
        for(Bestelling bestelling: this.bestellingen){
            if(bestelling.getState().equals(bestelling.getBetaald())){
                result += bestelling.getTijdStip();
                result += getTotaalString(bestelling);
                result += "\n";
            }
        }
        return result;
    }

    public void schrijfDbWegNaarFile(){
        this.db.save();
    }

    @Override
    public void registerObserver(Observer o) {
        if (o == null) {
            throw new IllegalArgumentException("Ongeldige observer");
        }
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < this.observers.size(); i++) {
            Observer observer = this.observers.get(i);
            observer.update();
        }
    }
}
