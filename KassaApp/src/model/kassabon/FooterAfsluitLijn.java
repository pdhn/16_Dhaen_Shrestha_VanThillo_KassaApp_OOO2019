package model.kassabon;


import model.Winkel;

public class FooterAfsluitLijn extends KassaBonDecorator {
    private String boodschap;

    public FooterAfsluitLijn(KassaBon kassaBon){
        super(kassaBon);
    }

    @Override
    public String printKassaBon(Winkel winkel) {
        return super.printKassaBon(winkel) + "\n" + boodschap;
    }

    @Override
    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }
}
