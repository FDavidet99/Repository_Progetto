package InterfacceDAO;

import java.sql.SQLException;

import Eccezioni.EccezioneCF;
import Entit�.Contratto;

public interface ContrattoDAO {
	public void InsertContratto(Contratto contratto) throws SQLException, EccezioneCF;

}
