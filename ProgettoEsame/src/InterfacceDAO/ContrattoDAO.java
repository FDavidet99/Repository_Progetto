package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Eccezioni.EccezioneCF;
import Entit�.Atleta;
import Entit�.Contratto;

public interface ContrattoDAO {
	
	public void InsertContratto(Contratto contratto) throws SQLException, EccezioneCF;
	public List<Contratto> GetContratti() throws SQLException, EccezioneCF;
	public List<Contratto> GetContrattiAttivi() throws SQLException, EccezioneCF;

}
