package model.kassabon;

import model.Winkel;

/**
 * @author Sander Van Thillo
 */
public interface KassaBon {
    String printKassaBon(Winkel winkel);
    void setBoodschap(String boodschap);
}
