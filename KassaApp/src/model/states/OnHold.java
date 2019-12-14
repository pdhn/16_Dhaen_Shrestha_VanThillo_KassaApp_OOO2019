package model.states;

import model.Bestelling;

public class OnHold extends State {

    public OnHold(Bestelling bestelling){
        super(bestelling);
    }

    @Override
    public void zetOffHold(){ bestelling.setState(bestelling.getActief());}
}
