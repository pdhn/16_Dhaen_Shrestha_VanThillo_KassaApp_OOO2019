package database;

import model.Artikel;

import java.util.List;

/**
 * @author 16_Dhaen_Shrestha_VanThillo_KassaApp_OOO2019
 */
public class ArtikelDB {
    private ArtikelDBStrategy artikelDBStrategy;

    public ArtikelDB() {
        setArtikelDBStrategy(new ArtikelDBInMemory());
    }

    public void setArtikelDBStrategy(ArtikelDBStrategy artikelDBStrategy) {
        this.artikelDBStrategy = artikelDBStrategy;
    }

    public Artikel getArtikel(int code){
        return artikelDBStrategy.getArtikel(code);
    }

    public List<Artikel> getArtikels(){
        return artikelDBStrategy.getArtikels();
    }

    public void load(){
        artikelDBStrategy.load();
    }

    public void save(){
        artikelDBStrategy.save();
    }
}
