package model.korting;

import model.Bestelling;
import model.ModelException;

/**
 * @author 16_Dhaen_Shrestha_VanThillo_KassaApp_OOO2019
 */
public class Geenkorting implements Korting {
    private int percentage, bedrag;

    @Override
    public void setPercentage(int percentage){
        if(percentage < 0 || percentage > 100) throw new ModelException("Geen geldig percentage");
        this.percentage = percentage;
    }

    @Override
    public void setBedrag(int bedrag){
        if(bedrag < 0) throw new ModelException("Geen geldig bedrag");
        this.bedrag = bedrag;
    }

    @Override
    public double getKorting(Bestelling bestelling) {
        return 0;
    }
}
