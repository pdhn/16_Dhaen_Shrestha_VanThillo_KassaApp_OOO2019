package database.tests;

import org.junit.Test;

public class TestArtikelExcelLoadSave {

    private static final String FILE_PATH = "src\\bestanden\\artikel.xls";
    private static final String FILE_PATH_TXT = "src\\bestanden\\artikel.txt";

    @Test
    public void testLoad() {
        /*ExcelPlugin excelPlugin = new ExcelPlugin();

        try {
            System.out.println(excelPlugin.read(new File(EXCEL_FILE_PATH)));
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //ArtikelExcelLoadSave artikel = new ArtikelExcelLoadSave();


        /*for (Artikel a : artikel.load(FILE_PATH)) {
            System.out.println(a.toString());
        }*/
        //TekstLoadSave tekstLoadSave = new TekstLoadSave();

        //tekstLoadSave.save(tekstLoadSave.load(FILE_PATH_TXT, "]"), FILE_PATH_TXT, ",");
    }

    @Test
    public void testSave() {
        /*ExcelPlugin excelPlugin = new ExcelPlugin();

        try {
            System.out.println(excelPlugin.read(new File(EXCEL_FILE_PATH)));
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //ArtikelExcelLoadSave artikel = new ArtikelExcelLoadSave();

        //artikel.save(artikel.load(FILE_PATH));
    }
}
