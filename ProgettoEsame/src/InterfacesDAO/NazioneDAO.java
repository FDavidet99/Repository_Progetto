package InterfacesDAO;

import java.sql.SQLException;
import java.util.List;

import Entity.Nazione;
import Entity.Provincia;

public interface NazioneDAO {
	
	public List<Nazione> GetNazioni() throws SQLException;
	public List<Provincia> GetProvinceByNazione(Nazione nazione) throws SQLException;
	public Nazione GetNazioneByCodiceAt(String codiceAt) throws SQLException; 
	
}
