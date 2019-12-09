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

    public void addArtikelToBestelling(){
        try{
            winkel.addArtikelToBestelling(kassaTab.getTextField());

            kassaTab.toonArtikels(winkel.getArtikelsFromBestellingForKassa());
            kassaTab.setTotaal("Totaal: " + winkel.getTotaalFromBestelling());
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Niet bestaande code");
            alert.showAndWait();
        }
    }

    public void removeArtikel(){
        try{
            winkel.removeArtikelFromBestelling(kassaTab.getTextField());

            kassaTab.toonArtikels(winkel.getArtikelsFromBestellingForKassa());
            kassaTab.setTotaal("Totaal: " + winkel.getTotaalFromBestelling());
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

            kassaTab.toonArtikels(winkel.getArtikelsFromBestellingForKassa());
            kassaTab.setTotaal("Totaal: " + winkel.getTotaalFromBestelling());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Geen bestelling on hold");
            alert.showAndWait();
        }
    }
}
