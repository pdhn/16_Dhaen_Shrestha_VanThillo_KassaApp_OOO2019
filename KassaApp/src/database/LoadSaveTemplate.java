package database;

import model.Artikel;

import java.util.ArrayList;

public interface LoadSaveTemplate {

    public ArrayList<Artikel> load(String bestand);

    public void save(ArrayList<Artikel> bestanden);
}
