package InterfacceDAO;

import java.sql.SQLException;

import Eccezioni.EccezioneCF;
import Entità.ProcuratoreSportivo;

public interface ProcuratoreSportivoDAO {
	public void InsertProcuratoreSportivo(ProcuratoreSportivo procuratore) throws SQLException, EccezioneCF;

}
