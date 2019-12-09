package view;


import controller.KassaTabController;
import database.ArtikelTekstLoadSave;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Bestelling;
import view.panels.InstellingenPane;
import view.panels.KassaTabPane;
import view.panels.ProductOverviewPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(){
		
	    TabPane tabPane = new TabPane();

        KassaTabController kassaTabController = new KassaTabController();
        GridPane kassaTabPane = new KassaTabPane(kassaTabController);
        Tab kassaTab = new Tab("Kassa", kassaTabPane);

        GridPane productOverviewPane = new ProductOverviewPane();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);

        GridPane instellingenPane = new InstellingenPane();
        Tab instellingTab = new Tab("Instellingen", instellingenPane);
        Tab logTab = new Tab("Log");

        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
