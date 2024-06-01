package interfaces;

import java.sql.Connection;

public interface ConnectionDB {
    Connection getConnection() ;
    void close();
}
