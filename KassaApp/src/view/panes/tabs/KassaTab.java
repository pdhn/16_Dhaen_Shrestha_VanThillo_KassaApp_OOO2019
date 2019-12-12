package view.panes.tabs;

import controller.KassaTabController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;
import model.Observer;
import model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class KassaTab extends GridPane implements Subject {
    private KassaTabController kassaTabController;
    private VBox vBox;
    private TableView table;
    private TextField textField;
    private Button addButton, onHoldButton, offHoldButton, afsluitButton;
    private Label artikelCode,totaal;
    private List<Observer> observers;

    public KassaTab(KassaTabController kassaTabController){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.kassaTabController =  kassaTabController;
        kassaTabController.setView(this);

        observers = new ArrayList<>();

        setLabels();
        setTextField();
        setButtons();
        setTableView();
        setHandlers();
    }

    private void setLabels() {
        artikelCode = new Label("Artikelcode:");
        this.add(artikelCode,0,0,1,1);

        totaal = new Label("Totaal:");
        this.add(totaal,0,11,2,1);
    }

    private void setTextField() {
        textField = new TextField();
        this.add(textField,1,0,1,1);
    }

    private void setButtons() {
        addButton = new Button("Voeg Toe");
        onHoldButton = new Button("On Hold");
        offHoldButton = new Button("Off Hold");
        afsluitButton = new Button("Sluit af");

        this.add(addButton,2,0,1,1);
        this.add(onHoldButton,3,0,1,1);
        this.add(offHoldButton,4,0,1,1);
        this.add(afsluitButton,2,11,1,1);
    }

    private void setTableView() {
        TableColumn<String, Artikel> column1 = new TableColumn<>("Omschrijving");
        column1.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<String, Artikel> column2 = new TableColumn<>("Prijs");
        column2.setCellValueFactory(new PropertyValueFactory<>("prijs"));

        table = new TableView();

        table.getColumns().add(column1);
        table.getColumns().add(column2);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        vBox = new VBox(table);
        this.add(vBox, 0, 1,6,10);
    }

    private void setHandlers() {
        addButton.setOnAction(event -> kassaTabController.addArtikelToBestelling());
        onHoldButton.setOnAction(event -> kassaTabController.setBestellingOnHold());
        offHoldButton.setOnAction(event -> kassaTabController.setBestellingOffHold());
        afsluitButton.setOnAction(event -> {
            kassaTabController.setTotaalWithKorting();
            notifyObservers();
        } );

        table.setRowFactory(ev -> {
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
                        kassaTabController.removeArtikel(artikelCode);
                    }
                }
            });
            return row;
        });
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

    @Override
    public void registerObserver(Observer o) {
        if (o == null) {
            throw new IllegalArgumentException("Ongeldige observer");
        }
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update();
        }
    }
}
