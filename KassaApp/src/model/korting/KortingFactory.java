package model.korting;

public class KortingFactory {
    public static Korting createKorting(String type){
        KortingEnum kortingEnum = KortingEnum.valueOf(type);
        String klasseNaam = kortingEnum.getKlasseNaam();
        Korting korting = null;
        try {
            Class kortingClass = Class.forName(klasseNaam);
            Object kortingObject = kortingClass.newInstance();
            korting = (Korting) kortingObject;
        } catch (Exception e){}
        return korting;

    }
}
