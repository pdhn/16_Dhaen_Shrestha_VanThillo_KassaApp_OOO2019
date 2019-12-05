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

    public void getArtikel(){
        try{
            Artikel a = artikelen.getArtikel(kassaTabPane.getTextField());
            bestelling.setTotaal(a.getPrijs());
            bestelling.voegArtikelToe(a);

            kassaTabPane.voegArtikelToe(a);
            kassaTabPane.setTotaal("Totaal: " + bestelling.getTotaal());
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("niet bestaande code");
            alert.showAndWait();
        }
    }


}
