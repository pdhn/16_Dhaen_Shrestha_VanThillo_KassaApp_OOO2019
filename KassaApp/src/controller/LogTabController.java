package controller;

import model.Winkel;
import view.panes.tabs.LogTab;

/**
 * @author Roshan Shrestha
 */
public class LogTabController {
    private LogTab logTab;
    private Winkel winkel;

    public LogTabController() { winkel =  Winkel.getInstance(); }

    public void setView(LogTab logTab) { this.logTab = logTab; }

    public void getStringFromBetaaldeBestellingen() {
        logTab.setLog(winkel.getTotaalStringFromBetaaldeBestellingen());
    }
}
