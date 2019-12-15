package controller;

import javafx.scene.control.Alert;
import model.Artikel;
import model.Winkel;
import view.panes.tabs.ArtikelTab;
import view.panes.tabs.InstellingenTab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InstellingenAndArtikelTabController {
    private InstellingenTab instellingenTab;

    private Winkel winkel;

    public InstellingenAndArtikelTabController() {
        winkel = Winkel.getInstance();
    }

    public void setView(InstellingenTab instellingenTab){
        this.instellingenTab = instellingenTab;
    }

    public List<String> getKortingStrategyList(){
        return winkel.getKortingStrategyList();
    }

    public void setKorting(){
        String type = instellingenTab.getKorting();
        int percentage = instellingenTab.getPercentageField();
        int bedrag = instellingenTab.getBedragField();
        try{
            winkel.setKorting(type, percentage, bedrag);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("De korting kon niet ingesteld worden");
            alert.showAndWait();
        }

    }

    public void setKassaBon() {
        try {
            Properties config = new Properties();
            FileInputStream in = new FileInputStream("src\\bestanden\\config.properties");
            config.load(in);
            in.close();

            config.setProperty("algemeneBoodschapCheck", instellingenTab.isCheckBox1Selected());
            config.setProperty("algemeneBoodschap", instellingenTab.getCheckBox1Field());
            config.setProperty("datumtijd", instellingenTab.isCheckBox2Selected());
            config.setProperty("kortingLijn", instellingenTab.isCheckBox3Selected());
            config.setProperty("btw", instellingenTab.isCheckBox4Selected());
            config.setProperty("afsluitlijnCheck", instellingenTab.isCheckBox5Selected());
            config.setProperty("afsluitlijn", instellingenTab.getCheckBox5Field());


            FileOutputStream os = new FileOutputStream("src\\bestanden\\config.properties");
            config.store(os, "---No Comment---");
            os.close();

            winkel.setKassaBon();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("De kassabon kon niet aangepast worden");
            alert.showAndWait();
        }
    }

    public void refreshTableArticleTab(ArtikelTab productOverviewPane) {
        productOverviewPane.refreshTable(getArtikels());
    }

    public ArrayList<Artikel> getArtikels() {
        return (ArrayList<Artikel>) this.winkel.getArtikelsFromDb();
    }

}
