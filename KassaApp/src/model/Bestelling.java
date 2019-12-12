package model;

import java.util.ArrayList;
import java.util.List;

public class Bestelling {
    private List<Artikel> artikels;
    private double totaal;
    private static final double BTW_PERCENTAGE = 0.06;

    public Bestelling(){
        artikels = new ArrayList<>();
        totaal = 0;
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
    }

    public void verwijderAlleArtikels(){
        for(Artikel artikel : artikels){
            artikels.remove(artikel);
        }
    }

    public List<Artikel> getArtikelsForKassa(){
        List<Artikel> kassaArtikels = new ArrayList<>();
        for(Artikel a : artikels){
            for(int i = 0; i < a.getAantal(); i++){
                kassaArtikels.add(a);
            }
        }
        return kassaArtikels;
    }

    public List<Artikel> getArtikelsForKlant(){ return this.artikels; }

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

    public double getTotaalMetBTW(){ return this.totaal + BTW_PERCENTAGE * this.totaal; }

    public void nieuweVerkoop(){

    }
}
