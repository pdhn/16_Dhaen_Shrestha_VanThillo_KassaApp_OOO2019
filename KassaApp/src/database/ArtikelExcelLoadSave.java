package database;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtikelExcelLoadSave extends ExcelLoadSaveTemplate {

    private HashMap<Integer, Artikel> artikelen;
    private static final String FILE_PATH = "src\\bestanden\\artikel.xls";
    private ExcelPlugin excelPlugin;

    public ArtikelExcelLoadSave() {
        artikelen = new HashMap();
        excelPlugin = new ExcelPlugin();
        this.load(FILE_PATH);
    }

    @Override
    public ArrayList<Artikel> load(String bestand) {

        try {
            ArrayList<ArrayList<String>> test = excelPlugin.read(new File(FILE_PATH));

            for (ArrayList<String> a : test) {

                Artikel artikel = new Artikel(Integer.parseInt(a.get(0)), a.get(1), a.get(2),
                        Double.parseDouble(a.get(3)), Integer.parseInt(a.get(4)));

                artikelen.put(artikel.getArtikelCode(), artikel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        return new ArrayList<Artikel>(artikelen.values());
    }

    @Override
    public void save(ArrayList<Artikel> bestanden) {

        ArrayList<ArrayList<String>> newBestandenToWriteArray = new ArrayList<>();

        for (Artikel a : bestanden) {
            ArrayList<String> tempBestandenAlsStringArray = new ArrayList<>();//Artikel als strings in array
            tempBestandenAlsStringArray.add(String.valueOf(a.getArtikelCode()));
            tempBestandenAlsStringArray.add(a.getOmschrijving());
            tempBestandenAlsStringArray.add(a.getArtikelGroep());
            tempBestandenAlsStringArray.add(Double.toString(a.getPrijs()));
            tempBestandenAlsStringArray.add(String.valueOf(a.getVoorraad()));

            newBestandenToWriteArray.add(tempBestandenAlsStringArray);// Arraylist van artikel als string in arraylist
        }

        try {
            excelPlugin.write(new File(FILE_PATH), newBestandenToWriteArray);

        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
