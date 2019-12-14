package model.kassabon;

import model.Winkel;

public class FooterA extends Decorator {
    private KassaBon kassaBon;
    private Winkel winkel;

    public FooterA(KassaBon kassaBon){
        this.kassaBon = kassaBon;
        winkel = Winkel.getInstance();
    }

    @Override
    public String printKassaBon() {
        return kassaBon.printKassaBon() + "\n" + "Prijs (zonder korting) " + winkel.getAfsluitBestelling().getTotaal() + "€\n"
                + "Korting: " + winkel.getAfsluitBestelling().getKorting() + " €";
    }
}
