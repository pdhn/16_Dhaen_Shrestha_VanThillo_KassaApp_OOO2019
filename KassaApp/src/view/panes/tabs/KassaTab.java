package view.panes.tabs;

import controller.KassaTabController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;

import java.util.List;
import java.util.Optional;

/**
 * @author Sander Van Thillo
 */
public class KassaTab extends GridPane {
    private KassaTabController kassaTabController;
    private VBox vBox;
    private TableView table;
    private TextField textField;
    private Button addButton, onHoldButton, offHoldButton, afsluitButton, betaalButton, annuleerButton;
    private Label artikelCode,totaal;

    public KassaTab(KassaTabController kassaTabController){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.kassaTabController =  kassaTabController;
        kassaTabController.setView(this);

        setLabels();
        setTextField();
        setButtons();
        setTableView();
        setHandlers();
    }

    private void setLabels() {
        this.artikelCode = new Label("Artikelcode:");
        this.add(this.artikelCode,0,0,1,1);

        this.totaal = new Label("Totaal:");
        this.add(this.totaal,0,11,2,1);
    }

    private void setTextField() {
        this.textField = new TextField();
        this.add(this.textField,1,0,1,1);
    }

    private void setButtons() {
        this.addButton = new Button("Voeg Toe");
        this.onHoldButton = new Button("On Hold");
        this.offHoldButton = new Button("Off Hold");
        this.afsluitButton = new Button("Sluit af");
        this.betaalButton = new Button("Betaal");
        this.annuleerButton = new Button("Annuleer");

        this.add(this.addButton,2,0,1,1);
        this.add(this.onHoldButton,3,0,1,1);
        this.add(this.offHoldButton,4,0,1,1);
        this.add(this.afsluitButton,2,11,1,1);
        this.add(this.betaalButton,3,11,1,1);
        this.add(this.annuleerButton,4,11,1,1);
    }

    private void setTableView() {
        TableColumn<String, Artikel> column1 = new TableColumn<>("Omschrijving");
        column1.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<String, Artikel> column2 = new TableColumn<>("Prijs");
        column2.setCellValueFactory(new PropertyValueFactory<>("prijs"));

        this.table = new TableView();

        this.table.getColumns().add(column1);
        this.table.getColumns().add(column2);

        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.vBox = new VBox(this.table);
        this.add(this.vBox, 0, 1,6,10);
    }

    private void setHandlers() {
        this.addButton.setOnAction(event -> this.kassaTabController.addArtikelToBestelling());
        this.onHoldButton.setOnAction(event -> this.kassaTabController.setBestellingOnHold());
        this.offHoldButton.setOnAction(event -> this.kassaTabController.setBestellingOffHold());
        this.afsluitButton.setOnAction(event -> this.kassaTabController.sluitBestellingAf());
        this.betaalButton.setOnAction(event -> this.kassaTabController.betaalBestelling());
        this.annuleerButton.setOnAction(event -> this.kassaTabController.annuleerBestelling());

        this.table.setRowFactory(ev -> {
            TableRow<Artikel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 1 && (!row.isEmpty())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Bevestiging");
                    alert.setHeaderText("Artikel verwijderen?");
                    alert.setContentText("Wilt u dit artikel verwijderen?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.isPresent() && result.get() == ButtonType.OK){
                        int artikelCode = row.getItem().getArtikelCode();
                        this.kassaTabController.removeArtikel(artikelCode);
                    }
                }
            });
            return row;
        });
    }

    public int getTextField(){
        return Integer.parseInt(this.textField.getText());
    }

    public void toonArtikels(List<Artikel> artikels){
        this.table.getItems().clear();
        for(Artikel a : artikels){
            this.table.getItems().add(a);
        }
    }

    public void setTotaal(String string){
        this.totaal.setText(string);
    }

    public void setBestellingOnHold(){
        this.table.getItems().clear();
        this.totaal.setText("Totaal:");
    }
}
