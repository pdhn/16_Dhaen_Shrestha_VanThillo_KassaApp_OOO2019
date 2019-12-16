package controller;

import model.Artikel;
import model.Winkel;
import view.panes.tabs.ArtikelTab;

import java.util.List;

public class ArtikelTabController {
    private ArtikelTab artikelTab;
    private Winkel winkel;

    public ArtikelTabController() { winkel = Winkel.getInstance(); }

    public void setView(ArtikelTab artikelTab) { this.artikelTab = artikelTab; }

    public List<Artikel> getArtikels() {
        return winkel.getArtikelsFromDb();
    }

    public void refresh() {
        artikelTab.toonArtikels(this.getArtikels());
    }
}
