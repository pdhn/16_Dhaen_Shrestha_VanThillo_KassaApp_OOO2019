package model.kassabon;


public class FooterAfsluitLijn extends KassaBonDecorator {
    private String boodschap;

    public FooterAfsluitLijn(KassaBon kassaBon){
        super(kassaBon);
    }

    @Override
    public String printKassaBon() {
        return super.printKassaBon() + "\n" + boodschap;
    }

    @Override
    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }
}
