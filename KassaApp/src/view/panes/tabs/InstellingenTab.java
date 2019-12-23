package view.panes.tabs;
import controller.InstellingenTabController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author Pim Dhaen & Sander Van Thillo
 */
public class InstellingenTab extends GridPane {
    private InstellingenTabController instellingenTabController;
    private ComboBox korting;
    private TextField percentageField, bedragField, checkBox1Field, checkBox5Field;
    private Label bestandTitel, bestandLabel, kortingTitel, infoLabel, percentageLabel, bedragLabel, kassaBonTitel, kassaBonLabel;
    private Button txtButton, excelButton, kortingButton, kassaBonButton;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;

    public InstellingenTab(InstellingenTabController instellingenTabController) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.instellingenTabController = instellingenTabController;
        instellingenTabController.setView(this);

        setInputOutput();
        setKortingen();
        setKassaBon();
    }

    private void setInputOutput() {
        this.bestandTitel = new Label("BESTANDEN");
        this.bestandLabel = new Label("Input/output file:");
        this.txtButton = new Button("Txt");
        this.excelButton = new Button("Excel");
        this.infoLabel = new Label();

        this.add(this.bestandTitel,0,0);
        this.add(this.bestandLabel, 0, 1);
        this.add(this.txtButton, 0, 2);
        this.add(this.excelButton,1,2);
        this.add(this.infoLabel,0,3,2,1);

        this.txtButton.setOnAction(event ->  this.instellingenTabController.setFileTxt());
        this.excelButton.setOnAction(event ->  this.instellingenTabController.setFileExcel());
    }

    private void setKortingen() {
        this.korting = new ComboBox(FXCollections.observableArrayList(this.instellingenTabController.getKortingStrategyList()));
        this.korting.setValue("Geen");

        this.kortingTitel = new Label("KORTINGEN");
        this.percentageLabel = new Label("Percentage");
        this.bedragLabel = new Label("Bedrag");
        this.percentageField = new TextField("0");
        this.bedragField = new TextField("0");
        this.kortingButton = new Button("Apply");

        this.add(this.kortingTitel,0,4);
        this.add(this.korting, 0, 5);
        this.add(this.percentageLabel,0,6);
        this.add(this.bedragLabel,1,6);
        this.add(this.percentageField,0,7);
        this.add(this.bedragField,1,7);
        this.add(this.kortingButton,0,8);

        this.kortingButton.setOnAction(event -> this.instellingenTabController.setKorting());
    }

    private void setKassaBon() {
        this.kassaBonTitel = new Label("KASSABON");
        this.kassaBonLabel = new Label("Extra lijnen voor kassabon:");
        this.checkBox1 = new CheckBox("Toon algemene boodschap");
        this.checkBox1Field = new TextField();
        this.checkBox2 = new CheckBox("Toon datum en tijd");
        this.checkBox3 = new CheckBox("Toon korting");
        this.checkBox4 = new CheckBox("Toon BTW");
        this.checkBox5 = new CheckBox("Afsluitlijn");
        this.checkBox5Field = new TextField();
        this.kassaBonButton = new Button("Apply");

        this.add(this.kassaBonTitel,0,9);
        this.add(this.kassaBonLabel,0,10);
        this.add(this.checkBox1,0,11);
        this.add(this.checkBox1Field,1,11);
        this.add(this.checkBox2,0,12);
        this.add(this.checkBox3,0,13);
        this.add(this.checkBox4,0,14);
        this.add(this.checkBox5,0,15);
        this.add(this.checkBox5Field,1,15);
        this.add(this.kassaBonButton,0,16);

        this.kassaBonButton.setOnAction(event -> this.instellingenTabController.setKassaBon());
    }

    public void setInfoLabel(String string){
        this.infoLabel.setText(string);
    }


    public String getKorting(){
        return this.korting.getValue().toString();
    }

    public int getPercentageField() {
        int percentage = 0;
        try {
            percentage = Integer.parseInt(this.percentageField.getText());
        } catch (Exception e) {
            setAlert();
        }
        return percentage;
    }

    public int getBedragField(){
        int bedrag = 0;
        try {
            bedrag = Integer.parseInt(this.bedragField.getText());
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
        return "" + this.checkBox1.isSelected();
    }

    public String getCheckBox1Field(){
        return this.checkBox1Field.getText();
    }

    public String isCheckBox2Selected(){
        return "" + this.checkBox2.isSelected();
    }

    public String isCheckBox3Selected(){
        return "" + this.checkBox3.isSelected();
    }

    public String isCheckBox4Selected(){
        return "" + this.checkBox4.isSelected();
    }

    public String isCheckBox5Selected(){
        return "" + this.checkBox5.isSelected();
    }

    public String getCheckBox5Field(){
        return this.checkBox5Field.getText();
    }
}

