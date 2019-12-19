package controller;

import javafx.scene.control.Alert;
import model.Winkel;
import view.panes.tabs.KassaTab;

public class KassaTabController {
    private KassaTab kassaTab;
    private Winkel winkel;

    public KassaTabController(){
        winkel = Winkel.getInstance();
    }

    public void setView(KassaTab kassaTab){
        this.kassaTab = kassaTab;
    }

    private void toonArtikelsEnTotaal(){
        kassaTab.toonArtikels(winkel.getArtikelsFromBestellingForKassa());
        if(winkel.getActieveBestelling() == null){
            kassaTab.setTotaal(winkel.getTotaalString(winkel.getAfsluitBestelling()));
        }
        else kassaTab.setTotaal(winkel.getTotaalString(winkel.getActieveBestelling()));
    }

    public void addArtikelToBestelling(){
        try{
            winkel.addArtikelToBestelling(kassaTab.getTextField());

            toonArtikelsEnTotaal();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Niet mogelijk om dit artikel op dit moment toe te voegen");
            alert.showAndWait();
        }
    }

    public void removeArtikel(int code){
        try{
            winkel.removeArtikelFromBestelling(code);

            toonArtikelsEnTotaal();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Artikel kan niet verwijderd worden");
            alert.showAndWait();
        }
    }

    public void setBestellingOnHold(){
        try{
            winkel.setBestellingOnHold();

            kassaTab.setBestellingOnHold();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Al een bestelling on hold");
            alert.showAndWait();
        }
    }

    public void setBestellingOffHold() {
        try {
            winkel.setBestellingOffHold();

            toonArtikelsEnTotaal();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Geen bestelling on hold");
            alert.showAndWait();
        }
    }

    public void sluitBestellingAf() {
        try {
            winkel.sluitBestellingAf();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Bestelling is al afgesloten");
            alert.showAndWait();
        }
    }

    public void betaalBestelling() {
        try {
            winkel.betaalBestelling();

            toonArtikelsEnTotaal();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Sluit de bestelling af alvorens ze te laten betalen");
            alert.showAndWait();
        }
    }

    public void annuleerBestelling() {
        try {
            winkel.annuleerBestelling(winkel.getAfsluitBestelling());

            toonArtikelsEnTotaal();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Sluit de bestelling af alvorens ze te annuleren");
            alert.showAndWait();
        }
    }
}
