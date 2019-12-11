package model.korting;

import model.Winkel;

public class Drempelkorting implements Korting {
    private Winkel winkel;
    private int percentage, bedrag;

    public Drempelkorting(int percentage, int bedrag){
        winkel = Winkel.getInstance();
        this.percentage = percentage;
        this.bedrag = bedrag;
    }

    @Override
    public double getKorting() {
        if(winkel.getTotaalFromBestelling() >= bedrag){
            return winkel.getTotaalFromBestelling()*this.percentage/100;
        }
        else{
            return 0;
        }
    }
}
