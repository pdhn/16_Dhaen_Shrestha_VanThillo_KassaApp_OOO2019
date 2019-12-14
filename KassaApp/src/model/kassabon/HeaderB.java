package model.kassabon;

import java.time.LocalDateTime;

public class HeaderB extends Decorator {
    private KassaBon kassaBon;

    public HeaderB(KassaBon kassaBon){
        this.kassaBon = kassaBon;
    }

    @Override
    public String printKassaBon() {
        return LocalDateTime.now() + "\n" + kassaBon.printKassaBon();
    }
}
