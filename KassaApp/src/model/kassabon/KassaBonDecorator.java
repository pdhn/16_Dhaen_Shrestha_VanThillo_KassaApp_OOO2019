package model.kassabon;

public abstract class KassaBonDecorator implements KassaBon {
    private KassaBon kassaBon;

    public KassaBonDecorator(KassaBon kassaBon){
        this.kassaBon = kassaBon;
    }

    @Override
    public String printKassaBon(){
        return this.kassaBon.printKassaBon();
    }
}
