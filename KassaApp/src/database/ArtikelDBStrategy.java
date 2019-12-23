package database;

import model.Artikel;

import java.util.List;

/**
 * @author 16_Dhaen_Shrestha_VanThillo_KassaApp_OOO2019
 */
public interface ArtikelDBStrategy {
    Artikel getArtikel(int code);
    List<Artikel> getArtikels();
    void load();
    void save();
}
