package controller;

import model.Winkel;
import view.panes.tabs.InstellingenTab;

public class InstellingenTabController {
    private InstellingenTab instellingenTab;
    private Winkel winkel;

    public InstellingenTabController() {
        winkel = Winkel.getInstance();
    }

    public void setView(InstellingenTab instellingenTab){
        this.instellingenTab = instellingenTab;
    }

    public void setKorting(){
        
    }
}
