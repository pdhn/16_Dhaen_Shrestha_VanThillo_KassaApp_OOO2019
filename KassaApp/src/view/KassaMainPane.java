package view;


import controller.KassaTabController;
import database.ArtikelTekstLoadSave;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Bestelling;
import view.panels.KassaTabPane;
import view.panels.ProductOverviewPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(Bestelling beselling){
		
	    TabPane tabPane = new TabPane();

        ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();
        KassaTabController kassaTabController = new KassaTabController(artikelTekstLoadSave,beselling);
        GridPane kassaTabPane = new KassaTabPane(kassaTabController);
        Tab kassaTab = new Tab("Kassa", kassaTabPane);

        GridPane productOverviewPane = new ProductOverviewPane();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);

        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");

        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
