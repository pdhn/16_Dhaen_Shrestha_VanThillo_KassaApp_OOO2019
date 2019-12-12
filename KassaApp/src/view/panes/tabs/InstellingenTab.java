package view.panes.tabs;
import controller.InstellingenTabController;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.ModelException;

import java.io.*;
import java.util.Properties;

public class InstellingenTab extends GridPane {
    private InstellingenTabController instellingenTabController;
    private ComboBox korting;
    private TextField percentageField, bedragField;
    private Label percentageLabel, bedragLabel;
    private Button kortingButton;

    private static final String FILE_PATH_PROPERTIES = "src\\bestanden\\config.properties";

    public InstellingenTab(InstellingenTabController instellingenTabController) {
        this.instellingenTabController = instellingenTabController;
        instellingenTabController.setView(this);

        final ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioBtnBestandTypeExcel = new RadioButton("Gebruik Excel voor Input/Output");
        RadioButton radioBtnBestandTypeTxt = new RadioButton("Gebruik txt voor Input/Output");

        Properties properties;

        try (InputStream inputStream = new FileInputStream(FILE_PATH_PROPERTIES)) {
            properties = new Properties();
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ModelException("Error reading config file");
        }

        radioBtnBestandTypeExcel.setToggleGroup(toggleGroup);
        radioBtnBestandTypeExcel.setSelected(Boolean.valueOf(properties.getProperty("excel")));

        radioBtnBestandTypeTxt.setToggleGroup(toggleGroup);
        radioBtnBestandTypeTxt.setSelected(Boolean.valueOf(properties.getProperty("txt")));


        this.add(radioBtnBestandTypeExcel, 0, 0);
        this.add(radioBtnBestandTypeTxt, 0, 1);

        Button confirmBtn = new Button("Confirm");

        confirmBtn.setOnAction(e -> {

            try (OutputStream outputStream = new FileOutputStream(FILE_PATH_PROPERTIES)) {

                properties.setProperty("excel", String.valueOf(radioBtnBestandTypeExcel.isSelected()));
                properties.setProperty("txt", String.valueOf(radioBtnBestandTypeTxt.isSelected()));

                properties.store(outputStream, null);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                throw new ModelException("Error writing to config file");
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new ModelException("Error writing to config file");
            }
        });

        this.add(confirmBtn, 0, 2);

        //Kortingen
        korting = new ComboBox(FXCollections.observableArrayList(instellingenTabController.getKortingStrategyList()));
        korting.setValue("Geen");

        percentageLabel = new Label("Percentage");
        bedragLabel = new Label("Bedrag");
        percentageField = new TextField("0");
        bedragField = new TextField("0");
        kortingButton = new Button("Apply");

        this.add(korting, 0, 4);
        this.add(percentageLabel,0,5);
        this.add(bedragLabel,1,5);
        this.add(percentageField,0,6);
        this.add(bedragField,1,6);
        this.add(kortingButton,0,7);

        kortingButton.setOnAction(event -> instellingenTabController.setKorting());

    }


    public String getKorting(){
        return korting.getValue().toString();
    }

    public int getPercentageField() {
        int percentage = 0;
        try {
            percentage = Integer.parseInt(percentageField.getText());
        } catch (Exception e) {
            setAlert();
        }
        return percentage;
    }

    public int getBedragField(){
        int bedrag = 0;
        try {
            bedrag = Integer.parseInt(bedragField.getText());
        } catch (Exception e) {
            setAlert();
        }
        return bedrag;
    }

    public void setAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Geef een geheel getal in");
        alert.showAndWait();
    }
}
