package model.kassabon;

public class HeaderA extends Decorator {
    private String boodschap;
    private KassaBon kassaBon;

    public HeaderA(KassaBon kassaBon){
        this.kassaBon = kassaBon;
    }

    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }

    @Override
    public String printKassaBon() {
        return this.boodschap + "\n" + kassaBon.printKassaBon() ;
    }
}
