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

        setLogNodes();
    }

    private void setLogNodes() {
        this.label = new Label("Betaalde bestellingen:");
        this.log = new TextArea();
        this.refresh = new Button("Refresh");

        this.add(this.label,0,0);
        this.add(this.log,0,1);
        this.add(this.refresh,0,2);

        this.refresh.setOnAction(event ->  this.logTabController.getStringFromBetaaldeBestellingen());
    }

    public void setLog(String string){
        this.log.setText(string);
    }
}
