package model.states;

import model.Artikel;
import model.Bestelling;
import model.BestellingHelper;
import model.ModelException;

public class SluitAf extends State {

    public SluitAf(Bestelling bestelling){
        super(bestelling);
    }

    @Override
    public void betaal(){ bestelling.setState(bestelling.getBetaald());}

    @Override
    public void verwijderArtikel(Artikel a){
        BestellingHelper.verwijderArtikel(a, bestelling.getArtikelsForKlant());
    }
}
