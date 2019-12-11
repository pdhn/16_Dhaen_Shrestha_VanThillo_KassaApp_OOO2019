package model.korting;

import model.Artikel;
import model.Winkel;

public class Duurstekorting implements Korting {
    private Winkel winkel;
    private int percentage;

    public Duurstekorting(int percentage){
        winkel = Winkel.getInstance();
        this.percentage = percentage;
    }

    @Override
    public double getKorting() {
        double duurste = 0;
        for(Artikel a : winkel.getArtikelsFromBestellingForKassa()){
            if(a.getPrijs() > duurste){
                duurste = a.getPrijs();
            }
        }
        return duurste*this.percentage/100;
    }
}
