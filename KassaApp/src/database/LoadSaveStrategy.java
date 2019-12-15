package database;

import java.util.List;

public interface LoadSaveStrategy<Object> {

    List<Object> load();
    void save(List<Object> lijstObjecten);

}
