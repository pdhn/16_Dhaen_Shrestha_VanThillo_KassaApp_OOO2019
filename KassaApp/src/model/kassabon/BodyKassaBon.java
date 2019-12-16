package model.kassabon;

import model.Artikel;
import model.Winkel;

public class BodyKassaBon implements KassaBon {

    public BodyKassaBon(){ }

    @Override
    public String printKassaBon(Winkel winkel) {
        String result = "\nOmscrhijving\t\tAantal\tPrijs\n";
        result += "**********************************\n";
        for(Artikel a: winkel.getArtikelsFromBestellingForKlant()){
            result += a.getOmschrijving() + "\t\t\t\t" + a.getAantal() + "\t" + a.getPrijs() + "\n";
        }
        result += "**********************************\n";
        result += "Betaald (inclusief korting) : " + winkel.getAfsluitBestelling().getTotaalMinKorting() + " €";
        return result;
    }

    @Override
    public void setBoodschap(String boodschap) { }
}
