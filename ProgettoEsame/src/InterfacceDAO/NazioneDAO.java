package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;
import Entita.Nazione;
import Entita.Provincia;

public interface NazioneDAO {
	
	public String getCodiceAt(String NomeNazione) throws SQLException;
	
}
