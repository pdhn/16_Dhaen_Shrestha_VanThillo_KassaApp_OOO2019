package model.korting;

/**
 * @author Pim Dhaen
 */
public enum KortingEnum {
    GEEN("Geen", "model.korting.Geenkorting"),
    GROEP("Groep", "model.korting.Groepkorting"),
    DREMPEL("Drempel", "model.korting.Drempelkorting"),
    DUURSTE("Duurste", "model.korting.Duurstekorting");

    private final String omschrijving;
    private final String klasseNaam;

    KortingEnum(String omschrijving, String klasseNaam) {
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    public String getOmschrijving() {
        return this.omschrijving;
    }

    public String getKlasseNaam() {
        return this.klasseNaam;
    }
}
