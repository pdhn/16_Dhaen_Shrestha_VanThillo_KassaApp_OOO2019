package database;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import model.Artikel;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestArtikelExcelLoadSave {

    private static final String FILE_PATH = "src\\bestanden\\artikel.xls";

    @Test
    public void testLoad() {
        /*ExcelPlugin excelPlugin = new ExcelPlugin();

        try {
            System.out.println(excelPlugin.read(new File(FILE_PATH)));
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ArtikelExcelLoadSave artikel = new ArtikelExcelLoadSave();


        for (Artikel a : artikel.load(FILE_PATH)) {
            System.out.println(a.toString());
        }
    }

    @Test
    public void testSave() {
        /*ExcelPlugin excelPlugin = new ExcelPlugin();

        try {
            System.out.println(excelPlugin.read(new File(FILE_PATH)));
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ArtikelExcelLoadSave artikel = new ArtikelExcelLoadSave();

        artikel.save(artikel.load(FILE_PATH));
    }
}
