package model.kassabon;

import model.Winkel;

public class FooterKorting extends KassaBonDecorator {
    private Winkel winkel;

    public FooterKorting(KassaBon kassaBon){
        super(kassaBon);
        winkel = Winkel.getInstance();
    }

    @Override
    public String printKassaBon() {
        return super.printKassaBon() + "\n" + "Prijs (zonder korting) " + winkel.getAfsluitBestelling().getTotaal() + "€\n"
                + "Korting: " + winkel.getAfsluitBestelling().getKorting() + " €";
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
