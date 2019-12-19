package controller;

import javafx.scene.control.Alert;
import model.Winkel;
import view.panes.tabs.InstellingenTab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

public class InstellingenTabController {
    private InstellingenTab instellingenTab;

    private Winkel winkel;

    public InstellingenTabController() {
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

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("De kassabon kon niet aangepast worden");
            alert.showAndWait();
        }
    }

    public void setFileTxt() {
        try{
            Properties config = new Properties();
            FileInputStream in = new FileInputStream("src\\bestanden\\config.properties");
            config.load(in);
            in.close();

            config.setProperty("file","Tekst");

            FileOutputStream os = new FileOutputStream("src\\bestanden\\config.properties");
            config.store(os, "---No Comment---");
            os.close();

            instellingenTab.setInfoLabel("De Tekst file zal bij de volgende sessie gebruikt worden.");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("De File kon niet aangepast worden");
            alert.showAndWait();
        }
    }

    public void setFileExcel() {
        try{
            Properties config = new Properties();
            FileInputStream in = new FileInputStream("src\\bestanden\\config.properties");
            config.load(in);
            in.close();

            config.setProperty("file","Excel");

            FileOutputStream os = new FileOutputStream("src\\bestanden\\config.properties");
            config.store(os, "---No Comment---");
            os.close();

            instellingenTab.setInfoLabel("De Excel file zal bij de volgende sessie gebruikt worden.");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("De File kon niet aangepast worden");
            alert.showAndWait();
        }

    }
}
