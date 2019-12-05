package controller;

import database.ArtikelTekstLoadSave;
import javafx.scene.control.Alert;
import model.Artikel;
import model.Bestelling;
import view.panels.KassaTabPane;

import java.util.ArrayList;

public class KassaTabController {
    private KassaTabPane kassaTabPane;
    private ArtikelTekstLoadSave artikelen;
    private Bestelling bestelling;
    private Bestelling bestellingOnHold;

    public KassaTabController(ArtikelTekstLoadSave artikelen, Bestelling bestelling){
        this.artikelen = artikelen;
        this.bestelling = bestelling;
        bestellingOnHold = null;
    }

    public void setView(KassaTabPane kassaTabPane){
        this.kassaTabPane = kassaTabPane;
    }

    public void addArtikel(){
        try{
            Artikel a = artikelen.getArtikel(kassaTabPane.getTextField());
            bestelling.addTotaal(a.getPrijs());
            bestelling.voegArtikelToe(a);

            kassaTabPane.voegArtikelToe(a);
            kassaTabPane.setTotaal("Totaal: " + bestelling.getTotaal());
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
            Artikel a = artikelen.getArtikel(kassaTabPane.getTextField());
            bestelling.removeTotaal(a.getPrijs());
            bestelling.verwijderArtikel(a);

            kassaTabPane.verwijderArtikel(a);
            kassaTabPane.setTotaal("Totaal: " + bestelling.getTotaal());
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
            if(bestellingOnHold != null) throw new IllegalArgumentException();
            bestellingOnHold = bestelling;
            bestelling.setArtikels(new ArrayList<>());
            bestelling.setTotaal(0);
            bestelling.notifyObservers();

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
            if (bestellingOnHold == null) throw new IllegalArgumentException();
            bestelling = bestellingOnHold;
            bestelling.notifyObservers();

            kassaTabPane.setBestellingOffHold(bestelling.getArtikels());
            kassaTabPane.setTotaal("Totaal: " + bestelling.getTotaal());

            bestellingOnHold = null;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Geen bestelling on hold");
            alert.showAndWait();
        }
    }
}
