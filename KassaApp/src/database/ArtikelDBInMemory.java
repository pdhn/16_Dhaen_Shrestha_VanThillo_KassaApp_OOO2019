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
        try{
            Properties config = new Properties();
            FileInputStream in = new FileInputStream("src\\bestanden\\config.properties");
            config.load(in);
            in.close();

            if(config.getProperty("file").equals("txt")){
                loadSaveStrategy = new ArtikelTekstLoadSave();
            }
            else loadSaveStrategy = new ArtikelExcelLoadSave();
        } catch (Exception e){
            throw new DBException("Fout tijdens het kiezen van de input file");
        }
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
