package InterfacceDAO;

import java.sql.SQLException;

import EccezioniPersona.EccezioneCF;
import Entit�.ProcuratoreSportivo;

public interface ProcuratoreSportivoDAO {
	public void InsertProcuratoreSportivo(ProcuratoreSportivo procuratore) throws SQLException, EccezioneCF;

}