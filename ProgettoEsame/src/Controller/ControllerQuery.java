package Controller;

import java.sql.Connection;

import java.sql.SQLException;

import DatabaseUtility.DatabaseConnection;
import ImplementationDAO.ImplementationDAO;
import ImplementationDAO.ImplementationDAO_Postgres;
import ImplementationDAO.ImplementazioniDAO;

public class ControllerQuery {
	private final ImplementazioniDAO implementazioneScelta = ImplementazioniDAO.postgres;
	private static ControllerQuery instance;
	private ImplementationDAO ImplementationDAO;
	
	private ControllerQuery() throws SQLException {
		if(implementazioneScelta == ImplementazioniDAO.postgres) {
			
			ImplementationDAO = new ImplementationDAO_Postgres(DatabaseConnection.getInstance().getConnection());
			
		}
	}
	
	public static ControllerQuery getInstance() throws SQLException {
		if(instance==null) {
			instance = new ControllerQuery();
		}
		return instance;
	}
	
	public ImplementationDAO getDAO() {
		return ImplementationDAO;
	}
	
}

