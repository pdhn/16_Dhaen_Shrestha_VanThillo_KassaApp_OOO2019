package model.states;

import model.Artikel;
import model.Bestelling;
import model.BestellingHelper;
import model.ModelException;

import java.util.List;

/**
 * @author Sander Van Thillo
 */
public class Actief extends State {

    public Actief(Bestelling bestelling){
        super(bestelling);
    }

    @Override
    public void zetOnHold(){
        bestelling.setState(bestelling.getOnHold());
    }

    @Override
    public void sluitAf(){
        bestelling.setState(bestelling.getSluitAf());
    }

    /**
     * Voegt een artikel toe aan de lijst van artikels dat een bestelling bevat
     * als de lijst het artikel al bevat wordt het aantal van het artikel met 1 verhoogd
     *
     * @param a
     */
    @Override
    public void voegArtikelToe(Artikel a){
        if(a == null) throw new ModelException("Geen geldig artikel");
        if(bestelling.getArtikelsForKlant().contains(a)){
            for(Artikel artikel: bestelling.getArtikelsForKlant()){
                if(artikel.equals(a)){
                    artikel.verhoogAantal();
                }
            }
        }
        else bestelling.getArtikelsForKlant().add(a);
    }

    @Override
    public void verwijderArtikel(Artikel a){
        BestellingHelper.verwijderArtikel(a, bestelling.getArtikelsForKlant());
    }
}
