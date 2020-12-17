package DatabaseUtility;

import java.sql.Connection;
import java.sql.SQLException;

import ImplementationDAO.ImplementationClass;
import ImplementationDAO.ImplementationClassPostgres;
import ImplementationDAO.ImplementazioniDAO;

public class ControllerQuery {
	private final ImplementazioniDAO implementazioneScelta = ImplementazioniDAO.postgres;
	private static ControllerQuery instance;
	private ImplementationClass dao;
	private ControllerQuery() throws SQLException
	{
		if(implementazioneScelta == ImplementazioniDAO.postgres)
		{
			//TODO
			dao = new ImplementationClassPostgres(DatabaseConnection.getInstance().getConnection());
			
		}
	}
	public static ControllerQuery getInstance(Connection c) throws SQLException
	{
		if(instance==null)
		{
			instance = new ControllerQuery();
		}
		return instance;
	}
	public ImplementationClass getDAO()
	{
		return dao;
	}
	
}

