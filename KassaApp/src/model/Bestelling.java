package model;

import model.korting.Korting;
import model.states.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sander Van Thillo & Roshan Shrestha
 */
public class Bestelling {
    private List<Artikel> artikels;
    private Korting korting;
    private State actief, onHold, sluitAf, betaald;
    private LocalDateTime tijdstip;

    private static final double BTW_PERCENTAGE = 0.06;

    private State state;

    public Bestelling(Korting korting){
        this.artikels = new ArrayList<>();
        setKorting(korting);

        this.actief = new Actief(this);
        this.onHold = new OnHold(this);
        this.sluitAf = new SluitAf(this);
        this.betaald = new Betaald(this);

        setState(this.actief);
    }

    public void setKorting(Korting korting) {
        this.korting = korting;
    }

    public void setState(State state){ this.state = state; }

    public void zetOnHold(){ this.state.zetOnHold(); }
    public void zetOffHold() { this.state.zetOffHold(); }
    public void sluitAf() { this.state.sluitAf(); }
    public void betaal() {
        state.betaal();
        this.tijdstip = LocalDateTime.now();
    }

    public State getState(){ return this.state; }
    public State getActief() { return this.actief; }
    public State getOnHold() { return this.onHold; }
    public State getSluitAf() { return this.sluitAf; }
    public State getBetaald() { return this.betaald; }

    public void voegArtikelToe(Artikel a){
        this.state.voegArtikelToe(a);
    }

    public void verwijderArtikel(Artikel a){
        this.state.verwijderArtikel(a);
    }

    public void setArtikels(List<Artikel> nieuweArtikels){
        this.artikels = nieuweArtikels;
    }

    /**
     * Geeft een lijst met dubbels terug
     *
     * @return lijst van artikelen
     */
    public List<Artikel> getArtikelsForKassa(){
        List<Artikel> kassaArtikels = new ArrayList<>();
        for(Artikel a : artikels){
            for(int i = 0; i < a.getAantal(); i++){
                kassaArtikels.add(a);
            }
        }
        return kassaArtikels;
    }

    /**
     * Zet een lijst van artikelen zonder dubbels door bij elke dubbel het aantal van dat artikel met
     * 1 te verhogen
     *
     * @param artikelsZonderAantallen
     */
    public void setArtikelsForKlant(List<Artikel> artikelsZonderAantallen) {
        for(Artikel artikel : artikelsZonderAantallen){
            if(this.artikels.contains(artikel)){
                int plaats = artikels.indexOf(artikel);
                this.artikels.get(plaats).verhoogAantal();
            }
            else{
                this.artikels.add(artikel);
            }
        }
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
        return this.korting.getKorting(this);
    }

    public double getTotaalMinKorting(){
        return getTotaal() - getKorting();
    }

    public String getTijdStip(){ return this.tijdstip + " "; }

    public double getBtw(){ return this.getTotaal()*BTW_PERCENTAGE; }

    public double getTotaalZonderBTW(){ return this.getTotaal()-this.getBtw(); }
}
