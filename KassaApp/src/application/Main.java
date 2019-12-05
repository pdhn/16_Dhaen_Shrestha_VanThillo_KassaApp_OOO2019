package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Bestelling;
import view.KassaView;
import view.KlantView;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Bestelling bestelling = new Bestelling();
		KassaView kassaView = new KassaView(bestelling);
		KlantView klantView = new KlantView(bestelling);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
