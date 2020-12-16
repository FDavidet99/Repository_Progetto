package Singleton;

import implementationDAO.ImplementazioniDAO;
import implementationDAO.PostgresImplemDAO;
import implementationDAO.ImplemDAO;

public class ControllerQuery {
	private final ImplementazioniDAO implementazioneScelta = ImplementazioniDAO.postgres;
	private static ControllerQuery instance;
	private ImplemDAO dao;
	private ControllerQuery()
	{
		if(implementazioneScelta == ImplementazioniDAO.postgres)
		{
			//TODO
			dao = new PostgresImplemDAO();
			
		}
	}
	public static ControllerQuery getInstance()
	{
		if(instance!=null)
		{
			instance = new ControllerQuery();
		}
		return instance;
	}

}
