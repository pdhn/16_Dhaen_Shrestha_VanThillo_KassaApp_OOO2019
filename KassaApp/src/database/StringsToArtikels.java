package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pim Dhaen
 */
public class StringsToArtikels {

    /**
     * Zet een lijst van strings om in een lijst van artikels
     *
     * @param lijstObjectenVanXlsBestand
     * @return lijst van artikels
     */
    public static List<Artikel> getArtikelsFromStrings(ArrayList<ArrayList<String>> lijstObjectenVanXlsBestand) {
        List<Artikel> artikelen = new ArrayList<>();
        for (ArrayList<String> as : lijstObjectenVanXlsBestand) {
            Artikel artikel = new Artikel(Integer.parseInt(as.get(0)), as.get(1), as.get(2),
                    Double.parseDouble(as.get(3)), Integer.parseInt(as.get(4)));

            artikelen.add(artikel);
        }
        return artikelen;
    }
}
