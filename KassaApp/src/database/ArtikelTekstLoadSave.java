package database;

import model.Artikel;

import java.util.*;

import static database.ArrayListConverter.convertToArrayListArtikel;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate implements ArtikelLoadSaveTemplate {

    private HashMap<Integer, Artikel> artikelen;

    protected static final String TXT_FILE_PATH = "src\\bestanden\\artikel.txt";
    protected static final String DELIMITER = ",";

    public ArtikelTekstLoadSave() {
        artikelen = new HashMap();
        this.load();
    }

    @Override
    public ArrayList<Artikel> load() {
        return this.load(TXT_FILE_PATH);
    }

    @Override
    public ArrayList<Artikel> load(String naamBestandOfTable) {
        // Load into HashMap
        ArrayList<ArrayList<String>> tempEntriesAsString = this.load(naamBestandOfTable, DELIMITER);

        return convertToArrayListArtikel(tempEntriesAsString, artikelen);
    }

    @Override
    public void save(ArrayList lijstObjecten) {
        this.save(lijstObjecten, TXT_FILE_PATH);
    }


    @Override
    public void save(ArrayList lijstObjecten, String naamBestandOfTable) {
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

        this.save(tempReturnEntries, naamBestandOfTable, DELIMITER);
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
