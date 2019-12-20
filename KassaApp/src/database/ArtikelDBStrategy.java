package database;

import model.Artikel;

import java.util.List;

/**
 * @author Sander Van Thillo
 */
public interface ArtikelDBStrategy {
    Artikel getArtikel(int code);
    List<Artikel> getArtikels();
    void load();
    void save();
}
