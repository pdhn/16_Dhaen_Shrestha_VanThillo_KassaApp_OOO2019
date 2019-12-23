package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.KassaView;
import view.KlantView;

/***
 *
 * @author 16_Dhaen_Shrestha_VanThillo_KassaApp_OOO2019
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
