package controller;

import database.ArtikelTekstLoadSave;
import javafx.scene.control.Alert;
import model.Artikel;
import model.Bestelling;
import view.panels.KassaTabPane;

public class KassaTabController {
    private KassaTabPane kassaTabPane;
    private ArtikelTekstLoadSave artikelen;
    private Bestelling bestelling;

    public KassaTabController(ArtikelTekstLoadSave artikelen, Bestelling bestelling){
        this.artikelen = artikelen;
        this.bestelling = bestelling;
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


}
