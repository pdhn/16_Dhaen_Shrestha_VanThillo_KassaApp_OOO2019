package database;

import java.util.ArrayList;
import java.util.List;

public interface LoadSaveTemplate<Object> {

    ArrayList<Object> load();

    ArrayList<Object> load(String naamBestandOfTable);

    void save(List<Object> lijstObjecten);

    void save(List<Object> lijstObjecten, String naamBestandOfTable);


}
