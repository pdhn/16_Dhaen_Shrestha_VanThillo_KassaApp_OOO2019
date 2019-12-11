package model.korting;

import model.Winkel;

public class Groepkorting implements Korting {
    private Winkel winkel;
    private int percentage;

    public Groepkorting(int percentage){
        winkel = Winkel.getInstance();
        this.percentage = percentage;
    }

    @Override
    public double getKorting() {
        return winkel.getTotaalFromBestelling()*this.percentage/100;
    }
}
