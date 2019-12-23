package model;

/**
 * @author Pim Dhaen & Roshan Shrestha
 */
public class Artikel {

    private int artikelCode;
    private String omschrijving;
    private String artikelGroep;
    private double prijs;
    private int voorraad;
    private int aantal;


    public Artikel(int artikelCode, String omschrijving, String artikelGroep, double prijs, int voorraad) {
        this.artikelCode = artikelCode;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
        this.artikelGroep = artikelGroep;
        this.voorraad = voorraad;
        this.aantal = 1;
    }


    public int getArtikelCode() {
        return this.artikelCode;
    }

    public String getOmschrijving() {
        return this.omschrijving;
    }

    public String getArtikelGroep() {
        return this.artikelGroep;
    }

    public double getPrijs() {
        return this.prijs;
    }

    public void setVoorraad(int voorraad){
        this.voorraad = voorraad;
    }

    public int getVoorraad() {
        return this.voorraad;
    }

    public void verhoogAantal() { this.aantal++; }

    public void verlaagAantal() { this.aantal--; }

    public void setAantal(int aantal){
        if(aantal < 1 ) throw new ModelException("Aantal mag niet kleiner zijn dan 1");
        this.aantal = aantal;
    }

    public int getAantal(){ return this.aantal; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikel artikel = (Artikel) o;
        return artikelCode == artikel.artikelCode;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "artikelCode=" + artikelCode +
                ", omschrijving='" + omschrijving + '\'' +
                ", artikelGroep='" + artikelGroep + '\'' +
                ", prijs=" + prijs +
                ", voorraad=" + voorraad +
                '}';
    }
}
