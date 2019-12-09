package view.panels;

import controller.KassaTabController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;

import java.util.List;


public class KassaTabPane extends GridPane {
    private KassaTabController kassaTabController;
    private VBox vBox;
    private TableView table;
    private TextField textField;
    private Button addButton, removeButton, onHoldButton, offHoldButton;
    private Label totaal;

    public KassaTabPane(KassaTabController kassaTabController){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Artikelcode:"),0,0,1,1);

        textField = new TextField();
        this.add(textField,1,0,1,1);

        this.kassaTabController =  kassaTabController;
        kassaTabController.setView(this);

        addButton = new Button("Voeg Toe");
        addButton.setOnAction(event -> kassaTabController.addArtikelToBestelling());
        removeButton = new Button("Verwijder");
        removeButton.setOnAction(event -> kassaTabController.removeArtikel());
        onHoldButton = new Button("On Hold");
        onHoldButton.setOnAction(event -> kassaTabController.setBestellingOnHold());
        offHoldButton = new Button("Off Hold");
        offHoldButton.setOnAction(event -> kassaTabController.setBestellingOffHold());

        this.add(addButton,2,0,1,1);
        this.add(removeButton, 3,0,1,1);
        this.add(onHoldButton,4,0,1,1);
        this.add(offHoldButton,5,0,1,1);

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

    public int getTextField(){
        return Integer.parseInt(textField.getText());
    }

    public void toonArtikels(List<Artikel> artikels){
        table.getItems().clear();
        for(Artikel a : artikels){
            table.getItems().add(a);
        }
    }

    public void setTotaal(String string){
        totaal.setText(string);
    }

    public void setBestellingOnHold(){
        table.getItems().clear();
        totaal.setText("Totaal:");
    }
}
