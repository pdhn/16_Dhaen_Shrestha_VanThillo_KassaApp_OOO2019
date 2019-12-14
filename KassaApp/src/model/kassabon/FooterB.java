package model.kassabon;

import model.Winkel;

public class FooterB extends Decorator {
    private KassaBon kassaBon;
    private Winkel winkel;

    public FooterB(KassaBon kassaBon){
        this.kassaBon = kassaBon;
        winkel = Winkel.getInstance();
    }

    @Override
    public String printKassaBon() {
        return null;
    }
}
