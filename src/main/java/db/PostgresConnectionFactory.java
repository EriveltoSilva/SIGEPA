package db;

import interfaces.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionFactory implements ConnectionDB {

    private final String DATABASE_HOST_NAME = "localhost";
    private final String DATABASE_NAME = "db_sigepa";
    private final int DATABASE_PORT = 5432;
    private final String DATABASE_USERNAME = "postgres";
    private final String DATABASE_PASSWORD = "postgres";
    private final String CONNECTION_URL = "jdbc:postgresql://"+DATABASE_HOST_NAME+":"+DATABASE_PORT+"/"+DATABASE_NAME;


    @Override
    public Connection getConnection() throws SQLException
    {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(CONNECTION_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.err.println("ERROR AO SE CONECTAR AO BANCO DE DADOS:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
