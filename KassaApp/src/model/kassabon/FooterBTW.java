package model.kassabon;

import model.Winkel;

public class FooterBTW extends KassaBonDecorator {
    private KassaBon kassaBon;
    private Winkel winkel;

    public FooterBTW(KassaBon kassaBon){
        this.kassaBon = kassaBon;
        winkel = Winkel.getInstance();
    }

    @Override
    public String printKassaBon() {
        return kassaBon.printKassaBon() + "\n" + "Totaal (zonder BTW) : " + winkel.getAfsluitBestelling().getTotaalZonderBTW() + " €"
                + "\n" + "BTW: " + winkel.getAfsluitBestelling().getBtw() + " €";
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
