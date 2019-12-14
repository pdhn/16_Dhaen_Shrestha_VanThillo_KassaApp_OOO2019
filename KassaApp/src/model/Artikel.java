package model;

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
        return artikelCode;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getArtikelGroep() {
        return artikelGroep;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setVoorraad(int voorraad){
        this.voorraad = voorraad;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void verhoogAantal() { aantal++; }

    public void verlaagAantal() { aantal--; }

    public int getAantal(){ return aantal; }

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
