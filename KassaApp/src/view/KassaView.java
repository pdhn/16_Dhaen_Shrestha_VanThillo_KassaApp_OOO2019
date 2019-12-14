package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Winkel;
import view.panes.KassaMainPane;

public class KassaView {
	private Stage stage = new Stage();
	private Winkel winkel;
	private static final String CSS_STYLESHEET = "src\\application\\application.css";
		
	public KassaView(){
		winkel = Winkel.getInstance();

		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);

		//scene.getStylesheets().add(getClass().getResource(CSS_STYLESHEET).toExternalForm()); // Add css sheet
		BorderPane borderPane = new KassaMainPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
		stage.setOnCloseRequest(event -> winkel.schrijfDbWegNaarFile());
	}
}
