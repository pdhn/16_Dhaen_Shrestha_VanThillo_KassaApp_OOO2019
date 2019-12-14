package model.states;

import model.Artikel;
import model.Bestelling;
import model.ModelException;

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
