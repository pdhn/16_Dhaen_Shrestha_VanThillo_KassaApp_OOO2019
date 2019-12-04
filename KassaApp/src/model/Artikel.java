package model;

public class Artikel {

    private int artikelCode;
    private String omschrijving;
    private String artikelGroep;
    private double prijs;
    private int voorraad;


    public Artikel(int artikelCode, String omschrijving, String artikelGroep, double prijs, int voorraad) {
        this.artikelCode = artikelCode;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
        this.artikelGroep = artikelGroep;
        this.voorraad = voorraad;
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

    public int getVoorraad() {
        return voorraad;
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