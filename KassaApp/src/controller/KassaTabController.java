package controller;

import javafx.scene.control.Alert;
import model.Winkel;
import view.panels.KassaTabPane;

public class KassaTabController {
    private KassaTabPane kassaTabPane;
    private Winkel winkel;

    public KassaTabController(){
        winkel = Winkel.getInstance();
    }

    public void setView(KassaTabPane kassaTabPane){
        this.kassaTabPane = kassaTabPane;
    }

    public void addArtikelToBestelling(){
        try{
            winkel.addArtikelToBestelling(kassaTabPane.getTextField());

            kassaTabPane.toonArtikels(winkel.getArtikelsFromBestellingVoorKassa());
            kassaTabPane.setTotaal("Totaal: " + winkel.getTotaalFromBestelling());
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
            winkel.removeArtikelFromBestelling(kassaTabPane.getTextField());

            kassaTabPane.toonArtikels(winkel.getArtikelsFromBestellingVoorKassa());
            kassaTabPane.setTotaal("Totaal: " + winkel.getTotaalFromBestelling());
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

            kassaTabPane.setBestellingOnHold();
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

            kassaTabPane.toonArtikels(winkel.getArtikelsFromBestellingVoorKassa());
            kassaTabPane.setTotaal("Totaal: " + winkel.getTotaalFromBestelling());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Geen bestelling on hold");
            alert.showAndWait();
        }
    }
}
