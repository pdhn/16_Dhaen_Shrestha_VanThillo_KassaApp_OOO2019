package view.panes;

import controller.ArtikelTabController;
import controller.InstellingenTabController;
import controller.KassaTabController;
import controller.LogTabController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import view.panes.tabs.InstellingenTab;
import view.panes.tabs.KassaTab;
import view.panes.tabs.ArtikelTab;
import view.panes.tabs.LogTab;

public class KassaMainPane extends BorderPane {
    private TabPane tabPane;
    private Tab kassaTab, artikelTab, instellingenTab, logTab;
    private GridPane kassaPane, artikelPane, instellingenPane, logPane;
    private KassaTabController kassaTabController;
    private ArtikelTabController artikelTabController;
    private InstellingenTabController instellingenTabController;
    private LogTabController logTabController;


    public KassaMainPane() {
        setKassaTab();
        setArtikelTab();
        setInstellingenTab();
        setLogTab();
        setTabPane();
    }

    private void setKassaTab() {
        this.kassaTabController = new KassaTabController();
        this.kassaPane = new KassaTab(this.kassaTabController);
        this.kassaTab = new Tab("Kassa", this.kassaPane);
    }

    private void setArtikelTab() {
        this.artikelTabController = new ArtikelTabController();
        this.artikelPane = new ArtikelTab(this.artikelTabController);
        this.artikelTab = new Tab("Artikelen", this.artikelPane);
    }

    private void setInstellingenTab() {
        this.instellingenTabController = new InstellingenTabController();
        this.instellingenPane = new InstellingenTab(this.instellingenTabController);
        this.instellingenTab = new Tab("Instellingen", this.instellingenPane);
    }

    private void setLogTab() {
        this.logTabController = new LogTabController();
        this.logPane = new LogTab(this.logTabController);
        this.logTab = new Tab("Log", this.logPane);
    }

    private void setTabPane() {
        this.tabPane = new TabPane();

        this.tabPane.getTabs().add(this.kassaTab);
        this.tabPane.getTabs().add(this.artikelTab);
        this.tabPane.getTabs().add(this.instellingenTab);
        this.tabPane.getTabs().add(this.logTab);
        this.setCenter(this.tabPane);
    }
}
