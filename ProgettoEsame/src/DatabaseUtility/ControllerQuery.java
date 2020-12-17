package DatabaseUtility;

import java.sql.Connection;
import java.sql.SQLException;

import ImplementationDAO.ImplementationClass;
import ImplementationDAO.ImplementationClassPostgres;
import ImplementationDAO.ImplementazioniDAO;

public class ControllerQuery {
	private final ImplementazioniDAO implementazioneScelta = ImplementazioniDAO.postgres;
	private static ControllerQuery instance;
	private ImplementationClass ImplementazioneScelta;
	
	private ControllerQuery() throws SQLException {
		if(implementazioneScelta == ImplementazioniDAO.postgres) {
			
			ImplementazioneScelta = new ImplementationClassPostgres(DatabaseConnection.getInstance().getConnection());
			
		}
	}
	
	public static ControllerQuery getInstance() throws SQLException {
		if(instance==null) {
			instance = new ControllerQuery();
		}
		return instance;
	}
	
	public ImplementationClass getDAO() {
		return ImplementazioneScelta;
	}
	
}

