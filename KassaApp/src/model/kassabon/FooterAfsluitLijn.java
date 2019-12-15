package model.kassabon;


public class FooterAfsluitLijn extends KassaBonDecorator {
    private KassaBon kassaBon;
    private String boodschap;

    public FooterAfsluitLijn(KassaBon kassaBon){
        this.kassaBon = kassaBon;
    }

    @Override
    public String printKassaBon() {
        return kassaBon.printKassaBon() + "\n" + boodschap;
    }

    @Override
    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }
}
