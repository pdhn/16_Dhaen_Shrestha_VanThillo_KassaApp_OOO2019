package view.panes;

import controller.KlantController;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;

import java.util.List;

public class KlantPane extends GridPane {
    private TableView table;
    private Label label;
    private VBox vBox;
    private Label totaal;
    private KlantController klantController;

    public KlantPane(KlantController klantController){
        this.klantController = klantController;
        klantController.setPane(this);

        setLabels();
        setTableView();
    }

    private void setLabels() {
        label = new Label("Artikellijst");
        this.add(label,0,0,1,1);

        totaal = new Label("Totaal:");
        this.add(totaal,0,11,2,1);
    }

    private void setTableView() {
        TableColumn<String, Artikel> column1 = new TableColumn<>("Omschrijving");
        column1.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<String, Artikel> column2 = new TableColumn<>("Aantal");
        column2.setCellValueFactory(new PropertyValueFactory<>("aantal"));

        TableColumn<String, Artikel> column3 = new TableColumn<>("Prijs");
        column3.setCellValueFactory(new PropertyValueFactory<>("prijs"));

        table = new TableView();

        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        vBox = new VBox(table);
        this.add(vBox, 0, 1,6,10);
    }

    public void toonArtikels(List<Artikel> artikels){
        table.getItems().clear();
        for(Artikel a: artikels){
            table.getItems().add(a);
        }
    }

    public void setTotaal(String string){
        totaal.setText(string);
    }
}
