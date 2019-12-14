package model.states;

import model.Artikel;
import model.Bestelling;
import model.ModelException;

public class SluitAf extends State {

    public SluitAf(Bestelling bestelling){
        super(bestelling);
    }

    @Override
    public void betaal(){ bestelling.setState(bestelling.getBetaald());}

    @Override
    public void verwijderArtikel(Artikel a){
        if(!bestelling.getArtikelsForKlant().contains(a)) throw new ModelException("Artikel kan niet verwijderd worden");
        Artikel artikel = null;
        for(Artikel artikel1: bestelling.getArtikelsForKlant()){
            if(artikel1.equals(a)){
                artikel = a;
            }
        }
        if(artikel.getAantal() > 1){
            artikel.verlaagAantal();
        }
        else bestelling.getArtikelsForKlant().remove(a);
    }
}
