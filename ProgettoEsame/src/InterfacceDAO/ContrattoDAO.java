package InterfacceDAO;

import java.sql.SQLException;
import java.util.List;

import Eccezioni.EccezioneCF;
import Entit�.Contratto;

public interface ContrattoDAO {
	public void InsertContratto(Contratto contratto) throws SQLException, EccezioneCF;
	public List<Contratto> getContratti() throws SQLException, EccezioneCF;

}
