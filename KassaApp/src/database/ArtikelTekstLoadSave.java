package database;

import model.Artikel;

import java.util.*;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {

    protected static final String TXT_FILE_PATH = "src\\bestanden\\artikel.txt";
    protected static final String DELIMITER = ",";

    public ArtikelTekstLoadSave() {

    }

    @Override
    public List<Artikel> load() {
        ArrayList<ArrayList<String>> tempEntriesAsString = this.load(TXT_FILE_PATH, DELIMITER);

        List<Artikel> artikelen = new ArrayList<>();
        for (ArrayList<String> as : tempEntriesAsString) {
            Artikel artikel = new Artikel(Integer.parseInt(as.get(0)), as.get(1), as.get(2),
                    Double.parseDouble(as.get(3)), Integer.parseInt(as.get(4)));

            artikelen.add(artikel);
        }
        return artikelen;
    }

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
