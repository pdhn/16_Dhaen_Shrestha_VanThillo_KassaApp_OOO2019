package database;

import model.Artikel;

import java.util.List;

/**
 * @author Sander Van Thillo
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
