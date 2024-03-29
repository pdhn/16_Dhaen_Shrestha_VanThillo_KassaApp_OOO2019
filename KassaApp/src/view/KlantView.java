package view;

import controller.KlantController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.panes.KlantPane;

public class KlantView {
	private Stage stage = new Stage();		
		
	public KlantView(){
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);

		KlantController klantController = new KlantController();
		GridPane gridPane = new KlantPane(klantController);
		root.getChildren().add(gridPane);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
