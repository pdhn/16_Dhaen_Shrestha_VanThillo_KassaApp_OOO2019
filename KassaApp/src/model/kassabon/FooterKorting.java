package model.kassabon;

import model.Winkel;

public class FooterKorting extends KassaBonDecorator {
    private KassaBon kassaBon;
    private Winkel winkel;

    public FooterKorting(KassaBon kassaBon){
        this.kassaBon = kassaBon;
        winkel = Winkel.getInstance();
    }

    @Override
    public String printKassaBon() {
        return kassaBon.printKassaBon() + "\n" + "Prijs (zonder korting) " + winkel.getAfsluitBestelling().getTotaal() + "€\n"
                + "Korting: " + winkel.getAfsluitBestelling().getKorting() + " €";
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
