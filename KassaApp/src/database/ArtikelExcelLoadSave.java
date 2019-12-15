package database;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ArtikelExcelLoadSave implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin;

    protected static final String EXCEL_FILE_PATH = "src\\bestanden\\artikel.xls";

    public ArtikelExcelLoadSave() {
        excelPlugin = new ExcelPlugin();
    }

    @Override
    public List<Artikel> load() {

        try {
            ArrayList<ArrayList<String>> lijstObjectenVanXlsBestand = excelPlugin.read(new File(EXCEL_FILE_PATH));

            List<Artikel> artikelen = new ArrayList<>();
            for (ArrayList<String> as : lijstObjectenVanXlsBestand) {
                Artikel artikel = new Artikel(Integer.parseInt(as.get(0)), as.get(1), as.get(2),
                        Double.parseDouble(as.get(3)), Integer.parseInt(as.get(4)));

                artikelen.add(artikel);
            }
            return artikelen;

        } catch (IOException e) {
            throw new DBException("IO Exception ---> \n" + e.getStackTrace());
        } catch (BiffException e) {
            throw new DBException("Biff Exception ---> \n" + e.getStackTrace());
        }
    }

    /**
     * @param lijstObjecten Objects in this ArrayList are converted to type Artikel.
     */
    @Override
    public void save(List lijstObjecten) {

        ArrayList<ArrayList<String>> newObjectenToWriteLijst = new ArrayList<>();

        for (Artikel a : (ArrayList<Artikel>) lijstObjecten) {
            ArrayList<String> tempObjectenAlsStringLijst = new ArrayList<>(); //Artikel als strings in een lijst

            tempObjectenAlsStringLijst.add(String.valueOf(a.getArtikelCode()));
            tempObjectenAlsStringLijst.add(a.getOmschrijving());
            tempObjectenAlsStringLijst.add(((Artikel) a).getArtikelGroep());
            tempObjectenAlsStringLijst.add(Double.toString(((Artikel) a).getPrijs()));
            tempObjectenAlsStringLijst.add(String.valueOf(((Artikel) a).getVoorraad()));

            newObjectenToWriteLijst.add(tempObjectenAlsStringLijst);// Lijst van (strings in een lijst)
        }

        try {
            excelPlugin.write(new File(EXCEL_FILE_PATH), newObjectenToWriteLijst);

        } catch (BiffException e) {
            throw new DBException("Biff Exception ---> \n" + e.getStackTrace());
        } catch (IOException e) {
            throw new DBException("IO Exception ---> \n" + e.getStackTrace());
        } catch (WriteException e) {
            throw new DBException("Error during writing to file ---> \n" + e.getStackTrace());
        }
    }
}
