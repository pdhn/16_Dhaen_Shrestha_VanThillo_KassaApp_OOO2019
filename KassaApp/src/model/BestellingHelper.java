package model;

import java.util.List;

public class BestellingHelper {
    public static void verwijderArtikel(Artikel a, List<Artikel> artikelsForKlant) {
        if(!artikelsForKlant.contains(a)) throw new ModelException("Artikel kan niet verwijderd worden");
        Artikel artikel = null;
        for(Artikel artikel1: artikelsForKlant){
            if(artikel1.equals(a)){
                artikel = a;
            }
        }
        if(artikel.getAantal() > 1){
            artikel.verlaagAantal();
        }
        else artikelsForKlant.remove(a);
    }
}
