package view.panes.tabs;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import model.ModelException;

import java.io.*;
import java.util.Properties;

public class InstellingenPane extends GridPane {

    private static final String FILE_PATH_PROPERTIES = "src\\bestanden\\config.properties";

    public InstellingenPane() {

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
                throw new ModelException("Error writing toconfig file");
            }
        });

        this.add(confirmBtn, 0, 2);
    }
}
