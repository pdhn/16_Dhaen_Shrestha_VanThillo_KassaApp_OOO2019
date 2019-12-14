package view.panes;


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
	public KassaMainPane(){
		
	    TabPane tabPane = new TabPane();

        KassaTabController kassaTabController = new KassaTabController();
        GridPane kassaTabPane = new KassaTab(kassaTabController);
        Tab kassaTab = new Tab("Kassa", kassaTabPane);

        GridPane productOverviewPane = new ArtikelTab();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);

        InstellingenTabController instellingenTabController = new InstellingenTabController();
        GridPane instellingenPane = new InstellingenTab(instellingenTabController);
        Tab instellingTab = new Tab("Instellingen", instellingenPane);

        LogTabController logTabController = new LogTabController();
        GridPane logPane = new LogTab(logTabController);
        Tab logTab = new Tab("Log", logPane);

        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
