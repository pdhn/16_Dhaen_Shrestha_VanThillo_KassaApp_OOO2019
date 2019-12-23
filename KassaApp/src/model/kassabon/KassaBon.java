package model.kassabon;

import model.Winkel;

/**
 * @author Sander Van Thillo & Roshan Shrestha
 */
public interface KassaBon {
    String printKassaBon(Winkel winkel);
    void setBoodschap(String boodschap);
}
