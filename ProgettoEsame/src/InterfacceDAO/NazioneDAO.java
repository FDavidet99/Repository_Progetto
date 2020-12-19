package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Entità.Nazione;
import Entità.Provincia;

public interface NazioneDAO {
	
	//public String GetCodiceAt(String NomeNazione) throws SQLException;
	public List<Nazione> GetNazioni() throws SQLException;
	public List<Provincia> GetProvinceByNazione(Nazione nazione) throws SQLException; 
	
}
