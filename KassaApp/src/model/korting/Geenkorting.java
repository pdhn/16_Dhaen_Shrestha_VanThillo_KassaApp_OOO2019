package model.korting;

import model.ModelException;

public class Geenkorting implements Korting {
    private int percentage, bedrag;

    public Geenkorting(){
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
    public double getKorting() {
        return 0;
    }
}
