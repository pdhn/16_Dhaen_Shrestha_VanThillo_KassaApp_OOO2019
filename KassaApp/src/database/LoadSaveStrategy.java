package database;

import java.util.List;

/**
 * @author Pim Dhaen & Roshan Shrestha
 */
public interface LoadSaveStrategy<Object> {

    List<Object> load();
    void save(List<Object> lijstObjecten);

}
