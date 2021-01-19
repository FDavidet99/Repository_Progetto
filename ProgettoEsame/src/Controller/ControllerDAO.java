package Controller;

import java.sql.Connection;

import java.sql.SQLException;

import DatabaseUtility.DatabaseConnection;
import ImplementationDAO.ImplementationDAO;
import ImplementationDAO.ImplementationDAO_Postgres;
import ImplementationDAO.ImplementazioniDAO;

public class ControllerDAO {
	private final ImplementazioniDAO implementazioneScelta = ImplementazioniDAO.postgres;
	private static ControllerDAO instance;
	private ImplementationDAO ImplementationDAO;
	
	private ControllerDAO() throws SQLException {
		if(implementazioneScelta == ImplementazioniDAO.postgres) {	
			ImplementationDAO = new ImplementationDAO_Postgres(DatabaseConnection.getInstance().getConnection());	
		}
		
	}
	
	public static ControllerDAO getInstance() throws SQLException {
		if(instance==null) {
			instance = new ControllerDAO();
		}
		return instance;
	}
	
	public ImplementationDAO getDAO() {
		return ImplementationDAO;
	}
	
}

