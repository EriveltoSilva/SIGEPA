package db;

import interfaces.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionSingleton implements ConnectionDB {

    private static PostgresConnectionSingleton instance;
    private Connection connection;

    private PostgresConnectionSingleton()
    {
        final String DATABASE_HOST_NAME = "localhost";
        final String DATABASE_NAME = "db_sigepa";
        final int DATABASE_PORT = 5432;
        final String DATABASE_USERNAME = "postgres";
        final String DATABASE_PASSWORD = "postgres";
        final String CONNECTION_URL = "jdbc:postgresql://"+DATABASE_HOST_NAME+":"+DATABASE_PORT+"/"+DATABASE_NAME;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(CONNECTION_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("ERROR AO SE CONECTAR AO BANCO DE DADOS:"+e.getMessage());
        }
    }

    public static PostgresConnectionSingleton getInstance() {
        if(instance == null) {
            synchronized (PostgresConnectionSingleton.class)
            {
                instance = new PostgresConnectionSingleton();
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        if(this.connection == null)
            this.connection = PostgresConnectionSingleton.getInstance().getConnection();
        return this.connection;
    }

    public void close() {
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("ERROR: FECHANDO A CONEXÃO COM O BANCO!");
            }
        }
    }
}
