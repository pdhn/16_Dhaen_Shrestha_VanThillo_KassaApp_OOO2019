package model.kassabon;

import java.time.LocalDateTime;

public class HeaderDatumTijd extends KassaBonDecorator {

    public HeaderDatumTijd(KassaBon kassaBon){
        super(kassaBon);
    }

    @Override
    public String printKassaBon() {
        return LocalDateTime.now() + "\n" + kassaBon.printKassaBon();
    }

    @Override
    public void setBoodschap(String boodschap) {

    }
}
