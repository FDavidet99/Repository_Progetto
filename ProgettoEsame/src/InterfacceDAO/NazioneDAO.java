package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entit�.Nazione;
import Entit�.Provincia;

public interface NazioneDAO {
	
	public String getCodiceAt(String NomeNazione) throws SQLException;
	
}
