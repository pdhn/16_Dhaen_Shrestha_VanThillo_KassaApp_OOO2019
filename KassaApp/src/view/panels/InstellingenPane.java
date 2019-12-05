package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class InstellingenPane extends GridPane {


    public InstellingenPane(){

        final ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioBtnBestandTypeExcel = new RadioButton("Gebruik Excel voor Input/Output");

        radioBtnBestandTypeExcel.setToggleGroup(toggleGroup);
        radioBtnBestandTypeExcel.setSelected(false);

        RadioButton radioBtnBestandTypeTxt = new RadioButton("Gebruik txt voor Input/Output");

        radioBtnBestandTypeTxt.setToggleGroup(toggleGroup);
        radioBtnBestandTypeTxt.setSelected(true);


        this.add(radioBtnBestandTypeExcel,0,0);
        this.add(radioBtnBestandTypeTxt, 0,1);

        Button confirmBtn = new Button("Confirm");

        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                
            }
        });

        this.add(confirmBtn, 0,2);
    }
}
