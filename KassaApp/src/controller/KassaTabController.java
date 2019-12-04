package controller;

import database.ArtikelTekstLoadSave;
import javafx.scene.control.Alert;
import model.Artikel;
import view.panels.KassaTabPane;

public class KassaTabController {
    private KassaTabPane kassaTabPane;
    private ArtikelTekstLoadSave artikelen;
    private double totaal;

    public KassaTabController(KassaTabPane kassaTabPane, ArtikelTekstLoadSave artikelen){
        this.kassaTabPane = kassaTabPane;
        this.artikelen = artikelen;
    }

    public void getArtikel(){
        try{
            Artikel a = artikelen.getArtikel(kassaTabPane.getTextField());
            totaal += a.getPrijs();
            kassaTabPane.voegArtikelToe(a);
            kassaTabPane.setTotaal("Totaal: " + totaal);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("niet bestaande code");
            alert.showAndWait();
        }
    }
}
