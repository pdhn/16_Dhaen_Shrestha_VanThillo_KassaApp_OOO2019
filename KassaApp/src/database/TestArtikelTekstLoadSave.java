package database;

import org.junit.Test;

public class TestArtikelTekstLoadSave {


    @Test
    public void testLoad() {
        ArtikelTekstLoadSave artikel = new ArtikelTekstLoadSave();


        artikel.save(artikel.load("C:\\Users\\pimdh\\Documents\\Semester_1_2020\\1_OO Ontwerpen (6)\\Group Werk\\KassaSysteem\\src\\bestanden\\artikel.txt"));
    }

}
