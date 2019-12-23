package model.kassabon;

import model.Winkel;

/**
 * @author Sander Van Thillo & Roshan Shrestha
 */
public class FooterKorting extends KassaBonDecorator {

    public FooterKorting(KassaBon kassaBon){
        super(kassaBon);
    }

    @Override
    public String printKassaBon(Winkel winkel) {
        return super.printKassaBon(winkel) + "\n" + "Prijs (zonder korting) " + winkel.getAfsluitBestelling().getTotaal() + "€\n"
                + "Korting: " + winkel.getAfsluitBestelling().getKorting() + " €";
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
