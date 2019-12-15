package model.kassabon;

import model.ModelException;

import java.io.FileInputStream;
import java.util.Properties;

public class KassaBonFactory {
    public static KassaBon createKassaBon(){
        KassaBon kassaBon = new BodyKassaBon();
        try {
            Properties config = new Properties();
            FileInputStream in = new FileInputStream("src\\bestanden\\config.properties");
            config.load(in);

            if(config.getProperty("algemeneBoodschapCheck").equals("true")){
                kassaBon = new HeaderAlgemeneBoodschap(kassaBon);
                kassaBon.setBoodschap(config.getProperty("algemeneBoodschap"));
            }
            if(config.getProperty("datumtijd").equals("true")){
                kassaBon = new HeaderDatumTijd(kassaBon);
            }
            if(config.getProperty("kortingLijn").equals("true")){
                kassaBon = new FooterKorting(kassaBon);
            }
            if(config.getProperty("btw").equals("true")){
                kassaBon = new FooterBTW(kassaBon);
            }
            if(config.getProperty("afsluitlijnCheck").equals("true")){
                kassaBon = new FooterAfsluitLijn(kassaBon);
                kassaBon.setBoodschap(config.getProperty("algemeneBoodschap"));

            }
        } catch (Exception e){
            throw new ModelException("Kassabon kon niet aangemaakt worden");
        }

        return kassaBon;
    }
}
