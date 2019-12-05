package database;

import model.Artikel;

import java.util.ArrayList;

public abstract class TekstLoadSaveTemplate implements LoadSaveTemplate {

    public abstract ArrayList<Artikel> load(String bestand);

    public abstract void save(ArrayList<Artikel> bestanden);

}
