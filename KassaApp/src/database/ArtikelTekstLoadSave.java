package database;

import model.Artikel;

import java.util.*;

/**
 * @author Pim Dhaen & Roshan Shrestha
 */
public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {

    protected static final String TXT_FILE_PATH = "src\\bestanden\\artikel.txt";
    protected static final String DELIMITER = ",";

    public ArtikelTekstLoadSave() {

    }

    /**
     * Gebruikt de load methode van TekstLoadSaveTemplate om een bestand in te lezen in een lijst van strings
     * en zet deze lijst om naar een lijst van artikels m.b.v de StringsToArtikels klasse
     *
     * @return lijst van artikels
     */
    @Override
    public List<Artikel> load() {
        ArrayList<ArrayList<String>> tempEntriesAsString = this.load(TXT_FILE_PATH, DELIMITER);

        return StringsToArtikels.getArtikelsFromStrings(tempEntriesAsString);
    }

    /**
     * Zet een lijst van artikels om in een lijst van strings en gebruikt de save methode van
     * TekstLoadSaveTemplate om ze weg te schrijven.
     *
     * @param lijstObjecten
     */
    @Override
    public void save(List lijstObjecten) {
        // Convert to list of list of strings to write to file
        ArrayList<ArrayList<String>> tempReturnEntries = new ArrayList<>();

        for (Artikel a : (ArrayList<Artikel>) lijstObjecten) {
            ArrayList<String> tempArtikelAsStrings = new ArrayList<>();

            tempArtikelAsStrings.add(String.valueOf(a.getArtikelCode()));
            tempArtikelAsStrings.add(a.getOmschrijving());
            tempArtikelAsStrings.add(a.getArtikelGroep());
            tempArtikelAsStrings.add(String.valueOf(a.getPrijs()));
            tempArtikelAsStrings.add(String.valueOf(a.getVoorraad()));

            tempReturnEntries.add(tempArtikelAsStrings);
        }

        this.save(tempReturnEntries, TXT_FILE_PATH, DELIMITER);
    }
}
