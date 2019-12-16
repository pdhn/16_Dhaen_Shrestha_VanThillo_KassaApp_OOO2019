package view.panes.tabs;
import controller.InstellingenTabController;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class InstellingenTab extends GridPane {
    private InstellingenTabController instellingenTabController;
    private ComboBox korting;
    private TextField percentageField, bedragField, checkBox1Field, checkBox5Field;
    private Label bestandLabel, infoLabel, percentageLabel, bedragLabel, kassaBonLabel;
    private Button txtButton, excelButton, kortingButton, kassaBonButton;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;

    public InstellingenTab(InstellingenTabController instellingenTabController) {
        this.instellingenTabController = instellingenTabController;
        instellingenTabController.setView(this);

        //Input-Output
        bestandLabel = new Label("Input/output file:");
        txtButton = new Button("Txt");
        excelButton = new Button("Excel");
        infoLabel = new Label("Deze file zal bij de volgende sessie gebruikt worden.");

        this.add(bestandLabel, 0, 0);
        this.add(txtButton, 0, 1);
        this.add(excelButton,1,1);
        this.add(infoLabel,0,2);

        txtButton.setOnAction(event ->  instellingenTabController.setFileTxt());
        excelButton.setOnAction(event ->  instellingenTabController.setFileExcel());

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

        //KassaBon
        kassaBonLabel = new Label("Extra lijnen voor kassabon:");
        checkBox1 = new CheckBox("Toon algemene boodschap");
        checkBox1Field = new TextField();
        checkBox2 = new CheckBox("Toon datum en tijd");
        checkBox3 = new CheckBox("Toon korting");
        checkBox4 = new CheckBox("Toon BTW");
        checkBox5 = new CheckBox("Afsluitlijn");
        checkBox5Field = new TextField();
        kassaBonButton = new Button("Apply");

        this.add(kassaBonLabel,0,8);
        this.add(checkBox1,0,9);
        this.add(checkBox1Field,1,9);
        this.add(checkBox2,0,10);
        this.add(checkBox3,0,11);
        this.add(checkBox4,0,12);
        this.add(checkBox5,0,13);
        this.add(checkBox5Field,1,14);
        this.add(kassaBonButton,0,15);

        kassaBonButton.setOnAction(event -> instellingenTabController.setKassaBon());
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

    private void setAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Geef een geheel getal in");
        alert.showAndWait();
    }

    public String isCheckBox1Selected(){
        return "" + checkBox1.isSelected();
    }

    public String getCheckBox1Field(){
        return checkBox1Field.getText();
    }

    public String isCheckBox2Selected(){
        return "" + checkBox2.isSelected();
    }

    public String isCheckBox3Selected(){
        return "" + checkBox3.isSelected();
    }

    public String isCheckBox4Selected(){
        return "" + checkBox4.isSelected();
    }

    public String isCheckBox5Selected(){
        return "" + checkBox5.isSelected();
    }

    public String getCheckBox5Field(){
        return checkBox5Field.getText();
    }

}

