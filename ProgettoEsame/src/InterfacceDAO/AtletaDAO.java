package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import EccezioniPersona.EccezioneCF;
import Entità.Atleta;

public interface AtletaDAO {
	public void InsertAtleta(Atleta atleta) throws SQLException, EccezioneCF;
	
	public List<Atleta> getAtleti()throws SQLException, EccezioneCF;
}
