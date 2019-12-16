package view.panes.tabs;

import controller.ArtikelTabController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;

import java.util.List;


public class ArtikelTab extends GridPane {
    private ArtikelTabController artikelTabController;
    private TableView table;
    private Label productsLabel;
    private Button refreshButton;

    public ArtikelTab(ArtikelTabController artikelTabController) {
        this.artikelTabController = artikelTabController;
        artikelTabController.setView(this);

        setTable();
        setRefresh();
    }

    private void setTable() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        productsLabel = new Label("Products");
        this.add(productsLabel, 0, 0, 1, 1);

        TableColumn<String, Artikel> column1 = new TableColumn<>("Artikel Code");
        column1.setCellValueFactory(new PropertyValueFactory<>("artikelCode"));

        TableColumn<String, Artikel> column2 = new TableColumn<>("Omschrijving");
        column2.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<String, Artikel> column3 = new TableColumn<>("Artikel Groep");
        column3.setCellValueFactory(new PropertyValueFactory<>("artikelGroep"));

        TableColumn<String, Artikel> column4 = new TableColumn<>("Prijs");
        column4.setCellValueFactory(new PropertyValueFactory<>("prijs"));

        TableColumn<String, Artikel> column5 = new TableColumn<>("Voorraad");
        column5.setCellValueFactory(new PropertyValueFactory<>("voorraad"));

        table = new TableView<>();

        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox(table);

        this.add(vBox, 0, 0);

        for(Artikel a : artikelTabController.getArtikels()){
            table.getItems().add(a);
        }
    }

    public void setRefresh() {
        refreshButton = new Button("Refresh");
        this.add(refreshButton,0,1);
        refreshButton.setOnAction(event -> artikelTabController.refresh());
    }

    public void toonArtikels(List<Artikel> artikels) {
        table.getItems().clear();
        for(Artikel a : artikels){
            table.getItems().add(a);
        }
    }
}
