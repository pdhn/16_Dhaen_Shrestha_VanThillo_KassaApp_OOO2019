package database;

import model.Artikel;

import java.util.ArrayList;

public abstract class TekstLoadSaveTemplate {

    public abstract ArrayList<Artikel> load(String bestand);

    public abstract void save(ArrayList<Artikel> bestanden);

}
