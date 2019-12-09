package model;

import java.util.ArrayList;
import java.util.List;

public class Bestelling {
    private List<Artikel> artikels;
    private double totaal;

    public Bestelling(){
        artikels = new ArrayList<>();
        totaal = 0;
    }

    public void voegArtikelToe(Artikel a){
        if(a == null) throw new ModelException("Geen geldig artikel");
        artikels.add(a);
    }

    public void verwijderArtikel(Artikel a){
        if(!artikels.contains(a) || a == null) throw new ModelException("Artikel kan niet verwijderd worden");
        artikels.remove(a);
    }

    public List<Artikel> getArtikels(){
        return this.artikels;
    }

    public List<Artikel> getArtikelsForKlant(){
        List<Artikel> klantArtikels = new ArrayList<>();
        for(Artikel a : artikels){
            if(!klantArtikels.contains(a)){
                klantArtikels.add(a);
            }
        }
        return klantArtikels;
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
}
