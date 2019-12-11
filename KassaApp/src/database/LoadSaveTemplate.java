package database;

import java.util.ArrayList;

public interface LoadSaveTemplate<Object> {

    ArrayList<Object> load();

    ArrayList<Object> load(String naamBestandOfTable);

    void save(ArrayList<Object> lijstObjecten);

    void save(ArrayList<Object> lijstObjecten, String naamBestandOfTable);


}
