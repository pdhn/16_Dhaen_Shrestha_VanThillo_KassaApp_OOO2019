package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.List;

public interface ArtikelLoadSaveTemplate extends LoadSaveTemplate {

    /**
     * @param artikelCode Must be larger than 1 and less than amount of articles saved.
     */
    Artikel getArtikel(int artikelCode);

    /**
     * @return All articles saved in HashMap.
     */
    List<Artikel> getArtikels();
}
