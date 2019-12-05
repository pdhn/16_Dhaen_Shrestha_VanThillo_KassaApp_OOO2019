package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Bestelling;

import java.io.File;

public class KassaView {
	private Stage stage = new Stage();
	private static final String CSS_STYLESHEET = "src\\application\\application.css";
		
	public KassaView(Bestelling bestelling){
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);

		//scene.getStylesheets().add(getClass().getResource(CSS_STYLESHEET).toExternalForm()); // Add css sheet
		BorderPane borderPane = new KassaMainPane(bestelling);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
