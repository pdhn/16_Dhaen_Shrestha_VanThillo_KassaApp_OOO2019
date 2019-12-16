package model.kassabon;

import model.Winkel;

import java.time.LocalDateTime;

public class HeaderDatumTijd extends KassaBonDecorator {

    public HeaderDatumTijd(KassaBon kassaBon){
        super(kassaBon);
    }

    @Override
    public String printKassaBon(Winkel winkel) {
        return "\n" + LocalDateTime.now() + super.printKassaBon(winkel);
    }

    @Override
    public void setBoodschap(String boodschap) {}
}
