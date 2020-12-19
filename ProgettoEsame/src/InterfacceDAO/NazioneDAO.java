package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.Nazione;
import Entità.Provincia;

public interface NazioneDAO {
	
	public String getCodiceAt(String NomeNazione) throws SQLException;
	
}
