package Singleton;

import implementationDAO.ImplementazioniDAO;

public class ControllerQuery {
	private final ImplementazioniDAO implementazioneScelta = ImplementazioniDAO.postgres;
	private static ControllerQuery instance;
	private ControllerQuery()
	{
		if(implementazioneScelta == ImplementazioniDAO.postgres)
		{
			//TODO
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
