package interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K>{

    int save(T obj);
    int update(T obj);
    int delete(K id);

    T convertToModel(ResultSet rs) throws SQLException;

    T findById(K id);
    List<T> findAll();

    //String[] mapTableFields();
    //String[] getTableColumns();
}
