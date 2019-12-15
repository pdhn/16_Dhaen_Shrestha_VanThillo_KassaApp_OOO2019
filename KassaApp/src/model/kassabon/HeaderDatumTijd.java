package model.kassabon;

import java.time.LocalDateTime;

public class HeaderDatumTijd extends KassaBonDecorator {
    private KassaBon kassaBon;

    public HeaderDatumTijd(KassaBon kassaBon){
        this.kassaBon = kassaBon;
    }

    @Override
    public String printKassaBon() {
        return LocalDateTime.now() + "\n" + kassaBon.printKassaBon();
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
