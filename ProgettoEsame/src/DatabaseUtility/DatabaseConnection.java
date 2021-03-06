package DatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instance;
    private Connection Connection = null;
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";
    private final String IP = "localhost";
    private final String PORT = "5432";
    private final String DBNAME = "DB_AgenziaProcuratori";
    private String url = "jdbc:postgresql://"+IP+":"+PORT+"/"+DBNAME;

    private DatabaseConnection() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
			Connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (ClassNotFoundException ex) {
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
