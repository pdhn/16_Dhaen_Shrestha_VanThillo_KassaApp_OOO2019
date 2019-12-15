package model.kassabon;

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
    public String printKassaBon() {
        return this.boodschap + "\n" + kassaBon.printKassaBon() ;
    }
}
