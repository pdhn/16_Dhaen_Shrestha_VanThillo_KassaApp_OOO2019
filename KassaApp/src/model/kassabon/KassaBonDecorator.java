package model.kassabon;

import model.Winkel;

/**
 * @author Sander Van Thillo
 */
public abstract class KassaBonDecorator implements KassaBon {
    private KassaBon kassaBon;

    public KassaBonDecorator(KassaBon kassaBon){
        this.kassaBon = kassaBon;
    }

    @Override
    public String printKassaBon(Winkel winkel){
        return kassaBon.printKassaBon(winkel);
    }
}
