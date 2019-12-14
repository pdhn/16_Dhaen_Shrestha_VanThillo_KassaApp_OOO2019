package model.kassabon;

import model.Artikel;
import model.Winkel;

public class Standaard implements KassaBon {
    private Winkel winkel;

    public Standaard(){
        winkel = Winkel.getInstance();
    }

    @Override
    public String printKassaBon() {
        String result = "Omscrhijving       Aantal  Prijs\n";
        result += "**********************************\n";
        for(Artikel a: winkel.getArtikelsFromBestellingForKlant()){
            result += a.getArtikelCode() + "        " + a.getAantal() + "   " + a.getPrijs() + "\n";
        }
        result += "**********************************\n";
        result += "Betaald (inclusief korting) : " + winkel.getAfsluitBestelling().getTotaalMinKorting() + " €";
        return result;
    }
}
