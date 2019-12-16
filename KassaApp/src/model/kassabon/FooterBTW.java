package model.kassabon;

import model.Winkel;

public class FooterBTW extends KassaBonDecorator {

    public FooterBTW(KassaBon kassaBon){
        super(kassaBon);
    }

    @Override
    public String printKassaBon(Winkel winkel) {
        return super.printKassaBon(winkel) + "\n" + "Totaal (zonder BTW) : " + winkel.getAfsluitBestelling().getTotaalZonderBTW() + " €"
                + "\n" + "BTW: " + winkel.getAfsluitBestelling().getBtw() + " €";
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
