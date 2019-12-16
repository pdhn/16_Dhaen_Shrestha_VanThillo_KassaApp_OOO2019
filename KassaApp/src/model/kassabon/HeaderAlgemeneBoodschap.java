package model.kassabon;

import model.Winkel;

public class HeaderAlgemeneBoodschap extends KassaBonDecorator {
    private String boodschap;

    public HeaderAlgemeneBoodschap(KassaBon kassaBon){
        super(kassaBon);
    }

    @Override
    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }

    @Override
    public String printKassaBon(Winkel winkel) {
        return "\n" + this.boodschap + super.printKassaBon(winkel) ;
    }
}
