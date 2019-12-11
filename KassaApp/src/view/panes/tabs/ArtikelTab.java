package view.panes.tabs;

import database.ArtikelExcelLoadSave;
import database.ArtikelLoadSaveTemplate;
import database.ArtikelTekstLoadSave;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;
import model.ModelException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


public class ArtikelTab extends GridPane {
    private TableView table;

    private static final String FILE_PATH_PROPERTIES = "src\\bestanden\\config.properties";


    public ArtikelTab() {

        ArtikelLoadSaveTemplate db = initDB();

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Products:"), 0, 0, 1, 1);

        ArrayList<Artikel> artikels = (ArrayList<Artikel>) db.getArtikels();

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

        for (Artikel a : artikels) { // Add each article to table
            table.getItems().add(a);
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox(table);

        this.add(vBox, 0, 0);

    }

    public ArtikelLoadSaveTemplate initDB() {

        // --- db reads from excel or txt depending on config.properties file ---
        try (InputStream inputStream = new FileInputStream(FILE_PATH_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            if (Boolean.valueOf(properties.getProperty("txt"))) {
                return new ArtikelTekstLoadSave();
            } else {
                return new ArtikelExcelLoadSave();
            }
        } catch (IOException e) {
            throw new ModelException("Error reading config file --->\n");
        }
        // ---^db reads from excel or txt depending on config.properties file^---

    }

}
