package DatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DatabaseConnection {
	private static DatabaseConnection instance;
    private Connection Connection = null;
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";
    private final String IP = "localhost";
    private final String PORT = "5432";
    private String url = "jdbc:postgresql://"+IP+":"+PORT+"/DB_Agenzia_Procuratori";

    private DatabaseConnection() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
			Connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
		  }		 
    }

    public Connection getConnection() {
        return Connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        else
            if (instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }
        return instance;
    }


}
