package model;

import database.ArtikelDB;
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

    private static final String FILE_PATH_PROPERTIES = "src\\bestanden\\config.properties";

    private Winkel() {
        db = new ArtikelDB();
        bestellingen = new ArrayList<>();
        observers = new ArrayList<>();
        korting = new Geenkorting();
        voegBestellingToe(new Bestelling(this.korting));
    }

    public static Winkel getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Winkel();
        }
        return uniqueInstance;
    }

    public void voegBestellingToe(Bestelling bestelling){
        if(bestelling == null) throw new ModelException("Geen geldige bestelling");
        bestellingen.add(bestelling);
    }

    public void annuleerBestelling(Bestelling bestelling){
        if(bestelling == null) throw new ModelException("Geen geldige bestelling");
        bestellingen.remove(bestelling);
        voegBestellingToe(new Bestelling(this.korting));
        notifyObservers();
    }

    public void betaalBestelling(){
        pasVoorraadAan();
        getAfsluitBestelling().setKassaBon();
        System.out.println(getAfsluitBestelling().getKassaBonPrintMethode());
        setBestellingBetaald();
        voegBestellingToe(new Bestelling(this.korting));
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
        for(Bestelling b: bestellingen){
            if(b.getState().equals(b.getActief())){
                bestelling = b;
            }
        }
        return bestelling;
    }

    public void sluitBestellingAf(){
        getActieveBestelling().sluitAf();
    }

    public Bestelling getAfsluitBestelling(){
        Bestelling bestelling = null;
        for(Bestelling b: bestellingen){
            if(b.getState().equals(b.getSluitAf())){
                bestelling = b;
            }
        }
        return bestelling;
    }

    public void setBestellingOnHold() {
        if (getBestellingOnHold() != null) throw new ModelException("Er is al een bestelling on hold");
        getActieveBestelling().zetOnHold();
        bestellingen.add(new Bestelling(this.korting));
        notifyObservers();
    }

    public void setBestellingOffHold() {
        if( getBestellingOnHold() == null) throw new ModelException("Er is geen bestelling on hold");
        bestellingen.remove(getActieveBestelling());
        getBestellingOnHold().zetOffHold();
        notifyObservers();
    }

    private Bestelling getBestellingOnHold(){
        Bestelling bestelling = null;
        for(Bestelling b: bestellingen){
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

    public void setKassaBon() {
        if (getActieveBestelling() == null){
            getAfsluitBestelling().setKassaBon();
        }
        else getActieveBestelling().setKassaBon();
    }

    public void addArtikelToBestelling(int artikelCode) {
        Artikel a = db.getArtikel(artikelCode);
        getActieveBestelling().voegArtikelToe(a);
        notifyObservers();
    }

    public void removeArtikelFromBestelling(int artikelCode) {
        Artikel a = db.getArtikel(artikelCode);
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
        return db.getArtikels();
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
        for(Bestelling bestelling: bestellingen){
            if(bestelling.getState().equals(bestelling.getBetaald())){
                result += bestelling.getTijdStip();
                result += getTotaalString(bestelling);
                result += "\n";
            }
        }
        return result;
    }

    public void schrijfDbWegNaarFile(){
        db.save();
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
