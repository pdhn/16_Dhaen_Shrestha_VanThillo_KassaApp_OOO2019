package database;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static database.ArrayListConverter.convertToArrayListArtikel;

public class ArtikelExcelLoadSave extends ExcelLoadSaveTemplate implements ArtikelLoadSaveTemplate {

    private HashMap<Integer, Artikel> artikelen;
    private ExcelPlugin excelPlugin;

    protected static final String EXCEL_FILE_PATH = "src\\bestanden\\artikel.xls";

    public ArtikelExcelLoadSave() {
        artikelen = new HashMap();
        excelPlugin = new ExcelPlugin();
        this.load(EXCEL_FILE_PATH);
    }

    @Override
    public ArrayList load() {
        return this.load(EXCEL_FILE_PATH);
    }

    @Override
    public ArrayList<Artikel> load(String naamXlsBestand) {

        try {
            ArrayList<ArrayList<String>> lijstObjectenVanXlsBestand = excelPlugin.read(new File(naamXlsBestand));

            return convertToArrayListArtikel(lijstObjectenVanXlsBestand, artikelen);
        } catch (IOException e) {
            throw new DBException("IO Exception ---> \n" + e.getStackTrace());
        } catch (BiffException e) {
            throw new DBException("Biff Exception ---> \n" + e.getStackTrace());
        }
    }

    @Override
    public void save(ArrayList lijstObjecten) {
        this.save(lijstObjecten, EXCEL_FILE_PATH);
    }

    /**
     * @param lijstObjecten Objects in this ArrayList are converted to type Artikel.
     */
    @Override
    public void save(ArrayList lijstObjecten, String naamBestand) {

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
            excelPlugin.write(new File(naamBestand), newObjectenToWriteLijst);

        } catch (BiffException e) {
            throw new DBException("Biff Exception ---> \n" + e.getStackTrace());
        } catch (IOException e) {
            throw new DBException("IO Exception ---> \n" + e.getStackTrace());
        } catch (WriteException e) {
            throw new DBException("Error during writing to file ---> \n" + e.getStackTrace());
        }
    }

    /**
     * @param artikelCode Must be larger than 1 and less than amount of articles saved.
     */
    public Artikel getArtikel(int artikelCode) {
        if (artikelCode < 1 || artikelCode > artikelen.size()) throw new DBException("Niet bestaande artikel code.");
        return artikelen.get(artikelCode);
    }

    /**
     * @return All articles saved in HashMap.
     */
    public List<Artikel> getArtikels() {
        return new ArrayList<>(artikelen.values());
    }
}
