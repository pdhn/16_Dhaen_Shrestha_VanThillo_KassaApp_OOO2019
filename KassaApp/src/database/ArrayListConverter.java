package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayListConverter {

    /**
     * @param tempEntriesAsString ArrayList<ArrayList<String>> to be converted to ArrayList<Artikel>
     * @param artikelen HashMap instance of class to place converted
     * @return Converts ArrayList<ArrayList<String>> to ArrayList<Artikel> and places them in HashMap.
     */
    public static ArrayList<Artikel> convertToArrayListArtikel(ArrayList<ArrayList<String>> tempEntriesAsString, HashMap artikelen) {

        for (ArrayList<String> as : tempEntriesAsString) {
            Artikel artikel = new Artikel(Integer.parseInt(as.get(0)), as.get(1), as.get(2),
                    Double.parseDouble(as.get(3)), Integer.parseInt(as.get(4)));

            artikelen.put(artikel.getArtikelCode(), artikel);
        }

        return new ArrayList<>(artikelen.values());
    }

}
