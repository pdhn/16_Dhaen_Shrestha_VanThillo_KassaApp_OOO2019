package model.korting;

import model.Bestelling;
import model.ModelException;
import model.Winkel;

public class Drempelkorting implements Korting {
    private int percentage, bedrag;

    public Drempelkorting(){
    }

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
        if(bestelling.getTotaal() >= bedrag){
            return bestelling.getTotaal()*this.percentage/100;
        }
        else{
            return 0;
        }
    }
}
