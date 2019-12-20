package model;

import java.util.List;

/**
 * @author Sander Van Thillo
 */
public class BestellingHelper {

    /**
     * Verwijdert een artikel uit een lijst van artikels als het aantal van dat artikel op één staat
     * Anders wordt het aantal van dat artikel met één verlaagd.
     *
     * @param a
     * @param artikelsForKlant
     */
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
