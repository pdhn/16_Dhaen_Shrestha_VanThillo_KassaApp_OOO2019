package controller;

import model.Artikel;
import model.Bestelling;
import view.KlantPane;
import model.Observer;
import model.Subject;

public class KlantController implements Observer {
    private KlantPane klantPane;
    private Bestelling bestelling;

    public KlantController(Bestelling bestelling){
        this.bestelling = bestelling;
        bestelling.registerObserver(this);
    }

    public void setPane(KlantPane klantPane){
        this.klantPane = klantPane;
    }

    @Override
    public void update() {
        klantPane.voegArtikelToe(bestelling.getLaatsteArtikel());
        klantPane.setTotaal("Totaal: " + bestelling.getTotaal());
    }
}
