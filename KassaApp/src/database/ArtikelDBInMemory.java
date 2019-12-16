package database;

import model.Artikel;

import java.io.FileInputStream;
import java.util.*;

public class ArtikelDBInMemory implements ArtikelDBStrategy {
    private Map<Integer,Artikel> artikelen = new HashMap<>();
    private LoadSaveStrategy loadSaveStrategy;

    public ArtikelDBInMemory(){
        setLoadSaveStrategy();
    }

    private void setLoadSaveStrategy() {
        loadSaveStrategy = LoadSaveFactory.createLoadSave();
        setArtikelen();
    }

    private void setArtikelen() {
        List<Artikel> lijst = loadSaveStrategy.load();
        for(Artikel artikel: lijst){
            artikelen.put(artikel.getArtikelCode(),artikel);
        }
    }

    @Override
    public Artikel getArtikel(int code) {
        return artikelen.get(code);
    }

    @Override
    public List<Artikel> getArtikels() {
        return new ArrayList<>(artikelen.values());
    }

    @Override
    public void load() {
        setLoadSaveStrategy();
    }

    @Override
    public void save() {
        loadSaveStrategy.save(getArtikels());
    }
}
