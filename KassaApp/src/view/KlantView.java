package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Bestelling;

public class KlantView {
	private Stage stage = new Stage();		
		
	public KlantView(){
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);

		GridPane gridPane = new KlantPane();
		root.getChildren().add(gridPane);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
