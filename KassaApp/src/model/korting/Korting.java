package model.korting;

import model.Bestelling;

/**
 * @author Pim Dhaen
 */
public interface Korting {
    void setPercentage(int percentage);
    void setBedrag(int bedrag);
    double getKorting(Bestelling bestelling);
}
