package view.panes.tabs;

import controller.LogTabController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class LogTab extends GridPane {
    private LogTabController logTabController;
    private Label label;
    private TextArea log;
    private Button refresh;

    public LogTab(LogTabController logTabController){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.logTabController = logTabController;
        logTabController.setView(this);

        label = new Label("Betaalde bestellingen:");
        log = new TextArea();
        refresh = new Button("Refresh");

        this.add(label,0,0);
        this.add(log,0,1);
        this.add(refresh,0,2);

        refresh.setOnAction(event ->  logTabController.getStringFromBetaaldeBestellingen());
    }

    public void setLog(String string){
        log.setText(string);
    }
}
