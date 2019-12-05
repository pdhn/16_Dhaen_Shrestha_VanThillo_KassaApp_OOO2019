package controller;

import model.Bestelling;
import view.KlantPane;
import model.Observer;

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
        klantPane.updateArtikelList(bestelling.getArtikels());
        klantPane.setTotaal("Totaal: " + bestelling.getTotaal());
    }
}
