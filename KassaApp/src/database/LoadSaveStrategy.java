package database;

import java.util.List;

/**
 * @author 16_Dhaen_Shrestha_VanThillo_KassaApp_OOO2019
 */
public interface LoadSaveStrategy<Object> {

    List<Object> load();
    void save(List<Object> lijstObjecten);

}
