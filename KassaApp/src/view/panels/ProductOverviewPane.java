package view.panels;

import database.ArtikelTekstLoadSave;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Artikel;

import java.util.ArrayList;


public class ProductOverviewPane extends GridPane {
	private TableView table;
	private ArtikelTekstLoadSave artikelen;
	private static final String FILE_PATH = "C:\\Users\\pimdh\\Documents\\Semester_1_2020\\1_OO Ontwerpen (6)\\Group Werk\\KassaSysteem\\src\\bestanden\\artikel.txt";


	public ProductOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Products:"), 0, 0, 1, 1);

		this.artikelen = new ArtikelTekstLoadSave();
		ArrayList<Artikel> artikelenInArrayList;
		artikelenInArrayList = artikelen.load(FILE_PATH);

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

		for (Artikel a: artikelenInArrayList) { // Add each article to table
			table.getItems().add(a);
		}

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		VBox vBox = new VBox(table);

		this.add(vBox, 0, 0);
		/*
		tableView.getItems().add(new Person("John", "Doe"));
		tableView.getItems().add(new Person("Jane", "Deer"));

		VBox vbox = new VBox(tableView);

		Scene scene = new Scene(vbox);

		primaryStage.setScene(scene);

		primaryStage.show();
		*/
	}
	
	

}
