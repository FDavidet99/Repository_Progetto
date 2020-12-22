package Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

	private static ConnectionDB instance;
    private ConnectionDB connection = null;
    private final String USERNAME = "admin";
    private final String PASSWORD = "";
    private final String IP = "localhost";
    private final String PORT = "5432";
    private final String DBNAME = "unina";
    private final String url = "jdbc:postgresql://"+IP+":"+PORT+"/"+DBNAME;

    private ConnectionDB() throws SQLException {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = (ConnectionDB) DriverManager.getConnection(url, USERNAME, PASSWORD);

        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }

    }

    public ConnectionDB getConnection() {
        return connection;
    }

    public static ConnectionDB getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new ConnectionDB();
        }
        else
            if (((Connection) instance.getConnection()).isClosed())
            {
                instance = new ConnectionDB();
            }

        return instance;
    }


}
