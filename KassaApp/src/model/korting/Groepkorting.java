package model.korting;

import model.ModelException;
import model.Winkel;

public class Groepkorting implements Korting {
    private Winkel winkel;
    private int percentage, bedrag;

    public Groepkorting(){
        winkel = Winkel.getInstance();

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
        return winkel.getTotaalFromBestelling()*this.percentage/100;
    }
}
