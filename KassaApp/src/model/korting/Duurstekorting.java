package model.korting;

import model.Artikel;
import model.Bestelling;
import model.ModelException;

/**
 * @author Sander Van Thillo
 */
public class Duurstekorting implements Korting {
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
        double duurste = 0;
        for(Artikel a : bestelling.getArtikelsForKassa()){
            if(a.getPrijs() > duurste){
                duurste = a.getPrijs();
            }
        }
        return duurste;
    }
}
