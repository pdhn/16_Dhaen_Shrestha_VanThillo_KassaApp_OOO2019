package controller;

import javafx.scene.control.Alert;
import model.Winkel;
import view.panes.tabs.InstellingenTab;

import java.util.List;

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
}
