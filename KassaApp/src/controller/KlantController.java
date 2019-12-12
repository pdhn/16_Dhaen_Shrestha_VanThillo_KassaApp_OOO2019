package controller;

import model.Winkel;
import view.panes.KlantPane;
import model.Observer;

public class KlantController implements Observer {
    private KlantPane klantPane;
    private Winkel winkel;

    public KlantController(){
        winkel = Winkel.getInstance();
        winkel.registerObserver(this);
    }

    public void setPane(KlantPane klantPane){
        this.klantPane = klantPane;
    }

    private void toonArtikelsEnTotaal(){
        klantPane.toonArtikels(winkel.getArtikelsFromBestellingForKassa());
        klantPane.setTotaal("Totaal: " + winkel.getTotaalFromBestelling() + " - Korting: " + winkel.getKortingForBestelling() + " = " + winkel.getTotaalMetKorting());
    }

    @Override
    public void update() {
        toonArtikelsEnTotaal();
    }
}
