package view;

import controller.KassaTabController;
import controller.KlantController;
import database.ArtikelTekstLoadSave;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;
import model.Bestelling;
import view.panels.KassaTabPane;

import javax.swing.text.TabableView;

public class KlantPane extends GridPane {
    private TableView table;
    private Label label;
    private VBox vBox;
    private Label totaal;
    private KlantController klantController;

    public KlantPane(Bestelling bestelling){
        klantController = new KlantController(bestelling);
        klantController.setPane(this);

        label = new Label("Artikellijst");
        this.add(label,0,0,1,1);

        TableColumn<String, Artikel> column1 = new TableColumn<>("Omschrijving");
        column1.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<String, Artikel> column2 = new TableColumn<>("Prijs");
        column2.setCellValueFactory(new PropertyValueFactory<>("prijs"));

        table = new TableView();

        table.getColumns().add(column1);
        table.getColumns().add(column2);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        vBox = new VBox(table);
        this.add(vBox, 0, 1,3,10);

        totaal = new Label("Totaal:");
        this.add(totaal,0,11,2,1);
    }

    public void voegArtikelToe(Artikel a){
        table.getItems().add(a);
    }

    public void setTotaal(String string){
        totaal.setText(string);
    }
}
