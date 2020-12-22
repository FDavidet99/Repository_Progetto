package InterfacceDAO;

import java.sql.SQLException;

import EccezioniPersona.EccezioneCF;
import Entità.ProcuratoreSportivo;

public interface ProcuratoreSportivoDAO {
	public void InsertProcuratoreSportivo(ProcuratoreSportivo procuratore) throws SQLException, EccezioneCF;

}
