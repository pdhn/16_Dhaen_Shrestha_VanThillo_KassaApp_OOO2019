package model.korting;

import model.Artikel;
import model.ModelException;
import model.Winkel;

public class Duurstekorting implements Korting {
    private Winkel winkel;
    private int percentage, bedrag;

    public Duurstekorting(){
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
        double duurste = 0;
        for(Artikel a : winkel.getArtikelsFromBestellingForKassa()){
            if(a.getPrijs() > duurste){
                duurste = a.getPrijs();
            }
        }
        return duurste;
    }
}
