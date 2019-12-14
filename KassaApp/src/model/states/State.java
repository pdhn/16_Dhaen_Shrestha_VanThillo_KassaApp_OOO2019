package model.states;

import model.Artikel;
import model.Bestelling;
import model.ModelException;

public class State {
    Bestelling bestelling;

    public State(Bestelling bestelling){
        this.bestelling = bestelling;
    }

    public void zetOnHold() { throw new ModelException("De bestelling kan niet on hold gezet worden"); }
    public void zetOffHold() { throw new ModelException("De bestelling kan niet off hold gehaald worden"); }
    public void sluitAf() { throw new ModelException("De bestelling kan niet afgesloten worden"); }
    public void betaal() { throw new ModelException("De bestelling kan nog niet betaald worden"); }

    public void voegArtikelToe(Artikel a) { throw new ModelException("Er kan geen artikel toegevoegd worden"); }
    public void verwijderArtikel(Artikel a) { throw new ModelException("Er kan geen artikel verwijderd worden"); }

}
