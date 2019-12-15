package model.kassabon;

public class HeaderAlgemeneBoodschap extends KassaBonDecorator {
    private String boodschap;
    private KassaBon kassaBon;

    public HeaderAlgemeneBoodschap(KassaBon kassaBon){
        this.kassaBon = kassaBon;
    }

    @Override
    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }

    @Override
    public String printKassaBon() {
        return this.boodschap + "\n" + kassaBon.printKassaBon() ;
    }
}
