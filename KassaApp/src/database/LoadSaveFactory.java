package database;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadSaveFactory {
    public static LoadSaveStrategy createLoadSave(){
        LoadSaveStrategy loadSaveStrategy = null;
        try{
            Properties config = new Properties();
            FileInputStream in = new FileInputStream("src\\bestanden\\config.properties");
            config.load(in);

            Class loadSaveClass = Class.forName("database.Artikel" + config.getProperty("file") + "LoadSave");
            Object loadSaveObject = loadSaveClass.newInstance();
            loadSaveStrategy = (LoadSaveStrategy) loadSaveObject;

        }catch (Exception e){
            throw new DBException("loadSaveFile kon niet aangemaakt worden");
        }
        return loadSaveStrategy;
    }
}
