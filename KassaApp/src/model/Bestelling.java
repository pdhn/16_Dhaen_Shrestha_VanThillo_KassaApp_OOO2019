package model;

import model.korting.Korting;
import model.states.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bestelling {
    private List<Artikel> artikels;
    private Korting korting;
    private State actief, onHold, sluitAf, betaald;
    private LocalDateTime tijdstip;

    private static final double BTW_PERCENTAGE = 0.06;

    private State state;

    public Bestelling(Korting korting){
        artikels = new ArrayList<>();
        setKorting(korting);

        actief = new Actief(this);
        onHold = new OnHold(this);
        sluitAf = new SluitAf(this);
        betaald = new Betaald(this);

        setState(actief);
    }

    public void setKorting(Korting korting) {
        this.korting = korting;
    }

    public void setState(State state){ this.state = state; }

    public void zetOnHold(){ state.zetOnHold(); }
    public void zetOffHold() { state.zetOffHold(); }
    public void sluitAf() { state.sluitAf(); }
    public void betaal() {
        state.betaal();
        this.tijdstip = LocalDateTime.now();
    }

    public State getState(){ return state; }
    public State getActief() { return actief; }
    public State getOnHold() { return onHold; }
    public State getSluitAf() { return sluitAf; }
    public State getBetaald() { return betaald; }


    public void voegArtikelToe(Artikel a){
        state.voegArtikelToe(a);
    }

    public void verwijderArtikel(Artikel a){
        state.verwijderArtikel(a);
    }

    public List<Artikel> getArtikelsForKassa(){
        List<Artikel> kassaArtikels = new ArrayList<>();
        for(Artikel a : artikels){
            for(int i = 0; i < a.getAantal(); i++){
                kassaArtikels.add(a);
            }
        }
        return kassaArtikels;
    }

    public List<Artikel> getArtikelsForKlant(){ return this.artikels; }

    public double getTotaal(){
        double totaal = 0;
        for(Artikel a : this.getArtikelsForKassa()){
            totaal += a.getPrijs();
        }
        return totaal;
    }

    public double getKorting(){
        return korting.getKorting(this);
    }

    public double getTotaalMinKorting(){
        return getTotaal() - getKorting();
    }

    public String getTijdStip(){ return this.tijdstip + " "; }

    public double getTotaalMetBTW(){ return 0; }

}
