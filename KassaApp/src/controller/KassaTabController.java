package controller;

import javafx.scene.control.Alert;
import model.Bestelling;
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
        kassaTab.setTotaal("Totaal: " + winkel.getTotaalFromBestelling() + " - Korting: " + winkel.getKortingForBestelling() + " = " + winkel.getTotaalMetKorting());
    }

    public void addArtikelToBestelling(){
        try{
            winkel.addArtikelToBestelling(kassaTab.getTextField());

            toonArtikelsEnTotaal();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Niet bestaande code " + e.getMessage());
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
    /**
    public void nieuweVerkoop(){
        try {
        }
    }
     **/
}
