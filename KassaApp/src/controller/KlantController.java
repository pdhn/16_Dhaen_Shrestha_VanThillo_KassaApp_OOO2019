package controller;

import model.Winkel;
import view.panes.KlantPane;
import model.Observer;

/**
 * @author Sander Van Thillo
 */
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
        klantPane.toonArtikels(winkel.getArtikelsFromBestellingForKlant());
        if(winkel.getActieveBestelling() == null){
            klantPane.setTotaal(winkel.getTotaalString(winkel.getAfsluitBestelling()));
        }
        else klantPane.setTotaal(winkel.getTotaalString(winkel.getActieveBestelling()));
    }

    @Override
    public void update() {
        toonArtikelsEnTotaal();
    }
}
