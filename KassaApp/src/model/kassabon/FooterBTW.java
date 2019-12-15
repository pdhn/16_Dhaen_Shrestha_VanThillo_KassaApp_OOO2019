package model.kassabon;

import model.Winkel;

public class FooterBTW extends KassaBonDecorator {
    private Winkel winkel;

    public FooterBTW(KassaBon kassaBon){
        super(kassaBon);
        winkel = Winkel.getInstance();
    }

    @Override
    public String printKassaBon() {
        return super.printKassaBon() + "\n" + "Totaal (zonder BTW) : " + winkel.getAfsluitBestelling().getTotaalZonderBTW() + " €"
                + "\n" + "BTW: " + winkel.getAfsluitBestelling().getBtw() + " €";
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
